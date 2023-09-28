package com.panjin.mq.server.impl;

import com.panjin.mq.api.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * @author panjin
 */
@Slf4j
public class RocketMessageProducer<T> implements MessageProducer<T> {

    private final RocketMQTemplate rocketMQTemplate;

    public RocketMessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @Override
    public void send(String topic, T message) {
        log.info("send message to rocketmq, topic: {}, message: {}", topic, message);
        rocketMQTemplate.convertAndSend(topic, message);
    }

}
