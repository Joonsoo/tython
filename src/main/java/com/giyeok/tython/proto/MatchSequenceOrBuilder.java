// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface MatchSequenceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.MatchSequence)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractPattern patterns = 1;</code>
   */
  java.util.List<com.giyeok.tython.proto.AbstractPattern> 
      getPatternsList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractPattern patterns = 1;</code>
   */
  com.giyeok.tython.proto.AbstractPattern getPatterns(int index);
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractPattern patterns = 1;</code>
   */
  int getPatternsCount();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractPattern patterns = 1;</code>
   */
  java.util.List<? extends com.giyeok.tython.proto.AbstractPatternOrBuilder> 
      getPatternsOrBuilderList();
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractPattern patterns = 1;</code>
   */
  com.giyeok.tython.proto.AbstractPatternOrBuilder getPatternsOrBuilder(
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
   * <code>int32 end_lineno = 4;</code>
   * @return The endLineno.
   */
  int getEndLineno();

  /**
   * <code>int32 end_col_offset = 5;</code>
   * @return The endColOffset.
   */
  int getEndColOffset();
}
