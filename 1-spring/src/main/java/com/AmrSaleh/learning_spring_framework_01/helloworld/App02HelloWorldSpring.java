package com.AmrSaleh.learning_spring_framework_01.helloworld;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
	// It is responsible for creating the objects and wiring them together
	// It is the entry point of the application
	public static void main(String[] args) {
		// 1: Launch the Spring Context using try-with-resources
		try(var appContext= new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)){
		// 2: Configure the things that we want to be managed by Spring 
		// HelloWorldConfiguration - @Configuration
		// name/country - @Bean

		// 3: Get the beans that managed by Spring
		System.out.println(appContext.getBean("name"));
		System.out.println(appContext.getBean("country"));
		System.out.println(appContext.getBean("person2WithMethodCall"));
		System.out.println(appContext.getBean("person2WithParameters"));
		System.out.println(appContext.getBean("person3WithParameters"));
		System.out.println(appContext.getBean("address2"));
		var address= appContext.getBean(Address.class);
		System.out.println(address.city());

		// Get all the beans names
		// var beansNamesList= appContext.getBeanDefinitionNames();
		// for (String beanName: beansNamesList){
		// 	System.out.println(beanName);
		// }

		// Get all the beans names
		Arrays.stream(appContext.getBeanDefinitionNames()).forEach(System.out::println);
		}
	}
}