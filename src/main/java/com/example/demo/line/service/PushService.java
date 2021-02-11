package com.example.demo.line.service;

import com.example.demo.keys.ImagesURL;
import com.example.demo.keys.LineKeys;
import com.example.demo.line.action.entity.LocationAction;
import com.example.demo.line.action.entity.MessageAction;
import com.example.demo.line.action.entity.QuickReplyAction;
import com.example.demo.line.message.entity.*;
import com.example.demo.line.util.SendMessageUtil;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushService implements LineKeys, ImagesURL {

	private final SendMessageUtil sendMessageUtil;
	private final JsonParserUtil jsonParserUtil;
	private final UUIDUtil uuidUtil;

	@Autowired
	public PushService (SendMessageUtil sendMessageUtil,JsonParserUtil jsonParserUtil,UUIDUtil uuidUtil){
		this.sendMessageUtil = sendMessageUtil;
		this.jsonParserUtil = jsonParserUtil;
		this.uuidUtil = uuidUtil;
	}

	// show spring init components and other tags at starting server
	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}


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

		sendMessage(userIds, uuid, push);
	}

	private void sendMessage(String[] userIds, String uuid, Push push) {
		if (userIds.length == 1) {
			push.setTo(userIds[0]);
		} else {
			push.setTo(Arrays.toString(userIds));
		}

		String jsonData = jsonParserUtil.jsonToString(push);

		sendMessageUtil.sendPush(uuid, jsonData);
	}

	public void sendPostQuickReplies(String[] userIds) {
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

		sendMessage(userIds, uuid, push);
	}
}
