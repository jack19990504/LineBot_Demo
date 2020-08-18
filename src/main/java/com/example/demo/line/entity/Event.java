package com.example.demo.line.entity;

import com.example.demo.line.action.entity.PostBack;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    private String replyToken;
    private String mode;
    private String type;
    private Source source;
    private String timestamp;
    private Message message;

    private PostBack postBack;
    
    
    public Event() {
		
	}
	public Event(String type, Source source, Message message,String replyToken) {
		this.type = type;
		this.source = source;
		this.message = message;
		this.replyToken = replyToken;
	}
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
	public PostBack getPostBack() {
		return postBack;
	}
	public void setPostBack(PostBack postBack) {
		this.postBack = postBack;
	}
    
}