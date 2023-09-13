package com.panjin.springamqp.errorhandling.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.panjin.springamqp.errorhandling.configuration.SimpleDlqAmqpConfiguration.EXCHANGE_MESSAGES;
import static com.panjin.springamqp.errorhandling.configuration.SimpleDlqAmqpConfiguration.QUEUE_MESSAGES_DLQ;

/**
 * @author panjin
 */
public class SimpleDlqAmqpContainer {
    private static final Logger log = LoggerFactory.getLogger(SimpleDlqAmqpContainer.class);
    private final RabbitTemplate rabbitTemplate;

    public SimpleDlqAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessages(Message message) {
        log.info("Received failed message: {}", message.toString());
    }

    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessagesRequeue(Message failedMessage) {
        log.info("Received failed message, requeueing: {}", failedMessage.toString());
        log.info("Received failed message, requeueing: {}", failedMessage.getMessageProperties().getReceivedRoutingKey());
        rabbitTemplate.send(EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(), failedMessage);
    }
}
