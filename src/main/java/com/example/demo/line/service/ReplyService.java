package com.example.demo.line.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.keys.LineKeys;
import com.example.demo.line.message.entity.FlexMessage;
import com.example.demo.line.message.entity.Message;
import com.example.demo.line.message.entity.Reply;
import com.example.demo.line.message.entity.TextMessage;
import com.example.demo.line.util.SendMessageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReplyService implements LineKeys {

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



}