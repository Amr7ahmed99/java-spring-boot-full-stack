package com.amr_saleh.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails userDetails1= this.createNewUserDetails("AmrSaleh", "123456", "USER");
        UserDetails userDetails2= this.createNewUserDetails("admin", "admin", "ADMIN");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }
    
    private UserDetails createNewUserDetails(String username, String password, String... roles) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        return User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

    // All URLs are protected and will be authenticated using HTTP Basic authentication
    // A login form is shown for unauthenticated requests
    // CSRF (cross site request forgery) protection is enabled by default
    // H2 console is allowed to be accessed without authentication
    // But the H2 console is using frames in its html view, so we need to disable header frame options
    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		http.csrf(AbstractHttpConfigurer::disable);
		http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)); // Starting from SB 3.1.x
		return http.build();
	}

}
