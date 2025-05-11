package com.AmrSaleh.learning_spring_framework_02.e1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
public class BeanScopesLauncherApplication {
    public static void main(String[] args){
        try(var appContext= new AnnotationConfigApplicationContext(BeanScopesLauncherApplication.class)){

            System.out.println(appContext.getBean(NormalClass.class));
            System.out.println(appContext.getBean(NormalClass.class));
            System.out.println(appContext.getBean(NormalClass.class));

            System.out.println(appContext.getBean(PrototypeClass.class));
            System.out.println(appContext.getBean(PrototypeClass.class));
            System.out.println(appContext.getBean(PrototypeClass.class));
        }
    }
}

// any spring bean is by default @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
class NormalClass{

}

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass{
}
