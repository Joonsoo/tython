// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface RaiseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.Raise)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr exc = 1;</code>
   * @return Whether the exc field is set.
   */
  boolean hasExc();
  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr exc = 1;</code>
   * @return The exc.
   */
  com.giyeok.tython.proto.AbstractExpr getExc();
  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr exc = 1;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getExcOrBuilder();

  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr cause = 2;</code>
   * @return Whether the cause field is set.
   */
  boolean hasCause();
  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr cause = 2;</code>
   * @return The cause.
   */
  com.giyeok.tython.proto.AbstractExpr getCause();
  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr cause = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getCauseOrBuilder();

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
