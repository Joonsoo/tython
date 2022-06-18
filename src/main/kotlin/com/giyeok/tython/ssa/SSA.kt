package com.giyeok.tython.ssa

import com.giyeok.tython.parse.*

data class SSAVar(val name: String) {
  override fun toString(): String = "%$name"
}

sealed class SSA

// newScope이 true면 여기서 정의되는 name들은 바깥 스콥에서 보이지 않음
data class SSABlock(val stmts: List<SSA>, val newScope: Boolean)
data class SSABlockValue(val block: SSABlock, val value: SSAVar)

data class LoadConst(val dest: SSAVar, val value: Constant) : SSA()
data class LoadConstBool(val dest: SSAVar, val value: Boolean) : SSA()

// lookup table은 어쩌지? -> SSA가 실행될 때 LoadName의 context만 바꿔주면 됨
// default value들은? -> 마찬가지로 LoadName의 context만 잘 해주면 됨
data class LoadFunctionDef(val dest: SSAVar, val functionDef: FunctionDef) : SSA()
data class LoadClassDef(val dest: SSAVar, val classDef: ClassDef) : SSA()
data class LoadLambda(val dest: SSAVar, val lambda: Lambda) : SSA()

data class LoadName(val dest: SSAVar, val source: String) : SSA()
data class StoreName(val dest: String, val source: SSAVar) : SSA()

data class LoadAttribute(val dest: SSAVar, val obj: SSAVar, val attrName: String) : SSA()
data class StoreAttribute(val obj: SSAVar, val attrName: String, val value: SSAVar) : SSA()

data class LoadSubscript(val dest: SSAVar, val obj: SSAVar, val slice: SSAVar) : SSA()
data class StoreSubscript(val obj: SSAVar, val slice: SSAVar, val value: SSAVar) : SSA()

data class StoreImport(val destName: String, val from: Pair<Int, String>?, val name: String) : SSA()

// try except 핸들러 구현시 사용..
data class LoadLatestException(val dest: SSAVar) : SSA()

// value를 unpack하기 전에 크기 확인
data class CheckUnpackSize(val value: SSAVar, val exactSize: Int?, val minSize: Int?) : SSA()

// index가 0보다 작으면 뒤에서부터. -1은 가장 뒤, -2는 뒤에서 두번째, ...
data class Unpack(val dest: SSAVar, val value: SSAVar, val index: Int) : SSA()

// value에서 앞의 ignoreFirst개, 뒤의 ignoreLast개만큼을 제외한 만큼을 dest에 넣음
data class UnpackRest(
  val dest: SSAVar,
  val value: SSAVar,
  val ignoreFirst: Int,
  val ignoreLast: Int
) : SSA()

data class BinaryOp(val dest: SSAVar, val op: AbstractOperator, val lhs: SSAVar, val rhs: SSAVar) :
  SSA()

data class UnOp(val dest: SSAVar, val op: AbstractUnaryop, val operand: SSAVar) : SSA()

data class BooleanOp(val dest: SSAVar, val op: AbstractBoolop, val lhs: SSAVar, val rhs: SSAVar) :
  SSA()

data class CompareOp(val dest: SSAVar, val op: AbstractCmpop, val lhs: SSAVar, val rhs: SSAVar) :
  SSA()

data class CreateList(val dest: SSAVar, val elems: List<SSAVar>) : SSA()
data class CreateSet(val dest: SSAVar, val elems: List<SSAVar>) : SSA()
data class CreateTuple(val dest: SSAVar, val elems: List<SSAVar>) : SSA()
data class CreateDict(val dest: SSAVar, val elems: List<Pair<SSAVar, SSAVar>>) : SSA()
data class CreateSlice(val dest: SSAVar, val start: SSAVar?, val stop: SSAVar?, val step: SSAVar?) :
  SSA()

data class JoinString(val dest: SSAVar, val values: List<SSAVar>) : SSA()
data class FormatString(
  val dest: SSAVar,
  val string: SSAVar,
  val conversion: Int,
  val format: SSAVar?
) : SSA()

// generator도 LoadFunctionDef, LoadClassDef, LoadLamba에서와 마찬가지로 실제 실행할 때 LoadName의 context만 잘 바꿔주면 됨
// ListComp, SetComp, DictComp는 CreateGenerator의 내용으로 들어갈 것을 그냥 현재 블록에 넣는 정도의 차이
data class CreateGenerator(val dest: SSAVar) : SSA()

data class ForLoop(
  // loop를 돌 대상 iterable
  val iter: SSAVar,
  // body에서 iter의 element의 이름
  val iterVar: SSAVar,
  val iterAssign: SSABlock,
  val body: SSABlock,
  val orelse: SSABlock?
) : SSA()

data class WhileLoop(val test: SSABlockValue, val body: SSABlock, val orelse: SSABlock?) : SSA()

// dest := iterator(value)
data class CreateIter(val dest: SSAVar, val value: SSAVar) : SSA()
data class NextIter(val dest: SSAVar, val iter: SSAVar) : SSA()
data class BranchByIter(
  val value: SSAVar,
  val branchIfHasNext: String,
  val branchIfEmpty: String
) : SSA()

// list comp, set comp, dict comp

data class IfThenElse(
  val dest: SSAVar,
  val test: SSAVar,
  val then: SSABlockValue,
  val orelse: SSABlockValue
) : SSA()

data class CallFunc(
  val dest: SSAVar,
  val func: SSAVar,
  val posArgs: List<SSAVar>,
  val namedArgs: Map<String, SSAVar>
) : SSA()

data class IfStmt(val test: SSAVar, val then: SSABlock, val orelse: SSABlock?) : SSA()
data class RaiseStmt(val exc: SSAVar?, val cause: SSAVar?) : SSA()
data class ReturnStmt(val value: SSAVar?) : SSA()

data class TryStmt(
  val body: SSABlock,
  val excepts: List<ExceptBlock>,
  val orelse: SSABlock?,
  val finally: SSABlock?
) : SSA()

// catching이 null이면 모든 예외를 잡음
data class ExceptBlock(val catching: SSABlockValue?, val exceptionAs: String?, val body: SSABlock)

data class AssignVar(val dest: SSAVar, val source: SSAVar) : SSA()

object BreakLoop : SSA()
object ContinueLoop : SSA()

data class Phi(val dest: SSAVar, val cands: List<SSAVar>) : SSA()
data class Branch(val test: SSAVar, val branchIfTrue: String, val branchIfFalse: String) : SSA()
data class Jump(val jumpTo: String) : SSA()
