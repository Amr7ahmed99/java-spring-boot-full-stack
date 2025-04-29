package com.AmrSaleh.learning_spring_framework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person(String name, String country, Address address) {}
record Address(String firstLine, String city){}
record Game(String name, String type){}

@Configuration
public class HelloWorldConfiguration {
    // @Bean annotation is used to tell Spring that this method will return an object that should be registered as a bean in the Spring application context
    @Bean
    String name() {
        return "Amr Saleh";
    }

    @Bean
    String country() {
        return "Egypt";
    }

    @Bean
    Person person2WithMethodCall() {
        return new Person(name(), country(), address1());
    }

    @Bean
    @Primary // This bean will be used by default if there are multiple beans of the same type
    Person person2WithParameters(String name, String country, Address address) {
        // This method will be called by Spring and the parameters will be injected by Spring
        return new Person(name, country, address);
    }

    @Bean
    Person person3WithParameters(String name, String country,@Qualifier("address2Qualifier") Address address) {
        // This method will be called by Spring and the parameters will be injected by Spring
        return new Person(name, country, address);
    }

    @Bean(name = "address1")
    @Primary // This bean will be used by default if there are multiple beans of the same type
    Address address1(){
        return new Address("Mokatam", "Cairo");
    }

    @Bean(name = "address2")
    @Qualifier("address2Qualifier") // This bean will be used if we specify the name of the bean
    Address address2(){
        return new Address("Maadi", "Cairo");
    }
}