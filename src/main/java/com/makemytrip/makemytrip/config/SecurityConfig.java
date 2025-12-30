package com.makemytrip.makemytrip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){ //encodes password
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            
            @Override
            public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**") // allow all endpoints
            .allowedOrigins("*")  //any websites
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //these http request
            .allowedHeaders("*") //all headers
            .allowCredentials(false); //do not authentication
            }
        };
    }
    
}
