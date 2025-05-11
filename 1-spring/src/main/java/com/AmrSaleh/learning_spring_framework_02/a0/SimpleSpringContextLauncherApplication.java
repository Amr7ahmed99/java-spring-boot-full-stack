package com.AmrSaleh.learning_spring_framework_02.a0;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SimpleSpringContextLauncherApplication {
    public static void main(String[] args) {
        try(var context= new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)){
        }
    }
}