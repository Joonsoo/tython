package com.giyeok.tython.ssa

class SSAVarIssuer(val prefix: String = "", initialVars: Set<SSAVar> = setOf()) {
  private var idCounter = 0
  private val variables = initialVars.toMutableSet()

  fun newVar(): SSAVar {
    idCounter += 1
    var newVar = SSAVar("$prefix$idCounter")
    while (variables.contains(newVar)) {
      idCounter += 1
      newVar = SSAVar("$prefix$idCounter")
    }
    variables.add(newVar)
    return newVar
  }

  fun issuedVariables() = variables.toSet()
}
