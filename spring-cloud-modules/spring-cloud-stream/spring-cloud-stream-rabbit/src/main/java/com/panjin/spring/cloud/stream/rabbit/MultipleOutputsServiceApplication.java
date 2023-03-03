package com.panjin.spring.cloud.stream.rabbit;

import com.panjin.spring.cloud.stream.rabbit.processor.MyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 这里使用的是过时的方法
 * springcloudstream从3.1.X之后使用的函数式编程，后续使用新的方式编程
 * 这个只是做一个参数，新版本不推荐使用这个方式
 * @author panjin
 */
@SpringBootApplication
@EnableBinding(MyProcessor.class)
public class MultipleOutputsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleOutputsServiceApplication.class, args);
    }

    @Autowired
    private MyProcessor processor;

    /**
     * 值小于10将消息路由到一个通道，大于等于10，路由到另外一个通道
     * @param val
     */
    @StreamListener(MyProcessor.INPUT)
    public void routeValues(Integer val) {
        if (val < 10) {
            processor.anOutput()
                    .send(message(val));
        } else {
            processor.anotherOutput()
                    .send(message(val));
        }
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val)
                .build();
    }
}
