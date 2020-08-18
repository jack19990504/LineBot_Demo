package com.example.demo.line.message.entity;

import java.util.List;

public class Reply {
	
	private String replyToken;
	
	private List<Message> messages;

	public String getReplyToken() {
		return replyToken;
	}

	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	} 
	
	

}
