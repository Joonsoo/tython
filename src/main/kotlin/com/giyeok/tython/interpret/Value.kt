package com.giyeok.tython.interpret

import com.giyeok.tython.parse.ClassDef
import com.giyeok.tython.parse.FunctionDef
import com.giyeok.tython.parse.Lambda

sealed class Value

sealed interface ObjectValue {
  val attrs: MutableMap<String, Value>

  fun findAttr(name: String): Value? = attrs[name]
}

object NoneValue : Value()
data class IntValue(val value: Long) : Value()
data class FloatValue(val value: Double) : Value()
data class ComplexValue(val real: Double, val imag: Double) : Value()
data class StringValue(val value: String) : Value()
data class BoolValue(val value: Boolean) : Value()
data class BridgeFuncValue(val function: (List<Value>) -> ValueOrException) : Value()
data class BoundBridgeFuncValue(
  val instanceValue: Value,
  val function: (Value, List<Value>) -> ValueOrException
) : Value()

data class FuncDefValue(
  val lookupTable: AbstractLookupTable,
  val value: FunctionDef,
  override val attrs: MutableMap<String, Value>
) : Value(), ObjectValue

data class BoundFuncDefValue(
  val instanceValue: ClassInstanceValue,
  val funcDef: FuncDefValue,
) : Value(), ObjectValue {
  override val attrs: MutableMap<String, Value>
    get() = funcDef.attrs
}

data class LambdaValue(
  val lookupTable: AbstractLookupTable,
  val value: Lambda,
  override val attrs: MutableMap<String, Value>
) : Value(), ObjectValue

data class BoundLambdaValue(
  val instanceValue: ClassInstanceValue,
  val lambda: LambdaValue,
) : Value(), ObjectValue {
  override val attrs: MutableMap<String, Value>
    get() = lambda.attrs
}

data class ClassDefValue(
  val lookupTable: AbstractLookupTable,
  val value: ClassDef,
  val bases: List<Value>,
  override val attrs: MutableMap<String, Value>
) : Value(), ObjectValue {
  fun findAttrTraverse(name: String, visited: MutableSet<ClassDef>): Value? {
    val fromThis = attrs[name]
    if (fromThis != null) {
      return fromThis
    }
    bases.forEach { base ->
      base as ClassDefValue
      if (!visited.contains(base.value)) {
        visited.add(base.value)
        val fromBase = base.findAttrTraverse(name, visited)
        if (fromBase != null) {
          return fromBase
        }
      }
    }
    return null
  }
}

data class ModuleValue(override val attrs: MutableMap<String, Value>) : Value(), ObjectValue

data class TupleValue(val values: List<Value>) : Value()
data class ListValue(val values: MutableList<Value>) : Value() {
  companion object {
    fun getMember(instance: Value, name: String): Value {
      when (name) {
        "append" ->
          return BoundBridgeFuncValue(instance) { list: Value, args: List<Value> ->
            list as ListValue
            list.values.add(args[0])
            ValueOrException.Succeeded(NoneValue)
          }
      }
      TODO()
    }
  }
}

data class DictValue(val values: MutableMap<String, Value>) : Value()

data class ClassInstanceValue(
  val clsDef: ClassDefValue,
  override val attrs: MutableMap<String, Value>
) : Value(), ObjectValue {
  override fun findAttr(name: String): Value? {
    return attrs[name] ?: clsDef.findAttrTraverse(name, mutableSetOf())
  }
}

sealed class ValueOrException {
  data class Succeeded(val value: Value) : ValueOrException()
  data class Failed(val exception: Value?, val cause: Value?) : ValueOrException()
}
