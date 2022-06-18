package com.giyeok.tython

import com.giyeok.jparser.Inputs
import com.giyeok.jparser.NGrammar
import com.giyeok.jparser.ParseForest
import com.giyeok.jparser.ParseResultTree.BindNode
import com.giyeok.jparser.ParseResultTree.Node
import com.giyeok.jparser.ParseResultTree.SequenceNode
import com.giyeok.jparser.ParseResultTree.TerminalNode
import com.giyeok.jparser.ParsingErrors
import com.giyeok.jparser.milestone.MilestoneParser
import com.giyeok.jparser.milestone.MilestoneParserContext
import com.giyeok.jparser.nparser.ParseTreeUtil
import com.giyeok.jparser.nparser.ParseTreeUtil.unrollRepeat0
import com.giyeok.jparser.nparser.ParseTreeUtil.unrollRepeat1
import com.giyeok.jparser.proto.GrammarProto
import com.giyeok.jparser.proto.GrammarProtobufConverter
import com.giyeok.jparser.proto.MilestoneParserDataProto
import com.giyeok.jparser.proto.MilestoneParserProtobufConverter
import com.giyeok.jparser.utils.FileUtil.readFileBytes
import java.util.Base64

object AsdlAst {

  sealed trait WithParseNode { val parseNode: Node }
  case class Attributes(attrs: List[Param])(override val parseNode: Node) extends WithParseNode
  case class ModuleDef(name: String, defs: List[SuperClassDef])(override val parseNode: Node) extends WithParseNode
  case class Param(typeName: String, typeAttr: TypeAttr.Value, name: String)(override val parseNode: Node) extends WithParseNode
  case class SealedClassDefs(subs: List[SubClassDef])(override val parseNode: Node) extends SuperClassDefBody with WithParseNode
  case class SubClassDef(name: String, params: Option[List[Param]])(override val parseNode: Node) extends WithParseNode
  case class SuperClassDef(name: String, body: SuperClassDefBody, attrs: Option[Attributes])(override val parseNode: Node) extends WithParseNode
  sealed trait SuperClassDefBody extends WithParseNode
  case class TupleDef(body: List[Param])(override val parseNode: Node) extends SuperClassDefBody with WithParseNode
  object TypeAttr extends Enumeration { val OPTIONAL, PLAIN, REPEATED = Value }

  def matchAttributesDef(node: Node): Attributes = {
    val BindNode(v1, v2) = node
    val v6 = v1.id match {
      case 116 =>
        val v3 = v2.asInstanceOf[SequenceNode].children(2)
        val BindNode(v4, v5) = v3
        assert(v4.id == 80)
        Attributes(matchParams(v5))(v2)
    }
    v6
  }

  def matchDefs(node: Node): ModuleDef = {
    val BindNode(v7, v8) = node
    val v12 = v7.id match {
      case 3 =>
        val v9 = v8.asInstanceOf[SequenceNode].children(1)
        val BindNode(v10, v11) = v9
        assert(v10.id == 47)
        matchModuleDef(v11)
    }
    v12
  }

  def matchModuleDef(node: Node): ModuleDef = {
    val BindNode(v13, v14) = node
    val v33 = v13.id match {
      case 48 =>
        val v15 = v14.asInstanceOf[SequenceNode].children(2)
        val BindNode(v16, v17) = v15
        assert(v16.id == 67)
        val v18 = v14.asInstanceOf[SequenceNode].children(6)
        val BindNode(v19, v20) = v18
        assert(v19.id == 69)
        val v21 = v14.asInstanceOf[SequenceNode].children(7)
        val v22 = unrollRepeat0(v21).map { elem =>
        val BindNode(v23, v24) = elem
        assert(v23.id == 128)
        val BindNode(v25, v26) = v24
        val v32 = v25.id match {
        case 129 =>
          val BindNode(v27, v28) = v26
          assert(v27.id == 130)
          val v29 = v28.asInstanceOf[SequenceNode].children(1)
          val BindNode(v30, v31) = v29
          assert(v30.id == 69)
          matchSuperClassDef(v31)
      }
        v32
        }
        ModuleDef(matchName(v17), List(matchSuperClassDef(v20)) ++ v22)(v14)
    }
    v33
  }

