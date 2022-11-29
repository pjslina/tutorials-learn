package com.panjin.autowire.sample;

import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@FormatterType("Bar")
@Component
public class BarFormatter implements Formatter {

    @Override
    public String format() {
        return "bar";
    }

}
