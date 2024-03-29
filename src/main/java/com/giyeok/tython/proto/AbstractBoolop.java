// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

/**
 * Protobuf type {@code com.giyeok.tython.proto.AbstractBoolop}
 */
public final class AbstractBoolop extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.giyeok.tython.proto.AbstractBoolop)
    AbstractBoolopOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AbstractBoolop.newBuilder() to construct.
  private AbstractBoolop(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AbstractBoolop() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AbstractBoolop();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AbstractBoolop(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
            com.giyeok.tython.proto.And.Builder subBuilder = null;
            if (abstractBoolopCase_ == 1) {
              subBuilder = ((com.giyeok.tython.proto.And) abstractBoolop_).toBuilder();
            }
            abstractBoolop_ =
                input.readMessage(com.giyeok.tython.proto.And.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((com.giyeok.tython.proto.And) abstractBoolop_);
              abstractBoolop_ = subBuilder.buildPartial();
            }
            abstractBoolopCase_ = 1;
            break;
          }
          case 18: {
            com.giyeok.tython.proto.Or.Builder subBuilder = null;
            if (abstractBoolopCase_ == 2) {
              subBuilder = ((com.giyeok.tython.proto.Or) abstractBoolop_).toBuilder();
            }
            abstractBoolop_ =
                input.readMessage(com.giyeok.tython.proto.Or.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((com.giyeok.tython.proto.Or) abstractBoolop_);
              abstractBoolop_ = subBuilder.buildPartial();
            }
            abstractBoolopCase_ = 2;
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
    return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_AbstractBoolop_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_AbstractBoolop_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.giyeok.tython.proto.AbstractBoolop.class, com.giyeok.tython.proto.AbstractBoolop.Builder.class);
  }

  private int abstractBoolopCase_ = 0;
  private java.lang.Object abstractBoolop_;
  public enum AbstractBoolopCase
      implements com.google.protobuf.Internal.EnumLite,
          com.google.protobuf.AbstractMessage.InternalOneOfEnum {
    AND(1),
    OR(2),
    ABSTRACTBOOLOP_NOT_SET(0);
    private final int value;
    private AbstractBoolopCase(int value) {
      this.value = value;
    }
    /**
     * @param value The number of the enum to look for.
     * @return The enum associated with the given number.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static AbstractBoolopCase valueOf(int value) {
      return forNumber(value);
    }

    public static AbstractBoolopCase forNumber(int value) {
      switch (value) {
        case 1: return AND;
        case 2: return OR;
        case 0: return ABSTRACTBOOLOP_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public AbstractBoolopCase
  getAbstractBoolopCase() {
    return AbstractBoolopCase.forNumber(
        abstractBoolopCase_);
  }

  public static final int AND_FIELD_NUMBER = 1;
  /**
   * <code>.com.giyeok.tython.proto.And and = 1;</code>
   * @return Whether the and field is set.
   */
  @java.lang.Override
  public boolean hasAnd() {
    return abstractBoolopCase_ == 1;
  }
  /**
   * <code>.com.giyeok.tython.proto.And and = 1;</code>
   * @return The and.
   */
  @java.lang.Override
  public com.giyeok.tython.proto.And getAnd() {
    if (abstractBoolopCase_ == 1) {
       return (com.giyeok.tython.proto.And) abstractBoolop_;
    }
    return com.giyeok.tython.proto.And.getDefaultInstance();
  }
  /**
   * <code>.com.giyeok.tython.proto.And and = 1;</code>
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AndOrBuilder getAndOrBuilder() {
    if (abstractBoolopCase_ == 1) {
       return (com.giyeok.tython.proto.And) abstractBoolop_;
    }
    return com.giyeok.tython.proto.And.getDefaultInstance();
  }

  public static final int OR_FIELD_NUMBER = 2;
  /**
   * <code>.com.giyeok.tython.proto.Or or = 2;</code>
   * @return Whether the or field is set.
   */
  @java.lang.Override
  public boolean hasOr() {
    return abstractBoolopCase_ == 2;
  }
  /**
   * <code>.com.giyeok.tython.proto.Or or = 2;</code>
   * @return The or.
   */
  @java.lang.Override
  public com.giyeok.tython.proto.Or getOr() {
    if (abstractBoolopCase_ == 2) {
       return (com.giyeok.tython.proto.Or) abstractBoolop_;
    }
    return com.giyeok.tython.proto.Or.getDefaultInstance();
  }
  /**
   * <code>.com.giyeok.tython.proto.Or or = 2;</code>
   */
  @java.lang.Override
  public com.giyeok.tython.proto.OrOrBuilder getOrOrBuilder() {
    if (abstractBoolopCase_ == 2) {
       return (com.giyeok.tython.proto.Or) abstractBoolop_;
    }
    return com.giyeok.tython.proto.Or.getDefaultInstance();
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
    if (abstractBoolopCase_ == 1) {
      output.writeMessage(1, (com.giyeok.tython.proto.And) abstractBoolop_);
    }
    if (abstractBoolopCase_ == 2) {
      output.writeMessage(2, (com.giyeok.tython.proto.Or) abstractBoolop_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (abstractBoolopCase_ == 1) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, (com.giyeok.tython.proto.And) abstractBoolop_);
    }
    if (abstractBoolopCase_ == 2) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, (com.giyeok.tython.proto.Or) abstractBoolop_);
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
    if (!(obj instanceof com.giyeok.tython.proto.AbstractBoolop)) {
      return super.equals(obj);
    }
    com.giyeok.tython.proto.AbstractBoolop other = (com.giyeok.tython.proto.AbstractBoolop) obj;

    if (!getAbstractBoolopCase().equals(other.getAbstractBoolopCase())) return false;
    switch (abstractBoolopCase_) {
      case 1:
        if (!getAnd()
            .equals(other.getAnd())) return false;
        break;
      case 2:
        if (!getOr()
            .equals(other.getOr())) return false;
        break;
      case 0:
      default:
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
    switch (abstractBoolopCase_) {
      case 1:
        hash = (37 * hash) + AND_FIELD_NUMBER;
        hash = (53 * hash) + getAnd().hashCode();
        break;
      case 2:
        hash = (37 * hash) + OR_FIELD_NUMBER;
        hash = (53 * hash) + getOr().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.AbstractBoolop parseFrom(
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
  public static Builder newBuilder(com.giyeok.tython.proto.AbstractBoolop prototype) {
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
   * Protobuf type {@code com.giyeok.tython.proto.AbstractBoolop}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.giyeok.tython.proto.AbstractBoolop)
      com.giyeok.tython.proto.AbstractBoolopOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_AbstractBoolop_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_AbstractBoolop_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.giyeok.tython.proto.AbstractBoolop.class, com.giyeok.tython.proto.AbstractBoolop.Builder.class);
    }

    // Construct using com.giyeok.tython.proto.AbstractBoolop.newBuilder()
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
      abstractBoolopCase_ = 0;
      abstractBoolop_ = null;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_AbstractBoolop_descriptor;
    }

    @java.lang.Override
    public com.giyeok.tython.proto.AbstractBoolop getDefaultInstanceForType() {
      return com.giyeok.tython.proto.AbstractBoolop.getDefaultInstance();
    }

    @java.lang.Override
    public com.giyeok.tython.proto.AbstractBoolop build() {
      com.giyeok.tython.proto.AbstractBoolop result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.giyeok.tython.proto.AbstractBoolop buildPartial() {
      com.giyeok.tython.proto.AbstractBoolop result = new com.giyeok.tython.proto.AbstractBoolop(this);
      if (abstractBoolopCase_ == 1) {
        if (andBuilder_ == null) {
          result.abstractBoolop_ = abstractBoolop_;
        } else {
          result.abstractBoolop_ = andBuilder_.build();
        }
      }
      if (abstractBoolopCase_ == 2) {
        if (orBuilder_ == null) {
          result.abstractBoolop_ = abstractBoolop_;
        } else {
          result.abstractBoolop_ = orBuilder_.build();
        }
      }
      result.abstractBoolopCase_ = abstractBoolopCase_;
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
      if (other instanceof com.giyeok.tython.proto.AbstractBoolop) {
        return mergeFrom((com.giyeok.tython.proto.AbstractBoolop)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.giyeok.tython.proto.AbstractBoolop other) {
      if (other == com.giyeok.tython.proto.AbstractBoolop.getDefaultInstance()) return this;
      switch (other.getAbstractBoolopCase()) {
        case AND: {
          mergeAnd(other.getAnd());
          break;
        }
        case OR: {
          mergeOr(other.getOr());
          break;
        }
        case ABSTRACTBOOLOP_NOT_SET: {
          break;
        }
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
      com.giyeok.tython.proto.AbstractBoolop parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.giyeok.tython.proto.AbstractBoolop) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int abstractBoolopCase_ = 0;
    private java.lang.Object abstractBoolop_;
    public AbstractBoolopCase
        getAbstractBoolopCase() {
      return AbstractBoolopCase.forNumber(
          abstractBoolopCase_);
    }

    public Builder clearAbstractBoolop() {
      abstractBoolopCase_ = 0;
      abstractBoolop_ = null;
      onChanged();
      return this;
    }


    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.And, com.giyeok.tython.proto.And.Builder, com.giyeok.tython.proto.AndOrBuilder> andBuilder_;
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     * @return Whether the and field is set.
     */
    @java.lang.Override
    public boolean hasAnd() {
      return abstractBoolopCase_ == 1;
    }
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     * @return The and.
     */
    @java.lang.Override
    public com.giyeok.tython.proto.And getAnd() {
      if (andBuilder_ == null) {
        if (abstractBoolopCase_ == 1) {
          return (com.giyeok.tython.proto.And) abstractBoolop_;
        }
        return com.giyeok.tython.proto.And.getDefaultInstance();
      } else {
        if (abstractBoolopCase_ == 1) {
          return andBuilder_.getMessage();
        }
        return com.giyeok.tython.proto.And.getDefaultInstance();
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     */
    public Builder setAnd(com.giyeok.tython.proto.And value) {
      if (andBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        abstractBoolop_ = value;
        onChanged();
      } else {
        andBuilder_.setMessage(value);
      }
      abstractBoolopCase_ = 1;
      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     */
    public Builder setAnd(
        com.giyeok.tython.proto.And.Builder builderForValue) {
      if (andBuilder_ == null) {
        abstractBoolop_ = builderForValue.build();
        onChanged();
      } else {
        andBuilder_.setMessage(builderForValue.build());
      }
      abstractBoolopCase_ = 1;
      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     */
    public Builder mergeAnd(com.giyeok.tython.proto.And value) {
      if (andBuilder_ == null) {
        if (abstractBoolopCase_ == 1 &&
            abstractBoolop_ != com.giyeok.tython.proto.And.getDefaultInstance()) {
          abstractBoolop_ = com.giyeok.tython.proto.And.newBuilder((com.giyeok.tython.proto.And) abstractBoolop_)
              .mergeFrom(value).buildPartial();
        } else {
          abstractBoolop_ = value;
        }
        onChanged();
      } else {
        if (abstractBoolopCase_ == 1) {
          andBuilder_.mergeFrom(value);
        }
        andBuilder_.setMessage(value);
      }
      abstractBoolopCase_ = 1;
      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     */
    public Builder clearAnd() {
      if (andBuilder_ == null) {
        if (abstractBoolopCase_ == 1) {
          abstractBoolopCase_ = 0;
          abstractBoolop_ = null;
          onChanged();
        }
      } else {
        if (abstractBoolopCase_ == 1) {
          abstractBoolopCase_ = 0;
          abstractBoolop_ = null;
        }
        andBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     */
    public com.giyeok.tython.proto.And.Builder getAndBuilder() {
      return getAndFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     */
    @java.lang.Override
    public com.giyeok.tython.proto.AndOrBuilder getAndOrBuilder() {
      if ((abstractBoolopCase_ == 1) && (andBuilder_ != null)) {
        return andBuilder_.getMessageOrBuilder();
      } else {
        if (abstractBoolopCase_ == 1) {
          return (com.giyeok.tython.proto.And) abstractBoolop_;
        }
        return com.giyeok.tython.proto.And.getDefaultInstance();
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.And and = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.And, com.giyeok.tython.proto.And.Builder, com.giyeok.tython.proto.AndOrBuilder> 
        getAndFieldBuilder() {
      if (andBuilder_ == null) {
        if (!(abstractBoolopCase_ == 1)) {
          abstractBoolop_ = com.giyeok.tython.proto.And.getDefaultInstance();
        }
        andBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.giyeok.tython.proto.And, com.giyeok.tython.proto.And.Builder, com.giyeok.tython.proto.AndOrBuilder>(
                (com.giyeok.tython.proto.And) abstractBoolop_,
                getParentForChildren(),
                isClean());
        abstractBoolop_ = null;
      }
      abstractBoolopCase_ = 1;
      onChanged();;
      return andBuilder_;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.Or, com.giyeok.tython.proto.Or.Builder, com.giyeok.tython.proto.OrOrBuilder> orBuilder_;
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     * @return Whether the or field is set.
     */
    @java.lang.Override
    public boolean hasOr() {
      return abstractBoolopCase_ == 2;
    }
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     * @return The or.
     */
    @java.lang.Override
    public com.giyeok.tython.proto.Or getOr() {
      if (orBuilder_ == null) {
        if (abstractBoolopCase_ == 2) {
          return (com.giyeok.tython.proto.Or) abstractBoolop_;
        }
        return com.giyeok.tython.proto.Or.getDefaultInstance();
      } else {
        if (abstractBoolopCase_ == 2) {
          return orBuilder_.getMessage();
        }
        return com.giyeok.tython.proto.Or.getDefaultInstance();
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     */
    public Builder setOr(com.giyeok.tython.proto.Or value) {
      if (orBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        abstractBoolop_ = value;
        onChanged();
      } else {
        orBuilder_.setMessage(value);
      }
      abstractBoolopCase_ = 2;
      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     */
    public Builder setOr(
        com.giyeok.tython.proto.Or.Builder builderForValue) {
      if (orBuilder_ == null) {
        abstractBoolop_ = builderForValue.build();
        onChanged();
      } else {
        orBuilder_.setMessage(builderForValue.build());
      }
      abstractBoolopCase_ = 2;
      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     */
    public Builder mergeOr(com.giyeok.tython.proto.Or value) {
      if (orBuilder_ == null) {
        if (abstractBoolopCase_ == 2 &&
            abstractBoolop_ != com.giyeok.tython.proto.Or.getDefaultInstance()) {
          abstractBoolop_ = com.giyeok.tython.proto.Or.newBuilder((com.giyeok.tython.proto.Or) abstractBoolop_)
              .mergeFrom(value).buildPartial();
        } else {
          abstractBoolop_ = value;
        }
        onChanged();
      } else {
        if (abstractBoolopCase_ == 2) {
          orBuilder_.mergeFrom(value);
        }
        orBuilder_.setMessage(value);
      }
      abstractBoolopCase_ = 2;
      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     */
    public Builder clearOr() {
      if (orBuilder_ == null) {
        if (abstractBoolopCase_ == 2) {
          abstractBoolopCase_ = 0;
          abstractBoolop_ = null;
          onChanged();
        }
      } else {
        if (abstractBoolopCase_ == 2) {
          abstractBoolopCase_ = 0;
          abstractBoolop_ = null;
        }
        orBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     */
    public com.giyeok.tython.proto.Or.Builder getOrBuilder() {
      return getOrFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     */
    @java.lang.Override
    public com.giyeok.tython.proto.OrOrBuilder getOrOrBuilder() {
      if ((abstractBoolopCase_ == 2) && (orBuilder_ != null)) {
        return orBuilder_.getMessageOrBuilder();
      } else {
        if (abstractBoolopCase_ == 2) {
          return (com.giyeok.tython.proto.Or) abstractBoolop_;
        }
        return com.giyeok.tython.proto.Or.getDefaultInstance();
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.Or or = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.Or, com.giyeok.tython.proto.Or.Builder, com.giyeok.tython.proto.OrOrBuilder> 
        getOrFieldBuilder() {
      if (orBuilder_ == null) {
        if (!(abstractBoolopCase_ == 2)) {
          abstractBoolop_ = com.giyeok.tython.proto.Or.getDefaultInstance();
        }
        orBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.giyeok.tython.proto.Or, com.giyeok.tython.proto.Or.Builder, com.giyeok.tython.proto.OrOrBuilder>(
                (com.giyeok.tython.proto.Or) abstractBoolop_,
                getParentForChildren(),
                isClean());
        abstractBoolop_ = null;
      }
      abstractBoolopCase_ = 2;
      onChanged();;
      return orBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.giyeok.tython.proto.AbstractBoolop)
  }

  // @@protoc_insertion_point(class_scope:com.giyeok.tython.proto.AbstractBoolop)
  private static final com.giyeok.tython.proto.AbstractBoolop DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.giyeok.tython.proto.AbstractBoolop();
  }

  public static com.giyeok.tython.proto.AbstractBoolop getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AbstractBoolop>
      PARSER = new com.google.protobuf.AbstractParser<AbstractBoolop>() {
    @java.lang.Override
    public AbstractBoolop parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AbstractBoolop(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AbstractBoolop> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AbstractBoolop> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.giyeok.tython.proto.AbstractBoolop getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

