package com.giyeok.tython.ssa.basicblock

import com.giyeok.tython.parse.ParseModules
import com.giyeok.tython.ssa.SSATransform
import com.giyeok.tython.ssa.printSSA

fun main() {
  val srcs = mapOf(
    "builtins" to "builtins.py",
    "a" to "a.py",
    "b" to "b.py",
    "c" to "c.py",
  )
  val moduleAsts = ParseModules.INSTNACE.load(srcs)

  val ssaTransform = SSATransform()
  val block = ssaTransform.traverseBlock(moduleAsts.getValue("a").body, true)

  printSSA(block, "")
  println()
  val cfg = SSAControlFlowGraphTransform().transform(block.stmts, null)

  cfg.nodes.entries.sortedBy { it.key }.forEach { (id, basicBlock) ->
    println("BB $id:")
    basicBlock.ssas.forEach { ssa ->
      printSSA(ssa, "  ")
    }
  }
  println("digraph G {")
  println("  ${cfg.entryPoint} [rank=min];")
  println("  ${cfg.exitPoint} [rank=max];")
  cfg.edges.forEach { edge ->
    when (edge) {
      is SSANormalFlowEdge -> {
        println("  ${edge.startNode} -> ${edge.endNode};")
      }
      is SSAExceptionCatchEdge -> TODO()
      is SSAAfterYieldEdge -> TODO()
    }
  }
  println("}")
}
