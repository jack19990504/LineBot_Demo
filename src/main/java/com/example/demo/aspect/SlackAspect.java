package com.example.demo.aspect;

import com.example.demo.airtable.service.AirTableService;
import com.example.demo.annotation.SendSlack;
import com.example.demo.line.entity.Event;
import com.example.demo.line.service.LineProfileService;
import com.example.demo.slack.service.SlackService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@Aspect
public class SlackAspect {


    private final SlackService slackService;
    private final LineProfileService lineProfileService;
    private final AirTableService airTableService;

    public SlackAspect(SlackService slackService,LineProfileService lineProfileService,AirTableService airTableService) {
        this.slackService = slackService;
        this.lineProfileService = lineProfileService;
        this.airTableService = airTableService;
    }

    @Value("${slack.template}")
    private String slack_template;

    @Pointcut("@annotation(com.example.demo.annotation.SendSlack)")
    public void pointcut() {
    }

    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void sendSlackMessage(JoinPoint joinPoint, Object result) {

        SendSlack annotation = getAnnotation(joinPoint);
        String userText = getText(joinPoint.getArgs()[0]);
        String messageType = annotation.messageType().toString();
        String nowTime = getNow();
        String userName = lineProfileService.getUserName(getUserId(joinPoint.getArgs()[0]));

        String returnMessage = String.format(slack_template,nowTime,userName,messageType,userText);
        var status = slackService.sendSlack(returnMessage) ? "Yes" : "No";

        airTableService.createLog(userName,userText,nowTime,status);

    }

    private String getText(Object o){
        if(o instanceof Optional){
            return ((Optional<Event>) o).get().getMessage().getText();
        }else{
            return "something went wrong";
        }
    }

    private String getUserId(Object o){
        if(o instanceof Optional){
            return ((Optional<Event>) o).get().getSource().getUserId();
        }else{
            return "something went wrong";
        }
    }

    private SendSlack getAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(SendSlack.class);
    }

    private String getNow(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return now.format(formatter);
    }

}
