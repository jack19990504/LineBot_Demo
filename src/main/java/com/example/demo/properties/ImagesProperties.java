package com.example.demo.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:images.properties")
@ConfigurationProperties(prefix = "images")
public class ImagesProperties {
    public static String redirectURL;
    public static String logoURL;
    public static String dogeURL;

    public void setRedirectURL(String redirectURL) {
        ImagesProperties.redirectURL = redirectURL;
    }

    public void setLogoURL(String logoURL) {
        ImagesProperties.logoURL = logoURL;
    }

    public void setDogeURL(String dogeURL) {
        ImagesProperties.dogeURL = dogeURL;
    }
}
