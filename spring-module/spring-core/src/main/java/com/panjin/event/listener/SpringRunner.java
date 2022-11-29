package com.panjin.event.listener;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author panjin
 */
public class SpringRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(EventConfig.class);
        ctx.start();
    }
}
