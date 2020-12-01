package com.example.demo.slack.service;

import com.example.demo.line.util.HttpClientUtil;
import com.example.demo.slack.entity.SlackMessage;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlackService {

    private HttpClientUtil httpClientUtil;

    @Value("${slack.webhook.url}")
    private String URL;

    public SlackService(HttpClientUtil httpClientUtil){
        this.httpClientUtil = httpClientUtil;
    }

    public boolean sendSlack(SlackMessage slackMessage){
        HttpPost httpPost = httpClientUtil.setSlackMessage(URL,slackMessage);
        int res = httpClientUtil.doRequest(httpPost).getStatusCode();

        return res == 200;

    }


}
