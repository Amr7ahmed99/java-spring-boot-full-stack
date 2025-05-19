package com.amr_saleh.springboot.myfirstwebapp.welcome;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.amr_saleh.springboot.myfirstwebapp.autherntication.AuthenticationService;

@Controller
public class WelcomeController {

    private AuthenticationService authenticationService;

    public WelcomeController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    

    //  JSP is a server-side view technology that allows you to create dynamic web pages using Java.
    //  JSP is a part of the Java EE (Enterprise Edition) specification and is used to create web applications.
    //  JSP files are compiled into servlets by the web container (like Apache Tomcat) at runtime.
    //  Dispatcher servlet talks to the view resolver to add the prefix and suffix to the view name, and then it will return the view object to the dispatcher servlet.
    @RequestMapping(value = "welcome", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String username, @RequestParam String password,  ModelMap model) {
        if(!authenticationService.authenticate(username, password)){
            model.put("errorMessage", "Invalid username or password, Please try again");
            return "login";
        }
        model.put("username", username);
        return "welcome";
    }
}
