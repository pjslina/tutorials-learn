package com.panjin.rocketmq.consumer;

import com.panjin.rocketmq.event.CartItemEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

/**
 * @author panjin
 */
@SpringBootApplication
public class CartEventConsumer {

    public static void main(String[] args) {
        SpringApplication.run(CartEventConsumer.class, args);
    }

    @Service
    @RocketMQMessageListener(topic = "cart-item-add-topic", consumerGroup = "cart-consumer_cart-item-add-topic")
    public class CardItemAddConsumer implements RocketMQListener<CartItemEvent> {
        public void onMessage(CartItemEvent addItemEvent) {
            System.out.println("Adding item: " + addItemEvent);
            // logic
        }
    }

    @Service
    @RocketMQMessageListener(topic = "cart-item-removed-topic", consumerGroup = "cart-consumer_cart-item-removed-topic")
    public class CardItemRemoveConsumer implements RocketMQListener<CartItemEvent> {
        public void onMessage(CartItemEvent removeItemEvent) {
            System.out.println("Removing item: " + removeItemEvent);
            // logic
        }
    }
}
