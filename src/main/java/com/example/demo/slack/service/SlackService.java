package com.example.demo.slack.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlackService {

    private final Slack slack;

    @Value("${slack.token}")
    String slackToken;


    public SlackService(){
        slack = Slack.getInstance();
    }

    public boolean sendSlack(String message){
        MethodsClient methods = slack.methods(slackToken);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("C01FJ07N0AX") // Use a channel ID `C1234567` is preferable
                .text(message)
                .build();
        ChatPostMessageResponse response = null;
        try{
            response = methods.chatPostMessage(request);
        } catch(Exception e){
            System.out.println("something went wrong");
        }

        return response.isOk();
    }


}
