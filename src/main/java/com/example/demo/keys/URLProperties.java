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

    public static String REPLY;
    public static String PUSH;
    public static String GET_USER_PROFILE;
    public static String ACCESS_TOKEN;
    public static String TOKEN;
    public static String VERIFY;
    public static String USER;

    public static String AIRTABLE_CREATE;
    public static String AIRTABLE_API_KEY;

    public void setReply(String reply) {
        URLProperties.REPLY = reply;
    }

    public void setPush(String push) {
        URLProperties.PUSH = push;
    }

    public void setGetUserProfile(String getUserProfile) {
        URLProperties.GET_USER_PROFILE = getUserProfile;
    }

    public void setAccessToken(String accessToken) {
        URLProperties.ACCESS_TOKEN = accessToken;
    }

    public void setToken(String token){
        URLProperties.TOKEN = token;
    }

    public void setVerify(String verify){ URLProperties.VERIFY = verify;}

    public void setUser(String user){ URLProperties.USER = user;}

    public void setAirtable_create(String airtable_create){ URLProperties.AIRTABLE_CREATE = airtable_create;}

    public void setAirtableApiKey(String airtableApiKey){URLProperties.AIRTABLE_API_KEY = airtableApiKey;}
}
