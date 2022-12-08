package com.panjin.springbootcrudapp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author panjin
 */
@SpringBootApplication(scanBasePackages={"com.panjin.springbootcrudapp.application"})
@EnableJpaRepositories(basePackages="com.panjin.springbootcrudapp.application.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.panjin.springbootcrudapp.application.entities")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
