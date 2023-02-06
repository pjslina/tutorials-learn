package com.panjin.grpc.server;

import com.panjin.grpc.HelloRequest;
import com.panjin.grpc.HelloResponse;
import com.panjin.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author panjin
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
