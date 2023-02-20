package com.panjin.camel.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author panjin
 */
public class App {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("camel-context.xml");
        // Keep main thread alive for some time to let application finish processing the input files.
        Thread.sleep(5000);
        applicationContext.close();
    }
}
