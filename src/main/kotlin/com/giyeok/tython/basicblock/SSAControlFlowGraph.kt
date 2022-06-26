package com.giyeok.tython.basicblock

import com.giyeok.tython.ssa.SSABasic

data class SSAControlFlowGraph(
  // nodes중에는 비어있는 노드가 있을 수도 있음
  val nodes: Map<String, SSABasicBlock>,
  val edges: Set<SSABasicBlockEdge>,
  val entryPoint: String,
  val exitPoint: String?,
  // key -> value: key의 노드에서 익셉션이 생기면 value에 정의된 노드로(앞에서부터) 전달
  val exceptionHandlers: Map<String, List<String>>,
) {
  // TODO 나중에는 필요하면 캐싱?
  fun incomingEdgesTo(node: String): Set<SSABasicBlockEdge> =
    edges.filter { it.endNode == node }.toSet()

  fun outgoingEdgesFrom(node: String): Set<SSABasicBlockEdge> =
    edges.filter { it.startNode == node }.toSet()
}

data class SSABasicBlock(val ssas: List<SSABasic>)

sealed class SSABasicBlockEdge {
  abstract val startNode: String
  abstract val endNode: String
}

data class SSANormalFlowEdge(override val startNode: String, override val endNode: String) :
  SSABasicBlockEdge()

// startNode를 실행하다 exception이 발생하면 endNode로 이동
data class SSAExceptionRaisedEdge(override val startNode: String, override val endNode: String) :
  SSABasicBlockEdge()

data class SSAAfterYieldEdge(override val startNode: String, override val endNode: String) :
  SSABasicBlockEdge()

data class SSAAfterAwaitEdge(override val startNode: String, override val endNode: String) :
  SSABasicBlockEdge()
