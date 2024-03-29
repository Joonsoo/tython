// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface MatchCaseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.MatchCase)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.giyeok.tython.proto.AbstractPattern pattern = 1;</code>
   * @return Whether the pattern field is set.
   */
  boolean hasPattern();
  /**
   * <code>.com.giyeok.tython.proto.AbstractPattern pattern = 1;</code>
   * @return The pattern.
   */
  com.giyeok.tython.proto.AbstractPattern getPattern();
  /**
   * <code>.com.giyeok.tython.proto.AbstractPattern pattern = 1;</code>
   */
  com.giyeok.tython.proto.AbstractPatternOrBuilder getPatternOrBuilder();

  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr guard = 2;</code>
   * @return Whether the guard field is set.
   */
  boolean hasGuard();
  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr guard = 2;</code>
   * @return The guard.
   */
  com.giyeok.tython.proto.AbstractExpr getGuard();
  /**
   * <code>optional .com.giyeok.tython.proto.AbstractExpr guard = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getGuardOrBuilder();

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
}
