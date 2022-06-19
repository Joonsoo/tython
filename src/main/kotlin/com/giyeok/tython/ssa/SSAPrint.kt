package com.giyeok.tython.ssa

import com.giyeok.tython.parse.*

fun printSSA(ssa: SSA, indent: String) {
  when (ssa) {
    is BinaryOp ->
      println("$indent${ssa.dest} := ${ssa.lhs} ${ssa.op.str()} ${ssa.rhs}")
    is BooleanOp -> TODO()
    is CallFunc -> {
      val args = ssa.posArgs.map { it.toString() } + ssa.namedArgs.map { "${it.key}=${it.value}" }
      println("$indent${ssa.dest} := call ${ssa.func}(${args.joinToString()})")
    }
    is CompareOp ->
      println("$indent${ssa.dest} := ${ssa.lhs} ${ssa.op.str()} ${ssa.rhs}")
    is IfStmt -> {
      println("${indent}if ${ssa.test}:")
      printSSA(ssa.then, "$indent  ")
      if (ssa.orelse != null) {
        println("${indent}else:")
        printSSA(ssa.orelse, "$indent  ")
      }
    }
    is IfThenElse -> {
      println("$indent${ssa.dest} := if ${ssa.test} then")
      printSSA(ssa.then, "$indent  ")
      println("${indent}else")
      printSSA(ssa.orelse, "$indent  ")
    }
    is LoadAttribute -> {
      println("$indent${ssa.dest} := attr ${ssa.obj}.${ssa.attrName}")
    }
    is CreateClassDef -> {
      println("$indent${ssa.dest} := class ${ssa.definedName}")
      // TODO
    }
    is LoadConst -> {
      val kind = ssa.value.kind?.let { "(kind=$it)" } ?: ""
      println("$indent${ssa.dest} := const ${ssa.value.value}$kind")
    }
    is LoadConstBool ->
      println("$indent${ssa.dest} := const bool ${ssa.value}")
    is CreateFunctionDef -> {
      println("$indent${ssa.dest} := function ${ssa.definedName}")
      ssa.decorators.forEach { deco ->
        println("$indent  @$deco")
      }
      println("$indent  scope=${ssa.variablesScope}(pureLocals=${ssa.variablesScope.pureLocals()})")
      printArgs(ssa.args, "$indent  ")
      if (ssa.returnType != null) {
        println("$indent  -> ${ssa.returnType}")
      }
      println("$indent  ====")
      printSSA(ssa.body, "$indent  ")
    }
    is LoadName ->
      println("$indent${ssa.dest} := name ${ssa.source}")
    is LoadSubscript ->
      println("$indent${ssa.dest} := ${ssa.obj}[${ssa.slice}]")
    is Phi ->
      println("$indent${ssa.dest} := phi(${ssa.cands.joinToString()})")
    is RaiseStmt ->
      println("${indent}raise ${ssa.exc} ${ssa.cause}")
    is ReturnStmt ->
      println("${indent}return ${ssa.value}")
    is StoreAttribute ->
      println("${indent}attr ${ssa.obj}.${ssa.attrName} := ${ssa.value}")
    is StoreName ->
      println("${indent}name ${ssa.dest} := ${ssa.source}")
    is StoreSubscript ->
      println("$indent${ssa.obj}[${ssa.slice}] := ${ssa.value}")
    is TryStmt -> TODO()
    is UnOp ->
      println("$indent${ssa.dest} := ${ssa.op.str()} ${ssa.operand}")
    is Unpack ->
      println("$indent${ssa.dest} := ${ssa.value} unpack [${ssa.index}]")
    is UnpackRest ->
      println("$indent${ssa.dest} := unpack rest ${ssa.value} first ${ssa.ignoreFirst} last ${ssa.ignoreLast}")
    is CreateList ->
      println("$indent${ssa.dest} := list [${ssa.elems.joinToString()}]")
    is CreateSet ->
      println("$indent${ssa.dest} := set {${ssa.elems.joinToString()}}")
    is CreateTuple ->
      println("$indent${ssa.dest} := tuple (${ssa.elems.joinToString()})")
    is CreateDict ->
      println("$indent${ssa.dest} := dict {${ssa.elems.joinToString { (k, v) -> "$k: $v" }}}")
    is CheckUnpackSize ->
      println("${indent}check size(${ssa.value}) == ${ssa.exactSize} or >= ${ssa.minSize}")
    is CreateSlice ->
      println("$indent${ssa.dest} := slice(${ssa.start}:${ssa.stop}:${ssa.step})")
    is JoinString ->
      println("$indent${ssa.dest} := joinString(${ssa.values.joinToString()})")
    is FormatString ->
      println("$indent${ssa.dest} := formatString(${ssa.string}, ${ssa.conversion}, ${ssa.format})")
    is ForLoop -> {
      println("${indent}for ${ssa.iterVar} in ${ssa.iter}:")
      printSSA(ssa.iterAssign, "$indent  ")
      println("$indent====")
      printSSA(ssa.body, "$indent  ")
      if (ssa.orelse != null) {
        println("${indent}else:")
        printSSA(ssa.orelse, "$indent  ")
      }
    }
    is WhileLoop -> {
      println("${indent}while:")
      printSSA(ssa.test, "$indent  ")
      println("$indent====")
      printSSA(ssa.body, "$indent  ")
      if (ssa.orelse != null) {
        println("${indent}else:")
        printSSA(ssa.orelse, "$indent  ")
      }
    }
    is AssignVar ->
      println("$indent${ssa.dest} := ${ssa.source}")
    is Branch ->
      println("${indent}branch by ${ssa.test} true -> ${ssa.branchIfTrue} false -> ${ssa.branchIfFalse}")
    is Jump ->
      println("${indent}jump ${ssa.jumpTo}")
    is NextIter ->
      println("$indent${ssa.dest} := next(${ssa.iter})")
    is BranchByIter ->
      println("${indent}branch if ${ssa.value} hasNext -> ${ssa.branchIfHasNext} empty -> ${ssa.branchIfEmpty}")
    BreakLoop ->
      println("${indent}break")
    ContinueLoop ->
      println("${indent}continue")
    is CreateIter ->
      println("$indent${ssa.dest} = iter(${ssa.value})")
    is AddToDict ->
      println("$indent${ssa.dest}.dictAdd(${ssa.key}, ${ssa.value})")
    is AddToList ->
      println("$indent${ssa.dest}.listAdd(${ssa.elem})")
    is AddToSet ->
      println("$indent${ssa.dest}.setAdd(${ssa.elem})")
    is CreateGenerator -> {
      println("$indent${ssa.dest} := generator")
      printSSA(ssa.body, "$indent  ")
      println("$indent  scope=${ssa.variablesScope}(pureLocals=${ssa.variablesScope.pureLocals()})")
    }
    is CreateLambda -> {
      println("$indent${ssa.dest} := lambda")
      printArgs(ssa.args, "$indent  ")
      println("$indent====")
      printSSA(ssa.body, "$indent  ")
    }
    is LoadLatestException -> TODO()
    is StoreImport -> TODO()
    is CompBlock -> {
      println("${indent}comp block")
      printSSA(ssa.body, "$indent  ")
      println("$indent  scope=${ssa.variablesScope}(pureLocals=${ssa.variablesScope.pureLocals()})")
    }
    is YieldStmt -> {
      // python yield는 generator에서 .send라는 메소드를 이용해서 값을 받을 수 있음.. 이상한 기능..
      // ssa.recv는 .send로 보내진 값을 받는 위치
      println("${indent}yield ${ssa.emit}${ssa.recv?.let { " (recv $it)" } ?: ""}")
    }
    else -> TODO()
  }
}

