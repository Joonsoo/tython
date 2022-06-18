package com.giyeok.tython

import com.giyeok.tython.interpret.*
import com.giyeok.tython.parse.ParseModules

fun main() {
  val srcs = mapOf(
    "builtins" to "builtins.py",
    "a" to "a.py",
    "b" to "b.py",
    "c" to "c.py",
  )
  val moduleAsts = ParseModules.INSTNACE.load(srcs)

  val lookupTable = LookupTable(
    RootLookupTable,
    mutableMapOf(
      "print" to (BridgeFuncValue { args ->
        println(args)
        ValueOrException.Succeeded(NoneValue)
      }),
      "getattr" to (BridgeFuncValue { args ->
        val value = args[0] as ClassInstanceValue
        val name = args[1] as StringValue
        val member = value.attrs[name.value]
        if (member != null) {
          ValueOrException.Succeeded(member)
        } else {
          ValueOrException.Failed(StringValue("Name not found"), null)
        }
      }),
      "setattr" to (BridgeFuncValue { args ->
        val target = args[0] as ClassInstanceValue
        val name = args[1] as StringValue
        val value = args[2]
        target.attrs[name.value] = value
        ValueOrException.Succeeded(NoneValue)
      }),
      "len" to (BridgeFuncValue { args ->
        when (val v = args[0]) {
          is TupleValue -> ValueOrException.Succeeded(IntValue(v.values.size.toLong()))
          is ListValue -> ValueOrException.Succeeded(IntValue(v.values.size.toLong()))
          else -> TODO()
        }
      }),
    )
  )

  val loadedModules = mutableMapOf<String, ModuleValue>()
  val interpreter0 = Interpreter(mapOf(), moduleAsts, loadedModules)
  interpreter0.executeSimpleBlock(lookupTable, moduleAsts["builtins"]!!.body)
  val interpreter1 = Interpreter(lookupTable.names.toMap(), moduleAsts, loadedModules)
  val result = interpreter1.executeSimpleBlock(lookupTable, moduleAsts["a"]!!.body)
  if (result is Interpreter.ExecuteResult.ExceptionRaised) {
    println("Exception: $result")
  }
}
