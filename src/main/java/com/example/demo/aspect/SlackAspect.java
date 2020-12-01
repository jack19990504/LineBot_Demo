package com.example.demo.aspect;

import com.example.demo.slack.entity.SlackMessage;
import com.example.demo.slack.service.SlackService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class SlackAspect {

    private SlackService slackService;

    public SlackAspect(SlackService slackService){
        this.slackService = slackService;
    }

    @Pointcut("@annotation(com.example.demo.annotation.SendSlackMessage)")
    public void pointcut() {
    }

    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void sendSlackMessage(JoinPoint joinPoint, Object result) {
        SlackMessage slackMessage = new SlackMessage();

        slackMessage.setText("test Message");
        slackMessage.setUsername("jack1");
        slackMessage.setIcon_emoji(":twice:");

        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);

        System.out.println(slackService.sendSlack(slackMessage));
    }


}
