// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface LambdaOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.Lambda)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.giyeok.tython.proto.Arguments args = 1;</code>
   * @return Whether the args field is set.
   */
  boolean hasArgs();
  /**
   * <code>.com.giyeok.tython.proto.Arguments args = 1;</code>
   * @return The args.
   */
  com.giyeok.tython.proto.Arguments getArgs();
  /**
   * <code>.com.giyeok.tython.proto.Arguments args = 1;</code>
   */
  com.giyeok.tython.proto.ArgumentsOrBuilder getArgsOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr body = 2;</code>
   * @return Whether the body field is set.
   */
  boolean hasBody();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr body = 2;</code>
   * @return The body.
   */
  com.giyeok.tython.proto.AbstractExpr getBody();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr body = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getBodyOrBuilder();

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
