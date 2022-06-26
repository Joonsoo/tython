package com.giyeok.tython.basicblock

import com.giyeok.tython.parse.ParseModules
import com.giyeok.tython.rewrite.PhizeLocals
import com.giyeok.tython.ssa.*
import java.io.PrintWriter

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
  val block = SSAGen(SSAVarIssuer("%")).traverseNewBlock(moduleAsts.getValue("a").body, globalScope)

  traverseVarRefs(block, globalScope)

  val p = PrintWriter(System.out)
  p.printSSA(block, "")
  println("$globalScope (pureLocals=${globalScope.pureLocals()})")
  println()
  val cfg = SSAControlFlowGraphGen().transform(block.stmts, null)

  p.printCfg(cfg)

  val f = PhizeLocals(cfg, globalScope.pureLocals(), setOf())
  val newCfg = f.run()
  p.printCfgDotGraphWithCode(newCfg)

  p.flush()
}
