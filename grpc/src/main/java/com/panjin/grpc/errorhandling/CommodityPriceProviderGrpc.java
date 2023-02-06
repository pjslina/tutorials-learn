package com.panjin.grpc.errorhandling;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.2)",
    comments = "Source: commodity_price.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CommodityPriceProviderGrpc {

  private CommodityPriceProviderGrpc() {}

  public static final String SERVICE_NAME = "commodityprice.CommodityPriceProvider";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.panjin.grpc.errorhandling.Commodity,
      com.panjin.grpc.errorhandling.CommodityQuote> getGetBestCommodityPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBestCommodityPrice",
      requestType = com.panjin.grpc.errorhandling.Commodity.class,
      responseType = com.panjin.grpc.errorhandling.CommodityQuote.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.panjin.grpc.errorhandling.Commodity,
      com.panjin.grpc.errorhandling.CommodityQuote> getGetBestCommodityPriceMethod() {
    io.grpc.MethodDescriptor<com.panjin.grpc.errorhandling.Commodity, com.panjin.grpc.errorhandling.CommodityQuote> getGetBestCommodityPriceMethod;
    if ((getGetBestCommodityPriceMethod = CommodityPriceProviderGrpc.getGetBestCommodityPriceMethod) == null) {
      synchronized (CommodityPriceProviderGrpc.class) {
        if ((getGetBestCommodityPriceMethod = CommodityPriceProviderGrpc.getGetBestCommodityPriceMethod) == null) {
          CommodityPriceProviderGrpc.getGetBestCommodityPriceMethod = getGetBestCommodityPriceMethod =
              io.grpc.MethodDescriptor.<com.panjin.grpc.errorhandling.Commodity, com.panjin.grpc.errorhandling.CommodityQuote>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBestCommodityPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.panjin.grpc.errorhandling.Commodity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.panjin.grpc.errorhandling.CommodityQuote.getDefaultInstance()))
              .setSchemaDescriptor(new CommodityPriceProviderMethodDescriptorSupplier("getBestCommodityPrice"))
              .build();
        }
      }
    }
    return getGetBestCommodityPriceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.panjin.grpc.errorhandling.Commodity,
      com.panjin.grpc.errorhandling.StreamingCommodityQuote> getBidirectionalListOfPricesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "bidirectionalListOfPrices",
      requestType = com.panjin.grpc.errorhandling.Commodity.class,
      responseType = com.panjin.grpc.errorhandling.StreamingCommodityQuote.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.panjin.grpc.errorhandling.Commodity,
      com.panjin.grpc.errorhandling.StreamingCommodityQuote> getBidirectionalListOfPricesMethod() {
    io.grpc.MethodDescriptor<com.panjin.grpc.errorhandling.Commodity, com.panjin.grpc.errorhandling.StreamingCommodityQuote> getBidirectionalListOfPricesMethod;
    if ((getBidirectionalListOfPricesMethod = CommodityPriceProviderGrpc.getBidirectionalListOfPricesMethod) == null) {
      synchronized (CommodityPriceProviderGrpc.class) {
        if ((getBidirectionalListOfPricesMethod = CommodityPriceProviderGrpc.getBidirectionalListOfPricesMethod) == null) {
          CommodityPriceProviderGrpc.getBidirectionalListOfPricesMethod = getBidirectionalListOfPricesMethod =
              io.grpc.MethodDescriptor.<com.panjin.grpc.errorhandling.Commodity, com.panjin.grpc.errorhandling.StreamingCommodityQuote>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "bidirectionalListOfPrices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.panjin.grpc.errorhandling.Commodity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.panjin.grpc.errorhandling.StreamingCommodityQuote.getDefaultInstance()))
              .setSchemaDescriptor(new CommodityPriceProviderMethodDescriptorSupplier("bidirectionalListOfPrices"))
              .build();
        }
      }
    }
    return getBidirectionalListOfPricesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CommodityPriceProviderStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommodityPriceProviderStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommodityPriceProviderStub>() {
        @java.lang.Override
        public CommodityPriceProviderStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommodityPriceProviderStub(channel, callOptions);
        }
      };
    return CommodityPriceProviderStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CommodityPriceProviderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommodityPriceProviderBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommodityPriceProviderBlockingStub>() {
        @java.lang.Override
        public CommodityPriceProviderBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommodityPriceProviderBlockingStub(channel, callOptions);
        }
      };
    return CommodityPriceProviderBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CommodityPriceProviderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommodityPriceProviderFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommodityPriceProviderFutureStub>() {
        @java.lang.Override
        public CommodityPriceProviderFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommodityPriceProviderFutureStub(channel, callOptions);
        }
      };
    return CommodityPriceProviderFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CommodityPriceProviderImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBestCommodityPrice(com.panjin.grpc.errorhandling.Commodity request,
        io.grpc.stub.StreamObserver<com.panjin.grpc.errorhandling.CommodityQuote> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBestCommodityPriceMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.panjin.grpc.errorhandling.Commodity> bidirectionalListOfPrices(
        io.grpc.stub.StreamObserver<com.panjin.grpc.errorhandling.StreamingCommodityQuote> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getBidirectionalListOfPricesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBestCommodityPriceMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.panjin.grpc.errorhandling.Commodity,
                com.panjin.grpc.errorhandling.CommodityQuote>(
                  this, METHODID_GET_BEST_COMMODITY_PRICE)))
          .addMethod(
            getBidirectionalListOfPricesMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                com.panjin.grpc.errorhandling.Commodity,
                com.panjin.grpc.errorhandling.StreamingCommodityQuote>(
                  this, METHODID_BIDIRECTIONAL_LIST_OF_PRICES)))
          .build();
    }
  }

  /**
   */
  public static final class CommodityPriceProviderStub extends io.grpc.stub.AbstractAsyncStub<CommodityPriceProviderStub> {
    private CommodityPriceProviderStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommodityPriceProviderStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommodityPriceProviderStub(channel, callOptions);
    }

    /**
     */
    public void getBestCommodityPrice(com.panjin.grpc.errorhandling.Commodity request,
        io.grpc.stub.StreamObserver<com.panjin.grpc.errorhandling.CommodityQuote> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBestCommodityPriceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.panjin.grpc.errorhandling.Commodity> bidirectionalListOfPrices(
        io.grpc.stub.StreamObserver<com.panjin.grpc.errorhandling.StreamingCommodityQuote> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getBidirectionalListOfPricesMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class CommodityPriceProviderBlockingStub extends io.grpc.stub.AbstractBlockingStub<CommodityPriceProviderBlockingStub> {
    private CommodityPriceProviderBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommodityPriceProviderBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommodityPriceProviderBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.panjin.grpc.errorhandling.CommodityQuote getBestCommodityPrice(com.panjin.grpc.errorhandling.Commodity request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBestCommodityPriceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CommodityPriceProviderFutureStub extends io.grpc.stub.AbstractFutureStub<CommodityPriceProviderFutureStub> {
    private CommodityPriceProviderFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommodityPriceProviderFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommodityPriceProviderFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.panjin.grpc.errorhandling.CommodityQuote> getBestCommodityPrice(
        com.panjin.grpc.errorhandling.Commodity request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBestCommodityPriceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BEST_COMMODITY_PRICE = 0;
  private static final int METHODID_BIDIRECTIONAL_LIST_OF_PRICES = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CommodityPriceProviderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CommodityPriceProviderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BEST_COMMODITY_PRICE:
          serviceImpl.getBestCommodityPrice((com.panjin.grpc.errorhandling.Commodity) request,
              (io.grpc.stub.StreamObserver<com.panjin.grpc.errorhandling.CommodityQuote>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BIDIRECTIONAL_LIST_OF_PRICES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.bidirectionalListOfPrices(
              (io.grpc.stub.StreamObserver<com.panjin.grpc.errorhandling.StreamingCommodityQuote>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CommodityPriceProviderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CommodityPriceProviderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.panjin.grpc.errorhandling.CommodityPriceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CommodityPriceProvider");
    }
  }

  private static final class CommodityPriceProviderFileDescriptorSupplier
      extends CommodityPriceProviderBaseDescriptorSupplier {
    CommodityPriceProviderFileDescriptorSupplier() {}
  }

  private static final class CommodityPriceProviderMethodDescriptorSupplier
      extends CommodityPriceProviderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CommodityPriceProviderMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CommodityPriceProviderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CommodityPriceProviderFileDescriptorSupplier())
              .addMethod(getGetBestCommodityPriceMethod())
              .addMethod(getBidirectionalListOfPricesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
