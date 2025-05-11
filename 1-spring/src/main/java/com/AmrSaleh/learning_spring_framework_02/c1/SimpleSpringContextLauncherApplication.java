package com.AmrSaleh.learning_spring_framework_02.c1;
import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class SimpleSpringContextLauncherApplication {
    public static void main(String[] args) {
        try(var context= new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)){
          Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
          var maxValue= context.getBean(BusinessCalculationService.class).findMax();
          System.out.println("Max Value is: "+ maxValue);
        }
    }
}