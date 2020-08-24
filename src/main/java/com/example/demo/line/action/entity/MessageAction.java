package com.example.demo.line.action.entity;

public class MessageAction extends Action{

	private String type;
	private String label;
	private String text;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MessageAction(String type, String label, String text) {
		super();
		this.type = type;
		this.label = label;
		this.text = text;
	}

	
	public MessageAction() {
		super();
	}
	
}
