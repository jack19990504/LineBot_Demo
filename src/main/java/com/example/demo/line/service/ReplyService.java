package com.example.demo.line.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.line.entity.PostMessageTemplate;
import com.example.demo.line.entity.ReplyMessageTemplate;
import com.example.demo.line.util.SendMessageUtil;

@Service
public class ReplyService {

	private static final Logger LOG = LoggerFactory.getLogger(ReplyService.class);
	
	@Autowired
	private SendMessageUtil sendMessageUtil;
	
	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t"+this.getClass().getSimpleName());
	}
	
	// send back one
	public void sendResponseMessage(String replyToken, String message) {

		ReplyMessageTemplate replyMessageTemplate = new ReplyMessageTemplate();

		replyMessageTemplate.setMessage(message, replyToken);

		boolean isDone = sendMessageUtil.sendReplyMessage(replyMessageTemplate.getMessage());

		System.out.println(isDone == true ? "成功回復" : "回復失敗");

	}
	
	// send back multi
	public void sendResponseMessage(String replyToken, String[] messages) {

		ReplyMessageTemplate replyMessageTemplate = new ReplyMessageTemplate();

		replyMessageTemplate.setMessage(messages, replyToken);

		boolean isDone = sendMessageUtil.sendReplyMessage(replyMessageTemplate.getMessage());

		System.out.println(isDone == true ? "成功回復" : "回復失敗");
	}
	
	// one flex
	public void sendResponseMessage_flex(String replyToken, String messages) {

      ReplyMessageTemplate replyMessageTemplate = new ReplyMessageTemplate();

      replyMessageTemplate.setFlexMessage(messages, replyToken);

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