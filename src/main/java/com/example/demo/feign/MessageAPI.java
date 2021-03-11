package com.example.demo.feign;

import com.example.demo.feign.configuration.MessageAPIConfiguration;
import com.example.demo.line.entity.LineUserProfile;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.message.entity.Push;
import com.example.demo.line.message.entity.Reply;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "messageAPIClient",
        url = "https://api.line.me/",
        configuration = MessageAPIConfiguration.class)
public interface MessageAPI {

    @PostMapping(value = "v2/bot/message/push/")
    Response push(Push push, @RequestHeader("X-Line-Retry-Key") String retryKey);

    @RequestMapping(method = RequestMethod.POST,value = "v2/bot/message/reply/")
    Response reply(Reply reply);

    @RequestMapping(method = RequestMethod.GET,value = "v2/bot/profile/{userId}")
    LineUserProfile lineUserProfile(@PathVariable("userId") String userId);

    @GetMapping(value = "v2/profile",produces = "application/json")
    LineUser lineUser(@RequestHeader(value = "Authorization") String authorizationHeader);


}
