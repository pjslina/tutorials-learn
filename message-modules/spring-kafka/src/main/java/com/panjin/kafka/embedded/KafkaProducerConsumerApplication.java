package com.panjin.kafka.embedded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author panjin
 */
@SpringBootApplication
public class KafkaProducerConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerConsumerApplication.class, args);
    }

}
