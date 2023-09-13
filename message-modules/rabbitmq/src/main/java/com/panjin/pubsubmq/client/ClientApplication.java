package com.panjin.pubsubmq.client;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author panjin
 */
@SpringBootApplication
public class ClientApplication {
    private static final String MESSAGE_QUEUE = "pizza-message-queue";

    @Bean
    public Queue queue() {
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 这里将container和queue绑定，可以绑定多个queue
        container.setQueueNames(MESSAGE_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public Consumer consumer() {
        return new Consumer();
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(Consumer consumer) {
        // 这里将consumer和receiveOrder方法绑定
        return new MessageListenerAdapter(consumer, "receiveOrder");
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
