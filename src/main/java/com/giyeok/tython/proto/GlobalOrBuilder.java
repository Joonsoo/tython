// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface GlobalOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.Global)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string names = 1;</code>
   * @return A list containing the names.
   */
  java.util.List<java.lang.String>
      getNamesList();
  /**
   * <code>repeated string names = 1;</code>
   * @return The count of names.
   */
  int getNamesCount();
  /**
   * <code>repeated string names = 1;</code>
   * @param index The index of the element to return.
   * @return The names at the given index.
   */
  java.lang.String getNames(int index);
  /**
   * <code>repeated string names = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the names at the given index.
   */
  com.google.protobuf.ByteString
      getNamesBytes(int index);

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
   * <code>optional int32 end_lineno = 4;</code>
   * @return Whether the endLineno field is set.
   */
  boolean hasEndLineno();
  /**
   * <code>optional int32 end_lineno = 4;</code>
   * @return The endLineno.
   */
  int getEndLineno();

  /**
   * <code>optional int32 end_col_offset = 5;</code>
   * @return Whether the endColOffset field is set.
   */
  boolean hasEndColOffset();
  /**
   * <code>optional int32 end_col_offset = 5;</code>
   * @return The endColOffset.
   */
  int getEndColOffset();
}
