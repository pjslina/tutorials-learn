// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: commodity_price.proto

package com.panjin.grpc.errorhandling;

public interface CommodityQuoteOrBuilder extends
    // @@protoc_insertion_point(interface_extends:commodityprice.CommodityQuote)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string commodity_name = 1;</code>
   * @return The commodityName.
   */
  java.lang.String getCommodityName();
  /**
   * <code>string commodity_name = 1;</code>
   * @return The bytes for commodityName.
   */
  com.google.protobuf.ByteString
      getCommodityNameBytes();

  /**
   * <code>string producer_name = 2;</code>
   * @return The producerName.
   */
  java.lang.String getProducerName();
  /**
   * <code>string producer_name = 2;</code>
   * @return The bytes for producerName.
   */
  com.google.protobuf.ByteString
      getProducerNameBytes();

  /**
   * <code>double price = 3;</code>
   * @return The price.
   */
  double getPrice();
}
