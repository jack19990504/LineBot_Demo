package com.example.demo.controller.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Handler;
import java.util.stream.Collectors;

import com.example.demo.annotation.SendSlackMessage;
import com.example.demo.line.handler.EventHandler;
import com.example.demo.line.handler.MessageHandler;
import com.example.demo.line.handler.PostBackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.line.entity.Event;
import com.example.demo.line.entity.EventWrapper;
import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.entity.LineUserDetail;
import com.example.demo.line.login.service.LineLoginService;
import com.example.demo.line.service.LineService;
import com.example.demo.line.service.PushService;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.mapper.UserMapper;

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


    private PushService pushService;
    private LineService lineService;
    private final HashMap<String, EventHandler> handlers;


    @Autowired
    public LineController(PushService pushService, LineService lineService) {
        this.pushService = pushService;
        this.lineService = lineService;

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

        //pushService.sendPostMessages(userIds, "test");

        pushService.sendPostQuickReplys(userIds);


        return ResponseEntity.ok().body("123");
    }

    //	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
//	@ResponseBody
//	@SendSlackMessage
//	public ResponseEntity<Object> ReceiveMessage3(@RequestBody EventWrapper eventWrapper) {
//
//		// filter : 篩出所有是 *訊息* |而且| 是 *文字* 的訊息
//		// collect : 將其加入Map中 為 <replyToken, Event>
//		Map<String, Object> data = eventWrapper.getEvents().stream().collect(Collectors.toMap(Event::getReplyToken,
//				x -> x));
//
//		Optional<Event> event;
//		// business logic
//		for (Map.Entry<String, Object> entry : data.entrySet()) {
//			event = Optional.ofNullable((Event) (entry.getValue()));
//			if (event.isPresent()) {
//				switch (event.get().getType()) {
//				case "message":
//					// if it is a text message, do something, it can be a image or a video as well
//					if ("text".equals(event.get().getMessage().getType())) {// send back same text
//						// lineService.message_text_Simple_Reply(event);
//						lineService.message_text_Simple_Reply(event);
//					}
//					break;
//				case "postback":
//					System.out.println("data : " + event.get().getPostBack().getData());
//					System.out.println("userId : " + event.get().getSource().getUserId());
//					break;
//				default:
//					System.out.println("it is not a message event!");
//					break;
//				}
//			}
//
//		}
//		data.clear();
//
//		return ResponseEntity.ok().body("123");
//	}
    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    @SendSlackMessage
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
