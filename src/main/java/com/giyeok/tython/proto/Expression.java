// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

/**
 * Protobuf type {@code com.giyeok.tython.proto.Expression}
 */
public final class Expression extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.giyeok.tython.proto.Expression)
    ExpressionOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Expression.newBuilder() to construct.
  private Expression(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Expression() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Expression();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Expression(
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
            com.giyeok.tython.proto.AbstractExpr.Builder subBuilder = null;
            if (body_ != null) {
              subBuilder = body_.toBuilder();
            }
            body_ = input.readMessage(com.giyeok.tython.proto.AbstractExpr.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(body_);
              body_ = subBuilder.buildPartial();
            }

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
    return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Expression_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Expression_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.giyeok.tython.proto.Expression.class, com.giyeok.tython.proto.Expression.Builder.class);
  }

  public static final int BODY_FIELD_NUMBER = 1;
  private com.giyeok.tython.proto.AbstractExpr body_;
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
   * @return Whether the body field is set.
   */
  @java.lang.Override
  public boolean hasBody() {
    return body_ != null;
  }
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
   * @return The body.
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExpr getBody() {
    return body_ == null ? com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : body_;
  }
  /**
   * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
   */
  @java.lang.Override
  public com.giyeok.tython.proto.AbstractExprOrBuilder getBodyOrBuilder() {
    return getBody();
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
    if (body_ != null) {
      output.writeMessage(1, getBody());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (body_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getBody());
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
    if (!(obj instanceof com.giyeok.tython.proto.Expression)) {
      return super.equals(obj);
    }
    com.giyeok.tython.proto.Expression other = (com.giyeok.tython.proto.Expression) obj;

    if (hasBody() != other.hasBody()) return false;
    if (hasBody()) {
      if (!getBody()
          .equals(other.getBody())) return false;
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
    if (hasBody()) {
      hash = (37 * hash) + BODY_FIELD_NUMBER;
      hash = (53 * hash) + getBody().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.giyeok.tython.proto.Expression parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Expression parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.Expression parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.giyeok.tython.proto.Expression parseFrom(
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
  public static Builder newBuilder(com.giyeok.tython.proto.Expression prototype) {
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
   * Protobuf type {@code com.giyeok.tython.proto.Expression}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.giyeok.tython.proto.Expression)
      com.giyeok.tython.proto.ExpressionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Expression_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Expression_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.giyeok.tython.proto.Expression.class, com.giyeok.tython.proto.Expression.Builder.class);
    }

    // Construct using com.giyeok.tython.proto.Expression.newBuilder()
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
      if (bodyBuilder_ == null) {
        body_ = null;
      } else {
        body_ = null;
        bodyBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.giyeok.tython.proto.Python310.internal_static_com_giyeok_tython_proto_Expression_descriptor;
    }

    @java.lang.Override
    public com.giyeok.tython.proto.Expression getDefaultInstanceForType() {
      return com.giyeok.tython.proto.Expression.getDefaultInstance();
    }

    @java.lang.Override
    public com.giyeok.tython.proto.Expression build() {
      com.giyeok.tython.proto.Expression result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.giyeok.tython.proto.Expression buildPartial() {
      com.giyeok.tython.proto.Expression result = new com.giyeok.tython.proto.Expression(this);
      if (bodyBuilder_ == null) {
        result.body_ = body_;
      } else {
        result.body_ = bodyBuilder_.build();
      }
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
      if (other instanceof com.giyeok.tython.proto.Expression) {
        return mergeFrom((com.giyeok.tython.proto.Expression)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.giyeok.tython.proto.Expression other) {
      if (other == com.giyeok.tython.proto.Expression.getDefaultInstance()) return this;
      if (other.hasBody()) {
        mergeBody(other.getBody());
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
      com.giyeok.tython.proto.Expression parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.giyeok.tython.proto.Expression) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.giyeok.tython.proto.AbstractExpr body_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> bodyBuilder_;
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     * @return Whether the body field is set.
     */
    public boolean hasBody() {
      return bodyBuilder_ != null || body_ != null;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     * @return The body.
     */
    public com.giyeok.tython.proto.AbstractExpr getBody() {
      if (bodyBuilder_ == null) {
        return body_ == null ? com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : body_;
      } else {
        return bodyBuilder_.getMessage();
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     */
    public Builder setBody(com.giyeok.tython.proto.AbstractExpr value) {
      if (bodyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        body_ = value;
        onChanged();
      } else {
        bodyBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     */
    public Builder setBody(
        com.giyeok.tython.proto.AbstractExpr.Builder builderForValue) {
      if (bodyBuilder_ == null) {
        body_ = builderForValue.build();
        onChanged();
      } else {
        bodyBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     */
    public Builder mergeBody(com.giyeok.tython.proto.AbstractExpr value) {
      if (bodyBuilder_ == null) {
        if (body_ != null) {
          body_ =
            com.giyeok.tython.proto.AbstractExpr.newBuilder(body_).mergeFrom(value).buildPartial();
        } else {
          body_ = value;
        }
        onChanged();
      } else {
        bodyBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     */
    public Builder clearBody() {
      if (bodyBuilder_ == null) {
        body_ = null;
        onChanged();
      } else {
        body_ = null;
        bodyBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     */
    public com.giyeok.tython.proto.AbstractExpr.Builder getBodyBuilder() {
      
      onChanged();
      return getBodyFieldBuilder().getBuilder();
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     */
    public com.giyeok.tython.proto.AbstractExprOrBuilder getBodyOrBuilder() {
      if (bodyBuilder_ != null) {
        return bodyBuilder_.getMessageOrBuilder();
      } else {
        return body_ == null ?
            com.giyeok.tython.proto.AbstractExpr.getDefaultInstance() : body_;
      }
    }
    /**
     * <code>.com.giyeok.tython.proto.AbstractExpr body = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder> 
        getBodyFieldBuilder() {
      if (bodyBuilder_ == null) {
        bodyBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.giyeok.tython.proto.AbstractExpr, com.giyeok.tython.proto.AbstractExpr.Builder, com.giyeok.tython.proto.AbstractExprOrBuilder>(
                getBody(),
                getParentForChildren(),
                isClean());
        body_ = null;
      }
      return bodyBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.giyeok.tython.proto.Expression)
  }

  // @@protoc_insertion_point(class_scope:com.giyeok.tython.proto.Expression)
  private static final com.giyeok.tython.proto.Expression DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.giyeok.tython.proto.Expression();
  }

  public static com.giyeok.tython.proto.Expression getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Expression>
      PARSER = new com.google.protobuf.AbstractParser<Expression>() {
    @java.lang.Override
    public Expression parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Expression(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Expression> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Expression> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.giyeok.tython.proto.Expression getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
