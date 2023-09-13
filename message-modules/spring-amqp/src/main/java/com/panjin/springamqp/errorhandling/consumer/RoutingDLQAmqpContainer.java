package com.panjin.springamqp.errorhandling.consumer;

import com.panjin.springamqp.errorhandling.configuration.SimpleDlqAmqpConfiguration;
import com.panjin.springamqp.errorhandling.errorhandler.BusinessException;
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
public class RoutingDLQAmqpContainer {
    private static final Logger log = LoggerFactory.getLogger(RoutingDLQAmqpContainer.class);
    private final RabbitTemplate rabbitTemplate;

    public RoutingDLQAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = SimpleDlqAmqpConfiguration.QUEUE_MESSAGES)
    public void receiveMessage(Message message) throws BusinessException {
        log.info("Received message: {}", message.toString());
        throw new BusinessException();
    }

    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessagesRequeue(Message failedMessage) {
        log.info("Received failed message, requeueing: {}", failedMessage.toString());
        rabbitTemplate.send(EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(), failedMessage);
    }
}
