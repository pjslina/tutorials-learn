package com.panjin.profiles;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author panjin
 */
@Configuration
@ComponentScan("com.panjin.profiles")
@PropertySource(value = "classpath:application.properties")
public class SpringProfilesConfig {

}
