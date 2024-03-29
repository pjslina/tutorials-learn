// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: commodity_price.proto

package com.panjin.grpc.errorhandling;

/**
 * Protobuf type {@code commodityprice.ErrorResponse}
 */
public final class ErrorResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:commodityprice.ErrorResponse)
    ErrorResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ErrorResponse.newBuilder() to construct.
  private ErrorResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ErrorResponse() {
    commodityName_ = "";
    accessToken_ = "";
    expectedToken_ = "";
    expectedValue_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ErrorResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.panjin.grpc.errorhandling.CommodityPriceProto.internal_static_commodityprice_ErrorResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.panjin.grpc.errorhandling.CommodityPriceProto.internal_static_commodityprice_ErrorResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.panjin.grpc.errorhandling.ErrorResponse.class, com.panjin.grpc.errorhandling.ErrorResponse.Builder.class);
  }

  public static final int COMMODITY_NAME_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object commodityName_ = "";
  /**
   * <code>string commodity_name = 1;</code>
   * @return The commodityName.
   */
  @java.lang.Override
  public java.lang.String getCommodityName() {
    java.lang.Object ref = commodityName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      commodityName_ = s;
      return s;
    }
  }
  /**
   * <code>string commodity_name = 1;</code>
   * @return The bytes for commodityName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCommodityNameBytes() {
    java.lang.Object ref = commodityName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      commodityName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ACCESS_TOKEN_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object accessToken_ = "";
  /**
   * <code>string access_token = 2;</code>
   * @return The accessToken.
   */
  @java.lang.Override
  public java.lang.String getAccessToken() {
    java.lang.Object ref = accessToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      accessToken_ = s;
      return s;
    }
  }
  /**
   * <code>string access_token = 2;</code>
   * @return The bytes for accessToken.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAccessTokenBytes() {
    java.lang.Object ref = accessToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      accessToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EXPECTED_TOKEN_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object expectedToken_ = "";
  /**
   * <code>string expected_token = 3;</code>
   * @return The expectedToken.
   */
  @java.lang.Override
  public java.lang.String getExpectedToken() {
    java.lang.Object ref = expectedToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      expectedToken_ = s;
      return s;
    }
  }
  /**
   * <code>string expected_token = 3;</code>
   * @return The bytes for expectedToken.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getExpectedTokenBytes() {
    java.lang.Object ref = expectedToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      expectedToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EXPECTED_VALUE_FIELD_NUMBER = 4;
  @SuppressWarnings("serial")
  private volatile java.lang.Object expectedValue_ = "";
  /**
   * <code>string expected_value = 4;</code>
   * @return The expectedValue.
   */
  @java.lang.Override
  public java.lang.String getExpectedValue() {
    java.lang.Object ref = expectedValue_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      expectedValue_ = s;
      return s;
    }
  }
  /**
   * <code>string expected_value = 4;</code>
   * @return The bytes for expectedValue.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getExpectedValueBytes() {
    java.lang.Object ref = expectedValue_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      expectedValue_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(commodityName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, commodityName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(accessToken_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, accessToken_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(expectedToken_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, expectedToken_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(expectedValue_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, expectedValue_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(commodityName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, commodityName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(accessToken_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, accessToken_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(expectedToken_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, expectedToken_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(expectedValue_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, expectedValue_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.panjin.grpc.errorhandling.ErrorResponse)) {
      return super.equals(obj);
    }
    com.panjin.grpc.errorhandling.ErrorResponse other = (com.panjin.grpc.errorhandling.ErrorResponse) obj;

    if (!getCommodityName()
        .equals(other.getCommodityName())) return false;
    if (!getAccessToken()
        .equals(other.getAccessToken())) return false;
    if (!getExpectedToken()
        .equals(other.getExpectedToken())) return false;
    if (!getExpectedValue()
        .equals(other.getExpectedValue())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + COMMODITY_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getCommodityName().hashCode();
    hash = (37 * hash) + ACCESS_TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getAccessToken().hashCode();
    hash = (37 * hash) + EXPECTED_TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getExpectedToken().hashCode();
    hash = (37 * hash) + EXPECTED_VALUE_FIELD_NUMBER;
    hash = (53 * hash) + getExpectedValue().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.panjin.grpc.errorhandling.ErrorResponse parseFrom(
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
  public static Builder newBuilder(com.panjin.grpc.errorhandling.ErrorResponse prototype) {
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
   * Protobuf type {@code commodityprice.ErrorResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:commodityprice.ErrorResponse)
      com.panjin.grpc.errorhandling.ErrorResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.panjin.grpc.errorhandling.CommodityPriceProto.internal_static_commodityprice_ErrorResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.panjin.grpc.errorhandling.CommodityPriceProto.internal_static_commodityprice_ErrorResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.panjin.grpc.errorhandling.ErrorResponse.class, com.panjin.grpc.errorhandling.ErrorResponse.Builder.class);
    }

    // Construct using com.panjin.grpc.errorhandling.ErrorResponse.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      commodityName_ = "";
      accessToken_ = "";
      expectedToken_ = "";
      expectedValue_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.panjin.grpc.errorhandling.CommodityPriceProto.internal_static_commodityprice_ErrorResponse_descriptor;
    }

    @java.lang.Override
    public com.panjin.grpc.errorhandling.ErrorResponse getDefaultInstanceForType() {
      return com.panjin.grpc.errorhandling.ErrorResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.panjin.grpc.errorhandling.ErrorResponse build() {
      com.panjin.grpc.errorhandling.ErrorResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.panjin.grpc.errorhandling.ErrorResponse buildPartial() {
      com.panjin.grpc.errorhandling.ErrorResponse result = new com.panjin.grpc.errorhandling.ErrorResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.panjin.grpc.errorhandling.ErrorResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.commodityName_ = commodityName_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.accessToken_ = accessToken_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.expectedToken_ = expectedToken_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.expectedValue_ = expectedValue_;
      }
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
      if (other instanceof com.panjin.grpc.errorhandling.ErrorResponse) {
        return mergeFrom((com.panjin.grpc.errorhandling.ErrorResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.panjin.grpc.errorhandling.ErrorResponse other) {
      if (other == com.panjin.grpc.errorhandling.ErrorResponse.getDefaultInstance()) return this;
      if (!other.getCommodityName().isEmpty()) {
        commodityName_ = other.commodityName_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getAccessToken().isEmpty()) {
        accessToken_ = other.accessToken_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (!other.getExpectedToken().isEmpty()) {
        expectedToken_ = other.expectedToken_;
        bitField0_ |= 0x00000004;
        onChanged();
      }
      if (!other.getExpectedValue().isEmpty()) {
        expectedValue_ = other.expectedValue_;
        bitField0_ |= 0x00000008;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
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
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              commodityName_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              accessToken_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              expectedToken_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            case 34: {
              expectedValue_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000008;
              break;
            } // case 34
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object commodityName_ = "";
    /**
     * <code>string commodity_name = 1;</code>
     * @return The commodityName.
     */
    public java.lang.String getCommodityName() {
      java.lang.Object ref = commodityName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        commodityName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string commodity_name = 1;</code>
     * @return The bytes for commodityName.
     */
    public com.google.protobuf.ByteString
        getCommodityNameBytes() {
      java.lang.Object ref = commodityName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        commodityName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string commodity_name = 1;</code>
     * @param value The commodityName to set.
     * @return This builder for chaining.
     */
    public Builder setCommodityName(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      commodityName_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string commodity_name = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCommodityName() {
      commodityName_ = getDefaultInstance().getCommodityName();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string commodity_name = 1;</code>
     * @param value The bytes for commodityName to set.
     * @return This builder for chaining.
     */
    public Builder setCommodityNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      commodityName_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object accessToken_ = "";
    /**
     * <code>string access_token = 2;</code>
     * @return The accessToken.
     */
    public java.lang.String getAccessToken() {
      java.lang.Object ref = accessToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        accessToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string access_token = 2;</code>
     * @return The bytes for accessToken.
     */
    public com.google.protobuf.ByteString
        getAccessTokenBytes() {
      java.lang.Object ref = accessToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        accessToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string access_token = 2;</code>
     * @param value The accessToken to set.
     * @return This builder for chaining.
     */
    public Builder setAccessToken(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      accessToken_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string access_token = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAccessToken() {
      accessToken_ = getDefaultInstance().getAccessToken();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string access_token = 2;</code>
     * @param value The bytes for accessToken to set.
     * @return This builder for chaining.
     */
    public Builder setAccessTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      accessToken_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private java.lang.Object expectedToken_ = "";
    /**
     * <code>string expected_token = 3;</code>
     * @return The expectedToken.
     */
    public java.lang.String getExpectedToken() {
      java.lang.Object ref = expectedToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        expectedToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string expected_token = 3;</code>
     * @return The bytes for expectedToken.
     */
    public com.google.protobuf.ByteString
        getExpectedTokenBytes() {
      java.lang.Object ref = expectedToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        expectedToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string expected_token = 3;</code>
     * @param value The expectedToken to set.
     * @return This builder for chaining.
     */
    public Builder setExpectedToken(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      expectedToken_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string expected_token = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearExpectedToken() {
      expectedToken_ = getDefaultInstance().getExpectedToken();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string expected_token = 3;</code>
     * @param value The bytes for expectedToken to set.
     * @return This builder for chaining.
     */
    public Builder setExpectedTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      expectedToken_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }

    private java.lang.Object expectedValue_ = "";
    /**
     * <code>string expected_value = 4;</code>
     * @return The expectedValue.
     */
    public java.lang.String getExpectedValue() {
      java.lang.Object ref = expectedValue_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        expectedValue_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string expected_value = 4;</code>
     * @return The bytes for expectedValue.
     */
    public com.google.protobuf.ByteString
        getExpectedValueBytes() {
      java.lang.Object ref = expectedValue_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        expectedValue_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string expected_value = 4;</code>
     * @param value The expectedValue to set.
     * @return This builder for chaining.
     */
    public Builder setExpectedValue(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      expectedValue_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>string expected_value = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearExpectedValue() {
      expectedValue_ = getDefaultInstance().getExpectedValue();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }
    /**
     * <code>string expected_value = 4;</code>
     * @param value The bytes for expectedValue to set.
     * @return This builder for chaining.
     */
    public Builder setExpectedValueBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      expectedValue_ = value;
      bitField0_ |= 0x00000008;
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


    // @@protoc_insertion_point(builder_scope:commodityprice.ErrorResponse)
  }

  // @@protoc_insertion_point(class_scope:commodityprice.ErrorResponse)
  private static final com.panjin.grpc.errorhandling.ErrorResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.panjin.grpc.errorhandling.ErrorResponse();
  }

  public static com.panjin.grpc.errorhandling.ErrorResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ErrorResponse>
      PARSER = new com.google.protobuf.AbstractParser<ErrorResponse>() {
    @java.lang.Override
    public ErrorResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<ErrorResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ErrorResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.panjin.grpc.errorhandling.ErrorResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

