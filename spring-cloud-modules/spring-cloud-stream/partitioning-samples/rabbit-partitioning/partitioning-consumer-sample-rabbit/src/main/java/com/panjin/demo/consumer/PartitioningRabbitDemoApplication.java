package com.panjin.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

/**
 * @author panjin
 */
@SpringBootApplication
public class PartitioningRabbitDemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(PartitioningRabbitDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PartitioningRabbitDemoApplication.class, args);
    }

    @Bean
    public Consumer<Message<String>> listen() {
        return message -> logger.info(message.getPayload() + " received from partition "
                + message.getHeaders().get(AmqpHeaders.CONSUMER_QUEUE));
    }

}
