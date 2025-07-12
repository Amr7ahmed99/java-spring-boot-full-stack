package com.spring.boot.learn_spring_aop.repository;

import org.springframework.stereotype.Repository;

import com.spring.boot.learn_spring_aop.annotations.TrackTime;

@Repository
public class DataService {
    
    @TrackTime
    public int[] retrieveData(){
        // throw new RuntimeException("something went wrong");
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new int[]{1,8,63,2,3,9};
    }
}
