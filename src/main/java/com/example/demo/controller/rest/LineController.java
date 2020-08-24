package com.example.demo.controller.rest;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Autowired
	PushService pushService;
	
	@Autowired
	LineService lineService;

	@Autowired
	LineLoginService lineLoginService;

	@Autowired
	UserMapper userMapper;

	@GetMapping("/hello")
	public void printHello() {
		System.out.println("hello");
	}

	/*
	 * step 1
	 * https://access.line.me/oauth2/v2.1/authorize?response_type=code&client_id=1654391521&redirect_uri=https%3A%2F%2F0187e4ef3e93.ngrok.io%2Fline%2Flogin&state=12345abcde&scope=openid%20profile 
	 * redirect to this api
	 */
	// get code
	@GetMapping("/login")
	public void getUesrInfo(@RequestParam(defaultValue = "code") String code ,@RequestParam(defaultValue = "state") String state)
	{
	  System.out.println("code :" + code);
	  System.out.println("state :" + state);
	  
	  if(!code.equals("code") && !state.equals("state"))
	  {
			AccessToken accessToken = lineLoginService.getUserAccessToken(code);
			LineUserDetail lineUserDetail = lineLoginService.getUserDetail(accessToken.getId_token());

			System.out.println("Line Login Success!");

			System.out.println("User Name : " + lineUserDetail.getName());
			System.out.println("UserId : " + lineUserDetail.getSub());
			System.out.println("User Picture : " + lineUserDetail.getPicture());
			
			LineUser lineUser = lineLoginService.getUser(accessToken.getAccess_token());
			
			System.out.println("User Name : " + lineUser.getDisplayName());
			System.out.println("UserId : " + lineUser.getUserId());
	  }  
	  
	}

	@GetMapping("/login/success")
	public String getUesrInfo2(@RequestParam(defaultValue = "code") String code,@RequestParam(defaultValue = "state") String state,Model model) {
		System.out.println("/login/success");
		System.out.println("code :" + code);
		System.out.println("state :" + state);
		if (!code.equals("code") && !state.equals("state")) {
			
			AccessToken accessToken = lineLoginService.getUserAccessToken(code);
			LineUserDetail lineUserDetail = lineLoginService.getUserDetail(accessToken.getId_token());

			System.out.println("Line Login Success!");

			System.out.println("User Name : " + lineUserDetail.getName());
			System.out.println("UserId : " + lineUserDetail.getSub());
			System.out.println("User Picture : " + lineUserDetail.getPicture());

			LineUser lineUser = lineLoginService.getUser(accessToken.getAccess_token());

			System.out.println("User Name : " + lineUser.getDisplayName());
			System.out.println("UserId : " + lineUser.getUserId());
			model.addAttribute("appName", appName);
			model.addAttribute("userName", lineUser.getDisplayName());
			model.addAttribute("image", lineUser.getPictureUrl());

		}
		return "home";

	}

	@GetMapping("/user")
	public void printUser() {
		User user = userMapper.findByState("123");
		System.out.println("id : " + user.getUserId() + "\tName : " + user.getUserName());
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

	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
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
			if (event.isPresent()) {
				switch (event.get().getType()) {
				case "message":
					// if it is a text message, do something, it can be a image or a video as well
					switch (event.get().getMessage().getType()) {
					case "text":
						// send back same text
						// lineService.message_text_Simple_Reply(event);
						lineService.message_text_Simple_Reply(event);
						break;
					default:
						break;
					}
					break;
				case "postback":
					System.out.println("data : " + event.get().getPostBack().getData());
					System.out.println("userId : " + event.get().getSource().getUserId());
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
