package com.panjin.mq.server.impl;

import com.panjin.mq.api.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author panjin
 */
@Slf4j
public class RabbitMessageProducer<T> implements MessageProducer<T> {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(String topic, T message) {
        log.info("send message to rabbitmq, topic: {}, message: {}", topic, message);
        rabbitTemplate.convertAndSend(topic, message);
    }
}
