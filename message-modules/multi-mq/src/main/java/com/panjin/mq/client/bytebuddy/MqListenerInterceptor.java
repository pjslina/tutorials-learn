package com.panjin.mq.client.bytebuddy;

import org.springframework.stereotype.Component;

@Component
public class MqListenerInterceptor {

//    @KafkaListener(topics = "person", groupId = "person")
    public void kafkaListener(String message) {
        // 处理Kafka消息
        System.out.println("Received Kafka Message: " + message);
    }

//    @RabbitListener(queues = "person")
    public void rabbitListener(String message) {
        // 处理RabbitMQ消息
        System.out.println("Received RabbitMQ Message: " + message);
    }
}
