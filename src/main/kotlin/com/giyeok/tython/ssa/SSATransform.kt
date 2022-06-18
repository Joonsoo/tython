package com.giyeok.tython.ssa

import com.giyeok.tython.parse.*

class SSATransform {
  private var idCounter = 0
  fun newVar(): SSAVar {
    idCounter += 1
    return SSAVar("$idCounter")
  }

  fun traverseBlock(block: List<AbstractStmt>, emitter: SSAEmitter) {
    block.forEach { stmt ->
      traverseStmt(stmt, emitter)
    }
  }

  fun traverseBlock(block: List<AbstractStmt>, newScope: Boolean): SSABlock {
    val emitter = SSAEmitter()
    traverseBlock(block, emitter)
    return emitter.getBlock(newScope)
  }

  fun unpack(target: AbstractExpr, value: SSAVar, emitter: SSAEmitter) {
    when (target) {
      is Name -> emitter.emit(StoreName(target.id, value))
      is Attribute -> {
        val obj = traverseExpr(target.value, emitter)
        emitter.emit(StoreAttribute(obj, target.attr, value))
      }
      is Subscript -> {
        val obj = traverseExpr(target.value, emitter)
        val slice = traverseExpr(target.slice, emitter)
        emitter.emit(StoreSubscript(obj, slice, value))
      }
      is Tuple -> {
        val starredIdx = target.elts.indexOfFirst { it is Starred }
        if (starredIdx >= 0) {
          check(target.elts.indexOfLast { it is Starred } == starredIdx) { "two starred expressions in assignment" }
          emitter.emit(CheckUnpackSize(value, null, target.elts.size - 1))
          target.elts.take(starredIdx).forEachIndexed { index, elemExpr ->
            val result = newVar()
            emitter.emit(Unpack(result, value, index))
            unpack(elemExpr, result, emitter)
          }
          val lastElems = target.elts.size - starredIdx - 1
          emitter.emit(UnpackRest(newVar(), value, starredIdx, lastElems))
          target.elts.takeLast(lastElems).forEachIndexed { index, elemExpr ->
            val result = newVar()
            // 0 *1 2 3
            // starredIdx = 1, lastElems = 4-1-1 = 2
            // index=0 -> 2, index=1 -> 3
            // Unpack(result, value, -2), Unpack(result, value, -1)
            emitter.emit(Unpack(result, value, -lastElems + index))
          }
        } else {
          emitter.emit(CheckUnpackSize(value, target.elts.size, null))
          target.elts.forEachIndexed { index, elemExpr ->
            val result = newVar()
            emitter.emit(Unpack(result, value, index))
            unpack(elemExpr, result, emitter)
          }
        }
      }
      else -> TODO()
    }
  }

