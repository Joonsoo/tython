// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface GeneratorExpOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.GeneratorExp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr elt = 1;</code>
   * @return Whether the elt field is set.
   */
  boolean hasElt();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr elt = 1;</code>
   * @return The elt.
   */
  com.giyeok.tython.proto.AbstractExpr getElt();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr elt = 1;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getEltOrBuilder();

  /**
   * <code>repeated .com.giyeok.tython.proto.Comprehension generators = 2;</code>
   */
  java.util.List<com.giyeok.tython.proto.Comprehension> 
      getGeneratorsList();
  /**
   * <code>repeated .com.giyeok.tython.proto.Comprehension generators = 2;</code>
   */
  com.giyeok.tython.proto.Comprehension getGenerators(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.Comprehension generators = 2;</code>
   */
  int getGeneratorsCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.Comprehension generators = 2;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.ComprehensionOrBuilder> 
      getGeneratorsOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.Comprehension generators = 2;</code>
   */
  com.giyeok.tython.proto.ComprehensionOrBuilder getGeneratorsOrBuilder(
      int index);

  /**
   * <code>int32 lineno = 3;</code>
   * @return The lineno.
   */
  int getLineno();

  /**
   * <code>int32 col_offset = 4;</code>
   * @return The colOffset.
   */
  int getColOffset();

  /**
   * <code>optional int32 end_lineno = 5;</code>
   * @return Whether the endLineno field is set.
   */
  boolean hasEndLineno();
  /**
   * <code>optional int32 end_lineno = 5;</code>
   * @return The endLineno.
   */
  int getEndLineno();

  /**
   * <code>optional int32 end_col_offset = 6;</code>
   * @return Whether the endColOffset field is set.
   */
  boolean hasEndColOffset();
  /**
   * <code>optional int32 end_col_offset = 6;</code>
   * @return The endColOffset.
   */
  int getEndColOffset();
}
