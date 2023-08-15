package com.panjin.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.AbstractResourceBasedMessageSource;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 这种方式每次不需要每次都读取文件，但是需要增加一种语言，就需要修改一下代码
 * @author panjin
 */
public class JsonMessageSource2 extends AbstractResourceBasedMessageSource {
    private final Map<String, Map<String, String>> messages = new HashMap<>();
    private final String baseName;
    private final ObjectMapper objectMapper;

    public JsonMessageSource2(String baseName, ObjectMapper objectMapper) {
        this.baseName = baseName;
        this.objectMapper = objectMapper;
        // 加载资源文件
        loadMessages(baseName);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String language = locale.toString();
        Map<String, String> langMessages = messages.get(language);

        if (langMessages != null && langMessages.containsKey(code)) {
            String message = langMessages.get(code);
            return createMessageFormat(message, locale);
        }
        return null;
    }

    private void loadMessages(String baseName) {
        try {
            // 加载语言资源文件，这里只能用en_US，不能用en-US，因为Locale的构造函数中，如果传入的字符串包含"-"，则会报错
            messages.put("en_US", loadMessagesForLanguage(baseName + "_" + "en_US"));
            messages.put("zh_CN", loadMessagesForLanguage(baseName + "_" + "zh_CN"));
            // 添加更多的语言资源文件

        } catch (JsonProcessingException e) {
            // 处理异常，例如记录日志等
        }
    }

    private Map<String, String> loadMessagesForLanguage(String baseName) throws JsonProcessingException {
        String resourceName = baseName + ".json";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
            TypeReference<Map<String, String>> typeRef = new TypeReference<Map<String, String>>() {};

            return objectMapper.readValue(inputStream, typeRef);
        } catch (IOException e) {
            // 处理异常，例如记录日志等
        }
        return Collections.emptyMap();
    }
}
