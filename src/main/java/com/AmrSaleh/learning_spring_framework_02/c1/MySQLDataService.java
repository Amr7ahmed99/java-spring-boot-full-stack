package com.AmrSaleh.learning_spring_framework_02.c1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("MySQLDataService")
public class MySQLDataService  implements DataService {
    @Override
    public int[] retrieveData() {
        // Simulating a MySQL data retrieval
        return new int[] { 10, 6, 8, 7, 9 };
    }
    
}