  def matchName(node: Node): String = {
    val BindNode(v34, v35) = node
    val v50 = v34.id match {
      case 59 =>
        val v36 = v35.asInstanceOf[SequenceNode].children.head
        val BindNode(v37, v38) = v36
        assert(v37.id == 60)
        val BindNode(v39, v40) = v38
        assert(v39.id == 61)
        val BindNode(v41, v42) = v40
        val v49 = v41.id match {
        case 62 =>
          val BindNode(v43, v44) = v42
          assert(v43.id == 63)
          val v45 = v44.asInstanceOf[SequenceNode].children.head
          val v46 = unrollRepeat1(v45).map { elem =>
          val BindNode(v47, v48) = elem
          assert(v47.id == 65)
          v48.asInstanceOf[TerminalNode].input.asInstanceOf[Inputs.Character].char
          }
          v46.map(x => x.toString).mkString("")
      }
        v49
    }
    v50
  }

  def matchParam(node: Node): Param = {
    val BindNode(v51, v52) = node
    val v81 = v51.id match {
      case 84 =>
        val v53 = v52.asInstanceOf[SequenceNode].children.head
        val BindNode(v54, v55) = v53
        assert(v54.id == 67)
        val v57 = v52.asInstanceOf[SequenceNode].children(1)
        val BindNode(v58, v59) = v57
        assert(v58.id == 85)
        val BindNode(v60, v61) = v59
        val v77 = v60.id match {
        case 96 =>
        None
        case 86 =>
          val BindNode(v62, v63) = v61
          val v76 = v62.id match {
          case 87 =>
            val BindNode(v64, v65) = v63
            assert(v64.id == 88)
            val v66 = v65.asInstanceOf[SequenceNode].children(1)
            val BindNode(v67, v68) = v66
            assert(v67.id == 89)
            val BindNode(v69, v70) = v68
            val v75 = v69.id match {
            case 90 =>
              val BindNode(v71, v72) = v70
              assert(v71.id == 91)
              TypeAttr.REPEATED
            case 93 =>
              val BindNode(v73, v74) = v70
              assert(v73.id == 94)
              TypeAttr.OPTIONAL
          }
            v75
        }
          Some(v76)
      }
        val v56 = v77
        val v78 = v52.asInstanceOf[SequenceNode].children(3)
        val BindNode(v79, v80) = v78
        assert(v79.id == 67)
        Param(matchName(v55), if (v56.isDefined) v56.get else TypeAttr.PLAIN, matchName(v80))(v52)
    }
    v81
  }

  def matchParams(node: Node): List[Param] = {
    val BindNode(v82, v83) = node
    val v99 = v82.id match {
      case 81 =>
        val v84 = v83.asInstanceOf[SequenceNode].children(2)
        val BindNode(v85, v86) = v84
        assert(v85.id == 83)
        val v87 = v83.asInstanceOf[SequenceNode].children(3)
        val v88 = unrollRepeat0(v87).map { elem =>
        val BindNode(v89, v90) = elem
        assert(v89.id == 99)
        val BindNode(v91, v92) = v90
        val v98 = v91.id match {
        case 100 =>
          val BindNode(v93, v94) = v92
          assert(v93.id == 101)
          val v95 = v94.asInstanceOf[SequenceNode].children(3)
          val BindNode(v96, v97) = v95
          assert(v96.id == 83)
          matchParam(v97)
      }
        v98
        }
        List(matchParam(v86)) ++ v88
    }
    v99
  }

