// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface ComprehensionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.Comprehension)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
   * @return Whether the target field is set.
   */
  boolean hasTarget();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
   * @return The target.
   */
  com.giyeok.tython.proto.AbstractExpr getTarget();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getTargetOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
   * @return Whether the iter field is set.
   */
  boolean hasIter();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
   * @return The iter.
   */
  com.giyeok.tython.proto.AbstractExpr getIter();
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getIterOrBuilder();

  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractExpr> 
      getIfsList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  com.giyeok.tython.proto.AbstractExpr getIfs(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  int getIfsCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractExprOrBuilder> 
      getIfsOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  com.giyeok.tython.proto.AbstractExprOrBuilder getIfsOrBuilder(
      int index);

  /**
   * <code>int32 is_async = 4;</code>
   * @return The isAsync.
   */
  int getIsAsync();
}