fun printArgs(args: CallableArgs, indent: String) {
  if (args.posOnlyArgs.isEmpty() && args.args.isEmpty() && args.vararg == null && args.kwOnlyArgs.isEmpty() && args.kwarg == null) {
    println("$indent(No args)")
    return
  }
  if (args.posOnlyArgs.isNotEmpty()) {
    args.posOnlyArgs.forEach { arg ->
      printArg(arg, indent)
    }
    println("$indent/")
  }
  args.args.forEach { arg ->
    printArg(arg, indent)
  }
  args.vararg?.let { arg ->
    printArg(arg, indent, prefix = "*")
  }
  if (args.kwOnlyArgs.isNotEmpty()) {
    println("$indent*")
    args.kwOnlyArgs.forEach { arg ->
      printArg(arg, indent)
    }
  }
  args.kwarg?.let { arg ->
    printArg(arg, indent, prefix = "**")
  }
}

fun printArg(arg: CallableArg, indent: String, prefix: String = "") {
  println("$indent${arg.annotation?.let { "$it " } ?: ""}$prefix${arg.name}${arg.annotation?.let { ": $it" } ?: ""}${arg.default?.let { " = $it" } ?: ""}")
}

fun printSSA(ssa: SSABlock, indent: String) {
  ssa.stmts.forEach {
    printSSA(it, indent)
  }
}

fun printSSA(ssa: SSABlockValue, indent: String) {
  printSSA(ssa.block, indent)
  println("$indent${ssa.value}")
}

fun AbstractOperator.str() = when (this) {
  Add -> "+"
  BitAnd -> "&"
  BitOr -> "|"
  BitXor -> "^"
  Div -> "/"
  FloorDiv -> "//"
  LShift -> "<<"
  MatMult -> "@"
  Mod -> "MOD"
  Mult -> "*"
  Pow -> "^"
  RShift -> ">>"
  Sub -> "-"
}

fun AbstractCmpop.str() = when (this) {
  Eq -> "=="
  Gt -> ">"
  GtE -> ">="
  In -> "in"
  Is -> "is"
  IsNot -> "is not"
  Lt -> "<"
  LtE -> "<="
  NotEq -> "!="
  NotIn -> "not in"
}

fun AbstractUnaryop.str() = when (this) {
  Invert -> "~"
  Not -> "not"
  UAdd -> "+"
  USub -> "-"
}
