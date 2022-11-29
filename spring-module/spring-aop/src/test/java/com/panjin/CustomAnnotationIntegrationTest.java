package com.panjin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

/**
 * SpringBootTest注解必须有@SpringBootConfiguration注解，这个注解在@SpringBootApplication中
 * 所有需要有SpringBoot的启动类
 */
@SpringBootTest
public class CustomAnnotationIntegrationTest {

    @Autowired
    private Service service;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testContextLoads() {
        int count = applicationContext.getBeanDefinitionCount();
        System.out.println("加载Bean的总量是：" + count);
        Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Test
    public void shouldApplyCustomAnnotation() throws InterruptedException {
        service.serve();;
    }
}
