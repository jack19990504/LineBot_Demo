package com.example.demo.line.message.entity;

import java.util.List;

public class Push {

	private String to;

	private List<EntityMessage> messages;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<EntityMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<EntityMessage> messages) {
		this.messages = messages;
	}

	public Push(String to, List<EntityMessage> messages) {
		super();
		this.to = to;
		this.messages = messages;
	}

	public Push(List<EntityMessage> messages) {
		super();
		this.messages = messages;
	}

	public Push() {
		super();
	}

}
