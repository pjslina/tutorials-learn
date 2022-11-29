package com.panjin.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author panjin
 */
@Configuration
@ComponentScan(basePackages="com.panjin.primary")
public class Config {

    @Bean
    public Employee johnEmployee(){
        return new Employee("John");
    }

    @Bean
    @Primary
    public Employee tonyEmployee(){
        return new Employee("Tony");
    }
}
