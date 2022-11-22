package com.panjin.caching.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * SpringCache的目的是解耦，定义接口，实现可以是Redis\Ehcache\Guava等等
 * 默认情况下,添加了jar依赖和@EnableCaching注解，SpringBoot会自动配置RedisCacheManager
 * @author panjin
 */
@SpringBootApplication
@EnableCaching
public class RedisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class, args);
    }
}
