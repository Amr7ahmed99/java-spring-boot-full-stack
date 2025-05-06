package com.AmrSaleh.learning_spring_framework_02.c1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class BusinessCalculationService {

    private DataService dataService;

    public BusinessCalculationService(@Qualifier("MySQLDataService") DataService dataService) {
        super();
        this.dataService = dataService;
    }

    public int findMax(){
         if(this.dataService==null){
            throw new NullPointerException("DataService is not autowired");
         }

         int[] retrievedData= this.dataService.retrieveData();
         int max= Integer.MIN_VALUE;
        for(int value: retrievedData){
            if(value>max){
                max= value;
            }
        }
        return max;
    }
    
}
