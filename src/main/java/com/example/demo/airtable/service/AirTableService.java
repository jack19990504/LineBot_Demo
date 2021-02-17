package com.example.demo.airtable.service;

import com.example.demo.airtable.entity.EntityWrapper;
import com.example.demo.airtable.entity.Log;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.JsonParserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AirTableService {

    {
        log.info("init :\t" + this.getClass().getSimpleName());
    }

    private final HttpClientUtil httpClientUtil;
    private final JsonParserUtil jsonParserUtil;

    public AirTableService(HttpClientUtil httpClientUtil,JsonParserUtil jsonParserUtil){
        this.httpClientUtil = httpClientUtil;
        this.jsonParserUtil = jsonParserUtil;
    }

    public void createLog(String user, String text, String time, String status){

        var fields = new EntityWrapper(new Log(user,text,time,status));

        HttpPost createLog = httpClientUtil.airTableCreateLog(jsonParserUtil.jsonToString(fields));

        var response = httpClientUtil.doRequest(createLog);

        log.info("是否成功寫入airTable : {}",response.getStatusCode() == 200 ? "true" : "false");

    }
}
