package com.panjin.function.rabbit;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

/**
 * @author panjin
 */
@SpringBootApplication
public class FunctionCompositionDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FunctionCompositionDemoApplication.class, args);
        CachingConnectionFactory bean = context.getBean(CachingConnectionFactory.class);
        System.out.println("RabbitMQ Host: " + bean.getHost());
        System.out.println("RabbitMQ Port: " + bean.getPort());
    }

    @Bean
    public Function<String, String> uppercase() {
        return v -> {
            v =  v.toUpperCase();
            System.out.println("Uppercase: " + v);
            return v;
        };
    }

    @Bean
    public Function<String, String> echo() {
        return v -> {
            System.out.println("Echo: " + v);
            return v;
        };
    }
}
