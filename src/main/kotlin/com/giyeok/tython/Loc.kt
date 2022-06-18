package com.giyeok.tython

import com.giyeok.tython.parse.AbstractExpr

sealed class Loc

data class ModuleLoc(val moduleName: String) : Loc()

data class ClassLoc(val parent: Loc, val className: String) : Loc()

data class FuncLoc(val parent: Loc, val funcName: String) : Loc()

data class ParamLoc(val parent: FuncLoc, val name: String) : Loc()

data class LocalVarLoc(val parent: Loc, val name: String) : Loc()

data class ClassFieldLoc(val parent: ClassLoc, val name: String) : Loc()

data class ClassStaticFieldLoc(val parent: ClassLoc, val name: String) : Loc()

data class FieldLoc(val parent: Loc, val name: String) : Loc()

data class SliceLoc(val obj: Loc, val slice: AbstractExpr) : Loc()

data class TupleUnpackLoc(val tuple: Loc, val index: Int) : Loc()

data class StmtLoc(val parent: Loc, val index: Int) : Loc()

data class ExprLoc(val stmt: StmtLoc, val index: Int) : Loc()

data class AssignDest(val stmt: StmtLoc, val index: Int) : Loc()

data class AssignValue(val stmt: StmtLoc, val index: Int) : Loc()
