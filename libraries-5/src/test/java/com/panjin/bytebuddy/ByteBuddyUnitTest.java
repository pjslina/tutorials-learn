package com.panjin.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ByteBuddyUnitTest {

    @Test
    void givenObject_whenToString_thenReturnHelloWorldString() throws InstantiationException, IllegalAccessException {
        DynamicType.Unloaded<Object> unloadedType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("Hello World ByteBuddy!"))
                .make();
        Class<?> dynamicType = unloadedType.load(getClass().getClassLoader()).getLoaded();
        assertEquals(dynamicType.newInstance().toString(), "Hello World ByteBuddy!");
    }

    @Test
    void givenSayHelloFoo_whenMethodDelegation_thenSayHelloBar() throws InstantiationException, IllegalAccessException {
        // ByteBuddy怎么知道调用Bar的哪个方法呢？首先匹配方法签名，返回类型，方法名和注解，如果有同样的，则使用BindingPriority注解的优先级最高的方法
        String r = new ByteBuddy().subclass(Foo.class)
                .method(named("sayHelloFoo")
                        .and(ElementMatchers.isDeclaredBy(Foo.class)
                                .and(ElementMatchers.returns(String.class))))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .sayHelloFoo();
        assertEquals(r, Bar.sayHelloBar());
    }

    @Test
    void givenMethodName_whenDefineMethod_thenCreateMethod() throws Exception {
        Class<?> type = new ByteBuddy()
                .subclass(Object.class)
                .name("MyClassName")
                .defineMethod("custom", String.class, Modifier.PUBLIC)
                .intercept(MethodDelegation.to(Bar.class))
                .defineField("x", String.class, Modifier.PUBLIC)
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded();

        Method m = type.getDeclaredMethod("custom", null);

        assertEquals(m.invoke(type.newInstance()), Bar.sayHelloBar());
        assertNotNull(type.getDeclaredField("x"));
    }

    @Test
    void givenFoo_whenRedefined_thenReturnFooRedefined() throws Exception {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(Foo.class)
                .method(named("sayHelloFoo"))
                .intercept(FixedValue.value("Hello Foo Redefined"))
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        Foo f = new Foo();
        assertEquals(f.sayHelloFoo(), "Hello Foo Redefined");
    }
}
