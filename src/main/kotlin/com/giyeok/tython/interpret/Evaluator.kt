package com.giyeok.tython.interpret

import com.giyeok.tython.parse.*

class Evaluator(val interpreter: Interpreter) {
  sealed class ParamsOrException {
    data class Succeeded(val params: Map<String, Value>) : ParamsOrException()
    data class Failed(val failed: ValueOrException.Failed) : ParamsOrException()
  }

  fun evaluateParams(
    lookup: AbstractLookupTable,
    args: Arguments,
    exprs: List<AbstractExpr>,
    firstArgValue: Value? = null,
  ): ParamsOrException {
    val posArgs = if (firstArgValue == null) {
      args.args.zip(exprs).associate { (arg, argExpr) ->
        val argOrException = evaluate(lookup, argExpr)
        if (argOrException is ValueOrException.Failed) {
          return ParamsOrException.Failed(argOrException)
        }
        arg.arg to ((argOrException as ValueOrException.Succeeded).value)
      }
    } else {
      val firstArg = args.args.first()
      val restArgs = args.args.drop(1).zip(exprs).associate { (arg, argExpr) ->
        val argOrException = evaluate(lookup, argExpr)
        if (argOrException is ValueOrException.Failed) {
          return ParamsOrException.Failed(argOrException)
        }
        arg.arg to ((argOrException as ValueOrException.Succeeded).value)
      }
      mapOf(firstArg.arg to firstArgValue) + restArgs
    }
    val varargs = if (args.vararg != null) {
      val posArgsSize = if (firstArgValue == null) args.args.size else args.args.size - 1
      val restArgsExprs = exprs.drop(posArgsSize)
      val restArgsOrException = evaluateOrException(lookup, restArgsExprs)
      if (restArgsOrException is ValuesOrException.Failed) {
        return ParamsOrException.Failed(restArgsOrException.failed)
      }
      mapOf(args.vararg.arg to TupleValue((restArgsOrException as ValuesOrException.Succeeded).values))
    } else mapOf()
    // TODO named args, kw args
    return ParamsOrException.Succeeded(posArgs + varargs)
  }

