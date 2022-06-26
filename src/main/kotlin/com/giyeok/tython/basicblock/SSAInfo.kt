package com.giyeok.tython.basicblock

import com.giyeok.tython.ssa.*

fun destOf(ssa: SSABasic): SSAVar? = when (ssa) {
  is AddToDict -> ssa.dest
  is AddToList -> ssa.dest
  is AddToSet -> ssa.dest
  is AssignVar -> ssa.dest
  is AwaitStmt -> ssa.dest
  is BinaryOp -> ssa.dest
  is BooleanOp -> ssa.dest
  is Branch, is BranchByIter -> null
  is CallFunc -> ssa.dest
  is CheckUnpackSize -> null
  is CompareOp -> ssa.dest
  is CreateClassDef -> ssa.dest
  is CreateDict -> ssa.dest
  is CreateFunctionDef -> ssa.dest
  is CreateGenerator -> ssa.dest
  is CreateIter -> ssa.dest
  is CreateLambda -> ssa.dest
  is CreateList -> ssa.dest
  is CreateSet -> ssa.dest
  is CreateSlice -> ssa.dest
  is CreateTuple -> ssa.dest
  is FormatString -> ssa.dest
  is JoinString -> ssa.dest
  is Jump -> null
  is LoadArgument -> ssa.dest
  is LoadAttribute -> ssa.dest
  is LoadConst -> ssa.dest
  is LoadConstBool -> ssa.dest
  is LoadLatestException -> ssa.dest
  is LoadName -> ssa.dest
  is LoadSubscript -> ssa.dest
  is NextIter -> ssa.dest
  is Phi -> ssa.dest
  is RaiseStmt -> null
  is ReturnStmt -> null
  is StoreAttribute -> null
  is StoreImport -> null
  is StoreName -> null
  is StoreLocalName -> null
  is StoreSubscript -> null
  is UnOp -> ssa.dest
  is Unpack -> ssa.dest
  is UnpackRest -> ssa.dest
  is YieldStmt -> ssa.dest
}

fun readsOf(ssa: SSABasic): Set<SSAVar> = when (ssa) {
  is AddToDict -> setOf(ssa.key, ssa.value)
  is AddToList -> setOf(ssa.elem)
  is AddToSet -> setOf(ssa.elem)
  is AssignVar -> setOf(ssa.source)
  is AwaitStmt -> setOf(ssa.value)
  is BinaryOp -> setOf(ssa.lhs, ssa.rhs)
  is BooleanOp -> setOf(ssa.lhs, ssa.rhs)
  is Branch -> setOf(ssa.test)
  is BranchByIter -> setOf(ssa.value)
  is CallFunc -> setOf(ssa.func) + ssa.posArgs + ssa.namedArgs.values
  is CheckUnpackSize -> setOf(ssa.value)
  is CompareOp -> setOf(ssa.lhs, ssa.rhs)
  is CreateClassDef -> ssa.decorators.toSet() + ssa.bases // TODO ssa.body?
  is CreateDict -> TODO()
  is CreateFunctionDef -> TODO()
  is CreateGenerator -> TODO()
  is CreateIter -> TODO()
  is CreateLambda -> TODO()
  is CreateList -> TODO()
  is CreateSet -> TODO()
  is CreateSlice -> TODO()
  is CreateTuple -> TODO()
  is FormatString -> TODO()
  is JoinString -> TODO()
  is Jump -> TODO()
  is LoadArgument -> TODO()
  is LoadAttribute -> TODO()
  is LoadConst -> TODO()
  is LoadConstBool -> TODO()
  is LoadLatestException -> TODO()
  is LoadName -> TODO()
  is LoadSubscript -> TODO()
  is NextIter -> TODO()
  is Phi -> TODO()
  is RaiseStmt -> TODO()
  is ReturnStmt -> TODO()
  is StoreAttribute -> TODO()
  is StoreImport -> TODO()
  is StoreName -> TODO()
  is StoreLocalName -> TODO()
  is StoreSubscript -> TODO()
  is UnOp -> TODO()
  is Unpack -> TODO()
  is UnpackRest -> TODO()
  is YieldStmt -> TODO()
}