  def matchSubClassDef(node: Node): SubClassDef = {
    val BindNode(v100, v101) = node
    val v119 = v100.id match {
      case 75 =>
        val v102 = v101.asInstanceOf[SequenceNode].children.head
        val BindNode(v103, v104) = v102
        assert(v103.id == 67)
        val v105 = v101.asInstanceOf[SequenceNode].children(1)
        val BindNode(v106, v107) = v105
        assert(v106.id == 76)
        val BindNode(v108, v109) = v107
        val v118 = v108.id match {
        case 96 =>
        None
        case 77 =>
          val BindNode(v110, v111) = v109
          val v117 = v110.id match {
          case 78 =>
            val BindNode(v112, v113) = v111
            assert(v112.id == 79)
            val v114 = v113.asInstanceOf[SequenceNode].children(1)
            val BindNode(v115, v116) = v114
            assert(v115.id == 80)
            matchParams(v116)
        }
          Some(v117)
      }
        SubClassDef(matchName(v104), v118)(v101)
    }
    v119
  }

  def matchSuperClassDef(node: Node): SuperClassDef = {
    val BindNode(v120, v121) = node
    val v142 = v120.id match {
      case 70 =>
        val v122 = v121.asInstanceOf[SequenceNode].children.head
        val BindNode(v123, v124) = v122
        assert(v123.id == 67)
        val v125 = v121.asInstanceOf[SequenceNode].children(4)
        val BindNode(v126, v127) = v125
        assert(v126.id == 72)
        val v128 = v121.asInstanceOf[SequenceNode].children(5)
        val BindNode(v129, v130) = v128
        assert(v129.id == 111)
        val BindNode(v131, v132) = v130
        val v141 = v131.id match {
        case 96 =>
        None
        case 112 =>
          val BindNode(v133, v134) = v132
          val v140 = v133.id match {
          case 113 =>
            val BindNode(v135, v136) = v134
            assert(v135.id == 114)
            val v137 = v136.asInstanceOf[SequenceNode].children(1)
            val BindNode(v138, v139) = v137
            assert(v138.id == 115)
            matchAttributesDef(v139)
        }
          Some(v140)
      }
        SuperClassDef(matchName(v124), matchSuperClassDefBody(v127), v141)(v121)
    }
    v142
  }

  def matchSuperClassDefBody(node: Node): SuperClassDefBody = {
    val BindNode(v143, v144) = node
    val v163 = v143.id match {
      case 73 =>
        val v145 = v144.asInstanceOf[SequenceNode].children.head
        val BindNode(v146, v147) = v145
        assert(v146.id == 74)
        val v148 = v144.asInstanceOf[SequenceNode].children(1)
        val v149 = unrollRepeat0(v148).map { elem =>
        val BindNode(v150, v151) = elem
        assert(v150.id == 106)
        val BindNode(v152, v153) = v151
        val v159 = v152.id match {
        case 107 =>
          val BindNode(v154, v155) = v153
          assert(v154.id == 108)
          val v156 = v155.asInstanceOf[SequenceNode].children(3)
          val BindNode(v157, v158) = v156
          assert(v157.id == 74)
          matchSubClassDef(v158)
      }
        v159
        }
        SealedClassDefs(List(matchSubClassDef(v147)) ++ v149)(v144)
      case 110 =>
        val v160 = v144.asInstanceOf[SequenceNode].children.head
        val BindNode(v161, v162) = v160
        assert(v161.id == 80)
        TupleDef(matchParams(v162))(v144)
    }
    v163
  }

  def matchStart(node: Node): ModuleDef = {
    val BindNode(start, BindNode(_, body)) = node
    assert(start.id == 1)
    matchDefs(body)
  }

    val milestoneParserData = MilestoneParserProtobufConverter.convertProtoToMilestoneParserData(
      MilestoneParserDataProto.MilestoneParserData.parseFrom(readFileBytes("/home/joonsoo/Documents/workspace/tython/src/main/resources/parserdata.pb")))

val milestoneParser = new MilestoneParser(milestoneParserData)

def parse(text: String): Either[ParseForest, ParsingErrors.ParsingError] =
  milestoneParser.parseAndReconstructToForest(text)

def parseAst(text: String): Either[ModuleDef, ParsingErrors.ParsingError] =
  parse(text) match {
    case Left(forest) => Left(matchStart(forest.trees.head))
    case Right(error) => Right(error)
  }


}
