package com.panjin.mq.client.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Hello {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        String str = new ByteBuddy().subclass(Object.class).method(named("toString"))
                .intercept(FixedValue.value("Hello World!")).make()
                .load(Hello.class.getClassLoader()).getLoaded().newInstance().toString();
        System.out.println(str);
    }
}
