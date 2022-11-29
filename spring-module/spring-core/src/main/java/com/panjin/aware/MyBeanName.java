package com.panjin.aware;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @author panjin
 */
public class MyBeanName implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }
}
