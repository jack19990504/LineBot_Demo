package com.example.demo;

import com.example.demo.slack.service.SlackService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlackServiceTests {

    @Value("${slack.template}")
    private String slack_template;

    @Autowired
    private SlackService slackService;

    @Test
    public void test_sendSlack(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        var isSucceed = slackService.sendSlack(String.format(slack_template, now.format(formatter),"test","test","on test"));

        Assertions.assertTrue(isSucceed);
    }


}
