package com.panjin.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

/**
 * 这种方式每次请求都要去读取文件，可以考虑将文件内容缓存到内存中
 * @author panjin
 */
public class JsonMessageSource extends AbstractResourceBasedMessageSource {

    private final String baseName;
    private final ObjectMapper objectMapper;

    public JsonMessageSource(String baseName, ObjectMapper objectMapper) {
        this.baseName = baseName;
        this.objectMapper = objectMapper;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String resourceName = baseName + "_" + locale.toString() + ".json";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);

        if (inputStream != null) {
            try {
                Map<String, String> messages = objectMapper.readValue(inputStream, new TypeReference<Map<String, String>>() {});
                String message = messages.get(code);
                if (StringUtils.hasText(message)) {
                    return createMessageFormat(message, locale);
                }
            } catch (IOException e) {
                // 处理异常，例如记录日志等
            }
        }

        return null;
    }
}
