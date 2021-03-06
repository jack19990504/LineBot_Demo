package com.example.demo.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:lineLogin.properties")
@ConfigurationProperties(prefix = "login")
public class LineLoginProperties {

    public static String grant_type;
    public static String redirect_uri;
    public static String client_id;
    public static String client_secret;

    public void setGrant_type(String grant_type) {
        LineLoginProperties.grant_type = grant_type;
    }

    public void setRedirect_uri(String redirect_uri) {
        LineLoginProperties.redirect_uri = redirect_uri;
    }

    public void setClient_id(String client_id) { LineLoginProperties.client_id = client_id; }

    public void setClient_secret(String client_secret) {
        LineLoginProperties.client_secret = client_secret;
    }
}
