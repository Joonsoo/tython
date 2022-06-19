package com.giyeok.tython.ssa.basicblock.transform

import com.giyeok.tython.ssa.basicblock.SSAControlFlowGraph

// CFG를 받아서 local variable들을 LoadName, StoreName하지 않도록 하고 필요한 phi op 추가
// 이 때 각 local 변수의 값을 갖고 있을 수 있는 SSAVar들의 목록을 저장해두어야 분석할 때 쓸 수 있을듯?
class UnrefLocals(val graph: SSAControlFlowGraph, val locals: Set<String>) {
}
