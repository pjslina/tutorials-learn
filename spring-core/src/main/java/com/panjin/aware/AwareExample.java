package com.panjin.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The Aware interface is a mix of callback, listener, and observer design patterns.
 * BeanNameAware 使对象知道容器中定义的 bean 名称。
 * BeanNameAware 的典型用例可能是获取 bean 名称以进行日志记录或连接。
 * BeanFactoryAware 用于注入 BeanFactory 对象。
 * 对于 BeanFactoryAware，它可能是从遗留代码中使用 spring bean 的能力。
 * 在大多数情况下，我们应该避免使用任何 Aware 接口，除非我们需要它们。
 * @author panjin
 */
public class AwareExample {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MyBeanName myBeanName = context.getBean(MyBeanName.class);

        MyBeanFactory myBeanFactory = context.getBean(MyBeanFactory.class);
        myBeanFactory.getMyBeanName();
    }
}
