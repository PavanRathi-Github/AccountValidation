package com.example.accountvalidation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class RestTemplateConfig {

    private static Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

    @Value("${accountvalidator.numberOfThreads}")
    private Integer numberOfThreads;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    public Executor executor(){
        logger.info("Rest template thread pool number of threads - {}", numberOfThreads);
        return Executors.newFixedThreadPool(numberOfThreads);
    }
}
