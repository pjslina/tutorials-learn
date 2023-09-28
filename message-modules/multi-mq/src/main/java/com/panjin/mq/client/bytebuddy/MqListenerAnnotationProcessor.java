package com.panjin.mq.client.bytebuddy;

import com.panjin.mq.annotation.MqListener;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author panjin
 */
@Component
public class MqListenerAnnotationProcessor implements BeanPostProcessor {

    private final Environment environment;

    public MqListenerAnnotationProcessor(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Class<?> clazz = bean.getClass();
        for (var method : clazz.getDeclaredMethods()) {
            MqListener mqListenerAnnotation = method.getAnnotation(MqListener.class);
            if (mqListenerAnnotation != null) {
                String mqType = environment.getProperty("mq.type"); // 从配置文件中获取MQ类型
                if (StringUtils.hasText(mqType)) {
                    addMqListenerAnnotation(method, mqType, mqListenerAnnotation.topic());
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    private void addMqListenerAnnotation(java.lang.reflect.Method method, String mqType, String topic) {
        DynamicType.Builder<?> builder = new ByteBuddy()
                .redefine(method.getDeclaringClass())
                .method(ElementMatchers.is(method))
                .intercept(MethodDelegation.to(MqListenerInterceptor.class));
        if ("kafka".equalsIgnoreCase(mqType)) {
            builder.annotateType(AnnotationDescription
                    .Builder.ofType(KafkaListener.class)
                    .define("value", topic)
                    .build());
        } else if ("rabbitmq".equalsIgnoreCase(mqType)) {
            builder = builder.annotateType(AnnotationDescription
                    .Builder.ofType(RabbitListener.class)
                    .defineArray("queues", topic)
                    .build());
        }
        builder.make()
                .load(getClass().getClassLoader())
                .getLoaded();
    }
}