  fun traverseStmt(stmt: AbstractStmt, emitter: SSAEmitter) {
    when (stmt) {
      is AnnAssign -> TODO()
      is Assert -> TODO()
      is Assign -> {
        val value = traverseExpr(stmt.value, emitter)
        stmt.targets.forEach { target ->
          unpack(target, value, emitter)
        }
      }
      is AsyncFor -> TODO()
      is AsyncFunctionDef -> TODO()
      is AsyncWith -> TODO()
      is AugAssign -> {
        val target = traverseExpr(stmt.target, emitter)
        val value = traverseExpr(stmt.value, emitter)
        val result = newVar()
        emitter.emit(BinaryOp(result, stmt.op, target, value))
        unpack(stmt.target, value, emitter)
      }
      is Break -> {
        emitter.emit(BreakLoop)
      }
      is ClassDef -> {
        val result = newVar()
        emitter.emit(LoadClassDef(result, stmt))
        emitter.emit(StoreName(stmt.name, result))
      }
      is Continue -> {
        emitter.emit(ContinueLoop)
      }
      is Delete -> TODO()
      is Expr -> {
        traverseExpr(stmt.value, emitter)
      }
      is For -> {
        val iterSource = traverseExpr(stmt.iter, emitter)
        val iter = newVar()
        emitter.emit(CreateIter(iter, iterSource))
        val iterVar = newVar()
        val iterAssignEmitter = SSAEmitter()
        iterAssignEmitter.emit(NextIter(iterVar, iter))
        unpack(stmt.target, iterVar, iterAssignEmitter)
        val iterAssign = iterAssignEmitter.getBlock(false)
        val bodyBlock = traverseBlock(stmt.body, false)
        val orelseBlock = if (stmt.orelse.isEmpty()) null else {
          traverseBlock(stmt.orelse, false)
        }
        emitter.emit(ForLoop(iter, iterVar, iterAssign, bodyBlock, orelseBlock))
      }
      is FunctionDef -> {
        val result = newVar()
        emitter.emit(LoadFunctionDef(result, stmt))
        emitter.emit(StoreName(stmt.name, result))
      }
      is Global -> TODO()
      is If -> {
        val test = traverseExpr(stmt.test, emitter)
        val body = traverseBlock(stmt.body, false)
        val orelse = if (stmt.orelse.isEmpty()) null else {
          traverseBlock(stmt.orelse, false)
        }
        emitter.emit(IfStmt(test, body, orelse))
      }
      is Import -> {
        stmt.names.forEach { name ->
          val asname = name.asname ?: name.name.substringAfterLast('.')
          emitter.emit(StoreImport(asname, null, name.name))
        }
      }
      is ImportFrom -> {
        // level?
        val from = Pair(stmt.level!!, stmt.module ?: "")
        stmt.names.forEach { name ->
          val asname = name.asname ?: name.name.substringAfterLast('.')
          emitter.emit(StoreImport(asname, from, name.name))
        }
      }
      is Match -> TODO()
      is Nonlocal -> TODO()
      is Pass -> {}
      is Raise -> {
        val exc = stmt.exc?.let { traverseExpr(it, emitter) }
        val cause = stmt.cause?.let { traverseExpr(it, emitter) }
        emitter.emit(RaiseStmt(exc, cause))
      }
      is Return -> {
        val value = stmt.value?.let { traverseExpr(it, emitter) }
        emitter.emit(ReturnStmt(value))
      }
      is Try -> {
        val bodyEmitter = SSAEmitter()
        traverseBlock(stmt.body, bodyEmitter)
        val body = bodyEmitter.getBlock(false)

        val handlers = stmt.handlers.map { handler ->
          handler as ExceptHandler
          val catching = handler.type?.let { traverseExprBlock(it, false) }
          val handlerEmitter = SSAEmitter()
          traverseBlock(handler.body, handlerEmitter)
          ExceptBlock(catching, handler.name, handlerEmitter.getBlock(false))
        }

        val orelse = if (stmt.orelse.isEmpty()) null else {
          val orelseEmitter = SSAEmitter()
          traverseBlock(stmt.orelse, orelseEmitter)
          orelseEmitter.getBlock(false)
        }

        val finally = if (stmt.finalbody.isEmpty()) null else {
          val finallyEmitter = SSAEmitter()
          traverseBlock(stmt.finalbody, finallyEmitter)
          finallyEmitter.getBlock(false)
        }
        emitter.emit(TryStmt(body, handlers, orelse, finally))
      }
      is While -> {
        val testBlock = traverseExprBlock(stmt.test, false)
        val bodyBlock = traverseBlock(stmt.body, false)
        val orelseBlock = if (stmt.orelse.isEmpty()) null else {
          traverseBlock(stmt.orelse, false)
        }
        emitter.emit(WhileLoop(testBlock, bodyBlock, orelseBlock))
      }
      is With -> {
        TODO()
      }
    }
  }

  fun constBoolBlock(bool: Boolean): SSABlockValue {
    val falseValue = newVar()
    return SSABlockValue(SSABlock(listOf(LoadConstBool(falseValue, false)), false), falseValue)
  }

