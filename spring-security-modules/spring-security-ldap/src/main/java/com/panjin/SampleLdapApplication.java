package com.panjin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 引入了template作为视图层，所以需要引入spring-boot-starter-thymeleaf
 * 其实这个不需要也是可以的，也就是说MyController\WebConfig\resources下的templates目录、static.css目录都可以不要
 * @author panjin
 */
@SpringBootApplication
public class SampleLdapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleLdapApplication.class, args);
    }
}
