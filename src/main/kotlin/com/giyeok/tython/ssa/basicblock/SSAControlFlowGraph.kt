package com.giyeok.tython.ssa.basicblock

import com.giyeok.tython.ssa.SSA
import com.giyeok.tython.ssa.SSAVar

data class SSAControlFlowGraph(
  // nodes중에는 비어있는 노드가 있을 수도 있음
  val nodes: Map<String, SSABasicBlock>,
  val edges: List<SSABasicBlockEdge>,
  val entryPoint: String,
  val exitPoint: String?,
)

data class SSABasicBlock(val ssas: List<SSA>)

sealed class SSABasicBlockEdge

data class SSANormalFlowEdge(val startNode: String, val endNode: String) : SSABasicBlockEdge()

// startNode를 실행하다 exception이 발생하면 endNode로 이동
data class SSAExceptionRaisedEdge(val startNode: String, val endNode: String) : SSABasicBlockEdge()

data class SSAAfterYieldEdge(val startNode: String, val endNode: String) : SSABasicBlockEdge()

data class SSAAfterAwaitEdge(val startNode: String, val endNode: String) : SSABasicBlockEdge()
