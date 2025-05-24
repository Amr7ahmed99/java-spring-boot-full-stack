package com.amr_saleh.springboot.myfirstwebapp.helloWithName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SayHelloWithName {

    //  Logger is a class that is used to log messages in Java applications.
    //  It is part of the SLF4J (Simple Logging Facade for Java) API, which provides a simple and unified interface for logging.
    private final Logger logger= LoggerFactory.getLogger(getClass());
    
    @RequestMapping("say-hello-with-name")
    public String sayHelloWithName(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        logger.info("info: i want this to be printed, Request parameter name: {}", name);
        logger.debug("debug: i want this to not be printed, Request parameter name: {}", name);
        return "sayHelloWithName";
    }
}
