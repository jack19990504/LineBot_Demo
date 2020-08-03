package com.example.demo.line.entity;

public class Event {
    private String replyToken;
    private String mode;
    private String type;
    private Source source;
    private String timestamp;
    private Message message;
    public String getReplyToken() {
        return replyToken;
    }
    public void setReplyToken(String replyToken) {
        this.replyToken = replyToken;
    }
    
    public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
}