  fun traverseExpr(expr: AbstractExpr, emitter: SSAEmitter): SSAVar = when (expr) {
    is Attribute -> {
      val value = traverseExpr(expr.value, emitter)
      val result = newVar()
      emitter.emit(LoadAttribute(result, value, expr.attr))
      result
    }
    is Await -> TODO()
    is BinOp -> {
      val lhs = traverseExpr(expr.left, emitter)
      val rhs = traverseExpr(expr.right, emitter)
      val result = newVar()
      emitter.emit(BinaryOp(result, expr.op, lhs, rhs))
      result
    }
    is BoolOp -> {
      fun convert(values: List<AbstractExpr>, emitter: SSAEmitter): SSAVar = if (values.size == 1) {
        traverseExpr(values.first(), emitter)
      } else {
        val first = traverseExpr(values.first(), emitter)
        val restEmitter = SSAEmitter()
        val rest = convert(values.drop(1), restEmitter)
        val restBlock = SSABlockValue(restEmitter.getBlock(false), rest)
        val result = newVar()
        when (expr.op) {
          And -> {
            val firstNot = newVar()
            emitter.emit(UnOp(firstNot, Not, first))
            val falseBlock = constBoolBlock(false)
            emitter.emit(IfThenElse(result, firstNot, falseBlock, restBlock))
          }
          Or -> {
            val trueBlock = constBoolBlock(true)
            emitter.emit(IfThenElse(result, first, trueBlock, restBlock))
          }
        }
        result
      }
      convert(expr.values, emitter)
      // BoolOp이 이렇게 묶여있는건 (a and b and c)에서 a, b, c 중 하나라도 False가 나오는게 있으면 바로 False 반환하고 종료하기 위함(or도 마찬가지)
      // a or b -> True if a else b
      // a or b or c -> True if a else (b or c) -> True if a else (True if b else c)
      // a and b -> False if not a else b
      // a and b and c -> False if not a else (b and c) -> False if not a else (False if not b else c)
      // ...
    }
    is Call -> {
      val func = traverseExpr(expr.func, emitter)
      val args = expr.args.map { arg -> traverseExpr(arg, emitter) }
      val kws = expr.keywords.associate { keyword ->
        keyword.arg!! to traverseExpr(keyword.value, emitter)
      }
      val result = newVar()
      emitter.emit(CallFunc(result, func, args, kws))
      result
    }
    is Compare -> {
      check(expr.ops.size == expr.comparators.size)

      fun convert(
        lhs: SSAVar,
        comps: List<Pair<AbstractCmpop, AbstractExpr>>,
        emitter: SSAEmitter
      ): SSAVar {
        val (op, rhsExpr) = comps.first()
        val rhs = traverseExpr(rhsExpr, emitter)
        val comp = newVar()
        emitter.emit(CompareOp(comp, op, lhs, rhs))
        return if (comps.size == 1) {
          comp
        } else {
          val compNot = newVar()
          emitter.emit(UnOp(compNot, Not, comp))
          val restEmitter = SSAEmitter()
          val rest = convert(rhs, comps.drop(1), restEmitter)
          val restBlock = SSABlockValue(restEmitter.getBlock(false), rest)

          val falseBlock = constBoolBlock(false)
          val result = newVar()
          emitter.emit(IfThenElse(result, compNot, falseBlock, restBlock))
          result
        }
      }

      convert(traverseExpr(expr.left, emitter), expr.ops.zip(expr.comparators), emitter)
      // Compare가 이렇게 묶여있는 것도 BoolOp과 마찬가지로 조건중 하나라도 안 맞으면 바로 False 반환하고 종료하기 위함
      // a < b < c === a < b and b < c -> False if not a < b else (b < c)
      // a < b < c < d === a < b and b < c and c < d -> False if not a < b else (b < c and c < d)
    }
    is Constant -> {
      val result = newVar()
      emitter.emit(LoadConst(result, expr))
      result
    }
    is Dict -> {
      check(expr.keys.size == expr.values.size)
      val pairExprs = expr.keys.zip(expr.values)
      val pairs = pairExprs.map { (keyExpr, valueExpr) ->
        val key = traverseExpr(keyExpr, emitter)
        val value = traverseExpr(valueExpr, emitter)
        key to value
      }
      val result = newVar()
      emitter.emit(CreateDict(result, pairs))
      result
    }
    is DictComp -> {
      expr.key
      expr.value
      expr.generators.first()
      TODO()
    }
    is FormattedValue -> {
      val value = traverseExpr(expr.value, emitter)
      val format = expr.format_spec?.let { traverseExpr(it, emitter) }
      // f"{x + 1!r:<10}" 에서 !r -> conversion=114,
      val result = newVar()
      emitter.emit(FormatString(result, value, expr.conversion, format))
      result
    }
    is GeneratorExp -> TODO()
    is IfExp -> {
      val test = traverseExpr(expr.test, emitter)
      val body = traverseExprBlock(expr.body, false)
      val orelse = traverseExprBlock(expr.orelse, false)
      val result = newVar()
      emitter.emit(IfThenElse(result, test, body, orelse))
      result
    }
    is JoinedStr -> {
      val values = expr.values.map { traverseExpr(it, emitter) }
      val result = newVar()
      emitter.emit(JoinString(result, values))
      result
    }
    is Lambda -> {
      val result = newVar()
      emitter.emit(LoadLambda(result, expr))
      result
    }
    is ListExpr -> {
      val elems = expr.elts.map { traverseExpr(it, emitter) }
      val result = newVar()
      emitter.emit(CreateList(result, elems))
      result
    }
    is ListComp -> {
      expr.elt
      expr.generators.first()
      TODO()
    }
    is Name -> {
      val result = newVar()
      emitter.emit(LoadName(result, expr.id))
      result
    }
    is NamedExpr -> TODO()
    is SetExpr -> {
      val elems = expr.elts.map { traverseExpr(it, emitter) }
      val result = newVar()
      emitter.emit(CreateSet(result, elems))
      result
    }
    is SetComp -> TODO()
    is Slice -> {
      val result = newVar()
      val lower = expr.lower?.let { traverseExpr(it, emitter) }
      val upper = expr.upper?.let { traverseExpr(it, emitter) }
      val step = expr.step?.let { traverseExpr(it, emitter) }
      emitter.emit(CreateSlice(result, lower, upper, step))
      result
    }
    is Subscript -> {
      val value = traverseExpr(expr.value, emitter)
      val slice = traverseExpr(expr.slice, emitter)
      val result = newVar()
      emitter.emit(LoadSubscript(result, value, slice))
      result
    }
    is Tuple -> {
      val elems = expr.elts.map { traverseExpr(it, emitter) }
      val result = newVar()
      emitter.emit(CreateTuple(result, elems))
      result
    }
    is UnaryOp -> {
      val result = newVar()
      val operand = traverseExpr(expr.operand, emitter)
      emitter.emit(UnOp(result, expr.op, operand))
      result
    }
    is Yield -> TODO()
    is YieldFrom -> TODO()
    else -> {
      // Starred는 처리하지 않아도 될..듯?
      TODO()
    }
  }

  fun traverseExprBlock(expr: AbstractExpr, newScope: Boolean): SSABlockValue {
    val emitter = SSAEmitter()
    val result = traverseExpr(expr, emitter)
    return SSABlockValue(SSABlock(emitter.get(), newScope), result)
  }
}

class SSAEmitter {
  private val ssas = mutableListOf<SSA>()

  fun emit(ssa: SSA): SSA {
    ssas.add(ssa)
    return ssa
  }

  fun get(): List<SSA> = ssas.toList()

  fun getBlock(newScope: Boolean): SSABlock = SSABlock(get(), newScope)
}