  fun evaluate(lookup: AbstractLookupTable, expr: AbstractExpr): ValueOrException {
    when (expr) {
      is Attribute -> {
        val valueOrException = evaluate(lookup, expr.value)
        if (valueOrException is ValueOrException.Failed) {
          return valueOrException
        }
        valueOrException as ValueOrException.Succeeded
        return when (val value = valueOrException.value) {
          is ClassInstanceValue -> {
            return value.findAttr(expr.attr)?.let { attrValue ->
              val boundValue = when (attrValue) {
                is FuncDefValue -> BoundFuncDefValue(value, attrValue)
                is LambdaValue -> BoundLambdaValue(value, attrValue)
                else -> attrValue
              }
              ValueOrException.Succeeded(boundValue)
            } ?: ValueOrException.Failed(StringValue("invalid attribute: ${expr.attr}"), null)
          }
          is ObjectValue ->
            value.attrs[expr.attr]?.let { ValueOrException.Succeeded(it) }
              ?: throw IllegalStateException("invalid attribute: ${expr.attr}")
          is ListValue -> ValueOrException.Succeeded(ListValue.getMember(value, expr.attr))
          else -> TODO()
        }
      }
      is Await -> TODO()
      is BinOp -> {
        val lhsOrException = evaluate(lookup, expr.left)
        if (lhsOrException is ValueOrException.Failed) {
          return lhsOrException
        }
        val rhsOrException = evaluate(lookup, expr.right)
        if (rhsOrException is ValueOrException.Failed) {
          return rhsOrException
        }
        val lhs = (lhsOrException as ValueOrException.Succeeded).value
        val rhs = (rhsOrException as ValueOrException.Succeeded).value
        when (expr.op) {
          Add -> {
            return when (lhs) {
              is IntValue -> when (rhs) {
                is IntValue -> ValueOrException.Succeeded(IntValue(lhs.value + rhs.value))
                else -> TODO()
              }
              else -> TODO()
            }
          }
          BitAnd -> TODO()
          BitOr -> TODO()
          BitXor -> TODO()
          Div -> TODO()
          FloorDiv -> TODO()
          LShift -> TODO()
          MatMult -> TODO()
          Mod -> TODO()
          Mult -> TODO()
          Pow -> TODO()
          RShift -> TODO()
          Sub -> TODO()
        }
      }
      is BoolOp -> {
        TODO()
      }
      is Call -> {
        val callingOrException = evaluate(lookup, expr.func)
        if (callingOrException is ValueOrException.Failed) {
          return callingOrException
        }

        fun executeFunc(
          argLookup: AbstractLookupTable,
          funcDef: FunctionDef,
          funcLookup: AbstractLookupTable,
          args: List<AbstractExpr>,
          instanceValue: Value?
        ): ValueOrException {
          val argsMapOrException = evaluateParams(argLookup, funcDef.args, args, instanceValue)
          if (argsMapOrException is ParamsOrException.Failed) {
            return argsMapOrException.failed
          }
          val argsMap = (argsMapOrException as ParamsOrException.Succeeded).params
          val newLookup = LookupTable(funcLookup, argsMap.toMutableMap())
          return interpreter.executeFuncBlock(newLookup, funcDef.body)
        }

        when (val calling = (callingOrException as ValueOrException.Succeeded).value) {
          is BridgeFuncValue -> {
            val argsOrException = evaluateOrException(lookup, expr.args)
            if (argsOrException is ValuesOrException.Failed) {
              return argsOrException.failed
            }
            val args = (argsOrException as ValuesOrException.Succeeded).values
            return calling.function(args)
          }
          is BoundBridgeFuncValue -> {
            val argsOrException = evaluateOrException(lookup, expr.args)
            if (argsOrException is ValuesOrException.Failed) {
              return argsOrException.failed
            }
            val args = (argsOrException as ValuesOrException.Succeeded).values
            return calling.function(calling.instanceValue, args)
          }
          is FuncDefValue ->
            return executeFunc(lookup, calling.value, calling.lookupTable, expr.args, null)
          is BoundFuncDefValue ->
            return executeFunc(
              lookup,
              calling.funcDef.value,
              calling.funcDef.lookupTable,
              expr.args,
              calling.instanceValue
            )
          is ClassDefValue -> {
            // create class instance
            val instanceValue = ClassInstanceValue(calling, mutableMapOf())
            val initFunc = calling.value.body.filterIsInstance<FunctionDef>().find {
              it.name == "__init__"
            }
            if (initFunc != null) {
              // __init__ 호출
              val initResult =
                executeFunc(lookup, initFunc, calling.lookupTable, expr.args, instanceValue)
              if (initResult is ValueOrException.Failed) {
                return initResult
              }
            }
            return ValueOrException.Succeeded(instanceValue)
          }
          is LambdaValue -> {
            val argsMapOrException = evaluateParams(lookup, calling.value.args, expr.args)
            if (argsMapOrException is ParamsOrException.Failed) {
              return argsMapOrException.failed
            }
            val argsMap = (argsMapOrException as ParamsOrException.Succeeded).params
            val newLookup = LookupTable(calling.lookupTable, argsMap.toMutableMap())
            return evaluate(newLookup, calling.value.body)
          }
          is BoundLambdaValue -> {
            val argsMapOrException =
              evaluateParams(lookup, calling.lambda.value.args, expr.args, calling.instanceValue)
            if (argsMapOrException is ParamsOrException.Failed) {
              return argsMapOrException.failed
            }
            val argsMap = (argsMapOrException as ParamsOrException.Succeeded).params
            val newLookup = LookupTable(calling.lambda.lookupTable, argsMap.toMutableMap())
            return evaluate(newLookup, calling.lambda.value.body)
          }
          else -> TODO()
        }
      }
      is Compare -> {
        val lhsOrException = evaluate(lookup, expr.left)
        if (lhsOrException is ValueOrException.Failed) {
          return lhsOrException
        }
        var lhs = (lhsOrException as ValueOrException.Succeeded).value
        for ((op, rhsExpr) in expr.ops.zip(expr.comparators)) {
          val rhsOrException = evaluate(lookup, rhsExpr)
          if (rhsOrException is ValueOrException.Failed) {
            return rhsOrException
          }
          val rhs = (rhsOrException as ValueOrException.Succeeded).value
          if (!compare(op, lhs, rhs)) {
            return ValueOrException.Succeeded(BoolValue(false))
          }
          lhs = rhs
        }
        return ValueOrException.Succeeded(BoolValue(true))
      }
      is Constant ->
        return when {
          expr.value.startsWith("<class 'int'>") ->
            ValueOrException.Succeeded(IntValue(expr.value.substring(13).toLong()))
          expr.value.startsWith("<class 'str'>") ->
            ValueOrException.Succeeded(StringValue(expr.value.substring(13)))
          expr.value.startsWith("<class 'bool'>") ->
            ValueOrException.Succeeded(BoolValue(expr.value.substring(14).toBoolean()))
          else -> TODO()
        }
      is Dict -> TODO()
      is DictComp -> TODO()
      is FormattedValue -> {
        val value = evaluate(lookup, expr.value)
        // TODO formatting
        return value
      }
      is GeneratorExp -> TODO()
      is IfExp -> TODO()
      is JoinedStr -> {
        val valuesOrException = evaluateOrException(lookup, expr.values)
        if (valuesOrException is ValuesOrException.Failed) {
          return valuesOrException.failed
        }
        val values = (valuesOrException as ValuesOrException.Succeeded).values
        return ValueOrException.Succeeded(StringValue(values.joinToString("")))
      }
      is Lambda -> {
        return ValueOrException.Succeeded(LambdaValue(lookup, expr, mutableMapOf()))
      }
      is ListComp -> TODO()
      is ListExpr -> {
        val valuesOrException = evaluateOrException(lookup, expr.elts)
        if (valuesOrException is ValuesOrException.Failed) {
          return valuesOrException.failed
        }
        val values = (valuesOrException as ValuesOrException.Succeeded).values
        return ValueOrException.Succeeded(ListValue(values.toMutableList()))
      }
      is Name ->
        return lookup[expr.id]?.let { ValueOrException.Succeeded(it) }
          ?: throw IllegalStateException("Name not found: ${expr.id} at ${expr.location}")
      is NamedExpr -> TODO()
      is SetExpr -> TODO()
      is SetComp -> TODO()
      is Slice -> {
        val sliceClassDef = interpreter.builtins.getValue("slice") as ClassDefValue
        val startOrException = expr.lower?.let { evaluate(lookup, it) }
        if (startOrException is ValueOrException.Failed) {
          return startOrException
        }
        val start = (startOrException as? ValueOrException.Succeeded)?.value
        val stopOrException = expr.upper?.let { evaluate(lookup, it) }
        if (stopOrException is ValueOrException.Failed) {
          return stopOrException
        }
        val stop = (stopOrException as? ValueOrException.Succeeded)?.value
        val stepOrException = expr.step?.let { evaluate(lookup, it) }
        if (stepOrException is ValueOrException.Failed) {
          return stepOrException
        }
        val step = (stepOrException as? ValueOrException.Succeeded)?.value
        return ValueOrException.Succeeded(ClassInstanceValue(sliceClassDef, listOfNotNull(
          start?.let { "start" to it },
          stop?.let { "stop" to it },
          step?.let { "step" to it }
        ).toMap().toMutableMap()))
      }
      is Starred -> TODO()
      is Subscript -> {
        val valueOrException = evaluate(lookup, expr.value)
        if (valueOrException is ValueOrException.Failed) {
          return valueOrException
        }
        val sliceOrException = evaluate(lookup, expr.slice)
        if (sliceOrException is ValueOrException.Failed) {
          return sliceOrException
        }
        val value = (valueOrException as ValueOrException.Succeeded).value
        val slice = (sliceOrException as ValueOrException.Succeeded).value
        return when (value) {
          is TupleValue -> when (slice) {
            is IntValue -> ValueOrException.Succeeded(value.values[slice.value.toInt()])
            else -> TODO()
          }
          is ListValue -> when (slice) {
            is IntValue -> ValueOrException.Succeeded(value.values[slice.value.toInt()])
            is ClassInstanceValue -> {
              if (slice.clsDef === interpreter.builtins["slice"]) {
                val rslice = rasterSlice(slice, value.values.size)
                ValueOrException.Succeeded(NoneValue)
              } else {
                TODO()
              }
            }
            else -> TODO()
          }
          else -> TODO()
        }
      }
      is Tuple -> {
        val valuesOrException = evaluateOrException(lookup, expr.elts)
        if (valuesOrException is ValuesOrException.Failed) {
          return valuesOrException.failed
        }
        val values = (valuesOrException as ValuesOrException.Succeeded).values
        return ValueOrException.Succeeded(TupleValue(values))
      }
      is UnaryOp -> {
        when (expr.op) {
          Invert -> TODO()
          Not -> {
            val valueOrException = evaluate(lookup, expr.operand)
            if (valueOrException is ValueOrException.Failed) {
              return valueOrException
            }
            val value = (valueOrException as ValueOrException.Succeeded).value
            return ValueOrException.Succeeded(BoolValue(!boolify(value)))
          }
          UAdd -> TODO()
          USub -> {
            val valueOrException = evaluate(lookup, expr.operand)
            if (valueOrException is ValueOrException.Failed) {
              return valueOrException
            }
            return when (val value = (valueOrException as ValueOrException.Succeeded).value) {
              is IntValue -> ValueOrException.Succeeded(IntValue(-value.value))
              else -> TODO()
            }
          }
        }
      }
      is Yield -> TODO()
      is YieldFrom -> TODO()
    }
  }

