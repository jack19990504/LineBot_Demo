package com.example.demo.line.service;

import java.util.ArrayList;
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
import com.example.demo.line.message.entity.FlexMessage;
import com.example.demo.line.message.entity.Message;
import com.example.demo.line.message.entity.QuickReply;
import com.example.demo.line.message.entity.QuickReplyMessage;
import com.example.demo.line.message.entity.Reply;
import com.example.demo.line.message.entity.TextMessage;
import com.example.demo.line.util.SendMessageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReplyService implements LineKeys,ImagesURL {

	private static final Logger LOG = LoggerFactory.getLogger(ReplyService.class);

	@Autowired
	private SendMessageUtil sendMessageUtil;
	@Autowired
	private ObjectMapper objectMapper;

	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	// send back one
	// message.length must smaller than 3
	public void sendResponseMessage(String replyToken, String... messages) {

		String uuid = UUID.randomUUID().toString();

		Reply reply = new Reply();

		List<Message> messagesList = new ArrayList<Message>();

		TextMessage textMessage;

		for (String message : messages) {
			textMessage = new TextMessage();
			textMessage.setType("text");
			textMessage.setText(message);
			messagesList.add(textMessage);
		}

		reply.setReplyToken(replyToken);
		reply.setMessages(messagesList);

		String jsonData = "";
		try {
			jsonData = objectMapper.writeValueAsString(reply);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(jsonData);

		boolean isDone = sendMessageUtil.sendReplyMessage(uuid, jsonData);

		if (!isDone) {
			replyFailedHashMap.put(uuid, jsonData);
		}

		System.out.println(isDone == true ? "成功回復" : "回復失敗");

	}

	// one flex
	public void sendResponseMessage_flex(String replyToken, FlexMessage flexMessage) {

		String uuid = UUID.randomUUID().toString();

		Reply reply = new Reply();

		List<Message> messageList = new ArrayList<Message>();

		messageList.add(flexMessage);

		reply.setReplyToken(replyToken);
		reply.setMessages(messageList);

		String jsonData = "";
		try {
			jsonData = objectMapper.writeValueAsString(reply);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(jsonData);

		boolean isDone = sendMessageUtil.sendReplyMessage(uuid, jsonData);

		if (!isDone) {
			replyFailedHashMap.put(uuid, jsonData);
		}

		System.out.println(isDone == true ? "成功回復" : "回復失敗");
	}

	@SuppressWarnings("unused")
	public void sendQuickReply(String replyToken) {
		
		String uuid = UUID.randomUUID().toString();

		// reply
		Reply reply = new Reply();
		reply.setReplyToken(replyToken);
		List<Message> messageList = new ArrayList<Message>();

		// quick reply
		QuickReplyMessage quickReplyMessage = new QuickReplyMessage();
		QuickReply quickReply = new QuickReply();
		QuickReplyAction quickReplyAction = new QuickReplyAction();
		List<QuickReplyAction> actionList = new ArrayList<QuickReplyAction>();
		Action action = new Action();
		
		TextMessage textMessage = new TextMessage();
		textMessage.setType("text");
		textMessage.setText("test");
		
		messageList.add(textMessage);

		// head
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
		
		
		reply.setMessages(messageList);


		String jsonData = "";
		try {
			jsonData = objectMapper.writeValueAsString(reply);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(jsonData);

		boolean isDone = sendMessageUtil.sendReplyMessage(uuid, jsonData);

		if (!isDone) {
			pushFailedHashMap.put(uuid, jsonData);
		}

		System.out.println(isDone == true ? "成功發送" : "發送失敗");
	}

}