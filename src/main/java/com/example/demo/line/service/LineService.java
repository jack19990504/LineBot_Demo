package com.example.demo.line.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.line.entity.PostMessageTemplate;
import com.example.demo.line.entity.ReplyMessageTemplate;
import com.example.demo.line.util.SendMessageUtil;

@Service
public class LineService {

	@Autowired
	SendMessageUtil sendMessageUtil;
	
	// show spring init components and other tags at starting server
	{System.out.println("init :"+this.getClass().getSimpleName());}
	
	public void sendResponseMessage(String replyToken, String message) {

		ReplyMessageTemplate replyMessageTemplate = new ReplyMessageTemplate();

		replyMessageTemplate.setMessage(message, replyToken);

		boolean isDone = sendMessageUtil.sendReplyMessage(replyMessageTemplate.getMessage());

		System.out.println(isDone == true ? "成功回復" : "回復失敗");

	}

	public void sendResponseMessage(String replyToken, String[] messages) {

		ReplyMessageTemplate replyMessageTemplate = new ReplyMessageTemplate();

		replyMessageTemplate.setMessage(messages, replyToken);

		boolean isDone = sendMessageUtil.sendReplyMessage(replyMessageTemplate.getMessage());

		System.out.println(isDone == true ? "成功回復" : "回復失敗");
	}

	// to one user
	public void sendPostMessages(String[] messages, String userId) {

		PostMessageTemplate postMessageTemplate = new PostMessageTemplate();

		postMessageTemplate.setMessage(messages, userId);

		boolean isDone = sendMessageUtil.sendPostMessage(postMessageTemplate.getMessage());

		System.out.println(isDone == true ? "成功發送" : "發送失敗");
	}

	public void sendPostMessages(String messages, String userId) {

		PostMessageTemplate postMessageTemplate = new PostMessageTemplate();

		postMessageTemplate.setMessage(messages, userId);

		boolean isDone = sendMessageUtil.sendPostMessage(postMessageTemplate.getMessage());

		System.out.println(isDone == true ? "成功發送" : "發送失敗");
	}

}