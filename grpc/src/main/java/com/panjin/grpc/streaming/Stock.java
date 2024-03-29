// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: stock_quote.proto

package com.panjin.grpc.streaming;

/**
 * Protobuf type {@code stockquote.Stock}
 */
public final class Stock extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:stockquote.Stock)
    StockOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Stock.newBuilder() to construct.
  private Stock(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Stock() {
    tickerSymbol_ = "";
    companyName_ = "";
    description_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Stock();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.panjin.grpc.streaming.StockQuoteProto.internal_static_stockquote_Stock_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.panjin.grpc.streaming.StockQuoteProto.internal_static_stockquote_Stock_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.panjin.grpc.streaming.Stock.class, com.panjin.grpc.streaming.Stock.Builder.class);
  }

  public static final int TICKER_SYMBOL_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object tickerSymbol_ = "";
  /**
   * <code>string ticker_symbol = 1;</code>
   * @return The tickerSymbol.
   */
  @java.lang.Override
  public java.lang.String getTickerSymbol() {
    java.lang.Object ref = tickerSymbol_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      tickerSymbol_ = s;
      return s;
    }
  }
  /**
   * <code>string ticker_symbol = 1;</code>
   * @return The bytes for tickerSymbol.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTickerSymbolBytes() {
    java.lang.Object ref = tickerSymbol_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      tickerSymbol_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int COMPANY_NAME_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object companyName_ = "";
  /**
   * <code>string company_name = 2;</code>
   * @return The companyName.
   */
  @java.lang.Override
  public java.lang.String getCompanyName() {
    java.lang.Object ref = companyName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      companyName_ = s;
      return s;
    }
  }
  /**
   * <code>string company_name = 2;</code>
   * @return The bytes for companyName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCompanyNameBytes() {
    java.lang.Object ref = companyName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      companyName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DESCRIPTION_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object description_ = "";
  /**
   * <code>string description = 3;</code>
   * @return The description.
   */
  @java.lang.Override
  public java.lang.String getDescription() {
    java.lang.Object ref = description_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      description_ = s;
      return s;
    }
  }
  /**
   * <code>string description = 3;</code>
   * @return The bytes for description.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDescriptionBytes() {
    java.lang.Object ref = description_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      description_ = b;
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(tickerSymbol_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, tickerSymbol_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(companyName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, companyName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(description_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, description_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(tickerSymbol_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, tickerSymbol_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(companyName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, companyName_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(description_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, description_);
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
    if (!(obj instanceof com.panjin.grpc.streaming.Stock)) {
      return super.equals(obj);
    }
    com.panjin.grpc.streaming.Stock other = (com.panjin.grpc.streaming.Stock) obj;

    if (!getTickerSymbol()
        .equals(other.getTickerSymbol())) return false;
    if (!getCompanyName()
        .equals(other.getCompanyName())) return false;
    if (!getDescription()
        .equals(other.getDescription())) return false;
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
    hash = (37 * hash) + TICKER_SYMBOL_FIELD_NUMBER;
    hash = (53 * hash) + getTickerSymbol().hashCode();
    hash = (37 * hash) + COMPANY_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getCompanyName().hashCode();
    hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
    hash = (53 * hash) + getDescription().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.panjin.grpc.streaming.Stock parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.panjin.grpc.streaming.Stock parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.panjin.grpc.streaming.Stock parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.panjin.grpc.streaming.Stock parseFrom(
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
  public static Builder newBuilder(com.panjin.grpc.streaming.Stock prototype) {
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
   * Protobuf type {@code stockquote.Stock}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:stockquote.Stock)
      com.panjin.grpc.streaming.StockOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.panjin.grpc.streaming.StockQuoteProto.internal_static_stockquote_Stock_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.panjin.grpc.streaming.StockQuoteProto.internal_static_stockquote_Stock_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.panjin.grpc.streaming.Stock.class, com.panjin.grpc.streaming.Stock.Builder.class);
    }

    // Construct using com.panjin.grpc.streaming.Stock.newBuilder()
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
      tickerSymbol_ = "";
      companyName_ = "";
      description_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.panjin.grpc.streaming.StockQuoteProto.internal_static_stockquote_Stock_descriptor;
    }

    @java.lang.Override
    public com.panjin.grpc.streaming.Stock getDefaultInstanceForType() {
      return com.panjin.grpc.streaming.Stock.getDefaultInstance();
    }

    @java.lang.Override
    public com.panjin.grpc.streaming.Stock build() {
      com.panjin.grpc.streaming.Stock result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.panjin.grpc.streaming.Stock buildPartial() {
      com.panjin.grpc.streaming.Stock result = new com.panjin.grpc.streaming.Stock(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.panjin.grpc.streaming.Stock result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.tickerSymbol_ = tickerSymbol_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.companyName_ = companyName_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.description_ = description_;
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
      if (other instanceof com.panjin.grpc.streaming.Stock) {
        return mergeFrom((com.panjin.grpc.streaming.Stock)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.panjin.grpc.streaming.Stock other) {
      if (other == com.panjin.grpc.streaming.Stock.getDefaultInstance()) return this;
      if (!other.getTickerSymbol().isEmpty()) {
        tickerSymbol_ = other.tickerSymbol_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getCompanyName().isEmpty()) {
        companyName_ = other.companyName_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (!other.getDescription().isEmpty()) {
        description_ = other.description_;
        bitField0_ |= 0x00000004;
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
              tickerSymbol_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              companyName_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              description_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
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

    private java.lang.Object tickerSymbol_ = "";
    /**
     * <code>string ticker_symbol = 1;</code>
     * @return The tickerSymbol.
     */
    public java.lang.String getTickerSymbol() {
      java.lang.Object ref = tickerSymbol_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        tickerSymbol_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string ticker_symbol = 1;</code>
     * @return The bytes for tickerSymbol.
     */
    public com.google.protobuf.ByteString
        getTickerSymbolBytes() {
      java.lang.Object ref = tickerSymbol_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        tickerSymbol_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string ticker_symbol = 1;</code>
     * @param value The tickerSymbol to set.
     * @return This builder for chaining.
     */
    public Builder setTickerSymbol(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      tickerSymbol_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string ticker_symbol = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTickerSymbol() {
      tickerSymbol_ = getDefaultInstance().getTickerSymbol();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string ticker_symbol = 1;</code>
     * @param value The bytes for tickerSymbol to set.
     * @return This builder for chaining.
     */
    public Builder setTickerSymbolBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      tickerSymbol_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object companyName_ = "";
    /**
     * <code>string company_name = 2;</code>
     * @return The companyName.
     */
    public java.lang.String getCompanyName() {
      java.lang.Object ref = companyName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        companyName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string company_name = 2;</code>
     * @return The bytes for companyName.
     */
    public com.google.protobuf.ByteString
        getCompanyNameBytes() {
      java.lang.Object ref = companyName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        companyName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string company_name = 2;</code>
     * @param value The companyName to set.
     * @return This builder for chaining.
     */
    public Builder setCompanyName(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      companyName_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string company_name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearCompanyName() {
      companyName_ = getDefaultInstance().getCompanyName();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string company_name = 2;</code>
     * @param value The bytes for companyName to set.
     * @return This builder for chaining.
     */
    public Builder setCompanyNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      companyName_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private java.lang.Object description_ = "";
    /**
     * <code>string description = 3;</code>
     * @return The description.
     */
    public java.lang.String getDescription() {
      java.lang.Object ref = description_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        description_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string description = 3;</code>
     * @return The bytes for description.
     */
    public com.google.protobuf.ByteString
        getDescriptionBytes() {
      java.lang.Object ref = description_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        description_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string description = 3;</code>
     * @param value The description to set.
     * @return This builder for chaining.
     */
    public Builder setDescription(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      description_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string description = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearDescription() {
      description_ = getDefaultInstance().getDescription();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string description = 3;</code>
     * @param value The bytes for description to set.
     * @return This builder for chaining.
     */
    public Builder setDescriptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      description_ = value;
      bitField0_ |= 0x00000004;
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


    // @@protoc_insertion_point(builder_scope:stockquote.Stock)
  }

  // @@protoc_insertion_point(class_scope:stockquote.Stock)
  private static final com.panjin.grpc.streaming.Stock DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.panjin.grpc.streaming.Stock();
  }

  public static com.panjin.grpc.streaming.Stock getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Stock>
      PARSER = new com.google.protobuf.AbstractParser<Stock>() {
    @java.lang.Override
    public Stock parsePartialFrom(
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

  public static com.google.protobuf.Parser<Stock> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Stock> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.panjin.grpc.streaming.Stock getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

