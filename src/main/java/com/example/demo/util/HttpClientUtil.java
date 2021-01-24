package com.example.demo.util;

import com.example.demo.util.entity.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class HttpClientUtil {

    public HttpResponse doRequest(HttpRequestBase httpRequest){
        // httpGet and httpPost are child from httpRequestBase
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpResponse httpResponse = null;

        try{
            response = httpclient.execute(httpRequest);
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

    public HttpGet setUserProfile(String URL,String accessToken,String userId){
        HttpGet httpGet = new HttpGet(String.format(URL, userId));
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        return  httpGet;
    }
}
