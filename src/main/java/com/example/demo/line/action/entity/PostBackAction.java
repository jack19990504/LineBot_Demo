package com.example.demo.line.action.entity;

public class PostBackAction extends Action {

	private String type;
	private String label;
	private String data;
	private String displayText;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public PostBackAction(String type, String label, String data, String displayText) {
		super();
		this.type = type;
		this.label = label;
		this.data = data;
		this.displayText = displayText;
	}

	public PostBackAction() {
		super();
	}

}
