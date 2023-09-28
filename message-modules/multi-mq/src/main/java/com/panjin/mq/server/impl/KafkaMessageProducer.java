package com.panjin.mq.server.impl;

import com.panjin.mq.api.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author panjin
 */
@Slf4j
public class KafkaMessageProducer<T> implements MessageProducer<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topic, T message) {
        log.info("send message to kafka, topic: {}, message: {}", topic, message);
        kafkaTemplate.send(topic, message);
    }
}
