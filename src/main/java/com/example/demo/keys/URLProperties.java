package com.example.demo.keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:URL.properties")
@ConfigurationProperties(prefix = "url")

public class URLProperties {

    public static String reply;
    public static String push;
    public static String getUserProfile;
    public static String accessToken;
    public static String token;
    public static String verify;
    public static String user;

    public void setReply(String reply) {
        URLProperties.reply = reply;
    }

    public void setPush(String push) {
        URLProperties.push = push;
    }

    public void setGetUserProfile(String getUserProfile) {
        URLProperties.getUserProfile = getUserProfile;
    }

    public void setAccessToken(String accessToken) {
        URLProperties.accessToken = accessToken;
    }

    public void setToken(String token){
        URLProperties.token = token;
    }

    public void setVerify(String verify){ URLProperties.verify = verify;}

    public void setUser(String user){ URLProperties.user = user;}
}
