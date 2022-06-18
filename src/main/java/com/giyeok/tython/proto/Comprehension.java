// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

/**
 * Protobuf type {@code com.giyeok.tython.proto.Comprehension}
 */
public final class Comprehension extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.giyeok.tython.proto.Comprehension)
    ComprehensionOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Comprehension.newBuilder() to construct.
  private Comprehension(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Comprehension() {
    ifs_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Comprehension();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Comprehension(
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
            if (iter_ != null) {
              subBuilder = iter_.toBuilder();
            }
            iter_ = input.readMessage(com.giyeok.tython.proto.AbstractExpr.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(iter_);
              iter_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              ifs_ = new java.util.ArrayList<com.giyeok.tython.proto.AbstractExpr>();
              mutable_bitField0_ |= 0x00000001;
            }
            ifs_.add(
                input.readMessage(com.giyeok.tython.proto.AbstractExpr.parser(), extensionRegistry));
            break;
          }
          case 32: {

            isAsync_ = input.readInt32();
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        ifs_ = java.util.Collections.unmodifiableList(ifs_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Comprehension_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Comprehension_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.giyeok.tython.proto.Comprehension.class, com.giyeok.tython.proto.Comprehension.Builder.class);
  }

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

  public static final int ITER_FIELD_NUMBER = 2;
  private com.giyeok.tython.proto.AbstractExpr iter_;
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
   * @return Whether the iter field is set.
   */
  @java.lang.Override
  public boolean hasIter() {
    return iter_ != null;
  }
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
   * @return The iter.
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExpr getIter() {
    return iter_ == null ? com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : iter_;
  }
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExprOrBuilder getIterOrBuilder() {
    return getIter();
  }

  public static final int IFS_FIELD_NUMBER = 3;
  private java.util.List<com.giyeok.tython.proto.AbstractExpr> ifs_;
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  @java.lang.Override
  public java.util.List<com.giyeok.tython.proto.AbstractExpr> getIfsList() {
    return ifs_;
  }
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.giyeok.tython.proto.AbstractExprOrBuilder> 
      getIfsOrBuilderList() {
    return ifs_;
  }
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  @java.lang.Override
  public int getIfsCount() {
    return ifs_.size();
  }
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExpr getIfs(int index) {
    return ifs_.get(index);
  }
  /**
   * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExprOrBuilder getIfsOrBuilder(
      int index) {
    return ifs_.get(index);
  }

  public static final int IS_ASYNC_FIELD_NUMBER = 4;
  private int isAsync_;
  /**
   * <code>int32 is_async = 4;</code>
   * @return The isAsync.
   */
  @java.lang.Override
  public int getIsAsync() {
    return isAsync_;
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
    if (iter_ != null) {
      output.writeMessage(2, getIter());
    }
    for (int i = 0; i < ifs_.size(); i++) {
      output.writeMessage(3, ifs_.get(i));
    }
    if (isAsync_ != 0) {
      output.writeInt32(4, isAsync_);
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
    if (iter_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getIter());
    }
    for (int i = 0; i < ifs_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, ifs_.get(i));
    }
    if (isAsync_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, isAsync_);
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
    if (!(obj instanceof com.giyeok.tython.proto.Comprehension)) {
      return super.equals(obj);
    }
    com.giyeok.tython.proto.Comprehension other = (com.giyeok.tython.proto.Comprehension) obj;

    if (hasTarget() != other.hasTarget()) return false;
    if (hasTarget()) {
      if (!getTarget()
          .equals(other.getTarget())) return false;
    }
    if (hasIter() != other.hasIter()) return false;
    if (hasIter()) {
      if (!getIter()
          .equals(other.getIter())) return false;
    }
    if (!getIfsList()
        .equals(other.getIfsList())) return false;
    if (getIsAsync()
        != other.getIsAsync()) return false;
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
    if (hasIter()) {
      hash = (37 * hash) + ITER_FIELD_NUMBER;
      hash = (53 * hash) + getIter().hashCode();
    }
    if (getIfsCount() > 0) {
      hash = (37 * hash) + IFS_FIELD_NUMBER;
      hash = (53 * hash) + getIfsList().hashCode();
    }
    hash = (37 * hash) + IS_ASYNC_FIELD_NUMBER;
    hash = (53 * hash) + getIsAsync();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.giyeok.tython.proto.Comprehension parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Comprehension parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.Comprehension parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.Comprehension parseFrom(
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
  public static Builder newBuilder(com.giyeok.tython.proto.Comprehension prototype) {
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
   * Protobuf type {@code com.giyeok.tython.proto.Comprehension}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.giyeok.tython.proto.Comprehension)
      com.giyeok.tython.proto.ComprehensionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Comprehension_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Comprehension_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.giyeok.tython.proto.Comprehension.class, com.giyeok.tython.proto.Comprehension.Builder.class);
    }

    // Construct using com.giyeok.tython.proto.Comprehension.newBuilder()
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
        getIfsFieldBuilder();
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
      if (iterBuilder_ == null) {
        iter_ = null;
      } else {
        iter_ = null;
        iterBuilder_ = null;
      }
      if (ifsBuilder_ == null) {
        ifs_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        ifsBuilder_.clear();
      }
      isAsync_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Comprehension_descriptor;
    }

    @java.lang.Override
    public com.giyeok.tython.proto.Comprehension getDefaultInstanceForType() {
      return com.giyeok.tython.proto.Comprehension.getDefaultInstance();
    }

    @java.lang.Override
    public com.giyeok.tython.proto.Comprehension build() {
      com.giyeok.tython.proto.Comprehension result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.giyeok.tython.proto.Comprehension buildPartial() {
      com.giyeok.tython.proto.Comprehension result = new com.giyeok.tython.proto.Comprehension(this);
      int from_bitField0_ = bitField0_;
      if (targetBuilder_ == null) {
        result.target_ = target_;
      } else {
        result.target_ = targetBuilder_.build();
      }
      if (iterBuilder_ == null) {
        result.iter_ = iter_;
      } else {
        result.iter_ = iterBuilder_.build();
      }
      if (ifsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          ifs_ = java.util.Collections.unmodifiableList(ifs_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.ifs_ = ifs_;
      } else {
        result.ifs_ = ifsBuilder_.build();
      }
      result.isAsync_ = isAsync_;
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
      if (other instanceof com.giyeok.tython.proto.Comprehension) {
        return mergeFrom((com.giyeok.tython.proto.Comprehension)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.giyeok.tython.proto.Comprehension other) {
      if (other == com.giyeok.tython.proto.Comprehension.getDefaultInstance()) return this;
      if (other.hasTarget()) {
        mergeTarget(other.getTarget());
      }
      if (other.hasIter()) {
        mergeIter(other.getIter());
      }
      if (ifsBuilder_ == null) {
        if (!other.ifs_.isEmpty()) {
          if (ifs_.isEmpty()) {
            ifs_ = other.ifs_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureIfsIsMutable();
            ifs_.addAll(other.ifs_);
          }
          onChanged();
        }
      } else {
        if (!other.ifs_.isEmpty()) {
          if (ifsBuilder_.isEmpty()) {
            ifsBuilder_.dispose();
            ifsBuilder_ = null;
            ifs_ = other.ifs_;
            bitField0_ = (bitField0_ & ~0x00000001);
            ifsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getIfsFieldBuilder() : null;
          } else {
            ifsBuilder_.addAllMessages(other.ifs_);
          }
        }
      }
      if (other.getIsAsync() != 0) {
        setIsAsync(other.getIsAsync());
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
      com.giyeok.tython.proto.Comprehension parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.giyeok.tython.proto.Comprehension) e.getUnfinishedMessage();
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

    private com.giyeok.tython.proto.AbstractExpr iter_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> iterBuilder_;
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     * @return Whether the iter field is set.
     */
    public boolean hasIter() {
      return iterBuilder_ != null || iter_ != null;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     * @return The iter.
     */
    public com.giyeok.tython.proto.AbstractExpr getIter() {
      if (iterBuilder_ == null) {
        return iter_ == null ? com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : iter_;
      } else {
        return iterBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     */
    public Builder setIter(com.giyeok.tython.proto.AbstractExpr value) {
      if (iterBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        iter_ = value;
        onChanged();
      } else {
        iterBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     */
    public Builder setIter(
        com.giyeok.tython.proto.AbstractExpr.Builder builderForValue) {
      if (iterBuilder_ == null) {
        iter_ = builderForValue.build();
        onChanged();
      } else {
        iterBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     */
    public Builder mergeIter(com.giyeok.tython.proto.AbstractExpr value) {
      if (iterBuilder_ == null) {
        if (iter_ != null) {
          iter_ =
            com.giyeok.tython.proto.AbstractExpr.newBuilder(iter_).mergeFrom(value).buildPartial();
        } else {
          iter_ = value;
        }
        onChanged();
      } else {
        iterBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     */
    public Builder clearIter() {
      if (iterBuilder_ == null) {
        iter_ = null;
        onChanged();
      } else {
        iter_ = null;
        iterBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     */
    public com.giyeok.tython.proto.AbstractExpr.Builder getIterBuilder() {
      
      onChanged();
      return getIterFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     */
    public com.giyeok.tython.proto.AbstractExprOrBuilder getIterOrBuilder() {
      if (iterBuilder_ != null) {
        return iterBuilder_.getMessageOrBuilder();
      } else {
        return iter_ == null ?
            com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : iter_;
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr iter = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> 
        getIterFieldBuilder() {
      if (iterBuilder_ == null) {
        iterBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder>(
                getIter(),
                getParentForChildren(),
                isClean());
        iter_ = null;
      }
      return iterBuilder_;
    }

    private java.util.List<com.giyeok.tython.proto.AbstractExpr> ifs_ =
      java.util.Collections.emptyList();
    private void ensureIfsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        ifs_ = new java.util.ArrayList<com.giyeok.tython.proto.AbstractExpr>(ifs_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> ifsBuilder_;

    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public java.util.List<com.giyeok.tython.proto.AbstractExpr> getIfsList() {
      if (ifsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(ifs_);
      } else {
        return ifsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public int getIfsCount() {
      if (ifsBuilder_ == null) {
        return ifs_.size();
      } else {
        return ifsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public com.giyeok.tython.proto.AbstractExpr getIfs(int index) {
      if (ifsBuilder_ == null) {
        return ifs_.get(index);
      } else {
        return ifsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder setIfs(
        int index, com.giyeok.tython.proto.AbstractExpr value) {
      if (ifsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureIfsIsMutable();
        ifs_.set(index, value);
        onChanged();
      } else {
        ifsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder setIfs(
        int index, com.giyeok.tython.proto.AbstractExpr.Builder builderForValue) {
      if (ifsBuilder_ == null) {
        ensureIfsIsMutable();
        ifs_.set(index, builderForValue.build());
        onChanged();
      } else {
        ifsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder addIfs(com.giyeok.tython.proto.AbstractExpr value) {
      if (ifsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureIfsIsMutable();
        ifs_.add(value);
        onChanged();
      } else {
        ifsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder addIfs(
        int index, com.giyeok.tython.proto.AbstractExpr value) {
      if (ifsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureIfsIsMutable();
        ifs_.add(index, value);
        onChanged();
      } else {
        ifsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder addIfs(
        com.giyeok.tython.proto.AbstractExpr.Builder builderForValue) {
      if (ifsBuilder_ == null) {
        ensureIfsIsMutable();
        ifs_.add(builderForValue.build());
        onChanged();
      } else {
        ifsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder addIfs(
        int index, com.giyeok.tython.proto.AbstractExpr.Builder builderForValue) {
      if (ifsBuilder_ == null) {
        ensureIfsIsMutable();
        ifs_.add(index, builderForValue.build());
        onChanged();
      } else {
        ifsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder addAllIfs(
        java.lang.Iterable<? extends com.giyeok.tython.proto.AbstractExpr> values) {
      if (ifsBuilder_ == null) {
        ensureIfsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, ifs_);
        onChanged();
      } else {
        ifsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder clearIfs() {
      if (ifsBuilder_ == null) {
        ifs_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        ifsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public Builder removeIfs(int index) {
      if (ifsBuilder_ == null) {
        ensureIfsIsMutable();
        ifs_.remove(index);
        onChanged();
      } else {
        ifsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public com.giyeok.tython.proto.AbstractExpr.Builder getIfsBuilder(
        int index) {
      return getIfsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public com.giyeok.tython.proto.AbstractExprOrBuilder getIfsOrBuilder(
        int index) {
      if (ifsBuilder_ == null) {
        return ifs_.get(index);  } else {
        return ifsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public java.util.List<? extends com.giyeok.tython.proto.AbstractExprOrBuilder> 
         getIfsOrBuilderList() {
      if (ifsBuilder_ != null) {
        return ifsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(ifs_);
      }
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public com.giyeok.tython.proto.AbstractExpr.Builder addIfsBuilder() {
      return getIfsFieldBuilder().addBuilder(
          com.giyeok.tython.proto.AbstractExpr.getDefaultInstance());
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public com.giyeok.tython.proto.AbstractExpr.Builder addIfsBuilder(
        int index) {
      return getIfsFieldBuilder().addBuilder(
          index, com.giyeok.tython.proto.AbstractExpr.getDefaultInstance());
    }
    /**
     * <code>repeated .com.giyeok.tython.proto.AbstractExpr ifs = 3;</code>
     */
    public java.util.List<com.giyeok.tython.proto.AbstractExpr.Builder> 
         getIfsBuilderList() {
      return getIfsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> 
        getIfsFieldBuilder() {
      if (ifsBuilder_ == null) {
        ifsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder>(
                ifs_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        ifs_ = null;
      }
      return ifsBuilder_;
    }

    private int isAsync_ ;
    /**
     * <code>int32 is_async = 4;</code>
     * @return The isAsync.
     */
    @java.lang.Override
    public int getIsAsync() {
      return isAsync_;
    }
    /**
     * <code>int32 is_async = 4;</code>
     * @param value The isAsync to set.
     * @return This builder for chaining.
     */
    public Builder setIsAsync(int value) {
      
      isAsync_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 is_async = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearIsAsync() {
      
      isAsync_ = 0;
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


    // @@protoc_insertion_point(builder_scope:com.giyeok.tython.proto.Comprehension)
  }

  // @@protoc_insertion_point(class_scope:com.giyeok.tython.proto.Comprehension)
  private static final com.giyeok.tython.proto.Comprehension DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.giyeok.tython.proto.Comprehension();
  }

  public static com.giyeok.tython.proto.Comprehension getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Comprehension>
      PARSER = new com.google.protobuf.AbstractParser<Comprehension>() {
    @java.lang.Override
    public Comprehension parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Comprehension(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Comprehension> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Comprehension> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.giyeok.tython.proto.Comprehension getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
