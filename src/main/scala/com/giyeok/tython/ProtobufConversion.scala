package com.giyeok.tython

import com.giyeok.tython.AsdlConversion.Name

import scala.collection.mutable

class ProtobufConversion(val names: Map[String, Name]) {

  def generateProtobufDef(moduleDef: AsdlAst.ModuleDef): String = {
    val builder = new mutable.StringBuilder()

    builder.append(
      """syntax = "proto3";
        |
        |package com.giyeok.tython.proto;
        |
        |option java_multiple_files = true;
        |""".stripMargin)

    def generateMessageDef(messageName: Name, params: List[AsdlAst.Param], attrs: Option[AsdlAst.Attributes]): String = {
      val builder = new mutable.StringBuilder()
      builder.append(s"message ${messageName.upperCamelCase} {\n")
      val attrParams = attrs.map(_.attrs).getOrElse(List())
      (params ++ attrParams).zipWithIndex.foreach { case (param, index) =>
        val attrStr = param.typeAttr match {
          case com.giyeok.tython.AsdlAst.TypeAttr.OPTIONAL => "optional "
          case com.giyeok.tython.AsdlAst.TypeAttr.REPEATED => "repeated "
          case com.giyeok.tython.AsdlAst.TypeAttr.PLAIN => ""
        }
        val typeName = param.typeName match {
          case "identifier" | "string" => "string"
          case "int" => "int32"
          case "constant" => "string"
          case e => names(e).upperCamelCase
        }
        builder.append(s"  $attrStr$typeName ${param.name} = ${index + 1};\n")
      }
      builder.append(s"}\n")
      builder.toString()
    }

    moduleDef.defs.foreach { d =>
      val name = names(d.name)
      d.body match {
        case AsdlAst.SealedClassDefs(subs) =>
          builder.append(s"message ${name.upperCamelCase} {\n")
          builder.append(s"  oneof ${name.snakeCase} {\n")
          subs.zipWithIndex.foreach { case (sub, index) =>
            val subName = names(sub.name)
            builder.append(s"    ${subName.upperCamelCase} ${subName.snakeCase} = ${index + 1};\n")
          }
          builder.append(s"  }\n")
          builder.append(s"}\n")
          subs.foreach { sub =>
            builder.append(generateMessageDef(names(sub.name), sub.params.getOrElse(List()), d.attrs))
          }
        case AsdlAst.TupleDef(body) =>
          builder.append(generateMessageDef(name, body, d.attrs))
      }
      builder.append("\n")
    }
    builder.toString()
  }
}
