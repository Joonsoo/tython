// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface UnaryOpOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.UnaryOp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.giyeok.tython.proto.AbstractUnaryop op = 1;</code>
   * @return Whether the op field is set.
   */
  boolean hasOp();
  /**
   * <code>.com.giyeok.tython.proto.AbstractUnaryop op = 1;</code>
   * @return The op.
   */
  com.giyeok.tython.proto.AbstractUnaryop getOp();
  /**
   * <code>.com.giyeok.tython.proto.AbstractUnaryop op = 1;</code>
   */
  com.giyeok.tython.proto.AbstractUnaryopOrBuilder getOpOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr operand = 2;</code>
   * @return Whether the operand field is set.
   */
  boolean hasOperand();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr operand = 2;</code>
   * @return The operand.
   */
  com.giyeok.tython.proto.AbstractExpr getOperand();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr operand = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getOperandOrBuilder();

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
