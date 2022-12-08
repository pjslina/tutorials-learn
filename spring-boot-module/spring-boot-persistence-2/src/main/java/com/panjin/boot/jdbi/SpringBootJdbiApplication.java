package com.panjin.boot.jdbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author panjin
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootJdbiApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJdbiApplication.class, args);
    }

}
