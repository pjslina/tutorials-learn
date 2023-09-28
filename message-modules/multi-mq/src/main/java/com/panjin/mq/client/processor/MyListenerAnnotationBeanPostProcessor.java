//package com.panjin.mq.client.processor;
//
//import com.panjin.mq.annotation.MqListener;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
///**
// * @author panjin
// */
//@Component
//public class MyListenerAnnotationBeanPostProcessor implements BeanPostProcessor {
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        Class<?> beanClass = bean.getClass();
//        MqListener mqListener = beanClass.getAnnotation(MqListener.class);
//
//        // 处理方法级注解
//        for (Method method : beanClass.getMethods()) {
//            mqListener = method.getAnnotation(MqListener.class);
//            if (mqListener != null) {
//                addMQListenerToMethod(method, mqListener);
//            }
//        }
//
//        return bean;
//    }
//
//    private void addMQListenerToMethod(Method method, MqListener mqListener) {
//
//        RabbitListener rl = convertToKafkaListener(mqListener);
//        method.setAccessible(true);
//        Annotation[] annotations = method.getDeclaredAnnotations();
//
//
//        Annotation[] newAnnotations = Arrays.copyOf(annotations, annotations.length + 1);
//        newAnnotations[annotations.length] = rl;
//        try {
//            // 使用反射获取原始注解信息
//            Field f = Method.class.getDeclaredField("declaredAnnotations");
//            f.setAccessible(true);
//
//            f.set(method, newAnnotations);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static RabbitListener getRabbitListener(MqListener mqListener) {
//        RabbitListener rabbitListener = new RabbitListener() {
//
//            @Override
//            public Class<? extends Annotation> annotationType() {
//                return null;
//            }
//
//            @Override
//            public String id() {
//                return "1";
//            }
//
//            @Override
//            public String containerFactory() {
//                return null;
//            }
//
//            @Override
//            public String[] queues() {
//                return new String[]{mqListener.topic()};
//            }
//
//            @Override
//            public Queue[] queuesToDeclare() {
//                return new Queue[0];
//            }
//
//            @Override
//            public boolean exclusive() {
//                return false;
//            }
//
//            @Override
//            public String priority() {
//                return null;
//            }
//
//            @Override
//            public String admin() {
//                return null;
//            }
//
//            @Override
//            public QueueBinding[] bindings() {
//                return new QueueBinding[0];
//            }
//
//            @Override
//            public String group() {
//                return null;
//            }
//
//            @Override
//            public String returnExceptions() {
//                return null;
//            }
//
//            @Override
//            public String errorHandler() {
//                return null;
//            }
//
//            @Override
//            public String concurrency() {
//                return null;
//            }
//
//            @Override
//            public String autoStartup() {
//                return null;
//            }
//
//            @Override
//            public String executor() {
//                return null;
//            }
//
//            @Override
//            public String ackMode() {
//                return null;
//            }
//
//            @Override
//            public String replyPostProcessor() {
//                return null;
//            }
//
//            @Override
//            public String messageConverter() {
//                return null;
//            }
//
//            @Override
//            public String replyContentType() {
//                return null;
//            }
//
//            @Override
//            public String converterWinsContentType() {
//                return null;
//            }
//        };
//        return rabbitListener;
//    }
//
//    private RabbitListener convertToKafkaListener(MqListener mqListener) {
//        return getRabbitListener(mqListener);
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        return bean;
//    }
//}
