package com.example.demo.line.service;

import com.example.demo.feign.MessageAPI;
import com.example.demo.keys.ImagesProperties;
import com.example.demo.line.action.entity.LocationAction;
import com.example.demo.line.action.entity.MessageAction;
import com.example.demo.line.action.entity.QuickReplyAction;
import com.example.demo.line.message.entity.*;
import com.example.demo.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushService {

	private final UUIDUtil uuidUtil;

	private final MessageAPI messageAPI;

	@Autowired
	public PushService (UUIDUtil uuidUtil, MessageAPI messageAPI){
		this.uuidUtil = uuidUtil;
		this.messageAPI = messageAPI;
	}

	// show spring init components and other tags at starting server
	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}


	public void sendPostMessages(String[] userIds, String... messages) {

		Push push = new Push();

		List<EntityMessage> messageList = new ArrayList<>();

		for (String message : messages) {
			messageList.add(new TextMessage("text",message));
		}

		push.setMessages(messageList);

		sendMessage(userIds, push);
	}

	public void sendPostQuickReplies(String[] userIds) {

		List<EntityMessage> messageList = new ArrayList<>();

		List<QuickReplyAction> actionList = new ArrayList<>();


		actionList.add(new QuickReplyAction("action", ImagesProperties.dogeURL,new MessageAction("message","doge","testMessage")));

		actionList.add(new QuickReplyAction("action",ImagesProperties.logoURL,new MessageAction("message","logo","testLogo")));

		actionList.add(new QuickReplyAction("action",new LocationAction("location","Send location")));

		// set
		QuickReplyMessage quickReplyMessage = new QuickReplyMessage("text","Select one",new QuickReply(actionList));
		messageList.add(quickReplyMessage);
		Push push = new Push(messageList);

		sendMessage(userIds, push);
	}


	private void sendMessage(String[] userIds,Push push) {
		if (userIds.length == 1) {
			push.setTo(userIds[0]);
		} else {
			push.setTo(Arrays.toString(userIds));
		}
		String uuid = uuidUtil.getRandomUUID();
		messageAPI.push(push,uuid);
	}
}
