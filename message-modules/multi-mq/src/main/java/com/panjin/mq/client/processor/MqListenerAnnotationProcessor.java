//package com.panjin.mq.client.processor;
//
//import com.panjin.mq.annotation.MqListener;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.core.annotation.AnnotationUtils;
//
//import java.util.Arrays;
//
///**
// * @author panjin
// */
//@Slf4j
//public class MqListenerAnnotationProcessor implements BeanFactoryPostProcessor {
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
//        Arrays.stream(beanDefinitionNames).forEach(beanName -> {
//            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
//            String className = beanDefinition.getBeanClassName();
//            if (className != null) {
//                Class<?> clazz = null;
//                try {
//                    clazz = Class.forName(className);
//                } catch (ClassNotFoundException e) {
//                    log.error("MqListenerAnnotationProcessor postProcessBeanFactory error", e);
//                }
//                if (clazz != null && clazz.isAnnotationPresent(MqListener.class)) {
//                    MqListener mqListenerAnnotation = clazz.getAnnotation(MqListener.class);
//                    MqListener mqListener = AnnotationUtils.synthesizeAnnotation(RabbitListener.class, null);
//                }
//            }
//        });
//    }
//}
