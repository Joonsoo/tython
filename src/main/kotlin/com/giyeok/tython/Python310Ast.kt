package com.giyeok.tython

data class Location(val lineno: Int, val colOffset: Int, val endLineno: Int?, val endColOffset: Int?)

sealed class AbstractMod {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractMod): AbstractMod =
      when(proto.abstractModCase) {
        com.giyeok.tython.proto.AbstractMod.AbstractModCase.MODULE ->
          Module.fromProto(proto.module)
        com.giyeok.tython.proto.AbstractMod.AbstractModCase.INTERACTIVE ->
          Interactive.fromProto(proto.interactive)
        com.giyeok.tython.proto.AbstractMod.AbstractModCase.EXPRESSION ->
          Expression.fromProto(proto.expression)
        com.giyeok.tython.proto.AbstractMod.AbstractModCase.FUNCTION_TYPE ->
          FunctionType.fromProto(proto.functionType)
        else -> TODO()
      }
  }
}
data class Module(
  val body: List<AbstractStmt>,
  val type_ignores: List<AbstractTypeIgnore>,
): AbstractMod() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Module): Module =
    Module(      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.typeIgnoresList.map { AbstractTypeIgnore.fromProto(it) },
)
}}
data class Interactive(
  val body: List<AbstractStmt>,
): AbstractMod() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Interactive): Interactive =
    Interactive(      proto.bodyList.map { AbstractStmt.fromProto(it) },
)
}}
data class Expression(
  val body: AbstractExpr,
): AbstractMod() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Expression): Expression =
    Expression(      AbstractExpr.fromProto(proto.body),
)
}}
data class FunctionType(
  val argtypes: List<AbstractExpr>,
  val returns: AbstractExpr,
): AbstractMod() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.FunctionType): FunctionType =
    FunctionType(      proto.argtypesList.map { AbstractExpr.fromProto(it) },
      AbstractExpr.fromProto(proto.returns),
)
}}

sealed class AbstractStmt {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractStmt): AbstractStmt =
      when(proto.abstractStmtCase) {
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.FUNCTION_DEF ->
          FunctionDef.fromProto(proto.functionDef)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.ASYNC_FUNCTION_DEF ->
          AsyncFunctionDef.fromProto(proto.asyncFunctionDef)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.CLASS_DEF ->
          ClassDef.fromProto(proto.classDef)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.RETURN ->
          Return.fromProto(proto.`return`)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.DELETE ->
          Delete.fromProto(proto.delete)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.ASSIGN ->
          Assign.fromProto(proto.assign)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.AUG_ASSIGN ->
          AugAssign.fromProto(proto.augAssign)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.ANN_ASSIGN ->
          AnnAssign.fromProto(proto.annAssign)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.FOR ->
          For.fromProto(proto.`for`)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.ASYNC_FOR ->
          AsyncFor.fromProto(proto.asyncFor)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.WHILE ->
          While.fromProto(proto.`while`)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.IF ->
          If.fromProto(proto.`if`)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.WITH ->
          With.fromProto(proto.with)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.ASYNC_WITH ->
          AsyncWith.fromProto(proto.asyncWith)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.MATCH ->
          Match.fromProto(proto.match)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.RAISE ->
          Raise.fromProto(proto.raise)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.TRY ->
          Try.fromProto(proto.`try`)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.ASSERT ->
          Assert.fromProto(proto.assert)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.IMPORT ->
          Import.fromProto(proto.import)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.IMPORT_FROM ->
          ImportFrom.fromProto(proto.importFrom)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.GLOBAL ->
          Global.fromProto(proto.global)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.NONLOCAL ->
          Nonlocal.fromProto(proto.nonlocal)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.EXPR ->
          Expr.fromProto(proto.expr)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.PASS ->
          Pass.fromProto(proto.pass)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.BREAK ->
          Break.fromProto(proto.`break`)
        com.giyeok.tython.proto.AbstractStmt.AbstractStmtCase.CONTINUE ->
          Continue.fromProto(proto.`continue`)
        else -> TODO()
      }
  }
  abstract val location: Location}
