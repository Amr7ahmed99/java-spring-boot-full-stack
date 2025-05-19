package com.amr_saleh.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    //  JSP is a server-side view technology that allows you to create dynamic web pages using Java.
    //  JSP is a part of the Java EE (Enterprise Edition) specification and is used to create web applications.
    //  JSP files are compiled into servlets by the web container (like Apache Tomcat) at runtime.
    //  Dispatcher servlet talks to the view resolver to add the prefix and suffix to the view name, and then it will return the view object to the dispatcher servlet.
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }
}
