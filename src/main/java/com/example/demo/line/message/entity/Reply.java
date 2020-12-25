package com.example.demo.line.message.entity;

import java.util.List;

public class Reply {
	
	private String replyToken;
	
	private List<EntityMessage> messages;

	public String getReplyToken() {
		return replyToken;
	}

	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}

	public List<EntityMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<EntityMessage> messages) {
		this.messages = messages;
	}

	public Reply(String replyToken, List<EntityMessage> messages) {
		super();
		this.replyToken = replyToken;
		this.messages = messages;
	}

	public Reply() {
		super();
	} 
	
	

}
