package com.giyeok.tython.absinterpret

import com.giyeok.tython.parse.ClassDef
import com.giyeok.tython.parse.Lambda
import com.giyeok.tython.proto.FunctionDef

sealed class AbsValue

sealed interface AbsObjectValue {
  val attrs: MutableMap<String, AbsValue>
}

object AbsNoneValue : AbsValue()
data class AbsIntValue(val values: MutableSet<Long>, val conditions: MutableSet<IntCondition>) :
  AbsValue()

data class AbsFloatValue(
  val values: MutableSet<Double>,
  val conditions: MutableSet<FloatCondition>
) : AbsValue()

data class AbsComplexValue(
  val values: MutableSet<Pair<Double, Double>>,
  val conditions: MutableSet<ComplexCondition>
) : AbsValue()

data class AbsStringValue(
  val values: MutableSet<String>,
  val conditions: MutableSet<StringCondition>
) : AbsValue()

data class AbsBoolValue(val value: MutableSet<Boolean>) : AbsValue()

data class AbsBridgeFuncValue(
  val func: (List<AbsValue>) -> AbsValue,
  val params: MutableMap<String, AbsValue>,
  val returns: AbsValue,
) : AbsValue()

data class AbsBoundBridgeFuncValue(
  val instanceValue: AbsValue,
  val func: AbsBridgeFuncValue,
) : AbsValue()

data class AbsFuncDefValue(
  val lookupTable: AbsLookupTable,
  val funcDef: FunctionDef,
  override val attrs: MutableMap<String, AbsValue>
) : AbsValue(), AbsObjectValue

data class AbsBoundFuncDefValue(
  val instanceValue: AbsValue,
  val funcDef: AbsFuncDefValue,
) : AbsValue(), AbsObjectValue {
  override val attrs: MutableMap<String, AbsValue>
    get() = funcDef.attrs
}

data class AbsLambdaValue(
  val lookupTable: AbsLookupTable,
  val lambda: Lambda,
  override val attrs: MutableMap<String, AbsValue>
) : AbsValue(), AbsObjectValue

data class AbsBoundLambdaValue(
  val instanceValue: AbsValue,
  val lambda: AbsLambdaValue,
) : AbsValue(), AbsObjectValue {
  override val attrs: MutableMap<String, AbsValue>
    get() = lambda.attrs
}

data class AbsClassDefValue(
  val lookupTable: AbsLookupTable,
  val classDef: ClassDef,
  val bases: List<AbsValue>,
  override val attrs: MutableMap<String, AbsValue>
) : AbsValue(), AbsObjectValue

data class AbsModuleValue(
  override val attrs: MutableMap<String, AbsValue>
) : AbsValue(), AbsObjectValue

data class AbsTupleValue(
  val values: MutableList<AbsValue>
) : AbsValue()

data class AbsListValue(
  val values: MutableList<AbsValue>
) : AbsValue()

data class AbsDictValue(
  val values: MutableMap<String, AbsValue>
) : AbsValue()

data class AbsClassInstanceValue(
  val clsDef: AbsClassDefValue,
  override val attrs: MutableMap<String, AbsValue>
) : AbsValue(), AbsObjectValue

data class AbsUnionValue(val values: MutableList<AbsValue>) : AbsValue()
data class AbsExceptionValue(val exception: AbsValue, val cause: AbsValue) : AbsValue()

sealed class IntCondition
sealed class FloatCondition
sealed class ComplexCondition
sealed class StringCondition

class AbsLookupTable