data class FunctionDef(
  val name: String,
  val args: Arguments,
  val body: List<AbstractStmt>,
  val decorator_list: List<AbstractExpr>,
  val returns: AbstractExpr?,
  val type_comment: String?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.FunctionDef): FunctionDef =
    FunctionDef(      proto.name,
      Arguments.fromProto(proto.args),
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.decoratorListList.map { AbstractExpr.fromProto(it) },
      if (proto.hasReturns()) AbstractExpr.fromProto(proto.returns) else null,
      if (proto.hasTypeComment()) proto.typeComment else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class AsyncFunctionDef(
  val name: String,
  val args: Arguments,
  val body: List<AbstractStmt>,
  val decorator_list: List<AbstractExpr>,
  val returns: AbstractExpr?,
  val type_comment: String?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.AsyncFunctionDef): AsyncFunctionDef =
    AsyncFunctionDef(      proto.name,
      Arguments.fromProto(proto.args),
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.decoratorListList.map { AbstractExpr.fromProto(it) },
      if (proto.hasReturns()) AbstractExpr.fromProto(proto.returns) else null,
      if (proto.hasTypeComment()) proto.typeComment else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class ClassDef(
  val name: String,
  val bases: List<AbstractExpr>,
  val keywords: List<Keyword>,
  val body: List<AbstractStmt>,
  val decorator_list: List<AbstractExpr>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.ClassDef): ClassDef =
    ClassDef(      proto.name,
      proto.basesList.map { AbstractExpr.fromProto(it) },
      proto.keywordsList.map { Keyword.fromProto(it) },
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.decoratorListList.map { AbstractExpr.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Return(
  val value: AbstractExpr?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Return): Return =
    Return(      if (proto.hasValue()) AbstractExpr.fromProto(proto.value) else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Delete(
  val targets: List<AbstractExpr>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Delete): Delete =
    Delete(      proto.targetsList.map { AbstractExpr.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Assign(
  val targets: List<AbstractExpr>,
  val value: AbstractExpr,
  val type_comment: String?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Assign): Assign =
    Assign(      proto.targetsList.map { AbstractExpr.fromProto(it) },
      AbstractExpr.fromProto(proto.value),
      if (proto.hasTypeComment()) proto.typeComment else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class AugAssign(
  val target: AbstractExpr,
  val op: AbstractOperator,
  val value: AbstractExpr,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.AugAssign): AugAssign =
    AugAssign(      AbstractExpr.fromProto(proto.target),
      AbstractOperator.fromProto(proto.op),
      AbstractExpr.fromProto(proto.value),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class AnnAssign(
  val target: AbstractExpr,
  val annotation: AbstractExpr,
  val value: AbstractExpr?,
  val simple: Int,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.AnnAssign): AnnAssign =
    AnnAssign(      AbstractExpr.fromProto(proto.target),
      AbstractExpr.fromProto(proto.annotation),
      if (proto.hasValue()) AbstractExpr.fromProto(proto.value) else null,
      proto.simple,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class For(
  val target: AbstractExpr,
  val iter: AbstractExpr,
  val body: List<AbstractStmt>,
  val orelse: List<AbstractStmt>,
  val type_comment: String?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.For): For =
    For(      AbstractExpr.fromProto(proto.target),
      AbstractExpr.fromProto(proto.iter),
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.orelseList.map { AbstractStmt.fromProto(it) },
      if (proto.hasTypeComment()) proto.typeComment else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class AsyncFor(
  val target: AbstractExpr,
  val iter: AbstractExpr,
  val body: List<AbstractStmt>,
  val orelse: List<AbstractStmt>,
  val type_comment: String?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.AsyncFor): AsyncFor =
    AsyncFor(      AbstractExpr.fromProto(proto.target),
      AbstractExpr.fromProto(proto.iter),
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.orelseList.map { AbstractStmt.fromProto(it) },
      if (proto.hasTypeComment()) proto.typeComment else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class While(
  val test: AbstractExpr,
  val body: List<AbstractStmt>,
  val orelse: List<AbstractStmt>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.While): While =
    While(      AbstractExpr.fromProto(proto.test),
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.orelseList.map { AbstractStmt.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class If(
  val test: AbstractExpr,
  val body: List<AbstractStmt>,
  val orelse: List<AbstractStmt>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.If): If =
    If(      AbstractExpr.fromProto(proto.test),
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.orelseList.map { AbstractStmt.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class With(
  val items: List<Withitem>,
  val body: List<AbstractStmt>,
  val type_comment: String?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.With): With =
    With(      proto.itemsList.map { Withitem.fromProto(it) },
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      if (proto.hasTypeComment()) proto.typeComment else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class AsyncWith(
  val items: List<Withitem>,
  val body: List<AbstractStmt>,
  val type_comment: String?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.AsyncWith): AsyncWith =
    AsyncWith(      proto.itemsList.map { Withitem.fromProto(it) },
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      if (proto.hasTypeComment()) proto.typeComment else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Match(
  val subject: AbstractExpr,
  val cases: List<MatchCase>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Match): Match =
    Match(      AbstractExpr.fromProto(proto.subject),
      proto.casesList.map { MatchCase.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Raise(
  val exc: AbstractExpr?,
  val cause: AbstractExpr?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Raise): Raise =
    Raise(      if (proto.hasExc()) AbstractExpr.fromProto(proto.exc) else null,
      if (proto.hasCause()) AbstractExpr.fromProto(proto.cause) else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Try(
  val body: List<AbstractStmt>,
  val handlers: List<AbstractExcepthandler>,
  val orelse: List<AbstractStmt>,
  val finalbody: List<AbstractStmt>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Try): Try =
    Try(      proto.bodyList.map { AbstractStmt.fromProto(it) },
      proto.handlersList.map { AbstractExcepthandler.fromProto(it) },
      proto.orelseList.map { AbstractStmt.fromProto(it) },
      proto.finalbodyList.map { AbstractStmt.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Assert(
  val test: AbstractExpr,
  val msg: AbstractExpr?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Assert): Assert =
    Assert(      AbstractExpr.fromProto(proto.test),
      if (proto.hasMsg()) AbstractExpr.fromProto(proto.msg) else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Import(
  val names: List<Alias>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Import): Import =
    Import(      proto.namesList.map { Alias.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class ImportFrom(
  val module: String?,
  val names: List<Alias>,
  val level: Int?,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.ImportFrom): ImportFrom =
    ImportFrom(      if (proto.hasModule()) proto.module else null,
      proto.namesList.map { Alias.fromProto(it) },
      if (proto.hasLevel()) proto.level else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Global(
  val names: List<String>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Global): Global =
    Global(      proto.namesList.map { it },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Nonlocal(
  val names: List<String>,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Nonlocal): Nonlocal =
    Nonlocal(      proto.namesList.map { it },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Expr(
  val value: AbstractExpr,
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Expr): Expr =
    Expr(      AbstractExpr.fromProto(proto.value),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Pass(
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Pass): Pass =
    Pass(      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Break(
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Break): Break =
    Break(      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Continue(
  override val location: Location,): AbstractStmt() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Continue): Continue =
    Continue(      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}

sealed class AbstractExpr {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractExpr): AbstractExpr =
      when(proto.abstractExprCase) {
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.BOOL_OP ->
          BoolOp.fromProto(proto.boolOp)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.NAMED_EXPR ->
          NamedExpr.fromProto(proto.namedExpr)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.BIN_OP ->
          BinOp.fromProto(proto.binOp)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.UNARY_OP ->
          UnaryOp.fromProto(proto.unaryOp)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.LAMBDA ->
          Lambda.fromProto(proto.lambda)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.IF_EXP ->
          IfExp.fromProto(proto.ifExp)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.DICT ->
          Dict.fromProto(proto.dict)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.SET_EXPR ->
          SetExpr.fromProto(proto.setExpr)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.LIST_COMP ->
          ListComp.fromProto(proto.listComp)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.SET_COMP ->
          SetComp.fromProto(proto.setComp)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.DICT_COMP ->
          DictComp.fromProto(proto.dictComp)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.GENERATOR_EXP ->
          GeneratorExp.fromProto(proto.generatorExp)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.AWAIT ->
          Await.fromProto(proto.await)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.YIELD ->
          Yield.fromProto(proto.yield)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.YIELD_FROM ->
          YieldFrom.fromProto(proto.yieldFrom)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.COMPARE ->
          Compare.fromProto(proto.compare)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.CALL ->
          Call.fromProto(proto.call)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.FORMATTED_VALUE ->
          FormattedValue.fromProto(proto.formattedValue)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.JOINED_STR ->
          JoinedStr.fromProto(proto.joinedStr)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.CONSTANT ->
          Constant.fromProto(proto.constant)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.ATTRIBUTE ->
          Attribute.fromProto(proto.attribute)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.SUBSCRIPT ->
          Subscript.fromProto(proto.subscript)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.STARRED ->
          Starred.fromProto(proto.starred)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.NAME ->
          Name.fromProto(proto.name)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.LIST_EXPR ->
          ListExpr.fromProto(proto.listExpr)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.TUPLE ->
          Tuple.fromProto(proto.tuple)
        com.giyeok.tython.proto.AbstractExpr.AbstractExprCase.SLICE ->
          Slice.fromProto(proto.slice)
        else -> TODO()
      }
  }
  abstract val location: Location}
data class BoolOp(
  val op: AbstractBoolop,
  val values: List<AbstractExpr>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.BoolOp): BoolOp =
    BoolOp(      AbstractBoolop.fromProto(proto.op),
      proto.valuesList.map { AbstractExpr.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class NamedExpr(
  val target: AbstractExpr,
  val value: AbstractExpr,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.NamedExpr): NamedExpr =
    NamedExpr(      AbstractExpr.fromProto(proto.target),
      AbstractExpr.fromProto(proto.value),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class BinOp(
  val left: AbstractExpr,
  val op: AbstractOperator,
  val right: AbstractExpr,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.BinOp): BinOp =
    BinOp(      AbstractExpr.fromProto(proto.left),
      AbstractOperator.fromProto(proto.op),
      AbstractExpr.fromProto(proto.right),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class UnaryOp(
  val op: AbstractUnaryop,
  val operand: AbstractExpr,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.UnaryOp): UnaryOp =
    UnaryOp(      AbstractUnaryop.fromProto(proto.op),
      AbstractExpr.fromProto(proto.operand),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Lambda(
  val args: Arguments,
  val body: AbstractExpr,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Lambda): Lambda =
    Lambda(      Arguments.fromProto(proto.args),
      AbstractExpr.fromProto(proto.body),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class IfExp(
  val test: AbstractExpr,
  val body: AbstractExpr,
  val orelse: AbstractExpr,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.IfExp): IfExp =
    IfExp(      AbstractExpr.fromProto(proto.test),
      AbstractExpr.fromProto(proto.body),
      AbstractExpr.fromProto(proto.orelse),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Dict(
  val keys: List<AbstractExpr>,
  val values: List<AbstractExpr>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Dict): Dict =
    Dict(      proto.keysList.map { AbstractExpr.fromProto(it) },
      proto.valuesList.map { AbstractExpr.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class SetExpr(
  val elts: List<AbstractExpr>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.SetExpr): SetExpr =
    SetExpr(      proto.eltsList.map { AbstractExpr.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class ListComp(
  val elt: AbstractExpr,
  val generators: List<Comprehension>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.ListComp): ListComp =
    ListComp(      AbstractExpr.fromProto(proto.elt),
      proto.generatorsList.map { Comprehension.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class SetComp(
  val elt: AbstractExpr,
  val generators: List<Comprehension>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.SetComp): SetComp =
    SetComp(      AbstractExpr.fromProto(proto.elt),
      proto.generatorsList.map { Comprehension.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class DictComp(
  val key: AbstractExpr,
  val value: AbstractExpr,
  val generators: List<Comprehension>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.DictComp): DictComp =
    DictComp(      AbstractExpr.fromProto(proto.key),
      AbstractExpr.fromProto(proto.value),
      proto.generatorsList.map { Comprehension.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class GeneratorExp(
  val elt: AbstractExpr,
  val generators: List<Comprehension>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.GeneratorExp): GeneratorExp =
    GeneratorExp(      AbstractExpr.fromProto(proto.elt),
      proto.generatorsList.map { Comprehension.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Await(
  val value: AbstractExpr,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Await): Await =
    Await(      AbstractExpr.fromProto(proto.value),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Yield(
  val value: AbstractExpr?,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Yield): Yield =
    Yield(      if (proto.hasValue()) AbstractExpr.fromProto(proto.value) else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class YieldFrom(
  val value: AbstractExpr,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.YieldFrom): YieldFrom =
    YieldFrom(      AbstractExpr.fromProto(proto.value),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Compare(
  val left: AbstractExpr,
  val ops: List<AbstractCmpop>,
  val comparators: List<AbstractExpr>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Compare): Compare =
    Compare(      AbstractExpr.fromProto(proto.left),
      proto.opsList.map { AbstractCmpop.fromProto(it) },
      proto.comparatorsList.map { AbstractExpr.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Call(
  val func: AbstractExpr,
  val args: List<AbstractExpr>,
  val keywords: List<Keyword>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Call): Call =
    Call(      AbstractExpr.fromProto(proto.func),
      proto.argsList.map { AbstractExpr.fromProto(it) },
      proto.keywordsList.map { Keyword.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class FormattedValue(
  val value: AbstractExpr,
  val conversion: Int,
  val format_spec: AbstractExpr?,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.FormattedValue): FormattedValue =
    FormattedValue(      AbstractExpr.fromProto(proto.value),
      proto.conversion,
      if (proto.hasFormatSpec()) AbstractExpr.fromProto(proto.formatSpec) else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class JoinedStr(
  val values: List<AbstractExpr>,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.JoinedStr): JoinedStr =
    JoinedStr(      proto.valuesList.map { AbstractExpr.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Constant(
  val value: String,
  val kind: String?,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Constant): Constant =
    Constant(      proto.value,
      if (proto.hasKind()) proto.kind else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Attribute(
  val value: AbstractExpr,
  val attr: String,
  val ctx: AbstractExprContext,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Attribute): Attribute =
    Attribute(      AbstractExpr.fromProto(proto.value),
      proto.attr,
      AbstractExprContext.fromProto(proto.ctx),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Subscript(
  val value: AbstractExpr,
  val slice: AbstractExpr,
  val ctx: AbstractExprContext,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Subscript): Subscript =
    Subscript(      AbstractExpr.fromProto(proto.value),
      AbstractExpr.fromProto(proto.slice),
      AbstractExprContext.fromProto(proto.ctx),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Starred(
  val value: AbstractExpr,
  val ctx: AbstractExprContext,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Starred): Starred =
    Starred(      AbstractExpr.fromProto(proto.value),
      AbstractExprContext.fromProto(proto.ctx),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Name(
  val id: String,
  val ctx: AbstractExprContext,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Name): Name =
    Name(      proto.id,
      AbstractExprContext.fromProto(proto.ctx),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class ListExpr(
  val elts: List<AbstractExpr>,
  val ctx: AbstractExprContext,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.ListExpr): ListExpr =
    ListExpr(      proto.eltsList.map { AbstractExpr.fromProto(it) },
      AbstractExprContext.fromProto(proto.ctx),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Tuple(
  val elts: List<AbstractExpr>,
  val ctx: AbstractExprContext,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Tuple): Tuple =
    Tuple(      proto.eltsList.map { AbstractExpr.fromProto(it) },
      AbstractExprContext.fromProto(proto.ctx),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}
data class Slice(
  val lower: AbstractExpr?,
  val upper: AbstractExpr?,
  val step: AbstractExpr?,
  override val location: Location,): AbstractExpr() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Slice): Slice =
    Slice(      if (proto.hasLower()) AbstractExpr.fromProto(proto.lower) else null,
      if (proto.hasUpper()) AbstractExpr.fromProto(proto.upper) else null,
      if (proto.hasStep()) AbstractExpr.fromProto(proto.step) else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}

sealed class AbstractExprContext {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractExprContext): AbstractExprContext =
      when(proto.abstractExprContextCase) {
        com.giyeok.tython.proto.AbstractExprContext.AbstractExprContextCase.LOAD ->
          Load.fromProto(proto.load)
        com.giyeok.tython.proto.AbstractExprContext.AbstractExprContextCase.STORE ->
          Store.fromProto(proto.store)
        com.giyeok.tython.proto.AbstractExprContext.AbstractExprContextCase.DEL ->
          Del.fromProto(proto.del)
        else -> TODO()
      }
  }
}
object Load: AbstractExprContext() {
  fun fromProto(proto: com.giyeok.tython.proto.Load): Load =
    Load
}
object Store: AbstractExprContext() {
  fun fromProto(proto: com.giyeok.tython.proto.Store): Store =
    Store
}
object Del: AbstractExprContext() {
  fun fromProto(proto: com.giyeok.tython.proto.Del): Del =
    Del
}

sealed class AbstractBoolop {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractBoolop): AbstractBoolop =
      when(proto.abstractBoolopCase) {
        com.giyeok.tython.proto.AbstractBoolop.AbstractBoolopCase.AND ->
          And.fromProto(proto.and)
        com.giyeok.tython.proto.AbstractBoolop.AbstractBoolopCase.OR ->
          Or.fromProto(proto.or)
        else -> TODO()
      }
  }
}
object And: AbstractBoolop() {
  fun fromProto(proto: com.giyeok.tython.proto.And): And =
    And
}
object Or: AbstractBoolop() {
  fun fromProto(proto: com.giyeok.tython.proto.Or): Or =
    Or
}

sealed class AbstractOperator {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractOperator): AbstractOperator =
      when(proto.abstractOperatorCase) {
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.ADD ->
          Add.fromProto(proto.add)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.SUB ->
          Sub.fromProto(proto.sub)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.MULT ->
          Mult.fromProto(proto.mult)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.MAT_MULT ->
          MatMult.fromProto(proto.matMult)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.DIV ->
          Div.fromProto(proto.div)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.MOD ->
          Mod.fromProto(proto.mod)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.POW ->
          Pow.fromProto(proto.pow)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.L_SHIFT ->
          LShift.fromProto(proto.lShift)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.R_SHIFT ->
          RShift.fromProto(proto.rShift)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.BIT_OR ->
          BitOr.fromProto(proto.bitOr)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.BIT_XOR ->
          BitXor.fromProto(proto.bitXor)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.BIT_AND ->
          BitAnd.fromProto(proto.bitAnd)
        com.giyeok.tython.proto.AbstractOperator.AbstractOperatorCase.FLOOR_DIV ->
          FloorDiv.fromProto(proto.floorDiv)
        else -> TODO()
      }
  }
}
object Add: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.Add): Add =
    Add
}
object Sub: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.Sub): Sub =
    Sub
}
object Mult: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.Mult): Mult =
    Mult
}
object MatMult: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.MatMult): MatMult =
    MatMult
}
object Div: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.Div): Div =
    Div
}
object Mod: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.Mod): Mod =
    Mod
}
object Pow: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.Pow): Pow =
    Pow
}
object LShift: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.LShift): LShift =
    LShift
}
object RShift: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.RShift): RShift =
    RShift
}
object BitOr: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.BitOr): BitOr =
    BitOr
}
object BitXor: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.BitXor): BitXor =
    BitXor
}
object BitAnd: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.BitAnd): BitAnd =
    BitAnd
}
object FloorDiv: AbstractOperator() {
  fun fromProto(proto: com.giyeok.tython.proto.FloorDiv): FloorDiv =
    FloorDiv
}

sealed class AbstractUnaryop {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractUnaryop): AbstractUnaryop =
      when(proto.abstractUnaryopCase) {
        com.giyeok.tython.proto.AbstractUnaryop.AbstractUnaryopCase.INVERT ->
          Invert.fromProto(proto.invert)
        com.giyeok.tython.proto.AbstractUnaryop.AbstractUnaryopCase.NOT ->
          Not.fromProto(proto.not)
        com.giyeok.tython.proto.AbstractUnaryop.AbstractUnaryopCase.U_ADD ->
          UAdd.fromProto(proto.uAdd)
        com.giyeok.tython.proto.AbstractUnaryop.AbstractUnaryopCase.U_SUB ->
          USub.fromProto(proto.uSub)
        else -> TODO()
      }
  }
}
object Invert: AbstractUnaryop() {
  fun fromProto(proto: com.giyeok.tython.proto.Invert): Invert =
    Invert
}
object Not: AbstractUnaryop() {
  fun fromProto(proto: com.giyeok.tython.proto.Not): Not =
    Not
}
object UAdd: AbstractUnaryop() {
  fun fromProto(proto: com.giyeok.tython.proto.UAdd): UAdd =
    UAdd
}
object USub: AbstractUnaryop() {
  fun fromProto(proto: com.giyeok.tython.proto.USub): USub =
    USub
}

sealed class AbstractCmpop {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractCmpop): AbstractCmpop =
      when(proto.abstractCmpopCase) {
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.EQ ->
          Eq.fromProto(proto.eq)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.NOT_EQ ->
          NotEq.fromProto(proto.notEq)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.LT ->
          Lt.fromProto(proto.lt)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.LT_E ->
          LtE.fromProto(proto.ltE)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.GT ->
          Gt.fromProto(proto.gt)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.GT_E ->
          GtE.fromProto(proto.gtE)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.IS ->
          Is.fromProto(proto.`is`)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.IS_NOT ->
          IsNot.fromProto(proto.isNot)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.IN ->
          In.fromProto(proto.`in`)
        com.giyeok.tython.proto.AbstractCmpop.AbstractCmpopCase.NOT_IN ->
          NotIn.fromProto(proto.notIn)
        else -> TODO()
      }
  }
}
object Eq: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.Eq): Eq =
    Eq
}
object NotEq: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.NotEq): NotEq =
    NotEq
}
object Lt: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.Lt): Lt =
    Lt
}
object LtE: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.LtE): LtE =
    LtE
}
object Gt: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.Gt): Gt =
    Gt
}
object GtE: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.GtE): GtE =
    GtE
}
object Is: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.Is): Is =
    Is
}
object IsNot: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.IsNot): IsNot =
    IsNot
}
object In: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.In): In =
    In
}
object NotIn: AbstractCmpop() {
  fun fromProto(proto: com.giyeok.tython.proto.NotIn): NotIn =
    NotIn
}

data class Comprehension(
  val target: AbstractExpr,
  val iter: AbstractExpr,
  val ifs: List<AbstractExpr>,
  val is_async: Int,
) {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Comprehension): Comprehension =
    Comprehension(      AbstractExpr.fromProto(proto.target),
      AbstractExpr.fromProto(proto.iter),
      proto.ifsList.map { AbstractExpr.fromProto(it) },
      proto.isAsync,
)
}}

sealed class AbstractExcepthandler {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractExcepthandler): AbstractExcepthandler =
      when(proto.abstractExcepthandlerCase) {
        com.giyeok.tython.proto.AbstractExcepthandler.AbstractExcepthandlerCase.EXCEPT_HANDLER ->
          ExceptHandler.fromProto(proto.exceptHandler)
        else -> TODO()
      }
  }
  abstract val location: Location}
data class ExceptHandler(
  val type: AbstractExpr?,
  val name: String?,
  val body: List<AbstractStmt>,
  override val location: Location,): AbstractExcepthandler() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.ExceptHandler): ExceptHandler =
    ExceptHandler(      if (proto.hasType()) AbstractExpr.fromProto(proto.type) else null,
      if (proto.hasName()) proto.name else null,
      proto.bodyList.map { AbstractStmt.fromProto(it) },
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}

data class Arguments(
  val posonlyargs: List<Arg>,
  val args: List<Arg>,
  val vararg: Arg?,
  val kwonlyargs: List<Arg>,
  val kw_defaults: List<AbstractExpr>,
  val kwarg: Arg?,
  val defaults: List<AbstractExpr>,
) {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Arguments): Arguments =
    Arguments(      proto.posonlyargsList.map { Arg.fromProto(it) },
      proto.argsList.map { Arg.fromProto(it) },
      if (proto.hasVararg()) Arg.fromProto(proto.vararg) else null,
      proto.kwonlyargsList.map { Arg.fromProto(it) },
      proto.kwDefaultsList.map { AbstractExpr.fromProto(it) },
      if (proto.hasKwarg()) Arg.fromProto(proto.kwarg) else null,
      proto.defaultsList.map { AbstractExpr.fromProto(it) },
)
}}

data class Arg(
  val arg: String,
  val annotation: AbstractExpr?,
  val type_comment: String?,
  val location: Location,) {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Arg): Arg =
    Arg(      proto.arg,
      if (proto.hasAnnotation()) AbstractExpr.fromProto(proto.annotation) else null,
      if (proto.hasTypeComment()) proto.typeComment else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}

data class Keyword(
  val arg: String?,
  val value: AbstractExpr,
  val location: Location,) {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Keyword): Keyword =
    Keyword(      if (proto.hasArg()) proto.arg else null,
      AbstractExpr.fromProto(proto.value),
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}

data class Alias(
  val name: String,
  val asname: String?,
  val location: Location,) {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Alias): Alias =
    Alias(      proto.name,
      if (proto.hasAsname()) proto.asname else null,
      Location(proto.lineno, proto.colOffset, if (proto.hasEndLineno()) proto.endLineno else null, if (proto.hasEndColOffset()) proto.endColOffset else null)
)
}}

data class Withitem(
  val context_expr: AbstractExpr,
  val optional_vars: AbstractExpr?,
) {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.Withitem): Withitem =
    Withitem(      AbstractExpr.fromProto(proto.contextExpr),
      if (proto.hasOptionalVars()) AbstractExpr.fromProto(proto.optionalVars) else null,
)
}}

