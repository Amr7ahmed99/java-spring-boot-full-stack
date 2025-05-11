package com.AmrSaleh.learning_spring_framework_02.c1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Repository
@Primary
public class MongoDbDataService implements DataService {
    @Override
    public int[] retrieveData() {
        // Simulating a MongoDB data retrieval
        return new int[] { 1, 4, 5, 2, 3 };
    }
    
}
