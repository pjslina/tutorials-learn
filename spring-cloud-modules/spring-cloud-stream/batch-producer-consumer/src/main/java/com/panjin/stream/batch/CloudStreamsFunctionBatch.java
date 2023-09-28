package com.panjin.stream.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author panjin
 */
@SpringBootApplication
public class CloudStreamsFunctionBatch {

    public static void main(String[] args) {
        SpringApplication.run(CloudStreamsFunctionBatch.class, args);
    }

    @Bean
    public Supplier<UUID> stringSupplier() {
        return () -> {
            var uuid = UUID.randomUUID();
            System.out.println(uuid + " -> batch-in");
            return uuid;
        };
    }

    @Bean
    public Function<List<UUID>, List<Message<String>>> digitRemovingConsumer() {
        return idBatch -> {
            System.out.println("Removed digits from batch of " + idBatch.size());
            return idBatch.stream()
                    .map(UUID::toString)
                    // Remove all digits from the UUID
                    .map(uuid -> uuid.replaceAll("\\d",""))
                    .map(noDigitString -> MessageBuilder.withPayload(noDigitString).build())
                    .collect(Collectors.toList());
        };
    }

    @KafkaListener(id = "batch-out", topics = "batch-out")
    public void listen(String in) {
        System.out.println("batch-out -> " + in);
    }
}
