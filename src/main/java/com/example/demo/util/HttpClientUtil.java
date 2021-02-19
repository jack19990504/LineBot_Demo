package com.example.demo.util;

import com.example.demo.keys.LineLoginProperties;
import com.example.demo.util.entity.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class HttpClientUtil {

    {
        log.info("init :\t" + this.getClass().getSimpleName());
    }

    public HttpResponse doRequest(HttpRequestBase httpRequest){
        // httpGet and httpPost are child from httpRequestBase
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpResponse httpResponse = null;

        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpRequest.setConfig(defaultConfig);

        try{
            response = httpclient.execute(httpRequest);
            httpResponse = new HttpResponse(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        }catch(Exception e){
                log.error("error occurs");
        }

        return httpResponse;
    }

    private HttpPost messageAPIBase(String URL,String accessToken){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setHeader("Authorization", "Bearer " + accessToken);

        return httpPost;
    }

    private HttpPost lineLoginBase(String URL){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;");

        return httpPost;
    }

    private HttpPost airTableBase(String URL,String api_Key){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setHeader("Authorization", "Bearer " + api_Key);

        return httpPost;
    }

    public HttpPost messageAPI_push(String URL, String accessToken, String message, String uuid){
        HttpPost httpPost = messageAPIBase(URL,accessToken);
        httpPost.setHeader("X-Line-Retry-Key", uuid);

        httpPost.setEntity(new StringEntity(message, StandardCharsets.UTF_8));

        return httpPost;
    }
    public HttpPost messageAPI_reply(String URL, String accessToken, String message){
        HttpPost httpPost = messageAPIBase(URL,accessToken);

        httpPost.setEntity(new StringEntity(message, StandardCharsets.UTF_8));

        return httpPost;
    }

    public HttpPost LineLoginRequest(String URL,String code){
        HttpPost httpPost = lineLoginBase(URL);

        try{
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("grant_type", LineLoginProperties.grant_type));
            params.add(new BasicNameValuePair("code",code));
            params.add(new BasicNameValuePair("redirect_uri",LineLoginProperties.redirect_uri));
            params.add(new BasicNameValuePair("client_id",LineLoginProperties.client_id));
            params.add(new BasicNameValuePair("client_secret",LineLoginProperties.client_secret));
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        }
        catch (Exception e){
            log.error("error");
        }

        return httpPost;
    }

    public HttpPost getLineUserDetail(String URL, String clientId, String idToken){
        HttpPost httpPost = lineLoginBase(URL);

        try {
            URI uri = new URIBuilder(httpPost.getURI())
                    .addParameter("client_id",clientId)
                    .addParameter("id_token",idToken).build();
            httpPost.setURI(uri);
        }
        catch (Exception e){
            log.error("error occurs while creating uri");
        }

        return httpPost;
    }

    public HttpGet getUserProfile(String URL, String accessToken, String userId){
        URL = String.format(URL,userId);
        HttpGet httpGet = new HttpGet(URL);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        return httpGet;
    }

    public HttpGet getLineUser(String URL,String accessToken){
        HttpGet httpGet = new HttpGet(URL);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        return httpGet;
    }


    public HttpPost airTableCreate(String airTableURL, String airTableAPIKey, String fields){

        HttpPost httpPost = airTableBase(airTableURL,airTableAPIKey);
        httpPost.setEntity(new StringEntity(fields,StandardCharsets.UTF_8));

        return httpPost;
    }
}
