package com.giyeok.tython.ssa.basicblock

import com.giyeok.tython.ssa.SSA

data class SSAControlFlowGraph(
  // nodes중에는 비어있는 노드가 있을 수도 있음
  val nodes: Map<String, SSABasicBlock>,
  val edges: List<SSABasicBlockEdge>,
  val entryPoint: String,
  val exitPoint: String?,
  // key -> value: key의 노드에서 익셉션이 생기면 value에 정의된 노드로(앞에서부터) 전달
  val exceptionHandlers: Map<String, List<String>>,
)

data class SSABasicBlock(val ssas: List<SSA>)

sealed class SSABasicBlockEdge

data class SSANormalFlowEdge(val startNode: String, val endNode: String) : SSABasicBlockEdge()

// startNode를 실행하다 exception이 발생하면 endNode로 이동
data class SSAExceptionRaisedEdge(val startNode: String, val endNode: String) : SSABasicBlockEdge()

data class SSAAfterYieldEdge(val startNode: String, val endNode: String) : SSABasicBlockEdge()

data class SSAAfterAwaitEdge(val startNode: String, val endNode: String) : SSABasicBlockEdge()
