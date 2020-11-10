package com.example.demo.line.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUtil {

    public String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
