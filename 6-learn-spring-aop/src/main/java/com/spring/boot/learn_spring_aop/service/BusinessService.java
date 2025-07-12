package com.spring.boot.learn_spring_aop.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.spring.boot.learn_spring_aop.repository.DataService;

@Service
public class BusinessService {
    private DataService dataService;

    public BusinessService(DataService dataService){
        this.dataService= dataService;
    }

    public int calculateMax(){
        return Arrays.stream(this.dataService.retrieveData()).max().orElse(0);
    }
}
