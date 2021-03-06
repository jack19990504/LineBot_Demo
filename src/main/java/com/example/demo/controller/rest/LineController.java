package com.example.demo.controller.rest;

import com.example.demo.line.entity.Event;
import com.example.demo.line.entity.EventWrapper;
import com.example.demo.line.handler.EventHandler;
import com.example.demo.line.handler.MessageHandler;
import com.example.demo.line.handler.PostBackHandler;
import com.example.demo.line.service.PushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RequestMapping("/line")
@Controller
@Slf4j
public class LineController {


    {
        log.info("init :\t" + this.getClass().getSimpleName());
    }

    @Value("${spring.application.name}")
    String appName;


    private final PushService pushService;
    private final HashMap<String, EventHandler> handlers;

    public LineController(PushService pushService, MessageHandler messageHandler , PostBackHandler postBackHandler) {
        this.pushService = pushService;

        handlers = new HashMap<>() {{
            put("message", messageHandler);
            put("postback", postBackHandler);
        }};

    }

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity printHello() {
        System.out.println("hello");
        return ResponseEntity.ok("hello welcome to my app :" + appName);
    }


    @PostMapping("/post")
    public ResponseEntity<Object> postMessage() {
        /*
         * 我的line user id 想獲取自己的line user id，可以啟動此 server，跟 bot 對話，即可在 console中獲取
         */
        String[] userIds = {"U848d0fb8269d111a96875ae3cb365ba6"};

        pushService.sendPostMessages(userIds,"123");


        return ResponseEntity.ok().body("123");
    }


    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> ReceiveMessage3(@RequestBody EventWrapper eventWrapper) {

        // filter : 篩出所有是 *訊息* |而且| 是 *文字* 的訊息
        // collect : 將其加入Map中 為 <replyToken, Event>
        Map<String, Object> data = eventWrapper.getEvents().stream().collect(Collectors.toMap(Event::getReplyToken,
                x -> x));

        Optional<Event> event;
        // business logic
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            event = Optional.ofNullable((Event) (entry.getValue()));
            String EventType = event.get().getType();
            if (handlers.containsKey(EventType)) {
                handlers.get(EventType).handle(event);
            }

        }
        data.clear();

        return ResponseEntity.ok().body("123");
    }
}
