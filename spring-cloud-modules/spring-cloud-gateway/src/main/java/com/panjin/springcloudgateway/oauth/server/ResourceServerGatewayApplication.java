package com.panjin.springcloudgateway.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

/**
 * 这里可以作为一个resource-server，也可以作为一个oauth-client，所有通过-p参数指定加载哪个配置文件
 * @author panjin
 */
@SpringBootApplication
@EnableWebFluxSecurity
public class ResourceServerGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceServerGatewayApplication.class,args);
    }
}
