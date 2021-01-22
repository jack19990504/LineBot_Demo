package com.example.demo.aspect;

import com.example.demo.annotation.SendSlack;
import com.example.demo.line.entity.Event;
import com.example.demo.slack.service.SlackService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@Aspect
public class SlackAspect {


    private final SlackService slackService;

    public SlackAspect(SlackService slackService) {
        this.slackService = slackService;
    }

    @Pointcut("@annotation(com.example.demo.annotation.SendSlack)")
    public void pointcut() {
    }

    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void sendSlackMessage(JoinPoint joinPoint, Object result) {

        SendSlack annotation = getAnnotation(joinPoint);
        String userText = getText(joinPoint.getArgs()[0]);
        String messageType = annotation.messageType().toString();
        String nowTime = getNow();

        String template = ":pog: receive message at : %s \ntype : %s, the content is \"%s\"";
        String returnMessage = String.format(template,nowTime,messageType,userText);
        slackService.sendSlack(returnMessage);
    }

    private String getText(Object o){
        if(o instanceof Optional){
            return ((Optional<Event>) o).get().getMessage().getText();
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
