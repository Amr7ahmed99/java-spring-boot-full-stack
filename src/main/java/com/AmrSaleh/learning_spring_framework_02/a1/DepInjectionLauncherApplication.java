package com.AmrSaleh.learning_spring_framework_02.a1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
public class DepInjectionLauncherApplication {

    @Component
    class YourBusinessClass {
        @Autowired // field injection, spring framework will inject the dependacy
        Dependacy1 dependacy1;
        @Autowired // field injection, spring framework will inject the dependacy
        Dependacy2 dependacy2;

        // Constructor injection is preferred over field injection, because it makes the class easier to test and understand and it allows for better separation of concerns.
        public String toString(){
            return "YourBusinessClass{" +
                    "dependacy1=" + dependacy1 +
                    ", dependacy2=" + dependacy2 +
                    '}';
        }
    }

    @Component
    class YourBusinessClass2 {
        Dependacy1 dependacy1;
        Dependacy2 dependacy2;

        // Setter injection
        @Autowired
        public void setDependacy1(Dependacy1 dependacy1) {
            System.out.println("Setter injection - setDependacy1");
            this.dependacy1 = dependacy1;
        }

        @Autowired
        public void setDependacy2(Dependacy2 dependacy2) {
            System.out.println("Setter injection - setDependacy2");
            this.dependacy2 = dependacy2;
        }
    }

    @Component
    class YourBusinessClass3{
        Dependacy1 dependacy1;
        Dependacy2 dependacy2;

        // Constructor injection is preferred over field and setter injection, because it makes the class easier to test and understand and it allows for better separation of concerns.
        public YourBusinessClass3(Dependacy1 dependacy1, Dependacy2 dependacy2) {
            System.out.println("Constructor injection - YourBusinessClass3");
            this.dependacy1 = dependacy1;
            this.dependacy2 = dependacy2;
        }
    }

    @Component
    class Dependacy1 {
        // Dependency logic goes here
    }

    @Component
    class Dependacy2 {
        // Dependency logic goes here
    }


    public static void main(String[] args) {
        try(var context= new AnnotationConfigApplicationContext(DepInjectionLauncherApplication.class)){
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(YourBusinessClass3.class));
        }
    }
}
