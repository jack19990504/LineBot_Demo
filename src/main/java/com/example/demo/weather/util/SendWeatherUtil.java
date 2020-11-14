package com.example.demo.weather.util;

import com.example.demo.line.util.HttpClientUtil;
import com.example.demo.line.util.entity.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendWeatherUtil {

    private HttpClientUtil httpClientUtil;

    @Autowired
    public SendWeatherUtil(HttpClientUtil httpClientUtil){
        this.httpClientUtil = httpClientUtil;
    }

    public HttpResponse getResponse(){
        HttpGet httpGet = httpClientUtil.setWeather("");

        HttpResponse httpResponse;

        httpResponse = httpClientUtil.doRequest(httpGet);

        return httpResponse;
    }

}
