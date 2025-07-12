package com.spring.boot.learn_spring_aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.spring.boot.learn_spring_aop.service.BusinessService;

@SpringBootApplication
public class LearnSpringAopApplication implements CommandLineRunner{

	private BusinessService businessService;
	private Logger logger;

	public LearnSpringAopApplication(BusinessService businessService){
		this.businessService= businessService;
		this.logger= LoggerFactory.getLogger(getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int maxVal= this.businessService.calculateMax();
		logger.info("the max value is {}", maxVal);
	}


}
