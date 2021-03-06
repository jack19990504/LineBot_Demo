package com.example.demo.feign;

import com.example.demo.feign.configuration.MessageAPIConfiguration;
import com.example.demo.line.entity.LineUserProfile;
import com.example.demo.line.message.entity.Push;
import com.example.demo.line.message.entity.Reply;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "messageAPIClient",
        url = "https://api.line.me/v2/bot/",
        configuration = MessageAPIConfiguration.class)
public interface MessageAPI {

    @RequestMapping(method = RequestMethod.POST,value = "message/push/")
    Response push(Push push, @RequestHeader("X-Line-Retry-Key") String retryKey);

    @RequestMapping(method = RequestMethod.POST,value = "message/reply/")
    Response reply(Reply reply);

    @RequestMapping(method = RequestMethod.GET,value = "profile/{userId}")
    LineUserProfile lineUserProfile(@PathVariable("userId") String userId);


}
