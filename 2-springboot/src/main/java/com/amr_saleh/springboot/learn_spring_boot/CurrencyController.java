package com.amr_saleh.springboot.learn_spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyConfigurationService config;

    @RequestMapping("/currency-configuration")
    private CurrencyConfigurationService getConfiguration(){
        return this.config;
    }
}
