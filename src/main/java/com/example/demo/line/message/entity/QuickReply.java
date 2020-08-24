package com.example.demo.line.message.entity;

import java.util.List;

import com.example.demo.line.action.entity.QuickReplyAction;

public class QuickReply extends Message{
	
	private List<QuickReplyAction> items;

	public List<QuickReplyAction> getItems() {
		return items;
	}

	public void setItems(List<QuickReplyAction> items) {
		this.items = items;
	}

	public QuickReply(List<QuickReplyAction> items) {
		super();
		this.items = items;
	}

	public QuickReply() {
		super();
	}
	
	

}
