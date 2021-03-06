package com.example.demo.feign.configuration;

import com.example.demo.keys.MessageAPIProperties;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageAPIConfiguration {

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization","Bearer " + MessageAPIProperties.ACCESS_TOKEN);
            requestTemplate.header("Content-Type", "application/json");
        };
    }

}
