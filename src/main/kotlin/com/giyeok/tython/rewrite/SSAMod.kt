package com.giyeok.tython.rewrite

import com.giyeok.tython.ssa.*

fun SSAVar.repl(replacing: Map<SSAVar, SSAVar>): SSAVar {
  var result = this
  while (replacing.containsKey(result)) {
    result = replacing.getValue(result)
  }
  return result
}

fun SSABasic.replaceVars(replacing: Map<SSAVar, SSAVar>): SSABasic {
  fun SSAVar.repl() = this.repl(replacing)
  fun CallableArg.replArg() = CallableArg(name, annotation?.repl(), typeComment, default?.repl())
  fun CallableArgs.replArgs() = CallableArgs(
    posOnlyArgs.map { it.replArg() },
    args.map { it.replArg() },
    vararg?.replArg(),
    kwOnlyArgs.map { it.replArg() },
    kwarg?.replArg()
  )

  return when (val ssa = this) {
    is AddToDict -> AddToDict(ssa.dest.repl(), ssa.key.repl(), ssa.value.repl())
    is AddToList -> AddToList(ssa.dest.repl(), ssa.elem.repl())
    is AddToSet -> AddToSet(ssa.dest.repl(), ssa.elem.repl())
    is AssignVar -> AssignVar(ssa.dest.repl(), ssa.source.repl())
    is AwaitStmt -> TODO()
    is BinaryOp -> BinaryOp(ssa.dest.repl(), ssa.op, ssa.lhs.repl(), ssa.rhs.repl())
    is BooleanOp -> TODO()
    is Branch -> Branch(ssa.test.repl(), ssa.branchIfTrue, ssa.branchIfFalse)
    is BranchByIter -> TODO()
    is CallFunc -> CallFunc(
      ssa.dest.repl(),
      ssa.func.repl(),
      ssa.posArgs.map { it.repl() },
      ssa.namedArgs.mapValues { it.value.repl() })
    is CheckUnpackSize -> TODO()
    is CompareOp -> CompareOp(ssa.dest.repl(), ssa.op, ssa.lhs.repl(), ssa.rhs.repl())
    is CreateClassDef -> TODO()
    is CreateDict -> TODO()
    is CreateFunctionDef -> CreateFunctionDef(
      ssa.dest.repl(),
      ssa.definedName,
      ssa.decorators.map { it.repl() },
      ssa.args.replArgs(),
      ssa.returnType?.repl(),
      ssa.body,
      ssa.variablesScope
    )
    is CreateGenerator -> TODO()
    is CreateIter -> TODO()
    is CreateLambda -> TODO()
    is CreateList -> TODO()
    is CreateSet -> TODO()
    is CreateSlice -> TODO()
    is CreateTuple -> TODO()
    is FormatString -> TODO()
    is JoinString -> TODO()
    is Jump -> Jump(ssa.jumpTo)
    is LoadArgument -> TODO()
    is LoadAttribute -> TODO()
    is LoadConst -> LoadConst(ssa.dest.repl(), ssa.value)
    is LoadConstBool -> TODO()
    is LoadLatestException -> TODO()
    is LoadName -> LoadName(ssa.dest.repl(), ssa.source)
    is LoadSubscript -> TODO()
    is NextIter -> TODO()
    is Phi -> Phi(ssa.dest.repl(), ssa.sources.map { it.repl() }.distinct(), ssa.varName)
    is RaiseStmt -> TODO()
    is ReturnStmt -> TODO()
    is StoreAttribute -> TODO()
    is StoreImport -> TODO()
    is StoreName -> StoreName(ssa.dest, ssa.source.repl())
    is StoreLocalName -> StoreLocalName(ssa.dest, ssa.source.repl())
    is StoreSubscript -> TODO()
    is UnOp -> TODO()
    is Unpack -> TODO()
    is UnpackRest -> TODO()
    is YieldStmt -> TODO()
  }
}
