package com.AmrSaleh.learning_spring_framework_02.g1;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class CDIContextLauncherApplication {
    public static void main(String[] args){
        try(var appContext= new AnnotationConfigApplicationContext(CDIContextLauncherApplication.class)){
            Arrays.stream(appContext.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(appContext.getBean(BusinessService.class).getDataService());
        }
    }
}

@Named
class BusinessService{
    private DataService dataService;

    @Inject
    private void setDataService(DataService dataService){
        System.out.println("DataService Injected by setter base injection");
        this.dataService= dataService;
    }

    public DataService getDataService(){
        return this.dataService;
    }
}

@Named
class DataService{
}


