package com.example.demo.util;

import com.example.demo.keys.LineLoginProperties;
import com.example.demo.keys.URLProperties;
import com.example.demo.util.entity.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
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
                log.error("error occurs");
        }

        return httpResponse;
    }


    private HttpPost setHttpPost_retry(String URL,String message,String uuid){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setHeader("Authorization", "Bearer " + URLProperties.accessToken);
        httpPost.setHeader("X-Line-Retry-Key", uuid);

        httpPost.setEntity(new StringEntity(message, StandardCharsets.UTF_8));

        return httpPost;
    }
    private HttpPost setHttpPost(String URL,String message){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
        httpPost.setHeader("Authorization", "Bearer " + URLProperties.accessToken);

        httpPost.setEntity(new StringEntity(message, StandardCharsets.UTF_8));

        return httpPost;
    }

    private HttpPost lineLoginBase(String URL){
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;");
        httpPost.setHeader("Authorization", "Bearer " + URLProperties.accessToken);

        return httpPost;
    }

    private HttpPost setLineLoginRequest(String URL,String code){
        HttpPost httpPost = lineLoginBase(URL);

        try {
            URI uri = new URIBuilder(httpPost.getURI())
                    .addParameter("grant_type", LineLoginProperties.grant_type)
                    .addParameter("code",code)
                    .addParameter("redirect_uri",LineLoginProperties.redirect_uri)
                    .addParameter("client_id",LineLoginProperties.client_id)
                    .addParameter("client_secret",LineLoginProperties.client_secret).build();
                    httpPost.setURI(uri);
        }
        catch (Exception e){
            log.error("error occurs while creating uri");
        }

        return httpPost;
    }

    private HttpPost setGetLineUserDetail(String URL,String idToken){
        HttpPost httpPost = lineLoginBase(URL);

        try {
            URI uri = new URIBuilder(httpPost.getURI())
                    .addParameter("client_id",LineLoginProperties.client_id)
                    .addParameter("id_token",idToken).build();
            httpPost.setURI(uri);
        }
        catch (Exception e){
            log.error("error occurs while creating uri");
        }

        return httpPost;
    }

    private HttpGet setHttpGet(String URL){
        HttpGet httpGet = new HttpGet(URL);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + URLProperties.accessToken);

        return httpGet;
    }

    private HttpGet setHttpGet(String URL,String accessToken){
        HttpGet httpGet = new HttpGet(URL);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        return httpGet;
    }

    public HttpPost setReply(String message){

        return setHttpPost(URLProperties.reply,message);
    }
    public HttpPost setPush(String message,String uuid){

        return setHttpPost_retry(URLProperties.push,message,uuid);
    }


    public HttpPost setLoginAPI(String code){

        return setLineLoginRequest(URLProperties.token,code);
    }

    public HttpPost setGetUserDetail(String idToken){

        return setGetLineUserDetail(URLProperties.verify,idToken);
    }

    public HttpGet setUserProfile(String userId){

        return setHttpGet(String.format(URLProperties.getUserProfile,userId));
    }

    public HttpGet setUserProfile_login(String accessToken){

        return setHttpGet(URLProperties.user,accessToken);
    }
}
