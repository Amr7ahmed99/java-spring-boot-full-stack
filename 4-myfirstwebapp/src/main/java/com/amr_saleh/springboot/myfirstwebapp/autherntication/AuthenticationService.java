package com.amr_saleh.springboot.myfirstwebapp.autherntication;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    public boolean authenticate (String username, String password) {
        // hard coded authentication
        return username.equalsIgnoreCase("AmrSaleh") && password.equalsIgnoreCase("123456");
    }
}
