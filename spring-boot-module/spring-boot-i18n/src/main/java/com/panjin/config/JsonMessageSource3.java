package com.panjin.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

public class JsonMessageSource3 implements MessageSource {

    private final ObjectMapper objectMapper;
    private Map<String, String> messages;

    public JsonMessageSource3(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        loadMessages();
    }

    private void loadMessages() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("citiao.json");
            TypeReference<Map<String, String>> typeReference = new TypeReference<Map<String, String>>() {};
            messages = objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            // 处理异常，例如记录日志等
        }
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String message = messages.get(code);
        if (StringUtils.hasText(message)) {
            return substituteVariables(message, args);
        }
        return defaultMessage;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        String message = messages.get(code);
        if (StringUtils.hasText(message)) {
            return substituteVariables(message, args);
        }
        throw new NoSuchMessageException(code, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        String[] codes = resolvable.getCodes();
        for (String code : codes) {
            String message = messages.get(code);
            if (StringUtils.hasText(message)) {
                return substituteVariables(message, resolvable.getArguments());
            }
        }
        throw new NoSuchMessageException(codes[codes.length - 1], locale);
    }

    private String substituteVariables(String message, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            String variable = "${" + i + "}";
            String argument = String.valueOf(args[i]);
            message = message.replace(variable, argument);
        }
        return message;
    }
}

