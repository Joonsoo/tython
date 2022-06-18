package com.giyeok.tython

import com.giyeok.tython.AsdlConversion.{Name, words}

import scala.collection.mutable

class KotlinConversion(val names: Map[String, Name]) {
  implicit class BypassKeywordName(str: String) {
    def bypassKeyword: String = str match {
      case "if" | "for" | "try" | "while" | "return" | "break" | "continue" | "is" | "in" => s"`$str`"
      case _ => str
    }
  }

  def paramGen(param: AsdlAst.Param): String = {
    val builder = new mutable.StringBuilder()

    def conversionExpr(source: String): String =
      param.typeName match {
        case "identifier" | "string" | "int" => source
        case "constant" => source // TODO
        case e => s"${names(e).upperCamelCase.bypassKeyword}.fromProto($source)"
      }

    val paramName = new Name(words(param.name))
    param.typeAttr match {
      case com.giyeok.tython.AsdlAst.TypeAttr.OPTIONAL =>
        builder.append(
          s"if (proto.has${paramName.upperCamelCase}()) ${conversionExpr(s"proto.${paramName.lowerCamelCase.bypassKeyword}")} else null"
        )
      case com.giyeok.tython.AsdlAst.TypeAttr.PLAIN =>
        builder.append(conversionExpr(s"proto.${paramName.lowerCamelCase.bypassKeyword}"))
      case com.giyeok.tython.AsdlAst.TypeAttr.REPEATED =>
        builder.append(s"proto.${paramName.lowerCamelCase.bypassKeyword}List.map { ${conversionExpr("it")} }")
    }
    builder.toString()
  }

  def generateKotlinDef(moduleDef: AsdlAst.ModuleDef, packageName: String): String = {
    val builder = new mutable.StringBuilder()

    builder.append(s"package $packageName\n")
    builder.append(s"\n")
    builder.append(s"data class Location(val lineno: Int, val colOffset: Int, val endLineno: Int?, val endColOffset: Int?)\n")
    builder.append(s"\n")

    def generateDataClassDef(className: Name, params: List[AsdlAst.Param], superClass: Option[Name], attrs: Option[AsdlAst.Attributes], locationOverriding: Boolean): String = {
      val builder = new mutable.StringBuilder()
      val isObject = params.isEmpty && attrs.isEmpty
      if (isObject) {
        builder.append(s"object ${className.upperCamelCase.bypassKeyword}")
      } else {
        builder.append(s"data class ${className.upperCamelCase.bypassKeyword}(\n")
        params.foreach { param =>
          val typeName = param.typeName match {
            case "identifier" | "string" => "String"
            case "int" => "Int"
            case "constant" => "String"
            case e => names(e).upperCamelCase.bypassKeyword
          }
          val typeStr = param.typeAttr match {
            case com.giyeok.tython.AsdlAst.TypeAttr.OPTIONAL => s"$typeName?"
            case com.giyeok.tython.AsdlAst.TypeAttr.REPEATED => s"List<$typeName>"
            case com.giyeok.tython.AsdlAst.TypeAttr.PLAIN => typeName
          }
          builder.append(s"  val ${param.name}: $typeStr,\n")
        }
        if (attrs.isDefined) {
          if (locationOverriding) {
            builder.append(s"  override val location: Location,")
          } else {
            builder.append(s"  val location: Location,")
          }
        }
        builder.append(s")")
      }
      if (superClass.isEmpty) {
        builder.append(" {\n")
      } else {
        builder.append(s": ${superClass.get.upperCamelCase.bypassKeyword}() {\n")
      }
      if (!isObject) {
        builder.append("companion object {")
      }
      builder.append(s"  fun fromProto(proto: $packageName.proto.${className.upperCamelCase.bypassKeyword}): ${className.upperCamelCase.bypassKeyword} =\n")
      if (isObject) {
        builder.append(s"    ${className.upperCamelCase.bypassKeyword}\n")
      } else {
        builder.append(s"    ${className.upperCamelCase.bypassKeyword}(")
        params.zipWithIndex.foreach { case (param, index) =>
          builder.append(s"      ${paramGen(param)},\n")
        }
        if (attrs.isDefined) {
          builder.append(s"      Location(")
          attrs.get.attrs.zipWithIndex.foreach { case (attr, index) =>
            builder.append(s"${paramGen(attr)}")
            if (index + 1 < attrs.get.attrs.size) {
              builder.append(", ")
            } else {
              builder.append(")\n")
            }
          }
        }
        builder.append(")\n")
      }
      if (!isObject) {
        builder.append("}")
      }
      builder.append("}\n")
      builder.toString()
    }

    moduleDef.defs.foreach { d =>
      d.body match {
        case AsdlAst.SealedClassDefs(subs) =>
          val name = names(d.name)
          builder.append(s"sealed class ${name.upperCamelCase.bypassKeyword} {\n")
          builder.append(s"  companion object {\n")
          builder.append(s"    fun fromProto(proto: $packageName.proto.${name.upperCamelCase.bypassKeyword}): ${name.upperCamelCase.bypassKeyword} =\n")
          builder.append(s"      when(proto.${name.lowerCamelCase.bypassKeyword}Case) {\n")
          subs.foreach { sub =>
            val subName = names(sub.name)
            builder.append(s"        $packageName.proto.${name.upperCamelCase.bypassKeyword}.${name.upperCamelCase.bypassKeyword}Case.${subName.upperSnakeCase.bypassKeyword} ->\n")
            builder.append(s"          ${subName.upperCamelCase.bypassKeyword}.fromProto(proto.${subName.lowerCamelCase.bypassKeyword})\n")
          }
          builder.append(s"        else -> TODO()\n")
          builder.append(s"      }\n")
          builder.append(s"  }\n")
          if (d.attrs.isDefined) {
            builder.append(s"  abstract val location: Location")
          }
          builder.append(s"}\n")
          subs.foreach { sub =>
            builder.append(generateDataClassDef(names(sub.name), sub.params.getOrElse(List()), Some(name), d.attrs, true))
          }
        case AsdlAst.TupleDef(body) =>
          builder.append(generateDataClassDef(names(d.name), body, None, d.attrs, false))
      }
      builder.append("\n")
    }
    builder.toString()
  }
}
