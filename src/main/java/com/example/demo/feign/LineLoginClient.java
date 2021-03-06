package com.example.demo.feign;

import com.example.demo.feign.configuration.LineLoginConfiguration;
import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.entity.LineUserDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "lineLoginClient",
        url = "https://api.line.me/",
        configuration = LineLoginConfiguration.class)
public interface LineLoginClient {

    @RequestMapping(value = "oauth2/v2.1/token", method = RequestMethod.POST ,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,headers = "Content-Type: application/x-www-form-urlencoded")
    AccessToken userAccessToken(Map<String, ?> formParams);

    @RequestMapping(value = "oauth2/v2.1/verify", method = RequestMethod.POST )
    LineUserDetail lineUserDetail(@RequestParam(value="client_id") String client_id, @RequestParam(value="id_token") String id_token);

    @RequestMapping(value = "v2/profile", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    LineUser lineUser(@RequestHeader("Authorization") String header);
}
