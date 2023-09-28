//package com.panjin.mq.client.bytebuddy;
//
//import net.bytebuddy.agent.builder.AgentBuilder;
//import net.bytebuddy.matcher.ElementMatchers;
//
//import java.lang.instrument.Instrumentation;
//
///**
// * @author panjin
// */
//public class AnnotationAgent {
//
//    public static void premain(String targetClassName, Instrumentation inst) throws ClassNotFoundException {
//        Class<?> targetClass = Class.forName(targetClassName);
//        new AgentBuilder.Default()
//                .type(ElementMatchers.nameStartsWith(targetClassName))
//                .transform(new Transformer(targetClass))
//                .installOn(inst);
//    }
//}
