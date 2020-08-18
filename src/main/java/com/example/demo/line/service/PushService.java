package com.example.demo.line.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.keys.ImagesURL;
import com.example.demo.keys.LineKeys;
import com.example.demo.line.action.entity.Action;
import com.example.demo.line.action.entity.QuickReplyAction;
import com.example.demo.line.message.entity.Message;
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

	@Autowired
	private SendMessageUtil sendMessageUtil;
	@Autowired
	private ObjectMapper objectMapper;

	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	// to one user
	public void sendPostMessages(String[] userIds, String... messages) {

		String uuid = UUID.randomUUID().toString();

		Push push = new Push();

		TextMessage textMessage;

		List<Message> messageList = new ArrayList<Message>();

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

		boolean isDone = sendMessageUtil.sendPostMessage(uuid, jsonData);

		if (!isDone) {
			pushFailedHashMap.put(uuid, jsonData);
		}

		System.out.println(isDone == true ? "成功發送" : "發送失敗");
	}

	public void sendPostQuickReplys(String[] userIds) {
		String uuid = UUID.randomUUID().toString();

		// push 
		Push push = new Push();
		List<Message> messageList = new ArrayList<Message>();

		// quick reply
		QuickReplyMessage quickReplyMessage = new QuickReplyMessage();
		QuickReply quickReply = new QuickReply();
		QuickReplyAction quickReplyAction = new QuickReplyAction();
		List<QuickReplyAction> actionList = new ArrayList<QuickReplyAction>();
		Action action = new Action();
		
		//head
		quickReplyMessage.setType("text");
		quickReplyMessage.setText("Select one ");
		
		// 1
		quickReplyAction.setType("action");
		quickReplyAction.setImageUrl(DOGE_URL);
		
		action.setText("doge");
		action.setType("message");
		action.setLabel("doge");
		action.setData("doge data");
		action.setImageUrl(DOGE_URL);
		quickReplyAction.setAction(action);
		actionList.add(quickReplyAction);

		// 2
		quickReplyAction = new QuickReplyAction();
		quickReplyAction.setType("action");
		quickReplyAction.setImageUrl(LOGO_URL);
		
		action = new Action();
		action.setText("logo");
		action.setType("message");
		action.setLabel("logo");
		action.setData("logo data");
		action.setImageUrl(LOGO_URL);
		quickReplyAction.setAction(action);
		actionList.add(quickReplyAction);

		// 3
		
		quickReplyAction = new QuickReplyAction();
		quickReplyAction.setType("action");
		
		action = new Action();
		action.setType("location");
		action.setLabel("Send location");
		quickReplyAction.setAction(action);
		actionList.add(quickReplyAction);
		
		// set
		quickReply.setItems(actionList);
		quickReplyMessage.setQuickReply(quickReply);
		messageList.add(quickReplyMessage);
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

		boolean isDone = sendMessageUtil.sendPostMessage(uuid, jsonData);

		if (!isDone) {
			pushFailedHashMap.put(uuid, jsonData);
		}

		System.out.println(isDone == true ? "成功發送" : "發送失敗");
	}
}