data class MatchCase(
  val pattern: AbstractPattern,
  val guard: AbstractExpr?,
  val body: List<AbstractStmt>,
) {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchCase): MatchCase =
    MatchCase(      AbstractPattern.fromProto(proto.pattern),
      if (proto.hasGuard()) AbstractExpr.fromProto(proto.guard) else null,
      proto.bodyList.map { AbstractStmt.fromProto(it) },
)
}}

sealed class AbstractPattern {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractPattern): AbstractPattern =
      when(proto.abstractPatternCase) {
        com.giyeok.tython.proto.AbstractPattern.AbstractPatternCase.MATCH_VALUE ->
          MatchValue.fromProto(proto.matchValue)
        com.giyeok.tython.proto.AbstractPattern.AbstractPatternCase.MATCH_SINGLETON ->
          MatchSingleton.fromProto(proto.matchSingleton)
        com.giyeok.tython.proto.AbstractPattern.AbstractPatternCase.MATCH_SEQUENCE ->
          MatchSequence.fromProto(proto.matchSequence)
        com.giyeok.tython.proto.AbstractPattern.AbstractPatternCase.MATCH_MAPPING ->
          MatchMapping.fromProto(proto.matchMapping)
        com.giyeok.tython.proto.AbstractPattern.AbstractPatternCase.MATCH_CLASS ->
          MatchClass.fromProto(proto.matchClass)
        com.giyeok.tython.proto.AbstractPattern.AbstractPatternCase.MATCH_STAR ->
          MatchStar.fromProto(proto.matchStar)
        com.giyeok.tython.proto.AbstractPattern.AbstractPatternCase.MATCH_AS ->
          MatchAs.fromProto(proto.matchAs)
        com.giyeok.tython.proto.AbstractPattern.AbstractPatternCase.MATCH_OR ->
          MatchOr.fromProto(proto.matchOr)
        else -> TODO()
      }
  }
  abstract val location: Location}
