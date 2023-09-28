package com.panjin.function.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

/**
 * @author panjin
 */
@SpringBootApplication
public class FunctionCompositionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionCompositionDemoApplication.class, args);
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
