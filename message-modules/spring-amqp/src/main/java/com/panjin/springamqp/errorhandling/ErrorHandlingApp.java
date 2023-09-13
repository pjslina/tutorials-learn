package com.panjin.springamqp.errorhandling;

import com.panjin.springamqp.errorhandling.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author panjin
 */
@SpringBootApplication
@EnableScheduling
public class ErrorHandlingApp {

    @Autowired
    MessageProducer messageProducer;

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlingApp.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        messageProducer.sendMessage();
    }
}