data class MatchValue(
  val value: AbstractExpr,
  override val location: Location,): AbstractPattern() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchValue): MatchValue =
    MatchValue(      AbstractExpr.fromProto(proto.value),
      Location(proto.lineno, proto.colOffset, proto.endLineno, proto.endColOffset)
)
}}
data class MatchSingleton(
  val value: String,
  override val location: Location,): AbstractPattern() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchSingleton): MatchSingleton =
    MatchSingleton(      proto.value,
      Location(proto.lineno, proto.colOffset, proto.endLineno, proto.endColOffset)
)
}}
data class MatchSequence(
  val patterns: List<AbstractPattern>,
  override val location: Location,): AbstractPattern() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchSequence): MatchSequence =
    MatchSequence(      proto.patternsList.map { AbstractPattern.fromProto(it) },
      Location(proto.lineno, proto.colOffset, proto.endLineno, proto.endColOffset)
)
}}
data class MatchMapping(
  val keys: List<AbstractExpr>,
  val patterns: List<AbstractPattern>,
  val rest: String?,
  override val location: Location,): AbstractPattern() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchMapping): MatchMapping =
    MatchMapping(      proto.keysList.map { AbstractExpr.fromProto(it) },
      proto.patternsList.map { AbstractPattern.fromProto(it) },
      if (proto.hasRest()) proto.rest else null,
      Location(proto.lineno, proto.colOffset, proto.endLineno, proto.endColOffset)
)
}}
data class MatchClass(
  val cls: AbstractExpr,
  val patterns: List<AbstractPattern>,
  val kwd_attrs: List<String>,
  val kwd_patterns: List<AbstractPattern>,
  override val location: Location,): AbstractPattern() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchClass): MatchClass =
    MatchClass(      AbstractExpr.fromProto(proto.cls),
      proto.patternsList.map { AbstractPattern.fromProto(it) },
      proto.kwdAttrsList.map { it },
      proto.kwdPatternsList.map { AbstractPattern.fromProto(it) },
      Location(proto.lineno, proto.colOffset, proto.endLineno, proto.endColOffset)
)
}}
data class MatchStar(
  val name: String?,
  override val location: Location,): AbstractPattern() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchStar): MatchStar =
    MatchStar(      if (proto.hasName()) proto.name else null,
      Location(proto.lineno, proto.colOffset, proto.endLineno, proto.endColOffset)
)
}}
data class MatchAs(
  val pattern: AbstractPattern?,
  val name: String?,
  override val location: Location,): AbstractPattern() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchAs): MatchAs =
    MatchAs(      if (proto.hasPattern()) AbstractPattern.fromProto(proto.pattern) else null,
      if (proto.hasName()) proto.name else null,
      Location(proto.lineno, proto.colOffset, proto.endLineno, proto.endColOffset)
)
}}
data class MatchOr(
  val patterns: List<AbstractPattern>,
  override val location: Location,): AbstractPattern() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.MatchOr): MatchOr =
    MatchOr(      proto.patternsList.map { AbstractPattern.fromProto(it) },
      Location(proto.lineno, proto.colOffset, proto.endLineno, proto.endColOffset)
)
}}

sealed class AbstractTypeIgnore {
  companion object {
    fun fromProto(proto: com.giyeok.tython.proto.AbstractTypeIgnore): AbstractTypeIgnore =
      when(proto.abstractTypeIgnoreCase) {
        com.giyeok.tython.proto.AbstractTypeIgnore.AbstractTypeIgnoreCase.TYPE_IGNORE ->
          TypeIgnore.fromProto(proto.typeIgnore)
        else -> TODO()
      }
  }
}
data class TypeIgnore(
  val lineno: Int,
  val tag: String,
): AbstractTypeIgnore() {
companion object {  fun fromProto(proto: com.giyeok.tython.proto.TypeIgnore): TypeIgnore =
    TypeIgnore(      proto.lineno,
      proto.tag,
)
}}

