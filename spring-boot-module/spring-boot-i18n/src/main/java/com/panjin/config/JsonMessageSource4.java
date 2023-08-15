package com.panjin.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这种方式每次不需要每次都读取文件，但是需要增加一种语言，就需要修改一下代码
 * @author panjin
 */
public class JsonMessageSource4 extends AbstractResourceBasedMessageSource {
    private final Map<String, Map<String, String>> messages = new HashMap<>();
    private final String baseName;
    private final ObjectMapper objectMapper;

    Pattern pattern = Pattern.compile("_(.*?)\\.");

    public JsonMessageSource4(String baseName, ObjectMapper objectMapper) {
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
            Resource[] resources1 = new PathMatchingResourcePatternResolver().getResources("classpath:/i18n/*.json");
            for (Resource resource : resources1) {
                String filename = resource.getFilename();
                String languageTag = getLanguageTag(filename);
                String replace = languageTag.replace("-", "_");
                System.out.println("languageTag="+languageTag+",replace="+replace);
                InputStream inputStream = resource.getInputStream();
                messages.put(replace, loadMessagesForLanguage(inputStream));
            }
            System.out.println("messages: " + messages);
            // 加载语言资源文件
//            messages.put("en_US", loadMessagesForLanguage(baseName + "_" + "en_US"));
//            messages.put("zh_CN", loadMessagesForLanguage(baseName + "_" + "zh_CN"));
            // 添加更多的语言资源文件

        } catch (JsonProcessingException e) {
            // 处理异常，例如记录日志等
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLanguageTag(String filename) {
        Matcher matcher = pattern.matcher(filename);

        // 如果找到匹配,返回匹配组(去掉开头的_)
        if (matcher.find()) {
            return matcher.group(1);
        }

        // 未找到匹配,返回默认语言
        return "zh_CN";
    }

    private Map<String, String> loadMessagesForLanguage(InputStream inputStream) throws IOException {
        JsonNode node = objectMapper.readTree(inputStream);
        Map<String, String> msgMap = new HashMap<>(16);
        parseNode(node, "", msgMap);
        return msgMap;
    }

    private Map<String, String> loadMessagesForLanguage(String baseName) throws JsonProcessingException {
        String resourceName = baseName + ".json";
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
            JsonNode node = objectMapper.readTree(inputStream);
            Map<String, String> msgMap = new HashMap<>(16);
            parseNode(node, "", msgMap);
            return msgMap;
        } catch (IOException e) {
            // 处理异常，例如记录日志等
        }
        return Collections.emptyMap();
    }

    private void parseNode(JsonNode node, String parentKey, Map<String, String> messages) {

        // 叶子节点,保存消息值
        if (node.isValueNode()) {
            messages.put(parentKey, node.asText());
        }

        // 对象节点,递归解析属性
        else if (node.isObject()) {
            for (Iterator<String> it = node.fieldNames(); it.hasNext();) {
                String key = it.next();
                if (parentKey.isEmpty()) {
                    parseNode(node.get(key), key, messages);
                } else {
                    parseNode(node.get(key), parentKey + "." + key, messages);
                }
            }
        }

    }
}
