package com.panjin.springcloudgateway.customerfilter.gatewayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author panjin
 */
@SpringBootApplication
@PropertySource("classpath:customfilters-global-application.properties")
public class Application01 {

    public static void main(String[] args) {
        SpringApplication.run(Application01.class, args);
    }
}
