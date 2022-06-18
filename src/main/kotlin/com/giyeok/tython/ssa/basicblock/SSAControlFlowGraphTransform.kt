package com.giyeok.tython.ssa.basicblock

import com.giyeok.tython.ssa.*

class SSAControlFlowGraphTransform {
  private var idCounter = 0
  fun newId(): String {
    idCounter += 1
    return "$idCounter"
  }

  class BasicBlockBuilder(val id: String, val ssas: MutableList<SSA>) {
    fun append(ssa: SSA) {
      ssas.add(ssa)
    }
  }

  fun newBlockBuilder() = BasicBlockBuilder(newId(), mutableListOf())

  fun SSAControlFlowGraph.append(vararg ssas: SSA): SSAControlFlowGraph {
    // this.exitPoint 끝에 ssa를 추가
    if (this.exitPoint == null) {
      return this
    }
    val exitBlock = this.nodes.getValue(this.exitPoint)
    val newExitBlock = SSABasicBlock(exitBlock.ssas + ssas)
    return this.copy(nodes = this.nodes + (this.exitPoint to newExitBlock))
  }

  class GraphBuilder(
    val nodes: MutableMap<String, SSABasicBlock>,
    val edges: MutableList<SSABasicBlockEdge>
  ) {
    fun addNode(blockBuilder: BasicBlockBuilder) {
      nodes[blockBuilder.id] = SSABasicBlock(blockBuilder.ssas.toList())
    }

    fun addEdge(edge: SSABasicBlockEdge) {
      edges.add(edge)
    }

    fun addGraph(graph: SSAControlFlowGraph) {
      check(graph.nodes.keys.intersect(nodes.keys).isEmpty())
      nodes.putAll(graph.nodes)
      edges.addAll(graph.edges)
    }

    fun build(entryPoint: String, exitPoint: String?): SSAControlFlowGraph =
      SSAControlFlowGraph(nodes.toMap(), edges.toList(), entryPoint, exitPoint)
  }

  data class LoopContext(val jumpByContinue: String, val jumpByBreak: String)

