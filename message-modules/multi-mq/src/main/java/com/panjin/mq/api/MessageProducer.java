package com.panjin.mq.api;

/**
 * @author panjin
 */
public interface MessageProducer<T> {

    /**
     * 发送消息
     * @param topic   topic
     * @param message 消息
     */
    void send(String topic, T message);
}
