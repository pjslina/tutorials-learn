package com.panjin.spring.patterns.factory;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Bar {

    private String name;
    
    public Bar(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
