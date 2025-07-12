package com.spring.boot.learn_spring_aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCutConfig {
    
    @Pointcut("bean(*Service*)")
    public void allPackageConfigUsingBean(){}

    @Pointcut("@annotation(com.spring.boot.learn_spring_aop.annotations.TrackTime)")
    public void trackTimeAnnotation(){}
}
