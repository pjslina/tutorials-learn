package com.panjin.mq.server;

import com.panjin.mq.api.MessageProducer;
import com.panjin.mq.entity.Person;
import org.springframework.stereotype.Service;

/**
 * @author panjin
 */
@Service
public class MyMessageService {

    private final MessageProducer<Person> messageProducer;

    public MyMessageService(MessageProducer<Person> messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void sendMessage(String topic, Person message) {
        messageProducer.send(topic, message);
    }
}
