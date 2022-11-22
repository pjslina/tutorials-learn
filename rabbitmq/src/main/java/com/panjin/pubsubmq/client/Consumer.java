package com.panjin.pubsubmq.client;

/**
 * @author panjin
 */
public class Consumer {

    public void receiveOrder(String message) {
        System.out.printf("Order received: %s%n", message);
    }
}
