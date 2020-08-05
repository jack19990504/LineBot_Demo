package com.example.demo.controller.rest;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.demo.line.service.LineService;
import com.example.demo.line.service.ReplyService;

@CrossOrigin("*")
@RequestMapping("/line")
@RestController
public class LineController {

	private static final Logger LOG = LoggerFactory.getLogger(LineController.class);
	{
		LOG.warn("init :"+this.getClass().getSimpleName());
	}
	
	@Autowired
	ReplyService replyService;

	@Autowired
	LineService lineService;

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
		Map<String, Object> data = eventWrapper.getEvents().stream().collect(Collectors.toMap(Event::getReplyToken,
				x -> new Event(x.getType(), x.getSource(), x.getMessage(), x.getReplyToken())));
		
		Optional<Event> event;
		// business logic
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			event = Optional.ofNullable((Event) (entry.getValue()));
			if (event.isPresent()) {
				switch (event.get().getType()) {
				case "message":
					// if it is a text message, do something, it can be a image or a video as well
					switch (event.get().getMessage().getType()) {
					case "text":
						// print some detail
						System.out.println("ReplyToken : " + event.get().getReplyToken());
						System.out.println("Text : " + event.get().getMessage().getText());
						System.out.println("UserId : " + event.get().getSource().getUserId());

						// send back same text
						lineService.message_text_Simple_Reply(event);
						LOG.warn("This is an ERROR log");
						break;
					default:
						break;
					}
					break;
				default:
					System.out.println("it is not a message event!");
					break;
				}
			}

		}
		data.clear();

		return ResponseEntity.ok().body("123");
	}
}
