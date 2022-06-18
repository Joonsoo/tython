// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

/**
 * Protobuf type {@code com.giyeok.tython.proto.NamedExpr}
 */
public final class NamedExpr extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.giyeok.tython.proto.NamedExpr)
    NamedExprOrBuilder {
private static final long serialVersionUID = 0L;
  // Use NamedExpr.newBuilder() to construct.
  private NamedExpr(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NamedExpr() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new NamedExpr();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private NamedExpr(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            com.giyeok.tython.proto.AbstractExpr.Builder subBuilder = null;
            if (target_ != null) {
              subBuilder = target_.toBuilder();
            }
            target_ = input.readMessage(com.giyeok.tython.proto.AbstractExpr.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(target_);
              target_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            com.giyeok.tython.proto.AbstractExpr.Builder subBuilder = null;
            if (value_ != null) {
              subBuilder = value_.toBuilder();
            }
            value_ = input.readMessage(com.giyeok.tython.proto.AbstractExpr.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(value_);
              value_ = subBuilder.buildPartial();
            }

            break;
          }
          case 24: {

            lineno_ = input.readInt32();
            break;
          }
          case 32: {

            colOffset_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000001;
            endLineno_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000002;
            endColOffset_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_NamedExpr_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_NamedExpr_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.giyeok.tython.proto.NamedExpr.class, com.giyeok.tython.proto.NamedExpr.Builder.class);
  }

  private int bitField0_;
  public static final int TARGET_FIELD_NUMBER = 1;
  private com.giyeok.tython.proto.AbstractExpr target_;
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
   * @return Whether the target field is set.
   */
  @java.lang.Override
  public boolean hasTarget() {
    return target_ != null;
  }
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
   * @return The target.
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExpr getTarget() {
    return target_ == null ? com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : target_;
  }
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExprOrBuilder getTargetOrBuilder() {
    return getTarget();
  }

  public static final int VALUE_FIELD_NUMBER = 2;
  private com.giyeok.tython.proto.AbstractExpr value_;
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
   * @return Whether the value field is set.
   */
  @java.lang.Override
  public boolean hasValue() {
    return value_ != null;
  }
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
   * @return The value.
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExpr getValue() {
    return value_ == null ? com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : value_;
  }
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExprOrBuilder getValueOrBuilder() {
    return getValue();
  }

  public static final int LINENO_FIELD_NUMBER = 3;
  private int lineno_;
  /**
   * <code>int32 lineno = 3;</code>
   * @return The lineno.
   */
  @java.lang.Override
  public int getLineno() {
    return lineno_;
  }

  public static final int COL_OFFSET_FIELD_NUMBER = 4;
  private int colOffset_;
  /**
   * <code>int32 col_offset = 4;</code>
   * @return The colOffset.
   */
  @java.lang.Override
  public int getColOffset() {
    return colOffset_;
  }

  public static final int END_LINENO_FIELD_NUMBER = 5;
  private int endLineno_;
  /**
   * <code>optional int32 end_lineno = 5;</code>
   * @return Whether the endLineno field is set.
   */
  @java.lang.Override
  public boolean hasEndLineno() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>optional int32 end_lineno = 5;</code>
   * @return The endLineno.
   */
  @java.lang.Override
  public int getEndLineno() {
    return endLineno_;
  }

  public static final int END_COL_OFFSET_FIELD_NUMBER = 6;
  private int endColOffset_;
  /**
   * <code>optional int32 end_col_offset = 6;</code>
   * @return Whether the endColOffset field is set.
   */
  @java.lang.Override
  public boolean hasEndColOffset() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>optional int32 end_col_offset = 6;</code>
   * @return The endColOffset.
   */
  @java.lang.Override
  public int getEndColOffset() {
    return endColOffset_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (target_ != null) {
      output.writeMessage(1, getTarget());
    }
    if (value_ != null) {
      output.writeMessage(2, getValue());
    }
    if (lineno_ != 0) {
      output.writeInt32(3, lineno_);
    }
    if (colOffset_ != 0) {
      output.writeInt32(4, colOffset_);
    }
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeInt32(5, endLineno_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeInt32(6, endColOffset_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (target_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getTarget());
    }
    if (value_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getValue());
    }
    if (lineno_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, lineno_);
    }
    if (colOffset_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, colOffset_);
    }
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, endLineno_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, endColOffset_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.giyeok.tython.proto.NamedExpr)) {
      return super.equals(obj);
    }
    com.giyeok.tython.proto.NamedExpr other = (com.giyeok.tython.proto.NamedExpr) obj;

    if (hasTarget() != other.hasTarget()) return false;
    if (hasTarget()) {
      if (!getTarget()
          .equals(other.getTarget())) return false;
    }
    if (hasValue() != other.hasValue()) return false;
    if (hasValue()) {
      if (!getValue()
          .equals(other.getValue())) return false;
    }
    if (getLineno()
        != other.getLineno()) return false;
    if (getColOffset()
        != other.getColOffset()) return false;
    if (hasEndLineno() != other.hasEndLineno()) return false;
    if (hasEndLineno()) {
      if (getEndLineno()
          != other.getEndLineno()) return false;
    }
    if (hasEndColOffset() != other.hasEndColOffset()) return false;
    if (hasEndColOffset()) {
      if (getEndColOffset()
          != other.getEndColOffset()) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasTarget()) {
      hash = (37 * hash) + TARGET_FIELD_NUMBER;
      hash = (53 * hash) + getTarget().hashCode();
    }
    if (hasValue()) {
      hash = (37 * hash) + VALUE_FIELD_NUMBER;
      hash = (53 * hash) + getValue().hashCode();
    }
    hash = (37 * hash) + LINENO_FIELD_NUMBER;
    hash = (53 * hash) + getLineno();
    hash = (37 * hash) + COL_OFFSET_FIELD_NUMBER;
    hash = (53 * hash) + getColOffset();
    if (hasEndLineno()) {
      hash = (37 * hash) + END_LINENO_FIELD_NUMBER;
      hash = (53 * hash) + getEndLineno();
    }
    if (hasEndColOffset()) {
      hash = (37 * hash) + END_COL_OFFSET_FIELD_NUMBER;
      hash = (53 * hash) + getEndColOffset();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.giyeok.tython.proto.NamedExpr parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.giyeok.tython.proto.NamedExpr parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.NamedExpr parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.NamedExpr parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.giyeok.tython.proto.NamedExpr prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.giyeok.tython.proto.NamedExpr}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.giyeok.tython.proto.NamedExpr)
      com.giyeok.tython.proto.NamedExprOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_NamedExpr_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_NamedExpr_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.giyeok.tython.proto.NamedExpr.class, com.giyeok.tython.proto.NamedExpr.Builder.class);
    }

    // Construct using com.giyeok.tython.proto.NamedExpr.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (targetBuilder_ == null) {
        target_ = null;
      } else {
        target_ = null;
        targetBuilder_ = null;
      }
      if (valueBuilder_ == null) {
        value_ = null;
      } else {
        value_ = null;
        valueBuilder_ = null;
      }
      lineno_ = 0;

      colOffset_ = 0;

      endLineno_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      endColOffset_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_NamedExpr_descriptor;
    }

    @java.lang.Override
    public com.giyeok.tython.proto.NamedExpr getDefaultInstanceForType() {
      return com.giyeok.tython.proto.NamedExpr.getDefaultInstance();
    }

    @java.lang.Override
    public com.giyeok.tython.proto.NamedExpr build() {
      com.giyeok.tython.proto.NamedExpr result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.giyeok.tython.proto.NamedExpr buildPartial() {
      com.giyeok.tython.proto.NamedExpr result = new com.giyeok.tython.proto.NamedExpr(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (targetBuilder_ == null) {
        result.target_ = target_;
      } else {
        result.target_ = targetBuilder_.build();
      }
      if (valueBuilder_ == null) {
        result.value_ = value_;
      } else {
        result.value_ = valueBuilder_.build();
      }
      result.lineno_ = lineno_;
      result.colOffset_ = colOffset_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.endLineno_ = endLineno_;
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.endColOffset_ = endColOffset_;
        to_bitField0_ |= 0x00000002;
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.giyeok.tython.proto.NamedExpr) {
        return mergeFrom((com.giyeok.tython.proto.NamedExpr)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.giyeok.tython.proto.NamedExpr other) {
      if (other == com.giyeok.tython.proto.NamedExpr.getDefaultInstance()) return this;
      if (other.hasTarget()) {
        mergeTarget(other.getTarget());
      }
      if (other.hasValue()) {
        mergeValue(other.getValue());
      }
      if (other.getLineno() != 0) {
        setLineno(other.getLineno());
      }
      if (other.getColOffset() != 0) {
        setColOffset(other.getColOffset());
      }
      if (other.hasEndLineno()) {
        setEndLineno(other.getEndLineno());
      }
      if (other.hasEndColOffset()) {
        setEndColOffset(other.getEndColOffset());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.giyeok.tython.proto.NamedExpr parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.giyeok.tython.proto.NamedExpr) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.giyeok.tython.proto.AbstractExpr target_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> targetBuilder_;
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     * @return Whether the target field is set.
     */
    public boolean hasTarget() {
      return targetBuilder_ != null || target_ != null;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     * @return The target.
     */
    public com.giyeok.tython.proto.AbstractExpr getTarget() {
      if (targetBuilder_ == null) {
        return target_ == null ? com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : target_;
      } else {
        return targetBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     */
    public Builder setTarget(com.giyeok.tython.proto.AbstractExpr value) {
      if (targetBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        target_ = value;
        onChanged();
      } else {
        targetBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     */
    public Builder setTarget(
        com.giyeok.tython.proto.AbstractExpr.Builder builderForValue) {
      if (targetBuilder_ == null) {
        target_ = builderForValue.build();
        onChanged();
      } else {
        targetBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     */
    public Builder mergeTarget(com.giyeok.tython.proto.AbstractExpr value) {
      if (targetBuilder_ == null) {
        if (target_ != null) {
          target_ =
            com.giyeok.tython.proto.AbstractExpr.newBuilder(target_).mergeFrom(value).buildPartial();
        } else {
          target_ = value;
        }
        onChanged();
      } else {
        targetBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     */
    public Builder clearTarget() {
      if (targetBuilder_ == null) {
        target_ = null;
        onChanged();
      } else {
        target_ = null;
        targetBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     */
    public com.giyeok.tython.proto.AbstractExpr.Builder getTargetBuilder() {
      
      onChanged();
      return getTargetFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     */
    public com.giyeok.tython.proto.AbstractExprOrBuilder getTargetOrBuilder() {
      if (targetBuilder_ != null) {
        return targetBuilder_.getMessageOrBuilder();
      } else {
        return target_ == null ?
            com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : target_;
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr target = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> 
        getTargetFieldBuilder() {
      if (targetBuilder_ == null) {
        targetBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder>(
                getTarget(),
                getParentForChildren(),
                isClean());
        target_ = null;
      }
      return targetBuilder_;
    }

    private com.giyeok.tython.proto.AbstractExpr value_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> valueBuilder_;
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     * @return Whether the value field is set.
     */
    public boolean hasValue() {
      return valueBuilder_ != null || value_ != null;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     * @return The value.
     */
    public com.giyeok.tython.proto.AbstractExpr getValue() {
      if (valueBuilder_ == null) {
        return value_ == null ? com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : value_;
      } else {
        return valueBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     */
    public Builder setValue(com.giyeok.tython.proto.AbstractExpr value) {
      if (valueBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        value_ = value;
        onChanged();
      } else {
        valueBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     */
    public Builder setValue(
        com.giyeok.tython.proto.AbstractExpr.Builder builderForValue) {
      if (valueBuilder_ == null) {
        value_ = builderForValue.build();
        onChanged();
      } else {
        valueBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     */
    public Builder mergeValue(com.giyeok.tython.proto.AbstractExpr value) {
      if (valueBuilder_ == null) {
        if (value_ != null) {
          value_ =
            com.giyeok.tython.proto.AbstractExpr.newBuilder(value_).mergeFrom(value).buildPartial();
        } else {
          value_ = value;
        }
        onChanged();
      } else {
        valueBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     */
    public Builder clearValue() {
      if (valueBuilder_ == null) {
        value_ = null;
        onChanged();
      } else {
        value_ = null;
        valueBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     */
    public com.giyeok.tython.proto.AbstractExpr.Builder getValueBuilder() {
      
      onChanged();
      return getValueFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     */
    public com.giyeok.tython.proto.AbstractExprOrBuilder getValueOrBuilder() {
      if (valueBuilder_ != null) {
        return valueBuilder_.getMessageOrBuilder();
      } else {
        return value_ == null ?
            com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : value_;
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr value = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> 
        getValueFieldBuilder() {
      if (valueBuilder_ == null) {
        valueBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder>(
                getValue(),
                getParentForChildren(),
                isClean());
        value_ = null;
      }
      return valueBuilder_;
    }

    private int lineno_ ;
    /**
     * <code>int32 lineno = 3;</code>
     * @return The lineno.
     */
    @java.lang.Override
    public int getLineno() {
      return lineno_;
    }
    /**
     * <code>int32 lineno = 3;</code>
     * @param value The lineno to set.
     * @return This builder for chaining.
     */
    public Builder setLineno(int value) {
      
      lineno_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 lineno = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearLineno() {
      
      lineno_ = 0;
      onChanged();
      return this;
    }

    private int colOffset_ ;
    /**
     * <code>int32 col_offset = 4;</code>
     * @return The colOffset.
     */
    @java.lang.Override
    public int getColOffset() {
      return colOffset_;
    }
    /**
     * <code>int32 col_offset = 4;</code>
     * @param value The colOffset to set.
     * @return This builder for chaining.
     */
    public Builder setColOffset(int value) {
      
      colOffset_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 col_offset = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearColOffset() {
      
      colOffset_ = 0;
      onChanged();
      return this;
    }

    private int endLineno_ ;
    /**
     * <code>optional int32 end_lineno = 5;</code>
     * @return Whether the endLineno field is set.
     */
    @java.lang.Override
    public boolean hasEndLineno() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional int32 end_lineno = 5;</code>
     * @return The endLineno.
     */
    @java.lang.Override
    public int getEndLineno() {
      return endLineno_;
    }
    /**
     * <code>optional int32 end_lineno = 5;</code>
     * @param value The endLineno to set.
     * @return This builder for chaining.
     */
    public Builder setEndLineno(int value) {
      bitField0_ |= 0x00000001;
      endLineno_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 end_lineno = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearEndLineno() {
      bitField0_ = (bitField0_ & ~0x00000001);
      endLineno_ = 0;
      onChanged();
      return this;
    }

    private int endColOffset_ ;
    /**
     * <code>optional int32 end_col_offset = 6;</code>
     * @return Whether the endColOffset field is set.
     */
    @java.lang.Override
    public boolean hasEndColOffset() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional int32 end_col_offset = 6;</code>
     * @return The endColOffset.
     */
    @java.lang.Override
    public int getEndColOffset() {
      return endColOffset_;
    }
    /**
     * <code>optional int32 end_col_offset = 6;</code>
     * @param value The endColOffset to set.
     * @return This builder for chaining.
     */
    public Builder setEndColOffset(int value) {
      bitField0_ |= 0x00000002;
      endColOffset_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 end_col_offset = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearEndColOffset() {
      bitField0_ = (bitField0_ & ~0x00000002);
      endColOffset_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.giyeok.tython.proto.NamedExpr)
  }

  // @@protoc_insertion_point(class_scope:com.giyeok.tython.proto.NamedExpr)
  private static final com.giyeok.tython.proto.NamedExpr DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.giyeok.tython.proto.NamedExpr();
  }

  public static com.giyeok.tython.proto.NamedExpr getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NamedExpr>
      PARSER = new com.google.protobuf.AbstractParser<NamedExpr>() {
    @java.lang.Override
    public NamedExpr parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new NamedExpr(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NamedExpr> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NamedExpr> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.giyeok.tython.proto.NamedExpr getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

