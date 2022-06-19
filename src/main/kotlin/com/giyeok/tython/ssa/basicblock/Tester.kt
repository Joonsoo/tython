package com.giyeok.tython.ssa.basicblock

import com.giyeok.tython.parse.ParseModules
import com.giyeok.tython.ssa.*

fun main() {
  val srcs = mapOf(
    "builtins" to "builtins.py",
    "a" to "a.py",
    "b" to "b.py",
    "c" to "c.py",
  )
  val moduleAsts = ParseModules.INSTNACE.load(srcs)

//  val args = (moduleAsts.getValue("a").body[0] as FunctionDef).args
//  println("posonlyargs: ${args.posonlyargs}")
//  println("args: ${args.args}")
//  println("vararg: ${args.vararg}")
//  println("kwonlyargs: ${args.kwonlyargs}")
//  println("kw_defaults: ${args.kw_defaults}")
//  println("kwarg: ${args.kwarg}")
//  println("defaults: ${args.defaults}")


  val globalScope = VariablesScope()
  val block = SSAGen().traverseNewBlock(moduleAsts.getValue("a").body, globalScope)

  traverseVarRefs(block, globalScope)

  printSSA(block, "")
  println("$globalScope (pureLocals=${globalScope.pureLocals()})")
  println()
  val cfg = SSAControlFlowGraphGen().transform(block.stmts, null)

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
      is SSAAfterYieldEdge -> TODO()
    }
  }
  println("}")
}
