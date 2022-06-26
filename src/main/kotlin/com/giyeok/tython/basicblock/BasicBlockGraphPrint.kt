package com.giyeok.tython.basicblock

import com.giyeok.tython.ssa.printSSA
import java.io.PrintWriter
import java.io.StringWriter

fun PrintWriter.printCfg(cfg: SSAControlFlowGraph) {
  cfg.nodes.entries.sortedBy { it.key }.forEach { (id, basicBlock) ->
    println("BB $id: (from ${cfg.edges.filter { it.endNode == id }.map { it.startNode }})")
    basicBlock.ssas.forEach { ssa ->
      printSSA(ssa, "  ")
    }
  }
  printCfgDotGraph(cfg)
}

fun PrintWriter.printCfgDotGraph(cfg: SSAControlFlowGraph) {
  println("digraph G {")
  println("  ${cfg.entryPoint} [rank=min];")
  println("  ${cfg.exitPoint} [rank=max];")
  cfg.edges.forEach { edge ->
    when (edge) {
      is SSANormalFlowEdge -> {
        println("  ${edge.startNode} -> ${edge.endNode};")
      }
      is SSAAfterYieldEdge -> TODO()
    }
  }
  println("}")
}

fun PrintWriter.printCfgDotGraphWithCode(cfg: SSAControlFlowGraph) {
  println("digraph G {")
  println("  ${cfg.entryPoint} [rank=min];")
  println("  ${cfg.exitPoint} [rank=max];")
  cfg.nodes.forEach { (nodeName, nodeBlock) ->
    val writer = StringWriter()
    val printer = PrintWriter(writer)
    printer.printSSA(nodeBlock.ssas, "")
    printer.flush()
    val labelText = "$nodeName\\n" + writer.toString().split('\n').joinToString("\\l")
    println("  $nodeName [shape=rectangle, fontname=\"monospace\", label=\"$labelText\"]")
  }
  cfg.edges.forEach { edge ->
    when (edge) {
      is SSANormalFlowEdge -> {
        println("  ${edge.startNode} -> ${edge.endNode};")
      }
      is SSAAfterYieldEdge -> TODO()
    }
  }
  println("}")
}