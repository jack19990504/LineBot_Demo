package com.example.demo.line.util;

import com.example.demo.line.util.entity.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class HttpClientUtil {

    public HttpResponse doRequest(HttpPost httpPost){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpResponse httpResponse = null;

        try{
            response = httpclient.execute(httpPost);
            httpResponse = new HttpResponse(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        }catch(Exception e){
                System.out.println("error");
        }

        return httpResponse;
    }

    public HttpResponse doRequest(HttpGet httpGet){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpResponse httpResponse = null;

        try{
            response = httpclient.execute(httpGet);
            httpResponse = new HttpResponse(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        }catch(Exception e){
            System.out.println("error");
        }

        return httpResponse;
    }

    public HttpPost setMessage(String URL,String accessToken,String message,String uuid){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setHeader("Authorization", "Bearer " + accessToken);
        httpPost.setHeader("X-Line-Retry-Key", uuid);

        httpPost.setEntity(new StringEntity(message, StandardCharsets.UTF_8));

        return httpPost;
    }

    public HttpPost setMessage(String URL,String accessToken,String message){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setHeader("Authorization", "Bearer " + accessToken);

        httpPost.setEntity(new StringEntity(message, StandardCharsets.UTF_8));

        return httpPost;
    }

    public HttpGet setWeather(String URL){
        HttpGet httpGet = new HttpGet("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-061?Authorization=CWB-4263648C-F829-4D72-B07D-62F049F17B24&locationName=%E4%BF%A1%E7%BE%A9%E5%8D%80&elementName=PoP12h,T");

        return httpGet;
    }
}
