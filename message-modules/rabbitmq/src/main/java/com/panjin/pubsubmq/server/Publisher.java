package com.panjin.pubsubmq.server;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.PostConstruct;

/**
 * @author panjin
 */
public class Publisher {

    private RabbitTemplate rabbitTemplate;
    private String queue;
    private String topic;

    public Publisher(RabbitTemplate rabbitTemplate, String queue, String topic) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.topic = topic;
    }

    @PostConstruct
    public void postMessages() {
        // 这里将消息发送到了queue，而不是exchange，所以消息会被发送到queue，而不是exchange
        rabbitTemplate.convertAndSend(queue, "1 Pepperoni");
        rabbitTemplate.convertAndSend(queue, "3 Margarita");
        rabbitTemplate.convertAndSend(queue, "1 Ham and Pineapple (yuck)");

        // 这里将消息发送到了exchange，而不是queue，所以消息会被发送到exchange，而不是queue
        rabbitTemplate.convertAndSend(topic, "notification", "New Deal on T-Shirts: 95% off!");
        rabbitTemplate.convertAndSend(topic, "notification", "2 for 1 on all Jeans!");
    }
}
