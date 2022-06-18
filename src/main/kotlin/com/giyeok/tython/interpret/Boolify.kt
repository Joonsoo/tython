package com.giyeok.tython.interpret

fun boolify(value: Value): Boolean = when (value) {
  is BoolValue -> value.value
  is BoundFuncDefValue -> TODO()
  is BridgeFuncValue -> TODO()
  is ClassDefValue -> TODO()
  is ClassInstanceValue -> TODO()
  is ComplexValue -> TODO()
  is DictValue -> TODO()
  is FloatValue -> TODO()
  is FuncDefValue -> TODO()
  is IntValue -> value.value != 0L
  is LambdaValue -> TODO()
  is ListValue -> value.values.isNotEmpty()
  is ModuleValue -> TODO()
  NoneValue -> false
  is StringValue -> value.value.isNotEmpty()
  is TupleValue -> TODO()
  is BoundBridgeFuncValue -> TODO()
  is BoundLambdaValue -> TODO()
}
