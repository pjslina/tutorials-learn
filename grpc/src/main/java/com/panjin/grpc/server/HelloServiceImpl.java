package com.panjin.grpc.server;

import com.panjin.grpc.HelloRequest;
import com.panjin.grpc.HelloResponse;
import com.panjin.grpc.HelloServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ThreadLocalRandom;

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

        // 构造请求异常场景
        int random = ThreadLocalRandom.current().nextInt(100);
        System.out.println("生成是随机数是：" + random);
        if (random > 30) {
            responseObserver.onError(Status.UNAVAILABLE.withDescription("For retry").asRuntimeException());
        } else {
            HelloResponse response = HelloResponse.newBuilder()
                    .setGreeting(greeting)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
