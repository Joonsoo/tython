// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface ClassDefOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.ClassDef)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr bases = 2;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractExpr> 
      getBasesList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr bases = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExpr getBases(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr bases = 2;</code>
   */
  int getBasesCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr bases = 2;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractExprOrBuilder> 
      getBasesOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr bases = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getBasesOrBuilder(
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
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 4;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractStmt> 
      getBodyList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 4;</code>
   */
  com.giyeok.tython.proto.AbstractStmt getBody(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 4;</code>
   */
  int getBodyCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 4;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractStmtOrBuilder> 
      getBodyOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 4;</code>
   */
  com.giyeok.tython.proto.AbstractStmtOrBuilder getBodyOrBuilder(
      int index);

  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 5;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractExpr> 
      getDecoratorListList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 5;</code>
   */
  com.giyeok.tython.proto.AbstractExpr getDecoratorList(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 5;</code>
   */
  int getDecoratorListCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 5;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractExprOrBuilder> 
      getDecoratorListOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 5;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getDecoratorListOrBuilder(
      int index);

  /**
   * <code>int32 lineno = 6;</code>
   * @return The lineno.
   */
  int getLineno();

  /**
   * <code>int32 col_offset = 7;</code>
   * @return The colOffset.
   */
  int getColOffset();

  /**
   * <code>optional int32 end_lineno = 8;</code>
   * @return Whether the endLineno field is set.
   */
  boolean hasEndLineno();
  /**
   * <code>optional int32 end_lineno = 8;</code>
   * @return The endLineno.
   */
  int getEndLineno();

  /**
   * <code>optional int32 end_col_offset = 9;</code>
   * @return Whether the endColOffset field is set.
   */
  boolean hasEndColOffset();
  /**
   * <code>optional int32 end_col_offset = 9;</code>
   * @return The endColOffset.
   */
  int getEndColOffset();
}
