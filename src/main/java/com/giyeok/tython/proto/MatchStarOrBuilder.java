// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface MatchStarOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.MatchStar)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string name = 1;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>optional string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>optional string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

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