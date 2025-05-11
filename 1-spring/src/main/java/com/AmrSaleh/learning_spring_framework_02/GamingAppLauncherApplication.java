package com.AmrSaleh.learning_spring_framework_02;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.AmrSaleh.learning_spring_framework_02.game.GameConsole;
import com.AmrSaleh.learning_spring_framework_02.game.GameRunner;

@Configuration
@ComponentScan(basePackages = "com.AmrSaleh.learning_spring_framework_02.game")
public class GamingAppLauncherApplication {
	public static void main(String[] args) {
		try(var appContext= new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)){
			// 2: Configure the things that we want to be managed by Spring 
			// App04GamingSpringBeans - @Configuration
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
