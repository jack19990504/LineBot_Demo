package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(* com.example.demo.controller.rest..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("=====before advice starts=====");
        String method = getMethodName(joinPoint);
        System.out.println(method);
        if(method.equals("ReceiveMessage3")){
 
        }
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        System.out.println("=====before advice ends=====");
    }

    private String getMethodName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getName();
    }

    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("=====after returning advice starts=====");
        if (result != null) {
            System.out.println(result);
        }
        System.out.println("=====after returning advice ends=====");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("API Time spent: " + spentTime);

        return result;
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("=====after throwing advice starts=====");
        System.out.println(getMethodName(joinPoint));
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        System.out.println(throwable.getMessage());
        System.out.println("=====after throwing advice ends=====");
    }

}
