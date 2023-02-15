package com.panjin.grpc.client;

import com.panjin.grpc.HelloRequest;
import com.panjin.grpc.HelloResponse;
import com.panjin.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author panjin
 */
public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                // 下面的重试设置，不对整个channel生效，重试需要通过ServiceConfig去配置
                .enableRetry().maxRetryAttempts(3)
                .usePlaintext()
            .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
          = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
            .setFirstName("PanJin")
            .setLastName("gRPC")
            .build());

        System.out.println("Response received from server:\n" + helloResponse);

        channel.shutdown();
    }
}
