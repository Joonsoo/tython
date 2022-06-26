package com.giyeok.tython.ssa

import com.giyeok.tython.parse.ParseModules

fun main() {
  val srcs = mapOf(
    "builtins" to "builtins.py",
    "a" to "a.py",
    "b" to "b.py",
    "c" to "c.py",
  )
  val moduleAsts = ParseModules.INSTNACE.load(srcs)

  val block = SSAGen(SSAVarIssuer("%"))
    .traverseNewBlock(moduleAsts.getValue("a").body, VariablesScope())

  block.stmts.forEach {
    System.console().writer().printSSA(it, "")
  }
}
