package com.panjin.autowire.sample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author panjin
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        FooService fooService = ctx.getBean(FooService.class);
        fooService.doStuff();
    }
}
