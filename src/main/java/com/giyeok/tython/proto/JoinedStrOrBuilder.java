// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface JoinedStrOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.JoinedStr)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr values = 1;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractExpr> 
      getValuesList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr values = 1;</code>
   */
  com.giyeok.tython.proto.AbstractExpr getValues(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr values = 1;</code>
   */
  int getValuesCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr values = 1;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractExprOrBuilder> 
      getValuesOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr values = 1;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getValuesOrBuilder(
      int index);

  /**
   * <code>int32 lineno = 2;</code>
   * @return The lineno.
   */
  int getLineno();

  /**
   * <code>int32 col_offset = 3;</code>
   * @return The colOffset.
   */
  int getColOffset();

  /**
   * <code>optional int32 end_lineno = 4;</code>
   * @return Whether the endLineno field is set.
   */
  boolean hasEndLineno();
  /**
   * <code>optional int32 end_lineno = 4;</code>
   * @return The endLineno.
   */
  int getEndLineno();

  /**
   * <code>optional int32 end_col_offset = 5;</code>
   * @return Whether the endColOffset field is set.
   */
  boolean hasEndColOffset();
  /**
   * <code>optional int32 end_col_offset = 5;</code>
   * @return The endColOffset.
   */
  int getEndColOffset();
}