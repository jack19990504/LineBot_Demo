package com.example.demo;

import com.example.demo.airtable.service.AirTableService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirTableServiceTests {

    @Autowired
    private AirTableService airTableService;

    @Test
    public void testLog_create(){


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        var isSucceed = airTableService.createLog("test","on Build",now.format(formatter),"Yes");

        Assertions.assertTrue(isSucceed);

    }
}
