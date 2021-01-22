package com.example.demo.controller.rest;

import com.example.demo.line.entity.Event;
import com.example.demo.line.entity.EventWrapper;
import com.example.demo.line.handler.EventHandler;
import com.example.demo.line.handler.MessageHandler;
import com.example.demo.line.handler.PostBackHandler;
import com.example.demo.line.service.LineService;
import com.example.demo.line.service.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LineController {

    private static final Logger LOG = LoggerFactory.getLogger(LineController.class);
    {
        LOG.warn("init :" + this.getClass().getSimpleName());
    }

    @Value("${spring.application.name}")
    String appName;


    private final PushService pushService;
    private final HashMap<String, EventHandler> handlers;


    @Autowired
    public LineController(PushService pushService, LineService lineService) {
        this.pushService = pushService;

        handlers = new HashMap<>() {{
            put("message", new MessageHandler(lineService));
            put("postback", new PostBackHandler());
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

        pushService.sendPostQuickReplys(userIds);


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
