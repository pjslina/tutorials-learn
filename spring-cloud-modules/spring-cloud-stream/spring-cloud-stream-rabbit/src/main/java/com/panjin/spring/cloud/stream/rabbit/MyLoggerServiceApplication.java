package com.panjin.spring.cloud.stream.rabbit;

import com.panjin.spring.cloud.stream.rabbit.messages.TextPlainMessageConverter;
import com.panjin.spring.cloud.stream.rabbit.model.LogMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * 这里使用的是过时的方法
 * springcloudstream从3.1.X之后使用的函数式编程，后续使用新的方式编程
 * 这个只是做一个参数，新版本不推荐使用这个方式
 * @author panjin
 */
@SpringBootApplication
@EnableBinding(Processor.class)
public class MyLoggerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyLoggerServiceApplication.class, args);
    }

    /**
     * 这个bean注释掉也不会有问题，没去研究
     * @return
     */
    @Bean
    public MessageConverter providesTextPlainMessageConverter() {
        return new TextPlainMessageConverter();
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public LogMessage enrichLogMessage(LogMessage log) {
        return new LogMessage(String.format("[1]: %s", log.getMessage()));
    }
}
