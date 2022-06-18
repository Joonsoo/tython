// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface FunctionDefOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.FunctionDef)
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
   * <code>.com.giyeok.tython.proto.Arguments args = 2;</code>
   * @return Whether the args field is set.
   */
  boolean hasArgs();
  /**
   * <code>.com.giyeok.tython.proto.Arguments args = 2;</code>
   * @return The args.
   */
  com.giyeok.tython.proto.Arguments getArgs();
  /**
   * <code>.com.giyeok.tython.proto.Arguments args = 2;</code>
   */
  com.giyeok.tython.proto.ArgumentsOrBuilder getArgsOrBuilder();

  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 3;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractStmt> 
      getBodyList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 3;</code>
   */
  com.giyeok.tython.proto.AbstractStmt getBody(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 3;</code>
   */
  int getBodyCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 3;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractStmtOrBuilder> 
      getBodyOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractStmt body = 3;</code>
   */
  com.giyeok.tython.proto.AbstractStmtOrBuilder getBodyOrBuilder(
      int index);

  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 4;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractExpr> 
      getDecoratorListList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 4;</code>
   */
  com.giyeok.tython.proto.AbstractExpr getDecoratorList(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 4;</code>
   */
  int getDecoratorListCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 4;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractExprOrBuilder> 
      getDecoratorListOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr decorator_list = 4;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getDecoratorListOrBuilder(
      int index);

  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr returns = 5;</code>
   * @return Whether the returns field is set.
   */
  boolean hasReturns();
  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr returns = 5;</code>
   * @return The returns.
   */
  com.giyeok.tython.proto.AbstractExpr getReturns();
  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr returns = 5;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getReturnsOrBuilder();

  /**
   * <code>optional string type_comment = 6;</code>
   * @return Whether the typeComment field is set.
   */
  boolean hasTypeComment();
  /**
   * <code>optional string type_comment = 6;</code>
   * @return The typeComment.
   */
  java.lang.String getTypeComment();
  /**
   * <code>optional string type_comment = 6;</code>
   * @return The bytes for typeComment.
   */
  com.google.protobuf.ByteString
      getTypeCommentBytes();

  /**
   * <code>int32 lineno = 7;</code>
   * @return The lineno.
   */
  int getLineno();

  /**
   * <code>int32 col_offset = 8;</code>
   * @return The colOffset.
   */
  int getColOffset();

  /**
   * <code>optional int32 end_lineno = 9;</code>
   * @return Whether the endLineno field is set.
   */
  boolean hasEndLineno();
  /**
   * <code>optional int32 end_lineno = 9;</code>
   * @return The endLineno.
   */
  int getEndLineno();

  /**
   * <code>optional int32 end_col_offset = 10;</code>
   * @return Whether the endColOffset field is set.
   */
  boolean hasEndColOffset();
  /**
   * <code>optional int32 end_col_offset = 10;</code>
   * @return The endColOffset.
   */
  int getEndColOffset();
}