package com.panjin.autowire.sample;

import org.springframework.stereotype.Component;

/**
 * @author panjin
 */
@FormatterType("Foo")
@Component
public class FooFormatter implements Formatter {

    @Override
    public String format() {
        return "foo";
    }

}
