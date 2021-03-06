package com.example.demo.airtable.service;

import com.example.demo.airtable.entity.EntityWrapper;
import com.example.demo.airtable.entity.Log;
import com.example.demo.properties.AirTableProperties;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.JsonParserUtil;
import lombok.extern.slf4j.Slf4j;
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

    public boolean createLog(String user, String text, String time, String status){

        var fields = new EntityWrapper(new Log(user,text,time,status));

        var createLog = httpClientUtil.airTableCreate(AirTableProperties.LOG_URL, AirTableProperties.API_KEY, jsonParserUtil.jsonToString(fields));

        var response = httpClientUtil.doRequest(createLog);

        var isSuccess = response.getStatusCode() == 200;

        log.info("是否成功寫入airTable : {}", isSuccess ? "true" : "false");

        return isSuccess;

    }
}
