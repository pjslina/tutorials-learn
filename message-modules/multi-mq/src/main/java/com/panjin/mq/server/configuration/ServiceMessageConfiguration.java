package com.panjin.mq.server.configuration;

import com.panjin.mq.api.MessageProducer;
import com.panjin.mq.server.impl.KafkaMessageProducer;
import com.panjin.mq.server.impl.RabbitMessageProducer;
import com.panjin.mq.server.impl.RocketMessageProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author panjin
 */
@Configuration
public class ServiceMessageConfiguration {

    @Bean
    @ConditionalOnProperty(name = "messaging.provider", havingValue = "kafka")
    public MessageProducer<Object> kafkaMessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        return new KafkaMessageProducer<>(kafkaTemplate);
    }

    @Bean
    @ConditionalOnProperty(name = "messaging.provider", havingValue = "rabbitmq")
    public MessageProducer<Object> rabbitMQMessageProducer(RabbitTemplate rabbitTemplate) {
        return new RabbitMessageProducer<>(rabbitTemplate);
    }

    @Bean
    @ConditionalOnProperty(name = "messaging.provider", havingValue = "kafka")
    public MessageProducer rocketMessageProducer(RocketMQTemplate rocketMQTemplate) {
        return new RocketMessageProducer(rocketMQTemplate);
    }
}
