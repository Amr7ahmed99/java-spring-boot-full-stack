package com.AmrSaleh.learning_spring_framework_02.f1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
@ComponentScan
public class PrePostAnnotationsContextLauncherApplication {

    public static void main(String[] args){
        try(var appContext= new AnnotationConfigApplicationContext(PrePostAnnotationsContextLauncherApplication.class)){
            Arrays.stream(appContext.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}

@Component
class SomeClass{
    private final SomeDependency someDependency;

    public SomeClass(SomeDependency someDependency){
        super();
        this.someDependency= someDependency;
        System.out.println("All dependencies are ready!");
    }

    @PostConstruct
    private void initialize(){
        this.someDependency.getReady();
    }

    @PreDestroy
    private void cleanUp(){
        System.out.println("CleanUp");
    }
}

@Component
class SomeDependency{
    public void getReady() {
        System.out.println("TODO:// add some logic here");
    }
}
