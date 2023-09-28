package com.panjin.stream.mutlibinder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author panjin
 */
@SpringBootApplication
public class MultibinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultibinderApplication.class, args);
    }

    @Bean
    public Function<String, String> process() {
        return String::toUpperCase;
    }

    static class TestProducer {

        private final AtomicBoolean semaphore = new AtomicBoolean(true);

        @Bean
        public Supplier<String> sendTestData() {
            return () -> this.semaphore.getAndSet(!this.semaphore.get()) ? "foo" : "bar";
        }
    }

    static class TestConsumer {

        private final Log logger = LogFactory.getLog(getClass());

        @Bean
        public Consumer<String> receive() {
            return s -> logger.info("Data received..." + s);
        }
    }
}
