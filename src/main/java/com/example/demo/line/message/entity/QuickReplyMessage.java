package com.example.demo.line.message.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuickReplyMessage extends Message {

	private String type;
	private String text;
	private QuickReply quickReply;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public QuickReply getQuickReply() {
		return quickReply;
	}

	public void setQuickReply(QuickReply quickReply) {
		this.quickReply = quickReply;
	}

}
