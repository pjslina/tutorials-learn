package com.panjin.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author panjin
 */
@Configuration
public class LocaleConfig {

//    @Bean
//    public MessageSource messageSource(ObjectMapper objectMapper) {
//        JsonMessageSource messageSource = new JsonMessageSource("messages", objectMapper);
//        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
//        return messageSource;
//    }

//    @Bean
//    public MessageSource messageSource(ObjectMapper objectMapper) {
//        JsonMessageSource2 messageSource = new JsonMessageSource2("messages", objectMapper);
//        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
//        return messageSource;
//    }

//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper();
//    }

    @Bean
    public MessageSource messageSource(ObjectMapper objectMapper) {
        return new JsonMessageSource3(objectMapper);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }
}
