package com.panjin.spring.jinq.config;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * @author panjin
 */
@Configuration
public class JinqProviderConfiguration {

    @Bean
    @Autowired
    JinqJPAStreamProvider jinqProvider(EntityManagerFactory emf) {
        return new JinqJPAStreamProvider(emf);
    }
}
