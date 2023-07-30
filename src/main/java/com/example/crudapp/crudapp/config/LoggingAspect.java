package com.example.crudapp.crudapp.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.example.crudapp.crudapp.Services.StudentService.*(..))")
    public void crudMethods() {

    }

    @Before("crudMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Executing Student Services: "+joinPoint.getSignature().getName());
    }

    @After("crudMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Student Services executed successfully: "+joinPoint.getSignature().getName());
    }

}
