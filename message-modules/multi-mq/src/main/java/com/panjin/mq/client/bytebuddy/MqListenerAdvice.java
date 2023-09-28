//package com.panjin.mq.client.bytebuddy;
//
//import net.bytebuddy.asm.Advice;
//import net.bytebuddy.description.annotation.AnnotationDescription;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
//public class MqListenerAdvice {
//
////    @Advice.OnMethodEnter
////    static void enter(@Advice.Argument(0) AnnotationDescription listener) {
////        // 将注解添加到该方法上
////        MethodDescription method =
////                (MethodDescription) TypeDescription.ForLoadedType.get(targetClass);
////        method.addAnnotation(listener);
////    }
//
//    @Advice.OnMethodEnter
//    static void enter(@Advice.Argument(0) AnnotationDescription listener,
//                      @Advice.Origin Method method) {
//
//        Method targetMethod = method.getMethod();
//
//        Annotation[] annos = targetMethod.getDeclaredAnnotations();
//        Annotation[] newAnnos = insertAnnotation(annos, listener);
//
//        targetMethod.setDeclaredAnnotations(newAnnos);
//    }
//
//    private static Annotation[] insertAnnotation(Annotation[] annos, AnnotationDescription newAnno) {
//        // 将newAnno插入数组并返回
//        Annotation[] newAnnotations = Arrays.copyOf(annos, annos.length + 1);
//        newAnnotations[annos.length] = (Annotation) newAnno;
//        return newAnnotations;
//    }
//}
