//write where the file lives
package com.collabspace.CollabSpace.config;

//import everything
//this file is for taking control of security and disabling spring boot's defaults
//1.import the one who allows configuration
import org.springframework.context.annotation.Configuration;
//2.after that tell spring i am taking control of security
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//3.spring is going to handle object not class, import one which tells object is to be handled
import org.springframework.context.annotation.Bean;
//4.get the object which the spring boot will handle
import org.springframework.security.web.SecurityFilterChain;
//5.now get the method which will return the object
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//write the class
@Configuration
@EnableWebSecurity
public class SecurityConfig{
    //write the method by telling spring boot to handle the object
    //disable csrf
    //authorize and authenticate every request
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        return http.build();
    }
}