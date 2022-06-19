package com.giyeok.tython.ssa

data class VariablesScope(
  // locals는 해당 함수 안에서 값이 한번이라도 셋팅된적 있는 변수 이름.
  val locals: MutableSet<String>,
  val nonlocalReads: MutableSet<String>,
  val nonlocals: MutableSet<String>,
  val globals: MutableSet<String>,
  // nested function/class 등에서 nonlocal, global로 정의된 이 스콥의 변수
  val nonlocalOrGlobalByNested: MutableSet<String>,
  // 이 스콥의 변수 중 nested function/class 등에서 implicit하게 읽어가고 있는 변수
  val readByNested: MutableSet<String>,
) {
  constructor() : this(
    mutableSetOf(),
    mutableSetOf(),
    mutableSetOf(),
    mutableSetOf(),
    mutableSetOf(),
    mutableSetOf()
  )

  fun isNotNonlocalOrGlobal(id: String): Boolean =
    !nonlocals.contains(id) && !globals.contains(id)

  fun pureLocals(): Set<String> = locals - nonlocalOrGlobalByNested - readByNested
}

fun traverseVarRefs(block: SSABlock, global: VariablesScope) {
  traverseVarRefs(block, global, listOf(global))
}

fun traverseVarRefs(block: SSABlock, global: VariablesScope, scopes: List<VariablesScope>) {
  fun addNewScope(newScope: VariablesScope) {
    val topScope = scopes.last()
    global.nonlocalOrGlobalByNested.addAll(newScope.globals)
    topScope.nonlocalOrGlobalByNested.addAll(newScope.nonlocals)
    newScope.nonlocalReads.forEach { variableName ->
      val scope =
        scopes.findLast { enclosingScope -> enclosingScope.locals.contains(variableName) }
      scope?.readByNested?.add(variableName)
    }
  }
  block.stmts.forEach { stmt ->
    when (stmt) {
      is CreateFunctionDef -> {
        addNewScope(stmt.variablesScope)
        traverseVarRefs(stmt.body, global, scopes + stmt.variablesScope)
      }
      is CreateClassDef -> {
        addNewScope(stmt.variablesScope)
        traverseVarRefs(stmt.body, global, scopes + stmt.variablesScope)
      }
      is CreateLambda -> {
        addNewScope(stmt.variablesScope)
        traverseVarRefs(stmt.body.block, global, scopes + stmt.variablesScope)
      }
      is CompBlock -> {
        addNewScope(stmt.variablesScope)
        traverseVarRefs(stmt.body, global, scopes + stmt.variablesScope)
      }
      else -> {
        // do nothing
      }
    }
  }
}
