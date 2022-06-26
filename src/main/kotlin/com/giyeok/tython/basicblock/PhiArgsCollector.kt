package com.giyeok.tython.basicblock

import com.giyeok.tython.parse.ParseModules
import com.giyeok.tython.ssa.*
import java.io.PrintWriter

class PhiArgsCollector(val cfg: SSAControlFlowGraph, val scope: VariablesScope) {
  data class LocalsMap(val locals: Map<String, SSAVar>)

  private val localNames = scope.pureLocals()

  fun traverseBlock(ssas: List<SSA>, locals: MutableMap<String, SSAVar>): LocalsMap {
    ssas.forEach { ssa ->
      when (ssa) {
        is StoreName ->
          if (localNames.contains(ssa.dest)) {
            locals[ssa.dest] = ssa.source
          }
        else -> {}
      }
    }
    return LocalsMap(locals.toMap())
  }

  // local 변수 이름 -> SSAVar set
  data class PhisMap(val phis: Map<String, Set<SSAVar>>)

  fun visitBlock(
    nodeName: String,
    // 블럭 노드 이름 -> local 변수 이름 -> SSAVar set
    phiMaps: MutableMap<String, MutableMap<String, MutableSet<SSAVar>>>,
    visited: MutableSet<String>,
  ) {
    val node = cfg.nodes.getValue(nodeName)
    val initialLocals = mutableMapOf<String, SSAVar>()
//    phiMaps[nodeName]?.forEach { (varName, phis) ->
//      if (phis.size == 1) {
//        initialLocals[varName] = phis.first()
//      } else {
//        initialLocals[varName] = SSAVar("v_${nodeName}_$varName")
//      }
//    }
    val exitLocalsMap = traverseBlock(node.ssas, initialLocals)
    val nextBlocks: Set<String> = if (node.ssas.isEmpty()) setOf() else {
      when (val last = node.ssas.last()) {
        is Branch -> setOf(last.branchIfTrue, last.branchIfFalse)
        is BranchByIter -> setOf(last.branchIfHasNext, last.branchIfEmpty)
        is Jump -> setOf(last.jumpTo)
        is RaiseStmt, is ReturnStmt -> setOf()
        else -> TODO()
      }
    }
    nextBlocks.forEach { nextBlock ->
      val nextBlockPhis = phiMaps.getOrPut(nextBlock) { mutableMapOf() }
      exitLocalsMap.locals.forEach { (name, lastValue) ->
        val phi = nextBlockPhis.getOrPut(name) { mutableSetOf() }
        phi.add(lastValue)
      }
      if (!visited.contains(nextBlock)) {
        visited.add(nextBlock)
        visitBlock(nextBlock, phiMaps, visited)
      }
    }
  }

  fun test(): Map<String, PhisMap> {
    val phiMaps = mutableMapOf<String, MutableMap<String, MutableSet<SSAVar>>>()

    visitBlock(cfg.entryPoint, phiMaps, mutableSetOf())
    return phiMaps.mapValues { (_, nodePhis) -> PhisMap(nodePhis.mapValues { it.value.toSet() }) }
  }
}

fun main() {
  val srcs = mapOf(
    "builtins" to "builtins.py",
    "a" to "a.py",
    "b" to "b.py",
    "c" to "c.py",
  )
  val moduleAsts = ParseModules.INSTNACE.load(srcs)

  val globalScope = VariablesScope()
  val block = SSAGen(SSAVarIssuer("%")).traverseNewBlock(moduleAsts.getValue("a").body, globalScope)

  traverseVarRefs(block, globalScope)

  PrintWriter(System.out).printSSA(block, "")
  println("$globalScope (pureLocals=${globalScope.pureLocals()})")
  println()
  val cfg = SSAControlFlowGraphGen().transform(block.stmts, null)

  cfg.nodes.entries.sortedBy { it.key }.forEach { (id, basicBlock) ->
    println("BB $id:")
    basicBlock.ssas.forEach { ssa ->
      System.console().writer().printSSA(ssa, "  ")
    }
  }
  PrintWriter(System.out).printCfgDotGraph(cfg)
  val phis = PhiArgsCollector(cfg, globalScope).test()
  println(phis)
}
