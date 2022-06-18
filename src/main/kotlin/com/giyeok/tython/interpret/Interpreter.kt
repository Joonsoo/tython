package com.giyeok.tython.interpret

import com.giyeok.tython.parse.*

sealed class AbstractLookupTable {
  abstract operator fun set(name: String, value: Value)
  abstract operator fun get(name: String): Value?
}

object RootLookupTable : AbstractLookupTable() {
  override fun set(name: String, value: Value) {
    throw NotImplementedError()
  }

  override fun get(name: String): Value? = null
}

class LookupTable(
  val parent: AbstractLookupTable,
  val names: MutableMap<String, Value>
) : AbstractLookupTable() {
  override fun set(name: String, value: Value) {
    names[name] = value
  }

  override fun get(name: String): Value? = names[name] ?: parent[name]
}

class Interpreter(
  val builtins: Map<String, Value>,
  val modules: Map<String, Module>,
  val loadedModules: MutableMap<String, ModuleValue>
) {
  val eval = Evaluator(this)

  fun moduleValue(module: Module): ValueOrException {
    val rootLookup = mutableMapOf<String, Value>()
    val result = executeSimpleBlock(
      LookupTable(LookupTable(RootLookupTable, builtins.toMutableMap()), rootLookup),
      module.body
    )
    if (result is ExecuteResult.ExceptionRaised) {
      return ValueOrException.Failed(result.exception, result.cause)
    }
    return ValueOrException.Succeeded(ModuleValue(rootLookup))
  }

  fun moduleValue(moduleName: String): ValueOrException {
    val loaded = loadedModules[moduleName]
    if (loaded != null) {
      return ValueOrException.Succeeded(loaded)
    }
    val importing = modules[moduleName]
      ?: return ValueOrException.Failed(
        StringValue("Failed to import $moduleName"),
        null
      )
    val moduleValueOrException =
      Interpreter(builtins, modules, loadedModules).moduleValue(importing)
    if (moduleValueOrException is ValueOrException.Failed) {
      return moduleValueOrException
    }
    loadedModules[moduleName] =
      (moduleValueOrException as ValueOrException.Succeeded).value as ModuleValue
    return moduleValueOrException
  }

  fun executeSimpleBlock(
    lookup: AbstractLookupTable,
    stmts: List<AbstractStmt>,
  ): ExecuteResult {
    stmts.forEach { stmt ->
      val result = execute(lookup, stmt)
      if (result !is ExecuteResult.NoResult) {
        return result
      }
    }
    return ExecuteResult.NoResult
  }

  fun executeFuncBlock(lookup: AbstractLookupTable, stmts: List<AbstractStmt>): ValueOrException {
    stmts.forEach { stmt ->
      when (val result = execute(lookup, stmt)) {
        ExecuteResult.BreakLoop ->
          throw IllegalStateException("break outside loop")
        ExecuteResult.ContinueLoop ->
          throw IllegalStateException("break outside loop")
        is ExecuteResult.ReturnFunc ->
          return ValueOrException.Succeeded(result.value)
        is ExecuteResult.ExceptionRaised ->
          return ValueOrException.Failed(result.exception, result.cause)
        else -> {}
      }
    }
    return ValueOrException.Succeeded(NoneValue)
  }

  fun executeLoopBlock(lookup: AbstractLookupTable, stmts: List<AbstractStmt>): ExecuteResult {
    stmts.forEach { stmt ->
      val result = execute(lookup, stmt)
      if (result !is ExecuteResult.NoResult) {
        return result
      }
    }
    return ExecuteResult.NoResult
  }

  sealed class ExecuteResult {
    object NoResult : ExecuteResult()

    data class ReturnFunc(val value: Value) : ExecuteResult()

    object BreakLoop : ExecuteResult()

    object ContinueLoop : ExecuteResult()

    data class ExceptionRaised(val exception: Value?, val cause: Value?) : ExecuteResult() {
      companion object {
        fun fromFailed(failed: ValueOrException.Failed) =
          ExceptionRaised(failed.exception, failed.cause)
      }
    }
  }

  fun execute(lookup: AbstractLookupTable, stmt: AbstractStmt): ExecuteResult {
    when (stmt) {
      is AnnAssign -> TODO()
      is Assert -> {
        val checkOrException = eval.evaluate(lookup, stmt.test)
        if (checkOrException is ValueOrException.Failed) {
          return ExecuteResult.ExceptionRaised.fromFailed(checkOrException)
        }
        val check = (checkOrException as ValueOrException.Succeeded).value
        if (!boolify(check)) {
          throw IllegalStateException("Assertion failed")
        }
      }
      is Assign -> {
        val assigningValueOrException = eval.evaluate(lookup, stmt.value)
        if (assigningValueOrException is ValueOrException.Failed) {
          return ExecuteResult.ExceptionRaised.fromFailed(assigningValueOrException)
        }
        val assigningValue = (assigningValueOrException as ValueOrException.Succeeded).value
        stmt.targets.forEach { target ->
          val failed = assignTo(lookup, target, assigningValue)
          if (failed != null) {
            return ExecuteResult.ExceptionRaised.fromFailed(failed)
          }
        }
      }
      is AsyncFor -> TODO()
      is AsyncFunctionDef -> TODO()
      is AsyncWith -> TODO()
      is AugAssign -> TODO()
      is ClassDef -> {
        val basesOrException = eval.evaluateOrException(lookup, stmt.bases)
        if (basesOrException is Evaluator.ValuesOrException.Failed) {
          return ExecuteResult.ExceptionRaised.fromFailed(basesOrException.failed)
        }
        val bases = (basesOrException as Evaluator.ValuesOrException.Succeeded).values
        val clsDef = ClassDefValue(lookup, stmt, bases, mutableMapOf())
        lookup[stmt.name] = clsDef
        val newLookup = LookupTable(lookup, mutableMapOf())
        val result = executeSimpleBlock(newLookup, stmt.body)
        clsDef.attrs.putAll(newLookup.names)
        if (result is ExecuteResult.ExceptionRaised) {
          return result
        }
        return ExecuteResult.NoResult
      }
      is FunctionDef -> {
        val funcDef = FuncDefValue(lookup, stmt, mutableMapOf())
        lookup[stmt.name] = funcDef
        return ExecuteResult.NoResult
      }
      is Delete -> TODO()
      is Expr -> {
        return when (val result = eval.evaluate(lookup, stmt.value)) {
          is ValueOrException.Failed -> ExecuteResult.ExceptionRaised.fromFailed(result)
          is ValueOrException.Succeeded -> ExecuteResult.NoResult
        }
      }
      is For -> {
        val iterOrException = eval.evaluate(lookup, stmt.iter)
        if (iterOrException is ValueOrException.Failed) {
          return ExecuteResult.ExceptionRaised.fromFailed(iterOrException)
        }
        when (val iter = (iterOrException as ValueOrException.Succeeded).value) {
          is ListValue -> {
            if (iter.values.isEmpty()) {
              val result = executeSimpleBlock(lookup, stmt.orelse)
              if (result !is ExecuteResult.NoResult) {
                return result
              }
            } else {
              for (value in iter.values) {
                assignTo(lookup, stmt.target, value)
                when (val loopCont = executeLoopBlock(lookup, stmt.body)) {
                  ExecuteResult.BreakLoop -> return ExecuteResult.NoResult
                  is ExecuteResult.ReturnFunc, is ExecuteResult.ExceptionRaised -> return loopCont
                  else -> {
                    // do nothing
                  }
                }
              }
            }
          }
          is ClassInstanceValue -> {
            // range 클래스이면
            val start = (iter.attrs["start"]!! as IntValue).value.toInt()
            val stop = (iter.attrs["stop"]!! as IntValue).value.toInt()
            val step = (iter.attrs["step"]!! as IntValue).value.toInt()
            var x = start
            while ((step > 0 && x < stop) || (step < 0 && x > stop)) {
              val newLookup = LookupTable(lookup, mutableMapOf())
              assignTo(newLookup, stmt.target, IntValue(x.toLong()))
              when (val loopCont = executeLoopBlock(newLookup, stmt.body)) {
                ExecuteResult.BreakLoop -> break
                is ExecuteResult.ReturnFunc, is ExecuteResult.ExceptionRaised -> return loopCont
                else -> {
                  // do nothing
                }
              }
              x += step
            }
          }
        }
      }
      is Global -> TODO()
      is If -> {
        val conditionOrException = eval.evaluate(lookup, stmt.test)
        if (conditionOrException is ValueOrException.Failed) {
          return ExecuteResult.ExceptionRaised.fromFailed(conditionOrException)
        }
        val condition = boolify((conditionOrException as ValueOrException.Succeeded).value)
        if (condition) {
          val result = executeSimpleBlock(lookup, stmt.body)
          if (result !is ExecuteResult.NoResult) {
            return result
          }
        } else {
          val result = executeSimpleBlock(lookup, stmt.orelse)
          if (result !is ExecuteResult.NoResult) {
            return result
          }
        }
      }
      is Import -> {
        stmt.names.forEach { importName ->
          val importingModuleOrException = moduleValue(importName.name)
          if (importingModuleOrException is ValueOrException.Failed) {
            return ExecuteResult.ExceptionRaised.fromFailed(importingModuleOrException)
          }
          val importingModule =
            (importingModuleOrException as ValueOrException.Succeeded).value as ModuleValue
          if (importName.asname != null) {
            lookup[importName.asname] = importingModule
          } else {
            lookup[importName.name.substringAfterLast('.')] = importingModule
          }
        }
      }
      is ImportFrom -> {
        val moduleValueOrException = stmt.module
        TODO()
      }
      is Match -> TODO()
      is Nonlocal -> TODO()
      is Pass -> {
        // do nothing
      }
      is Raise -> {
        val exceptionOrException = stmt.exc?.let { eval.evaluate(lookup, it) }
        if (exceptionOrException != null && exceptionOrException is ValueOrException.Failed) {
          return ExecuteResult.ExceptionRaised.fromFailed(exceptionOrException)
        }
        val causeOrException = stmt.cause?.let { eval.evaluate(lookup, it) }
        if (causeOrException != null && causeOrException is ValueOrException.Failed) {
          return ExecuteResult.ExceptionRaised.fromFailed(causeOrException)
        }
        val exception = (exceptionOrException as? ValueOrException.Succeeded)?.value
        val cause = (causeOrException as? ValueOrException.Succeeded)?.value
        return ExecuteResult.ExceptionRaised(exception, cause)
      }
      is Try -> TODO()
      is While -> {
        val firstConditionOrException = eval.evaluate(lookup, stmt.test)
        if (firstConditionOrException is ValueOrException.Failed) {
          return ExecuteResult.ExceptionRaised.fromFailed(firstConditionOrException)
        }
        val firstCondition =
          boolify((firstConditionOrException as ValueOrException.Succeeded).value)
        if (!firstCondition) {
          val result = executeSimpleBlock(lookup, stmt.orelse)
          if (result !is ExecuteResult.NoResult) {
            return result
          }
        } else {
          while (true) {
            when (val loopCont = executeLoopBlock(lookup, stmt.body)) {
              ExecuteResult.BreakLoop -> return ExecuteResult.NoResult
              is ExecuteResult.ReturnFunc, is ExecuteResult.ExceptionRaised ->
                return loopCont
              else -> {
                val testOrException = eval.evaluate(lookup, stmt.test)
                if (testOrException is ValueOrException.Failed) {
                  return ExecuteResult.ExceptionRaised.fromFailed(testOrException)
                }
                val test = boolify((testOrException as ValueOrException.Succeeded).value)
                if (!test) {
                  return ExecuteResult.NoResult
                }
              }
            }
          }
        }
      }
      is With -> TODO()
      is Return -> {
        return if (stmt.value == null) {
          ExecuteResult.ReturnFunc(NoneValue)
        } else {
          val returnValueOrException = eval.evaluate(lookup, stmt.value)
          if (returnValueOrException is ValueOrException.Failed) {
            return ExecuteResult.ExceptionRaised.fromFailed(returnValueOrException)
          }
          ExecuteResult.ReturnFunc((returnValueOrException as ValueOrException.Succeeded).value)
        }
      }
      is Break -> return ExecuteResult.BreakLoop
      is Continue -> return ExecuteResult.ContinueLoop
    }
    return ExecuteResult.NoResult
  }

  fun assignTo(
    lookup: AbstractLookupTable,
    target: AbstractExpr,
    value: Value
  ): ValueOrException.Failed? {
    when (target) {
      is Name -> lookup[target.id] = value
      is Attribute -> {
        val targetValueOrException = eval.evaluate(lookup, target.value)
        if (targetValueOrException is ValueOrException.Failed) {
          return targetValueOrException
        }
        val targetValue = (targetValueOrException as ValueOrException.Succeeded).value
        if (targetValue == NoneValue) {
          throw IllegalStateException("Cannot set to None value")
        }
        when (targetValue) {
          is ObjectValue ->
            targetValue.attrs[target.attr] = value
          else -> throw IllegalStateException("")
        }
      }
      is Subscript -> {
        val targetValueOrException = eval.evaluate(lookup, target.value)
        if (targetValueOrException is ValueOrException.Failed) {
          return targetValueOrException
        }
        val targetValue = (targetValueOrException as ValueOrException.Succeeded).value
        val sliceValueOrException = eval.evaluate(lookup, target.slice)
        if (sliceValueOrException is ValueOrException.Failed) {
          return sliceValueOrException
        }
        val sliceValue = (sliceValueOrException as ValueOrException.Succeeded).value
        when (targetValue) {
          is ListValue ->
            when (sliceValue) {
              is IntValue -> {
                val index = sliceValue.value.toInt()
                targetValue.values[index] = value
              }
              is ClassInstanceValue -> {
                if (sliceValue.clsDef === builtins["slice"]) {
                  val rslice = rasterSlice(sliceValue, targetValue.values.size)
                  // TODO
                } else {
                  TODO()
                }
              }
              else -> TODO()
            }
          is DictValue ->
            TODO()
          is ClassInstanceValue ->
            TODO()
          else -> TODO()
        }
      }
      is Tuple -> {
        value as TupleValue
        check(target.elts.size == value.values.size)
        target.elts.zip(value.values).forEach { (t, v) ->
          assignTo(lookup, t, v)
        }
      }
      else -> TODO()
    }
    return null
  }
}
