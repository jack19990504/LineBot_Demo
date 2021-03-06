package com.example.demo.keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:airTable.properties")
@ConfigurationProperties(prefix = "airtable")
public class AirTableProperties {

    public static String API_KEY;
    public static String LOG_URL;

    public void setApiKey(String apiKey){AirTableProperties.API_KEY = apiKey;}

    public void setLogUrl(String logUrl){AirTableProperties.LOG_URL = logUrl;}
}
