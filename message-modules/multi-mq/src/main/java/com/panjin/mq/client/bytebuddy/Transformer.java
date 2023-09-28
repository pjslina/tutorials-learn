//package com.panjin.mq.client.bytebuddy;
//
//import com.panjin.mq.annotation.MqListener;
//import net.bytebuddy.agent.builder.AgentBuilder;
//import net.bytebuddy.asm.Advice;
//import net.bytebuddy.description.annotation.AnnotationDescription;
//import net.bytebuddy.description.annotation.AnnotationValue;
//import net.bytebuddy.description.method.MethodDescription;
//import net.bytebuddy.description.type.TypeDescription;
//import net.bytebuddy.dynamic.DynamicType;
//import net.bytebuddy.matcher.ElementMatcher;
//import net.bytebuddy.matcher.ElementMatchers;
//import net.bytebuddy.utility.JavaModule;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.kafka.annotation.KafkaListener;
//
//import java.security.ProtectionDomain;
//
///**
// * @author panjin
// */
//public class Transformer implements AgentBuilder.Transformer {
//
//    private String mqType = "rabbitmq";
//
//    Class<?> targetClass;
//
//    public Transformer(Class<?> clazz) {
//        this.targetClass = clazz;
//    }
//    @Override
//    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
//        // 找到使用@MqListener注解的方法
//        MethodDescription method = typeDescription.getDeclaredMethods()
//                .filter(ElementMatchers.isAnnotatedWith(MqListener.class))
//                .getOnly();
//        // 根据配置构建对应监听器注解
//        AnnotationDescription listener = createListenerAnnotation(method);
//        ElementMatcher.Junction<MethodDescription> methodMatcher = ElementMatchers.named(method.getName());
//        // 1. 绑定Advice到方法
//        builder = builder.visit(Advice.to(MqListenerAdvice.class).on(methodMatcher));
//        // 2. 传递参数注解
//        return builder.visit(Advice.withCustomMapping().bind(listener).to(MqListenerAdvice.class));
//    }
//
//    private AnnotationDescription createListenerAnnotation(MethodDescription method) {
//        MqListener mqListener = method.getDeclaredAnnotations().ofType(MqListener.class).load();
//
//        if ("kafka".equals(mqType)) {
//            return AnnotationDescription.Builder
//                    .ofType(KafkaListener.class)
//                    .define("topic", mqListener.topic())
//                    .build();
//        } else if ("rabbitmq".equals(mqType)) {
//            return AnnotationDescription.Builder
//                    .ofType(RabbitListener.class)
//                    .defineArray("queue", mqListener.topic())
//                    .build();
//        }
//
//        return null;
//    }
//
//}
