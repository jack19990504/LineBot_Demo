package com.example.demo.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:messageAPI.properties")
@ConfigurationProperties(prefix = "url")

public class MessageAPIProperties {

    public static String REPLY;
    public static String PUSH;
    public static String GET_USER_PROFILE;
    public static String ACCESS_TOKEN;
    public static String TOKEN;
    public static String VERIFY;
    public static String USER;


    public void setReply(String reply) {
        MessageAPIProperties.REPLY = reply;
    }

    public void setPush(String push) {
        MessageAPIProperties.PUSH = push;
    }

    public void setGetUserProfile(String getUserProfile) {
        MessageAPIProperties.GET_USER_PROFILE = getUserProfile;
    }

    public void setAccessToken(String accessToken) { MessageAPIProperties.ACCESS_TOKEN = accessToken; }

    public void setToken(String token){ MessageAPIProperties.TOKEN = token; }

    public void setVerify(String verify){ MessageAPIProperties.VERIFY = verify;}

    public void setUser(String user){ MessageAPIProperties.USER = user;}

}
