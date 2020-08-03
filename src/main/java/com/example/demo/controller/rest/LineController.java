package com.example.demo.controller.rest;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.line.entity.Event;
import com.example.demo.line.entity.EventWrapper;
import com.example.demo.line.service.LogicService;
import com.example.demo.line.service.ReplyService;

@CrossOrigin("*")
@RequestMapping("/line")
@RestController
public class LineController {

	@Autowired
	ReplyService replyService;
	
	@Autowired
	LogicService logicService;

	@GetMapping("/hello")
	public void printHello() {
		System.out.println("hello");
	}

	@PostMapping("/post")
	public ResponseEntity<Object> postMessage() {
		/*
		 * 我的line user id 想獲取自己的line user id，可以啟動此 server，跟 bot 對話，即可在 console中獲取
		 */
		String userId = "U848d0fb8269d111a96875ae3cb365ba6";

		replyService.sendPostMessages("test", userId);

		return ResponseEntity.ok().body("123");
	}

//	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
//	@ResponseBody
//	public ResponseEntity<Object> ReceiveMessage(@RequestBody EventWrapper eventWrapper) {
//
//		// 使用疊代器
//		Iterator<?> iter = eventWrapper.getEvents().iterator();
//		
//		// init var
//		Event event;
//		while (iter.hasNext()) {
//			// 轉型
//			event = (Event) (iter.next());
//			// if it is a message event, do our logic. It can be a follow or unfollow or leave event as well.
//			switch (event.getType()) {
//			case "message":
//				// if it is a text message, do something, it can be a image or a video as well
//				switch (event.getMessage().getType()) {
//				case "text":
//					// print some detail
//					System.out.println("ReplyToken : " + event.getReplyToken());
//					System.out.println("Text : " + event.getMessage().getText());
//					System.out.println("UserId : " + event.getSource().getUserId());
//
//					// send back same text
//					replyService.sendResponseMessage(event.getReplyToken(), event.getMessage().getText());
//					break;
//				default :
//					break;
//				}
//				break;
//			default:
//				System.out.println("it is not a message event!");
//				break;
//			}
//
//		}
//
//		return ResponseEntity.ok().body("123");
//	}

	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public ResponseEntity<Object> ReceiveMessage3(@RequestBody EventWrapper eventWrapper) {

		// filter : 篩出所有是 *訊息* |而且| 是 *文字* 的訊息
		// collect : 將其加入Map中 為 <replyToken, Event>
		Map<String, Object> dataMap = eventWrapper.getEvents().stream()
				.collect(Collectors.toMap(Event::getReplyToken,x -> new Event(x.getType(), x.getSource(), x.getMessage() ,x.getReplyToken())));
		
		// business logic
		logicService.dismantle(dataMap);
		
		
		return ResponseEntity.ok().body("123");
	}
}
