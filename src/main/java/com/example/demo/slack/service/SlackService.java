package com.example.demo.slack.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:slack.properties")
public class SlackService {

    private final Slack slack;

    @Value("${slack.token}")
    String slackToken;


    public SlackService(){
        slack = Slack.getInstance();
    }

    public boolean sendSlack(){
        System.out.println(slackToken);
        MethodsClient methods = slack.methods(slackToken);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("C01FJ07N0AX") // Use a channel ID `C1234567` is preferable
                .text(":wave: Hi from a bot written in Java!")
                .build();
        ChatPostMessageResponse response = null;
        try{
            response = methods.chatPostMessage(request);
        } catch(Exception e){
            System.out.println("something went wrong");
        }
        assert response != null;
        System.out.println(response.getError());
        return response.isOk();
    }


}
