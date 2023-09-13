package com.panjin.springamqp.errorhandling.producer;

import com.panjin.springamqp.errorhandling.configuration.SimpleDlqAmqpConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author panjin
 */
@Service
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);
    private int messageNumber = 0;
    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage() {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(SimpleDlqAmqpConfiguration.EXCHANGE_MESSAGES, SimpleDlqAmqpConfiguration.QUEUE_MESSAGES, "Some message id:" + messageNumber++);
    }
}
