package com.AmrSaleh.learning_spring_framework_01;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.AmrSaleh.learning_spring_framework_01.game.GameConfiguration;
import com.AmrSaleh.learning_spring_framework_01.game.GameConsole;
import com.AmrSaleh.learning_spring_framework_01.game.GameRunner;

public class App03GamingSpringBeans {
	public static void main(String[] args) {
		try(var appContext= new AnnotationConfigApplicationContext(GameConfiguration.class)){
			// 2: Configure the things that we want to be managed by Spring 
			// GameConfiguration - @Configuration
			// game - @Bean
			// gameRunner - @Bean

			// 3: Get the beans that managed by Spring
			appContext.getBean(GameConsole.class).up();
			appContext.getBean(GameRunner.class).run();
			
			// Get all the beans names
			Arrays.stream(appContext.getBeanDefinitionNames()).forEach(System.out::println);
		}
	}
}
