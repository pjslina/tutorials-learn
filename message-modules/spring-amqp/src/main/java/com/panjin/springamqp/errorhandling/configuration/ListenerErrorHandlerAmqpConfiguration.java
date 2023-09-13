package com.panjin.springamqp.errorhandling.configuration;

import com.panjin.springamqp.errorhandling.errorhandler.CustomErrorHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

import static com.panjin.springamqp.errorhandling.configuration.SimpleDlqAmqpConfiguration.EXCHANGE_MESSAGES;
import static com.panjin.springamqp.errorhandling.configuration.SimpleDlqAmqpConfiguration.QUEUE_MESSAGES;


/**
 * @author panjin
 */
@Configuration
@ConditionalOnProperty(
  value = "amqp.configuration.current",
  havingValue = "listener-error")
public class ListenerErrorHandlerAmqpConfiguration {

    @Bean
    public ErrorHandler errorHandler() {
        return new CustomErrorHandler();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                               SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setErrorHandler(errorHandler());
        return factory;
    }

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
          .build();
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(EXCHANGE_MESSAGES);
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(QUEUE_MESSAGES);
    }
}
