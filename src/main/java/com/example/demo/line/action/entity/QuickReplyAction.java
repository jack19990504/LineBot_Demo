package com.example.demo.line.action.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuickReplyAction {
	
	private String type;

	private String imageUrl;
	
	private Action action;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	// all elements
	public QuickReplyAction(String type, String imageUrl, Action action) {
		super();
		this.type = type;
		this.imageUrl = imageUrl;
		this.action = action;
	}

	public QuickReplyAction(String type, Action action) {
		super();
		this.type = type;
		this.action = action;
	}
	
	public QuickReplyAction() {
		super();
	}
	
	

}
