package com.giyeok.tython.basicblock

import com.giyeok.tython.ssa.SSA
import com.giyeok.tython.ssa.SSABasic

fun SSAControlFlowGraph.toMutable() = MutableControlFlowGraph(
  nodes.mapValues { MutableBasicBlock(it.value.ssas.toMutableList()) }.toMutableMap(),
  edges.toMutableSet(),
  entryPoint,
  exitPoint,
  exceptionHandlers.mapValues { it.value.toMutableList() }.toMutableMap()
)

data class MutableControlFlowGraph(
  val nodes: MutableMap<String, MutableBasicBlock>,
  val edges: MutableSet<SSABasicBlockEdge>,
  var entryPoint: String,
  var exitPoint: String?,
  // key -> value: key의 노드에서 익셉션이 생기면 value에 정의된 노드로(앞에서부터) 전달
  val exceptionHandlers: MutableMap<String, MutableList<String>>,
) {
  private var nodeIdCounter = 0
  fun newNodeId(): String {
    nodeIdCounter += 1
    var newNodeId = "$nodeIdCounter"
    while (nodes.containsKey(newNodeId)) {
      nodeIdCounter += 1
      newNodeId = "$nodeIdCounter"
    }
    return newNodeId
  }

  // TODO 나중에는 필요하면 캐싱?
  fun incomingEdgesTo(node: String): Set<SSABasicBlockEdge> =
    edges.filter { it.endNode == node }.toSet()

  fun outgoingEdgesFrom(node: String): Set<SSABasicBlockEdge> =
    edges.filter { it.startNode == node }.toSet()

  fun toGraph() = SSAControlFlowGraph(
    nodes.mapValues { SSABasicBlock(it.value.ssas.toList()) },
    edges.toSet(),
    entryPoint,
    exitPoint,
    exceptionHandlers.mapValues { it.value.toList() }
  )

  fun prependStmts(ssas: List<SSABasic>) {
    if (incomingEdgesTo(entryPoint).isEmpty()) {
      nodes.getValue(entryPoint).ssas.addAll(0, ssas)
    } else {
      val newEntryPoint = newNodeId()
      nodes[newEntryPoint] = MutableBasicBlock(ssas.toMutableList())
      edges.add(SSANormalFlowEdge(newEntryPoint, entryPoint))
      entryPoint = newEntryPoint
    }
  }
}

data class MutableBasicBlock(val ssas: MutableList<SSABasic>)
