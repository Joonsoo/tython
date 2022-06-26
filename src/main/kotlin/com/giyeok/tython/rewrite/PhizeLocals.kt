package com.giyeok.tython.rewrite

import com.giyeok.tython.basicblock.*
import com.giyeok.tython.ssa.*

// CFG를 받아서 local variable들을 LoadName, StoreName하지 않도록 하고 필요한 phi op 추가
// -> StoreName은 locals()나 eval()때문에 해야할 수도?
// 이 때 각 local 변수의 값을 갖고 있을 수 있는 SSAVar들의 목록을 저장해두어야 분석할 때 쓸 수 있을듯?
class PhizeLocals(
  val graph: SSAControlFlowGraph,
  val locals: Set<String>,
  val argNames: Set<String>
) {
  val varIssuer = SSAVarIssuer("p", allVarsOf(graph))

  // 각 노드에 추가되어야 하는 phi op 목록
  val nodePhisMap = mutableMapOf<String, MutableMap<String, Pair<SSAVar, MutableSet<SSAVar>>>>()

  // 각 노드가 종료되는 시점의 local name -> SSAVar 매핑
  val exitLocalMaps = mutableMapOf<String, Map<String, SSAVar>>()

  fun traverseBlock(mutable: MutableControlFlowGraph, nodeName: String) {
    val node = mutable.nodes.getValue(nodeName)
    val phisMap = nodePhisMap.getOrPut(nodeName) { mutableMapOf() }

    val replacing =
      phisMap.flatMap { phi -> phi.value.second.map { it to phi.value.first } }.toMap()
        .toMutableMap()

    phisMap.values.forEach { it.second.clear() }

    val incomingLocalMaps =
      mutable.incomingEdgesTo(nodeName).map { exitLocalMaps.getOrElse(it.startNode) { mapOf() } }
    incomingLocalMaps.forEach { incomingMap ->
      incomingMap.forEach { (varName, ssaVar) ->
        if (!phisMap.containsKey(varName)) {
          val newVar = varIssuer.newVar()
          phisMap[varName] = Pair(newVar, mutableSetOf(ssaVar))
//          replacing[ssaVar] = newVar
        } else {
          phisMap.getValue(varName).second.add(ssaVar)
//          replacing[ssaVar] = phisMap.getValue(varName).first
        }
      }
    }
    val localMaps = phisMap.mapValues { it.value.first }.toMutableMap()
    val newSSAs: List<SSABasic> = node.ssas.mapNotNull {
      when (val r = it.replaceVars(replacing)) {
        is AssignVar -> {
          replacing[r.dest] = r.source
          r
        }
        is LoadArgument -> {
          localMaps[r.argName] = r.dest
          r
        }
        is LoadName -> {
          val localVar = localMaps[r.source]
          if (localVar != null) {
            // remove this SSA and replace the `it.dest` to localVar
            replacing[r.dest] = localVar
            null
          } else {
            r
          }
        }
        is StoreLocalName -> {
          localMaps[r.dest] = r.source
          r
        }
        is StoreName -> {
          if (locals.contains(r.dest) || argNames.contains(r.dest)) {
            localMaps[r.dest] = r.source
            StoreLocalName(r.dest, r.source)
          } else {
            r
          }
        }
        else -> {
          // do nothing
          r
        }
      }
    }
    mutable.nodes[nodeName] = MutableBasicBlock(newSSAs.toMutableList())
    if (exitLocalMaps[nodeName] != localMaps) {
      // localMaps에 변경이 있을 때만 outgoing node들을 방문
      exitLocalMaps[nodeName] = localMaps
      mutable.outgoingEdgesFrom(nodeName).forEach {
        traverseBlock(mutable, it.endNode)
      }
    }
  }

  fun run(): SSAControlFlowGraph {
    val mutable = graph.toMutable()

    mutable.prependStmts(argNames.map { LoadArgument(varIssuer.newVar(), it) })

    traverseBlock(mutable, mutable.entryPoint)

    // phi의 대상이 한개인 phi들은 replace로 정리
    val repls = mutableMapOf<SSAVar, SSAVar>()
    var changed = true
    while (changed) {
      changed = false
      nodePhisMap.forEach { (nodeName, phis) ->
        phis.forEach { (varName, phi) ->
          val rtargets = phi.second.map { it.repl(repls) }.toSet() - phi.first
          if (rtargets.size == 1) {
            if (repls[phi.first] != rtargets.first()) {
              changed = true
            }
            repls[phi.first] = rtargets.first()
          }
        }
      }
    }
    nodePhisMap.forEach { (nodeName, phis) ->
      val phiOps = phis.mapNotNull { (varName, phi) ->
        val (phied, targets) = phi
        val reducedTargets = targets.map { it.repl(repls) }.toSet()
        if (reducedTargets.size > 1) {
          Phi(phied, reducedTargets.sortedBy { it.name }, varName)
        } else null
      }
      mutable.nodes.getValue(nodeName).ssas.addAll(0, phiOps)
    }
    mutable.nodes.forEach { (nodeName, nodeBlock) ->
      mutable.nodes[nodeName] = MutableBasicBlock(nodeBlock.ssas.map {
        it.replaceVars(repls)
      }.toMutableList())
    }

    return mutable.toGraph()
  }
}
