package com.AmrSaleh.learning_spring_framework_02.d1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;



@Configuration
@ComponentScan
public class SimpleSpringContextLauncherApplication {
    public static void main(String[] args) {
        try(var context= new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)){
             context.getBean(ClassB.class).doSomething();
        }
    }
}

@Component
@Lazy
class ClassB {
    private ClassA classA;
    public ClassB(ClassA classA) {
        System.out.println("ClassB constructor called");
        this.classA = classA;
    }
    public void doSomething() {
        System.out.println("ClassB is doing something");
    }
}

@Component
class ClassA{

}