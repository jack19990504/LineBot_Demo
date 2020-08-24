package com.example.demo.line.message.entity;

import java.util.List;

public class Push {

	private String to;

	private List<Message> messages;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Push(String to, List<Message> messages) {
		super();
		this.to = to;
		this.messages = messages;
	}

	public Push(List<Message> messages) {
		super();
		this.messages = messages;
	}

	public Push() {
		super();
	}

}