fun allVarsOf(ssa: SSABasic): Set<SSAVar> = when (ssa) {
  is AddToDict -> setOf(ssa.dest, ssa.key, ssa.value)
  is AddToList -> setOf(ssa.dest, ssa.elem)
  is AddToSet -> setOf(ssa.dest, ssa.elem)
  is AssignVar -> setOf(ssa.dest, ssa.source)
  is AwaitStmt -> setOf(ssa.dest, ssa.value)
  is BinaryOp -> setOf(ssa.dest, ssa.lhs, ssa.rhs)
  is BooleanOp -> setOf(ssa.dest, ssa.lhs, ssa.rhs)
  is Branch -> setOf(ssa.test)
  is BranchByIter -> setOf(ssa.value)
  is CallFunc -> setOf(ssa.dest, ssa.func) + ssa.posArgs + ssa.namedArgs.values
  is CheckUnpackSize -> setOf(ssa.value)
  is CompareOp -> setOf(ssa.dest, ssa.lhs, ssa.rhs)
  is CreateClassDef -> setOf(ssa.dest) + ssa.decorators + ssa.bases
  is CreateDict -> setOf(ssa.dest) + (ssa.elems.map { it.second })
  is CreateFunctionDef -> setOf(ssa.dest) + ssa.decorators + setOfNotNull(ssa.returnType)
  is CreateGenerator -> setOf(ssa.dest)
  is CreateIter -> setOf(ssa.dest, ssa.value)
  is CreateLambda -> setOf(ssa.dest)
  is CreateList -> setOf(ssa.dest) + ssa.elems
  is CreateSet -> setOf(ssa.dest) + ssa.elems
  is CreateSlice -> setOfNotNull(ssa.dest, ssa.start, ssa.stop, ssa.step)
  is CreateTuple -> setOf(ssa.dest) + ssa.elems
  is FormatString -> setOfNotNull(ssa.dest, ssa.string, ssa.format)
  is JoinString -> setOf(ssa.dest) + ssa.values
  is Jump -> setOf()
  is LoadArgument -> setOf(ssa.dest)
  is LoadAttribute -> setOf(ssa.dest, ssa.obj)
  is LoadConst -> setOf(ssa.dest)
  is LoadConstBool -> setOf(ssa.dest)
  is LoadLatestException -> setOf(ssa.dest)
  is LoadName -> setOf(ssa.dest)
  is LoadSubscript -> setOf(ssa.dest, ssa.obj, ssa.slice)
  is NextIter -> setOf(ssa.dest, ssa.iter)
  is Phi -> setOf(ssa.dest) + ssa.sources
  is RaiseStmt -> setOfNotNull(ssa.exc, ssa.cause)
  is ReturnStmt -> setOfNotNull(ssa.value)
  is StoreAttribute -> setOf(ssa.obj, ssa.value)
  is StoreImport -> setOf()
  is StoreName -> setOf(ssa.source)
  is StoreLocalName -> setOf(ssa.source)
  is StoreSubscript -> setOf(ssa.obj, ssa.slice, ssa.value)
  is UnOp -> setOf(ssa.dest, ssa.operand)
  is Unpack -> setOf(ssa.dest, ssa.value)
  is UnpackRest -> setOf(ssa.dest, ssa.value)
  is YieldStmt -> setOfNotNull(ssa.dest, ssa.emit)
}

fun allVarsOf(controlFlowGraph: SSAControlFlowGraph): Set<SSAVar> =
  controlFlowGraph.nodes.values.flatMap { allVarsOf(it) }.toSet()

fun allVarsOf(basicBlock: SSABasicBlock): Set<SSAVar> =
  basicBlock.ssas.flatMap { allVarsOf(it) }.toSet()
