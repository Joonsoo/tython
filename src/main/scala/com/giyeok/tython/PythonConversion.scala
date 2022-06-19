package com.giyeok.tython

import com.giyeok.tython.AsdlConversion.Name

import scala.collection.mutable

class PythonConversion(val names: Map[String, Name]) {
  def escapeStr(str: String) = s""""$str""""

  private val keywords = Set("if", "for", "try", "while", "return", "break", "continue", "is", "in", "with",
    "raise", "assert", "import", "global", "nonlocal", "pass", "lambda", "await", "yield", "del", "and", "or", "not")

  def getattr(obj: String, attr: String): String =
    if (keywords.contains(attr)) s"getattr($obj, ${escapeStr(attr)})" else s"$obj.$attr"

  def setattr(obj: String, attr: String, value: String): String =
    if (keywords.contains(attr)) s"setattr($obj, ${escapeStr(attr)}, $value)" else s"$obj.$attr = $value"

  def isPlainType(typeName: String) = typeName match {
    case "identifier" | "string" | "int" | "constant" => true
    case _ => false
  }

  def generateDataClassConverter(name: String, pbModuleName: String, params: List[AsdlAst.Param]): String = {
    val builder = new mutable.StringBuilder()

    val nameWords = names(name)

    def setStmt(param: AsdlAst.Param, target: String, source: String): String = {
      if (param.typeName == "constant") {
        val v = getattr(source, param.name)
        setattr(target, param.name, "f\"{type(" + v + ")}{str(" + v + ")}\"")
      } else if (isPlainType(param.typeName)) {
        setattr(target, param.name, getattr(source, param.name))
      } else {
        val typeName = names(param.typeName)
        s"apply_${typeName.snakeCase}(${getattr(source, param.name)}, ${getattr(target, param.name)})"
      }
    }

    builder.append(s"def apply_${nameWords.snakeCase}(node: ast.$name, proto: $pbModuleName.${nameWords.upperCamelCase}):\n")
    if (params.isEmpty) {
      builder.append("  pass")
    }
    params.foreach { param =>
      param.typeAttr match {
        case com.giyeok.tython.AsdlAst.TypeAttr.PLAIN =>
          builder.append(s"  ${setStmt(param, "proto", "node")}\n")
        case com.giyeok.tython.AsdlAst.TypeAttr.OPTIONAL =>
          builder.append(s"  if ${getattr("node", param.name)} is not None:\n")
          builder.append(s"    ${setStmt(param, "proto", "node")}\n")
        case com.giyeok.tython.AsdlAst.TypeAttr.REPEATED =>
          builder.append(s"  for x in ${getattr("node", param.name)}:\n")
          if (isPlainType(param.typeName)) {
            builder.append(s"    ${getattr("proto", param.name)}.append(x)\n")
          } else {
            builder.append(s"    v = ${getattr("proto", param.name)}.add()\n")
            val typeName = names(param.typeName)
            builder.append(s"    apply_${typeName.snakeCase}(x, v)\n")
          }
      }
    }
    builder.append(s"\n")

    builder.toString()
  }

  def generatePythonConverter(moduleDef: AsdlAst.ModuleDef, pbModuleName: String): String = {
    val builder = new mutable.StringBuilder()

    builder.append(s"import ast\n")
    builder.append(s"import $pbModuleName\n")
    builder.append(s"\n")

    moduleDef.defs.foreach { d =>
      val name = names(d.name)
      val attrParams = d.attrs.map(_.attrs).getOrElse(List())
      d.body match {
        case AsdlAst.SealedClassDefs(subs) =>
          //          builder.append(s"def convert_${name.snakeCase}(node: ast.${d.name}) -> $pbModuleName.${name.upperCamelCase}:\n")
          //          builder.append(s"  proto = $pbModuleName.${name.upperCamelCase}()\n")
          //          builder.append(s"  apply_${name.upperCamelCase}(node, proto)\n")
          //          builder.append(s"  return proto\n")
          //          builder.append(s"\n")

          builder.append(s"def apply_${name.snakeCase}(node: ast.${d.name}, proto: $pbModuleName.${name.upperCamelCase}):\n")
          builder.append(s"  match node:\n")
          subs.foreach { sub =>
            val subName = names(sub.name)
            builder.append(s"    case ast.${sub.name}():\n")
            builder.append(s"      ${getattr("proto", subName.snakeCase)}.SetInParent()\n")
            builder.append(s"      apply_${subName.snakeCase}(node, ${getattr("proto", subName.snakeCase)})\n")
          }
          builder.append(s"\n")
          subs.foreach { sub =>
            builder.append(generateDataClassConverter(sub.name, pbModuleName, sub.params.getOrElse(List()) ++ attrParams))
          }
        case AsdlAst.TupleDef(body) =>
          builder.append(generateDataClassConverter(d.name, pbModuleName, body ++ attrParams))
      }
    }
    builder.toString
  }
}
