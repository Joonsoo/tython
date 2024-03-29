// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface CallOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.Call)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr func = 1;</code>
   * @return Whether the func field is set.
   */
  boolean hasFunc();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr func = 1;</code>
   * @return The func.
   */
  com.giyeok.tython.proto.AbstractExpr getFunc();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr func = 1;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getFuncOrBuilder();

  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr args = 2;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractExpr> 
      getArgsList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr args = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExpr getArgs(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr args = 2;</code>
   */
  int getArgsCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr args = 2;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractExprOrBuilder> 
      getArgsOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr args = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getArgsOrBuilder(
      int index);

  /**
   * <code>repeated .com.giyeok.tython.proto.Keyword keywords = 3;</code>
   */
  java.util.List<com.giyeok.tython.proto.Keyword> 
      getKeywordsList();
  /**
   * <code>repeated .com.giyeok.tython.proto.Keyword keywords = 3;</code>
   */
  com.giyeok.tython.proto.Keyword getKeywords(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.Keyword keywords = 3;</code>
   */
  int getKeywordsCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.Keyword keywords = 3;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.KeywordOrBuilder> 
      getKeywordsOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.Keyword keywords = 3;</code>
   */
  com.giyeok.tython.proto.KeywordOrBuilder getKeywordsOrBuilder(
      int index);

  /**
   * <code>int32 lineno = 4;</code>
   * @return The lineno.
   */
  int getLineno();

  /**
   * <code>int32 col_offset = 5;</code>
   * @return The colOffset.
   */
  int getColOffset();

  /**
   * <code>optional int32 end_lineno = 6;</code>
   * @return Whether the endLineno field is set.
   */
  boolean hasEndLineno();
  /**
   * <code>optional int32 end_lineno = 6;</code>
   * @return The endLineno.
   */
  int getEndLineno();

  /**
   * <code>optional int32 end_col_offset = 7;</code>
   * @return Whether the endColOffset field is set.
   */
  boolean hasEndColOffset();
  /**
   * <code>optional int32 end_col_offset = 7;</code>
   * @return The endColOffset.
   */
  int getEndColOffset();
}