  sealed class ValuesOrException {
    data class Succeeded(val values: List<Value>) : ValuesOrException()
    data class Failed(val failed: ValueOrException.Failed) : ValuesOrException()
  }

  fun evaluateOrException(
    lookup: AbstractLookupTable,
    exprs: List<AbstractExpr>
  ): ValuesOrException {
    val values = mutableListOf<Value>()
    for (expr in exprs) {
      when (val value = evaluate(lookup, expr)) {
        is ValueOrException.Succeeded -> {
          values.add(value.value)
        }
        is ValueOrException.Failed -> {
          return ValuesOrException.Failed(value)
        }
      }
    }
    return ValuesOrException.Succeeded(values)
  }

  fun compare(op: AbstractCmpop, lhs: Value, rhs: Value): Boolean = when (op) {
    Eq -> lhs == rhs
    Gt -> TODO()
    GtE -> TODO()
    In -> TODO()
    Is -> TODO()
    IsNot -> TODO()
    Lt -> when (lhs) {
      is IntValue -> when (rhs) {
        is IntValue -> lhs.value < rhs.value
        else -> TODO()
      }
      else -> TODO()
    }
    LtE -> when (lhs) {
      is IntValue -> when (rhs) {
        is IntValue -> lhs.value <= rhs.value
        else -> TODO()
      }
      else -> TODO()
    }
    NotEq -> lhs != rhs
    NotIn -> TODO()
  }
}
