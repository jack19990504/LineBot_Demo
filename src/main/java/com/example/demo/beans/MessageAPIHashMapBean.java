package com.example.demo.beans;

import com.example.demo.line.message.entity.Push;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessageAPIHashMapBean {

    @Bean("failedMap")
    public Map<String, Push> pushFailMap(){
        Map<String, Push> map = new HashMap<>();
        return map;
    }
}
