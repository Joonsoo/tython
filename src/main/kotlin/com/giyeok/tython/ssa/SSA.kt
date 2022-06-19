package com.giyeok.tython.ssa

import com.giyeok.tython.parse.*

data class SSAVar(val name: String) {
  override fun toString(): String = "%$name"
}

sealed class SSA

data class SSABlock(val stmts: List<SSA>)
data class SSABlockValue(val block: SSABlock, val value: SSAVar)

data class LoadConst(val dest: SSAVar, val value: Constant) : SSA()
data class LoadConstBool(val dest: SSAVar, val value: Boolean) : SSA()

// local variable은 nested function에서 nonlocal이나 global로 reference되지 않으면 삭제할 수 있다
// 하지만 SSA화 하는 중간에는 purely local variable인지 뭔지 알 수 없기 때문에 일단 variables context만 저장해 둔다.
// 함수 내부에서 eval이 사용되는 경우에도 그 함수의 모든 local variable을 purely local variable이라고 보장할 수 없게 된다.
data class CreateFunctionDef(
  val dest: SSAVar,
  val definedName: String,
  // decorators는 앞에서부터 순서대로 호출되어야 함. 정의된 것과 반대 순서
  val decorators: List<SSAVar>,
  val args: CallableArgs,
  val returnType: SSAVar?,
  val body: SSABlock,
  val variablesScope: VariablesScope,
) : SSA()

data class CreateClassDef(
  val dest: SSAVar,
  val definedName: String,
  val decorators: List<SSAVar>,
  val bases: List<SSAVar>,
  val body: SSABlock,
  val variablesScope: VariablesScope,
) : SSA()

data class CreateLambda(
  val dest: SSAVar,
  val args: CallableArgs,
  val body: SSABlockValue,
  val variablesScope: VariablesScope
) : SSA()

data class CallableArgs(
  // argument 리스트에서 '/' 앞에 있는 것들
  val posOnlyArgs: List<CallableArg>,
  // argument 리스트에서 '/'와 '*' 사이에 있는 것들
  val args: List<CallableArg>,
  val vararg: CallableArg?,
  val kwOnlyArgs: List<CallableArg>,
  val kwarg: CallableArg?,
)

data class CallableArg(
  val name: String,
  val annotation: SSAVar?,
  val typeComment: String?,
  val default: SSAVar?
)

// ListComp, SetComp, DictComp는 CreateGenerator의 내용으로 들어갈 것을 그냥 현재 블록에 넣는 정도의 차이
data class CompBlock(val body: SSABlock, val variablesScope: VariablesScope) : SSA()
data class CreateGenerator(
  val dest: SSAVar,
  val body: SSABlock,
  val variablesScope: VariablesScope
) : SSA()

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

data class AddToList(val dest: SSAVar, val elem: SSAVar) : SSA()
data class AddToSet(val dest: SSAVar, val elem: SSAVar) : SSA()
data class AddToDict(val dest: SSAVar, val key: SSAVar, val value: SSAVar) : SSA()

data class JoinString(val dest: SSAVar, val values: List<SSAVar>) : SSA()
data class FormatString(
  val dest: SSAVar,
  val string: SSAVar,
  val conversion: Int,
  val format: SSAVar?
) : SSA()

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
data class YieldStmt(val recv: SSAVar?, val emit: SSAVar?) : SSA()
data class AwaitStmt(val recv: SSAVar, val value: SSAVar) : SSA()

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
