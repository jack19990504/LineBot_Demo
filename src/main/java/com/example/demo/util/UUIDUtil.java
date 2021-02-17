package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UUIDUtil {

    {
        log.info("init :\t" + this.getClass().getSimpleName());
    }

    public String getRandomUUID(){
        return UUID.randomUUID().toString();
    }
}
