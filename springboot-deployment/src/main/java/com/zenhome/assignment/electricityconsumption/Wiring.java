package com.zenhome.assignment.electricityconsumption;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Wiring {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    // Normally everything should get wired automatically
    // but when you use the Mongo infra layer, you may want
    // to define your own MongoClient bean to return something like
    // Fongo if you don't want to use a real MongoDB
}