  fun transform(ssas: List<SSA>, loopCtx: LoopContext?): SSAControlFlowGraph {
    val builder = GraphBuilder(mutableMapOf(), mutableListOf())
    var currentBlock = newBlockBuilder()
    val entryPoint = currentBlock.id
    ssas.forEachIndexed { index, ssa ->
      when (ssa) {
        is AssignVar,
        is BinaryOp,
        is BooleanOp,
        is CallFunc,
        is CompareOp,
        is CreateDict,
        is CreateGenerator,
        is CreateIter,
        is CreateList,
        is CreateSet,
        is CreateSlice,
        is CreateTuple,
        is CheckUnpackSize, // TODO CheckUnpackSize같은건 별도 분기라고 볼 순 없나..?
        is LoadAttribute,
        is LoadClassDef,
        is LoadConst,
        is LoadConstBool,
        is LoadFunctionDef,
        is LoadName,
        is LoadSubscript,
        is NextIter,
        is StoreAttribute,
        is StoreName,
        is StoreSubscript,
        is UnOp,
        is Unpack,
        is UnpackRest,
        is JoinString,
        is FormatString -> {
          currentBlock.append(ssa)
        }
        is RaiseStmt, is ReturnStmt -> {
          currentBlock.append(ssa)
          builder.addNode(currentBlock)
          check(index + 1 == ssas.size) { "statement after break" }
          return builder.build(entryPoint, null)
        }
        is WhileLoop -> {
          val thisBlock = currentBlock
          val nextBlock = newBlockBuilder()
          currentBlock = nextBlock

          val orelse = ssa.orelse?.let {
            transform(it.stmts, null)
              .append(Jump(nextBlock.id))
          }
          val testBlock0 = transform(ssa.test.block.stmts, null)
          thisBlock.append(Jump(testBlock0.entryPoint))
          val normalEndNext = orelse?.entryPoint ?: nextBlock.id
          val bodyGraph =
            transform(ssa.body.stmts, LoopContext(testBlock0.entryPoint, nextBlock.id))
              .append(Jump(testBlock0.entryPoint))

          val testBlock =
            testBlock0.append(Branch(ssa.test.value, bodyGraph.entryPoint, normalEndNext))

          builder.addNode(thisBlock)
          builder.addGraph(testBlock)
          builder.addGraph(bodyGraph)
          orelse?.let {
            builder.addGraph(it)
            if (it.exitPoint != null) {
              builder.addEdge(SSANormalFlowEdge(it.exitPoint, nextBlock.id))
            }
          }
          builder.addEdge(SSANormalFlowEdge(thisBlock.id, testBlock.entryPoint))
          if (bodyGraph.exitPoint != null) {
            builder.addEdge(SSANormalFlowEdge(bodyGraph.exitPoint, testBlock.entryPoint))
          }
          if (testBlock.exitPoint != null) {
            builder.addEdge(SSANormalFlowEdge(testBlock.exitPoint, bodyGraph.entryPoint))
            builder.addEdge(SSANormalFlowEdge(testBlock.exitPoint, normalEndNext))
          }
        }
        is ForLoop -> {
          val thisBlock = currentBlock
          val nextBlock = newBlockBuilder()
          currentBlock = nextBlock

          val orelse = ssa.orelse?.let {
            transform(it.stmts, null)
              .append(Jump(nextBlock.id))
          }
          val iterAssignGraph0 = transform(ssa.iterAssign.stmts, null)
          // body가 break로 끝나지 않은 경우 body에서 ifEmpty시 branch될 블럭
          val normalEndNext = orelse?.entryPoint ?: nextBlock.id
          val branch = BranchByIter(ssa.iter, iterAssignGraph0.entryPoint, normalEndNext)
          val bodyGraph = transform(
            ssa.body.stmts,
            LoopContext(iterAssignGraph0.entryPoint, nextBlock.id)
          ).append(branch)

          val iterAssignGraph = iterAssignGraph0.append(Jump(bodyGraph.entryPoint))
          thisBlock.append(branch)

          builder.addNode(thisBlock)
          builder.addGraph(iterAssignGraph)
          builder.addGraph(bodyGraph)
          orelse?.let {
            builder.addGraph(it)
            if (it.exitPoint != null) {
              builder.addEdge(SSANormalFlowEdge(it.exitPoint, nextBlock.id))
            }
          }

          builder.addEdge(SSANormalFlowEdge(thisBlock.id, iterAssignGraph.entryPoint))
          builder.addEdge(SSANormalFlowEdge(thisBlock.id, normalEndNext))
          if (iterAssignGraph.exitPoint != null) {
            builder.addEdge(SSANormalFlowEdge(iterAssignGraph.exitPoint, bodyGraph.entryPoint))
          }
          if (bodyGraph.exitPoint != null) {
            builder.addEdge(SSANormalFlowEdge(bodyGraph.exitPoint, iterAssignGraph.entryPoint))
            builder.addEdge(SSANormalFlowEdge(bodyGraph.exitPoint, normalEndNext))
          }
        }
        BreakLoop -> {
          checkNotNull(loopCtx) { "'break' outside loop" }
          currentBlock.append(Jump(loopCtx.jumpByBreak))
          builder.addNode(currentBlock)
          builder.addEdge(SSANormalFlowEdge(currentBlock.id, loopCtx.jumpByBreak))
          check(index + 1 == ssas.size) { "statement after break" }
          return builder.build(entryPoint, null)
        }
        ContinueLoop -> {
          checkNotNull(loopCtx) { "'continue' outside loop" }
          currentBlock.append(Jump(loopCtx.jumpByContinue))
          builder.addNode(currentBlock)
          builder.addEdge(SSANormalFlowEdge(currentBlock.id, loopCtx.jumpByContinue))
          check(index + 1 == ssas.size) { "statement after continue" }
          return builder.build(entryPoint, null)
        }
        is IfStmt -> {
          val thisBlock = currentBlock
          val nextBlock = newBlockBuilder()
          currentBlock = nextBlock

          val thenGraph = transform(ssa.then.stmts, loopCtx).append(Jump(nextBlock.id))

          if (ssa.orelse == null) {
            thisBlock.append(Branch(ssa.test, thenGraph.entryPoint, nextBlock.id))

            builder.addEdge(SSANormalFlowEdge(thisBlock.id, nextBlock.id))
          } else {
            val elseGraph = transform(ssa.orelse.stmts, loopCtx).append(Jump(nextBlock.id))
            thisBlock.append(Branch(ssa.test, thenGraph.entryPoint, elseGraph.entryPoint))

            builder.addGraph(elseGraph)
            builder.addEdge(SSANormalFlowEdge(thisBlock.id, elseGraph.entryPoint))
            if (elseGraph.exitPoint != null) {
              builder.addEdge(SSANormalFlowEdge(elseGraph.exitPoint, nextBlock.id))
            }
          }
          builder.addNode(thisBlock)
          builder.addGraph(thenGraph)
          builder.addEdge(SSANormalFlowEdge(thisBlock.id, thenGraph.entryPoint))
          if (thenGraph.exitPoint != null) {
            builder.addEdge(SSANormalFlowEdge(thenGraph.exitPoint, nextBlock.id))
          }
        }
        is IfThenElse -> {
          val thisBlock = currentBlock
          val nextBlock = newBlockBuilder()
          currentBlock = nextBlock

          val thenGraph = transform(ssa.then.block.stmts, loopCtx).append(Jump(nextBlock.id))
          val elseGraph = transform(ssa.orelse.block.stmts, loopCtx).append(Jump(nextBlock.id))
          thisBlock.append(Branch(ssa.test, thenGraph.entryPoint, elseGraph.entryPoint))
          nextBlock.append(Phi(ssa.dest, listOf(ssa.then.value, ssa.orelse.value)))

          builder.addNode(thisBlock)
          builder.addEdge(SSANormalFlowEdge(thisBlock.id, thenGraph.entryPoint))
          builder.addEdge(SSANormalFlowEdge(thisBlock.id, elseGraph.entryPoint))
          builder.addGraph(thenGraph)
          builder.addGraph(elseGraph)
          if (thenGraph.exitPoint != null) {
            builder.addEdge(SSANormalFlowEdge(thenGraph.exitPoint, nextBlock.id))
          }
          if (elseGraph.exitPoint != null) {
            builder.addEdge(SSANormalFlowEdge(elseGraph.exitPoint, nextBlock.id))
          }
        }
        is TryStmt -> TODO()
        else ->
          // Branch, BranchByIter, Jump, Phi는 SSATransform에서 생성하지 않기 때문에 처리하지 않아도 됨
          TODO()
      }
    }
    val exitPoint = currentBlock.id
    builder.addNode(currentBlock)
    return builder.build(entryPoint, exitPoint)
  }
}
