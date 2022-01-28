package com.example.test.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public HelloBean helloBean(){
        return new HelloBeanImpl();
    }


}

