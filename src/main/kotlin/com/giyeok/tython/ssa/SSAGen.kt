package com.giyeok.tython.ssa

import com.giyeok.tython.parse.*

class SSAGen(val vars: SSAVarIssuer) {
  fun newVar(): SSAVar = vars.newVar()

  fun traverseBlock(block: List<AbstractStmt>, scope: VariablesScope, emitter: SSAEmitter) {
    block.forEach { stmt ->
      traverseStmt(stmt, scope, emitter)
    }
  }

  fun traverseNewBlock(block: List<AbstractStmt>, scope: VariablesScope): SSABlock {
    val emitter = SSAEmitter()
    traverseBlock(block, scope, emitter)
    return emitter.getBlock()
  }

  // value를 target에 assign
  fun unpackAssign(
    target: AbstractExpr,
    value: SSAVar,
    scope: VariablesScope,
    emitter: SSAEmitter
  ) {
    when (target) {
      is Name -> {
        if (scope.isNotNonlocalOrGlobal(target.id)) {
          scope.locals.add(target.id)
        }
        // TODO local variable name이어도 StoreName을 해줘야될까?
        emitter.emit(StoreName(target.id, value))
      }
      is Attribute -> {
        val obj = traverseExpr(target.value, scope, emitter)
        emitter.emit(StoreAttribute(obj, target.attr, value))
      }
      is Subscript -> {
        val obj = traverseExpr(target.value, scope, emitter)
        val slice = traverseExpr(target.slice, scope, emitter)
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
            unpackAssign(elemExpr, result, scope, emitter)
          }
          val lastElems = target.elts.size - starredIdx - 1
          val rest = newVar()
          emitter.emit(UnpackRest(rest, value, starredIdx, lastElems))
          unpackAssign(target.elts[starredIdx], rest, scope, emitter)
          target.elts.takeLast(lastElems).forEachIndexed { index, elemExpr ->
            val result = newVar()
            // 0 *1 2 3
            // starredIdx = 1, lastElems = 4-1-1 = 2
            // index=0 -> 2, index=1 -> 3
            // Unpack(result, value, -2), Unpack(result, value, -1)
            emitter.emit(Unpack(result, value, -lastElems + index))
            unpackAssign(elemExpr, result, scope, emitter)
          }
        } else {
          emitter.emit(CheckUnpackSize(value, target.elts.size, null))
          target.elts.forEachIndexed { index, elemExpr ->
            val result = newVar()
            emitter.emit(Unpack(result, value, index))
            unpackAssign(elemExpr, result, scope, emitter)
          }
        }
      }
      else -> TODO()
    }
  }

  fun traverseArguments(
    arguments: Arguments,
    scope: VariablesScope,
    emitter: SSAEmitter
  ): CallableArgs {
    fun traverseArg(arg: Arg, default: AbstractExpr?): CallableArg {
      val annotationVar = if (arg.annotation == null) null else {
        traverseExpr(arg.annotation, scope, emitter)
      }
      val defaultVar = if (default == null) null else {
        traverseExpr(default, scope, emitter)
      }
      return CallableArg(arg.arg, annotationVar, arg.type_comment, defaultVar)
    }

    val defaults =
      List(arguments.posonlyargs.size + arguments.args.size - arguments.defaults.size) { null } + arguments.defaults
    // default와 annotation을 evaluation하는 순서가 실제 파이썬하고 다를 수 있는데.. 상관 없지 않을까..
    val posOnlyArgs = arguments.posonlyargs.zip(defaults.take(arguments.posonlyargs.size))
      .map { (arg, default) -> traverseArg(arg, default) }
    val args = arguments.args.zip(defaults.drop(arguments.posonlyargs.size))
      .map { (arg, default) -> traverseArg(arg, default) }
    val vararg = if (arguments.vararg == null) null else {
      traverseArg(arguments.vararg, null)
    }
    val kwOnlyArgs = arguments.kwonlyargs.zip(arguments.kw_defaults)
      .map { (arg, default) -> traverseArg(arg, default) }
    val kwarg = if (arguments.kwarg == null) null else {
      traverseArg(arguments.kwarg, null)
    }

    return CallableArgs(posOnlyArgs, args, vararg, kwOnlyArgs, kwarg)
  }

  fun traverseForLoop(
    iterExpr: AbstractExpr,
    targetExpr: AbstractExpr,
    bodyBlock: SSABlock,
    scope: VariablesScope,
    emitter: SSAEmitter,
    orelseBlock: SSABlock?,
  ) {
    val iterSource = traverseExpr(iterExpr, scope, emitter)
    val iter = newVar()
    emitter.emit(CreateIter(iter, iterSource))
    val iterVar = newVar()
    val iterAssignEmitter = SSAEmitter()
    iterAssignEmitter.emit(NextIter(iterVar, iter))
    unpackAssign(targetExpr, iterVar, scope, iterAssignEmitter)
    val iterAssign = iterAssignEmitter.getBlock()
    emitter.emit(ForLoop(iter, iterVar, iterAssign, bodyBlock, orelseBlock))
  }

  fun createGenerator(
    comprehensions: List<Comprehension>,
    scope: VariablesScope,
    bodyBlock: SSABlock
  ): SSABlock = if (comprehensions.isEmpty()) {
    bodyBlock
  } else {
    val restBody = createGenerator(comprehensions.drop(1), scope, bodyBlock)
    val first = comprehensions.first()
    val realBody = first.ifs.asReversed().fold(restBody) { b, conditionExpr ->
      val testEmitter = SSAEmitter()
      val test = traverseExpr(conditionExpr, scope, testEmitter)
      testEmitter.emit(IfStmt(test, b, null))
      testEmitter.getBlock()
    }
    val forEmitter = SSAEmitter()
    traverseForLoop(first.iter, first.target, realBody, scope, forEmitter, null)
    forEmitter.getBlock()
  }

  fun traverseStmt(stmt: AbstractStmt, scope: VariablesScope, emitter: SSAEmitter) {
    when (stmt) {
      is AnnAssign -> TODO()
      is Assert -> TODO()
      is Assign -> {
        val value = traverseExpr(stmt.value, scope, emitter)
        stmt.targets.forEach { target ->
          unpackAssign(target, value, scope, emitter)
        }
      }
      is AsyncFor -> TODO()
      is AsyncFunctionDef -> TODO()
      is AsyncWith -> TODO()
      is AugAssign -> {
        val target = traverseExpr(stmt.target, scope, emitter)
        val operand = traverseExpr(stmt.value, scope, emitter)
        val result = newVar()
        emitter.emit(BinaryOp(result, stmt.op, target, operand))
        unpackAssign(stmt.target, result, scope, emitter)
      }
      is Break -> {
        emitter.emit(BreakLoop)
      }
      is ClassDef -> {
        val bases = stmt.bases.map { traverseExpr(it, scope, emitter) }
        // TODO stmt.keywords는 뭐지?
        val newScope = VariablesScope()
        val body = traverseNewBlock(stmt.body, newScope)
        val decorators = stmt.decorator_list.asReversed().map { decorator ->
          traverseExpr(decorator, scope, emitter)
        }

        val result = newVar()
        emitter.emit(CreateClassDef(result, stmt.name, decorators, bases, body, newScope))
        if (scope.isNotNonlocalOrGlobal(stmt.name)) {
          scope.locals.add(stmt.name)
        }
        // TODO local variable name이어도 StoreName을 해줘야될까?
        emitter.emit(StoreName(stmt.name, result))
      }
      is Continue -> {
        emitter.emit(ContinueLoop)
      }
      is Delete -> TODO()
      is Expr -> {
        traverseExpr(stmt.value, scope, emitter)
      }
      is For -> {
        val bodyBlock = traverseNewBlock(stmt.body, scope)
        val orelseBlock = if (stmt.orelse.isEmpty()) null else {
          traverseNewBlock(stmt.orelse, scope)
        }
        traverseForLoop(stmt.iter, stmt.target, bodyBlock, scope, emitter, orelseBlock)
      }
      is FunctionDef -> {
        val args = traverseArguments(stmt.args, scope, emitter)
        val decorators = stmt.decorator_list.asReversed().map { decorator ->
          traverseExpr(decorator, scope, emitter)
        }
        val returnType = stmt.returns?.let { traverseExpr(it, scope, emitter) }
        val newScope = VariablesScope()
        newScope.locals.addAll(args.posOnlyArgs.map { it.name })
        newScope.locals.addAll(args.args.map { it.name })
        args.vararg?.let { newScope.locals.add(it.name) }
        newScope.locals.addAll(args.kwOnlyArgs.map { it.name })
        args.kwarg?.let { newScope.locals.add(it.name) }
        val body = traverseNewBlock(stmt.body, newScope)

        val result = newVar()
        emitter.emit(
          CreateFunctionDef(result, stmt.name, decorators, args, returnType, body, newScope)
        )
        if (scope.isNotNonlocalOrGlobal(stmt.name)) {
          scope.locals.add(stmt.name)
        }
        // TODO local variable name이어도 StoreName을 해줘야될까?
        emitter.emit(StoreName(stmt.name, result))
      }
      is Global -> {
        check(scope.nonlocals.intersect(stmt.names.toSet()).isEmpty())
        check(scope.nonlocalReads.intersect(stmt.names.toSet()).isEmpty()) {
          "used prior to global declaration"
        }
        scope.globals.addAll(stmt.names)
      }
      is If -> {
        val test = traverseExpr(stmt.test, scope, emitter)
        val body = traverseNewBlock(stmt.body, scope)
        if (stmt.orelse.isEmpty()) {
          emitter.emit(IfStmt(test, body, null))
        } else {
          val orelseBlock = traverseNewBlock(stmt.orelse, scope)
          emitter.emit(IfStmt(test, body, orelseBlock))
        }
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
      is Nonlocal -> {
        // check(scope.parent != null) { "nonlocal declaration not allowed at module level" }
        check(scope.globals.intersect(stmt.names.toSet()).isEmpty()) { "nonlocal and global" }
        check(scope.nonlocalReads.intersect(stmt.names.toSet()).isEmpty()) {
          "used prior to nonlocal declaration"
        }
        scope.nonlocals.addAll(stmt.names)
      }
      is Pass -> {}
      is Raise -> {
        val exc = stmt.exc?.let { traverseExpr(it, scope, emitter) }
        val cause = stmt.cause?.let { traverseExpr(it, scope, emitter) }
        emitter.emit(RaiseStmt(exc, cause))
      }
      is Return -> {
        val value = stmt.value?.let { traverseExpr(it, scope, emitter) }
        emitter.emit(ReturnStmt(value))
      }
      is Try -> {
        val bodyEmitter = SSAEmitter()
        traverseBlock(stmt.body, scope, bodyEmitter)
        val body = bodyEmitter.getBlock()

        val handlers = stmt.handlers.map { handler ->
          handler as ExceptHandler
          val catching = handler.type?.let { traverseExprBlock(it, scope) }
          val handlerEmitter = SSAEmitter()
          traverseBlock(handler.body, scope, handlerEmitter)
          ExceptBlock(catching, handler.name, handlerEmitter.getBlock())
        }

        val orelse = if (stmt.orelse.isEmpty()) null else {
          val orelseEmitter = SSAEmitter()
          traverseBlock(stmt.orelse, scope, orelseEmitter)
          orelseEmitter.getBlock()
        }

        val finally = if (stmt.finalbody.isEmpty()) null else {
          val finallyEmitter = SSAEmitter()
          traverseBlock(stmt.finalbody, scope, finallyEmitter)
          finallyEmitter.getBlock()
        }
        emitter.emit(TryStmt(body, handlers, orelse, finally))
      }
      is While -> {
        val testBlock = traverseExprBlock(stmt.test, scope)
        val bodyBlock = traverseNewBlock(stmt.body, scope)
        val orelseBlock = if (stmt.orelse.isEmpty()) null else {
          traverseNewBlock(stmt.orelse, scope)
        }
        emitter.emit(WhileLoop(testBlock, bodyBlock, orelseBlock))
      }
      is With -> {
        TODO()
      }
    }
  }

  fun constBoolBlock(value: Boolean): SSABlockValue {
    val falseValue = newVar()
    return SSABlockValue(
      SSABlock(listOf(LoadConstBool(falseValue, value))), falseValue
    )
  }

  fun traverseExpr(expr: AbstractExpr, scope: VariablesScope, emitter: SSAEmitter): SSAVar =
    when (expr) {
      is Attribute -> {
        val value = traverseExpr(expr.value, scope, emitter)
        val result = newVar()
        emitter.emit(LoadAttribute(result, value, expr.attr))
        result
      }
      is Await -> {
        val value = traverseExpr(expr.value, scope, emitter)
        val result = newVar()
        emitter.emit(AwaitStmt(result, value))
        result
      }
      is BinOp -> {
        val lhs = traverseExpr(expr.left, scope, emitter)
        val rhs = traverseExpr(expr.right, scope, emitter)
        val result = newVar()
        emitter.emit(BinaryOp(result, expr.op, lhs, rhs))
        result
      }
      is BoolOp -> {
        fun convert(values: List<AbstractExpr>, emitter: SSAEmitter): SSAVar =
          if (values.size == 1) {
            traverseExpr(values.first(), scope, emitter)
          } else {
            val first = traverseExpr(values.first(), scope, emitter)
            val restEmitter = SSAEmitter()
            val rest = convert(values.drop(1), restEmitter)
            val restBlock = SSABlockValue(restEmitter.getBlock(), rest)
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
        val func = traverseExpr(expr.func, scope, emitter)
        val args = expr.args.map { arg -> traverseExpr(arg, scope, emitter) }
        val kws = expr.keywords.associate { keyword ->
          keyword.arg!! to traverseExpr(keyword.value, scope, emitter)
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
          val rhs = traverseExpr(rhsExpr, scope, emitter)
          val comp = newVar()
          emitter.emit(CompareOp(comp, op, lhs, rhs))
          return if (comps.size == 1) {
            comp
          } else {
            val compNot = newVar()
            emitter.emit(UnOp(compNot, Not, comp))
            val restEmitter = SSAEmitter()
            val rest = convert(rhs, comps.drop(1), restEmitter)
            val restBlock = SSABlockValue(restEmitter.getBlock(), rest)

            val falseBlock = constBoolBlock(false)
            val result = newVar()
            emitter.emit(IfThenElse(result, compNot, falseBlock, restBlock))
            result
          }
        }

        convert(traverseExpr(expr.left, scope, emitter), expr.ops.zip(expr.comparators), emitter)
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
          val key = traverseExpr(keyExpr, scope, emitter)
          val value = traverseExpr(valueExpr, scope, emitter)
          key to value
        }
        val result = newVar()
        emitter.emit(CreateDict(result, pairs))
        result
      }
      is DictComp -> {
        val result = newVar()
        emitter.emit(CreateList(result, listOf()))

        val eltEmitter = SSAEmitter()
        val newScope = VariablesScope()
        val keyVar = traverseExpr(expr.key, newScope, eltEmitter)
        val valueVar = traverseExpr(expr.value, newScope, eltEmitter)
        eltEmitter.emit(AddToDict(result, keyVar, valueVar))

        val generatorBlock = createGenerator(expr.generators, newScope, eltEmitter.getBlock())
        emitter.emit(ComprehensionBlock(generatorBlock, newScope))
        result
      }
      is FormattedValue -> {
        val value = traverseExpr(expr.value, scope, emitter)
        val format = expr.format_spec?.let { traverseExpr(it, scope, emitter) }
        // f"{x + 1!r:<10}" 에서 !r -> conversion=114,
        val result = newVar()
        emitter.emit(FormatString(result, value, expr.conversion, format))
        result
      }
      is GeneratorExp -> {
        val eltEmitter = SSAEmitter()
        val newScope = VariablesScope()
        val eltBlock = traverseExpr(expr.elt, newScope, eltEmitter)
        eltEmitter.emit(YieldStmt(null, eltBlock))

        val generatorBlock = createGenerator(expr.generators, newScope, eltEmitter.getBlock())
        val result = newVar()
        emitter.emit(CreateGenerator(result, generatorBlock, newScope))
        result
      }
      is IfExp -> {
        val test = traverseExpr(expr.test, scope, emitter)
        val body = traverseExprBlock(expr.body, scope)
        val orelse = traverseExprBlock(expr.orelse, scope)
        val result = newVar()
        emitter.emit(IfThenElse(result, test, body, orelse))
        result
      }
      is JoinedStr -> {
        val values = expr.values.map { traverseExpr(it, scope, emitter) }
        val result = newVar()
        emitter.emit(JoinString(result, values))
        result
      }
      is Lambda -> {
        val args = traverseArguments(expr.args, scope, emitter)
        val newScope = VariablesScope()
        val body = traverseExprBlock(expr.body, newScope)
        val result = newVar()
        emitter.emit(CreateLambda(result, args, body, newScope))
        result
      }
      is ListExpr -> {
        val elems = expr.elts.map { traverseExpr(it, scope, emitter) }
        val result = newVar()
        emitter.emit(CreateList(result, elems))
        result
      }
      is ListComp -> {
        val result = newVar()
        emitter.emit(CreateList(result, listOf()))

        val eltEmitter = SSAEmitter()
        val newScope = VariablesScope()
        val eltBlock = traverseExpr(expr.elt, newScope, eltEmitter)
        eltEmitter.emit(AddToList(result, eltBlock))

        val generatorBlock = createGenerator(expr.generators, newScope, eltEmitter.getBlock())
        emitter.emit(ComprehensionBlock(generatorBlock, newScope))
        result
      }
      is Name -> {
        val result = newVar()
        if (!scope.locals.contains(expr.id) && scope.isNotNonlocalOrGlobal(expr.id)) {
          scope.nonlocalReads.add(expr.id)
        }
        emitter.emit(LoadName(result, expr.id))
        result
      }
      is NamedExpr -> TODO()
      is SetExpr -> {
        val elems = expr.elts.map { traverseExpr(it, scope, emitter) }
        val result = newVar()
        emitter.emit(CreateSet(result, elems))
        result
      }
      is SetComp -> {
        val result = newVar()
        emitter.emit(CreateSet(result, listOf()))

        val eltEmitter = SSAEmitter()
        val newScope = VariablesScope()
        val eltBlock = traverseExpr(expr.elt, newScope, eltEmitter)
        eltEmitter.emit(AddToSet(result, eltBlock))

        val generatorBlock = createGenerator(expr.generators, newScope, eltEmitter.getBlock())
        emitter.emit(ComprehensionBlock(generatorBlock, newScope))
        result
      }
      is Slice -> {
        val result = newVar()
        val lower = expr.lower?.let { traverseExpr(it, scope, emitter) }
        val upper = expr.upper?.let { traverseExpr(it, scope, emitter) }
        val step = expr.step?.let { traverseExpr(it, scope, emitter) }
        emitter.emit(CreateSlice(result, lower, upper, step))
        result
      }
      is Subscript -> {
        val value = traverseExpr(expr.value, scope, emitter)
        val slice = traverseExpr(expr.slice, scope, emitter)
        val result = newVar()
        emitter.emit(LoadSubscript(result, value, slice))
        result
      }
      is Tuple -> {
        val elems = expr.elts.map { traverseExpr(it, scope, emitter) }
        val result = newVar()
        emitter.emit(CreateTuple(result, elems))
        result
      }
      is UnaryOp -> {
        val result = newVar()
        val operand = traverseExpr(expr.operand, scope, emitter)
        emitter.emit(UnOp(result, expr.op, operand))
        result
      }
      is Yield -> {
        val recv = newVar()
        val emit = expr.value?.let { traverseExpr(it, scope, emitter) }
        emitter.emit(YieldStmt(recv, emit))
        recv
      }
      is YieldFrom -> TODO()
      else -> {
        // Starred는 처리하지 않아도 될..듯?
        TODO()
      }
    }

  fun traverseExprBlock(expr: AbstractExpr, scope: VariablesScope): SSABlockValue {
    val emitter = SSAEmitter()
    val result = traverseExpr(expr, scope, emitter)
    // TODO SSABlock에 scope이 필요하지 않을까?
    return SSABlockValue(SSABlock(emitter.get()), result)
  }
}

class SSAEmitter {
  private val ssas = mutableListOf<SSA>()

  fun emit(ssa: SSA): SSA {
    ssas.add(ssa)
    return ssa
  }

  fun get(): List<SSA> = ssas.toList()

  fun getBlock(): SSABlock = SSABlock(get())
}
