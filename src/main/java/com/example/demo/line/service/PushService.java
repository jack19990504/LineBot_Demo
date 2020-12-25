package com.example.demo.line.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.util.UUIDUtil;
import com.example.demo.util.entity.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.keys.ImagesURL;
import com.example.demo.keys.LineKeys;
import com.example.demo.line.action.entity.LocationAction;
import com.example.demo.line.action.entity.MessageAction;
import com.example.demo.line.action.entity.QuickReplyAction;
import com.example.demo.line.message.entity.EntityMessage;
import com.example.demo.line.message.entity.Push;
import com.example.demo.line.message.entity.QuickReply;
import com.example.demo.line.message.entity.QuickReplyMessage;
import com.example.demo.line.message.entity.TextMessage;
import com.example.demo.line.util.SendMessageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PushService implements LineKeys, ImagesURL {

	private static final Logger LOG = LoggerFactory.getLogger(PushService.class);


	private final SendMessageUtil sendMessageUtil;
	private final ObjectMapper objectMapper;
	private final UUIDUtil uuidUtil;

	@Autowired
	public PushService (SendMessageUtil sendMessageUtil,ObjectMapper objectMapper,UUIDUtil uuidUtil){
		this.sendMessageUtil = sendMessageUtil;
		this.objectMapper = objectMapper;
		this.uuidUtil = uuidUtil;
	}

	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	// to one user
	public void sendPostMessages(String[] userIds, String... messages) {

		String uuid = uuidUtil.getRandomUUID();

		Push push = new Push();

		TextMessage textMessage;

		List<EntityMessage> messageList = new ArrayList<>();

		for (String message : messages) {
			textMessage = new TextMessage();
			textMessage.setType("text");
			textMessage.setText(message);
			messageList.add(textMessage);
		}

		push.setMessages(messageList);

		if (userIds.length == 1) {
			push.setTo(userIds[0]);
		} else {
			push.setTo(Arrays.toString(userIds));
		}

		System.out.println(Arrays.toString(userIds));

		String jsonData = "";
		try {
			jsonData = objectMapper.writeValueAsString(push);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(jsonData);

		HttpResponse httpResponse = sendMessageUtil.sendPost(uuid, jsonData);

		boolean isDone = httpResponse.getStatusCode() == 200;

		if (!isDone) {
			pushFailedHashMap.put(uuid, jsonData);
		}

		System.out.println(isDone ? "成功發送" : "發送失敗");
	}

	public void sendPostQuickReplys(String[] userIds) {
		String uuid = uuidUtil.getRandomUUID();

		List<EntityMessage> messageList = new ArrayList<>();

		List<QuickReplyAction> actionList = new ArrayList<>();
		

		actionList.add(new QuickReplyAction("action",DOGE_URL,new MessageAction("message","doge","testMessage")));

		actionList.add(new QuickReplyAction("action",LOGO_URL,new MessageAction("message","logo","testLogo")));

		actionList.add(new QuickReplyAction("action",new LocationAction("location","Send location")));
		
		// set
		QuickReplyMessage quickReplyMessage = new QuickReplyMessage("text","Select one",new QuickReply(actionList));
		messageList.add(quickReplyMessage);
		Push push = new Push(messageList);

		if (userIds.length == 1) {
			push.setTo(userIds[0]);
		} else {
			push.setTo(Arrays.toString(userIds));
		}

		System.out.println(Arrays.toString(userIds));

		String jsonData = "";
		try {
			jsonData = objectMapper.writeValueAsString(push);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(jsonData);

		HttpResponse httpResponse = sendMessageUtil.sendPost(uuid, jsonData);

		boolean isDone = httpResponse.getStatusCode() == 200;

		if (!isDone) {
			pushFailedHashMap.put(uuid, jsonData);
		}

		System.out.println(isDone ? "成功發送" : "發送失敗");
	}
}
