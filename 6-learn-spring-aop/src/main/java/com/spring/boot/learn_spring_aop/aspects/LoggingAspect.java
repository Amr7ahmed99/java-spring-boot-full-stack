package com.spring.boot.learn_spring_aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    
    private Logger logger= LoggerFactory.getLogger(getClass());
    
    @Before("execution(* com.spring.boot.learn_spring_aop.repository.*.*(..))") // PointCut (When to be applied)
    private void logMethodCallBefore(JoinPoint joinPoint){
        // Advice (What to be executed)
        logger.info("Before Aspect - {} is called with arguments {}", joinPoint, joinPoint.getArgs());
    }

    @After("com.spring.boot.learn_spring_aop.aspects.CommonPointCutConfig.allPackageConfigUsingBean()")
    private void logMethodCallAfterExecution(JoinPoint joinPoint){
        logger.info("After Aspect - {} is called with arguments {}", joinPoint, joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.spring.boot.learn_spring_aop.repository.*.*(..))", returning = "resultValue")
    private void logMethodCallAfterSuccesfulExecution(JoinPoint joinPoint, Object resultValue){
        logger.info("AfterReturning Aspect - {} has returned {}", joinPoint, resultValue);
    }

    @AfterThrowing(pointcut = "execution(* com.spring.boot.learn_spring_aop.repository.*.*(..))", throwing = "exception")
    private void logMethodCallAfterThrowingException(JoinPoint joinPoint, Exception exception){
        logger.info("AfterThrowing Aspect - {} has throwen an exception {}", joinPoint, exception);
    }
}
