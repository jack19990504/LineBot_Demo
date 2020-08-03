package com.example.demo.line.entity;

public class PostMessageTemplate {

	private String message = "{\"to\":\"replaceUserId\",\"messages\":[replaceMessage]}";

	// send a single 
	public void setMessage(String message, String userId) {
		replace("replaceUserId", userId);
		replace("replaceMessage", "{\"type\":\"text\",\"text\":\"" + message + "\"}");
	}

	// send multi
	public void setMessage(String[] messages, String userId) {
		replace("replaceUserId", userId);
		final StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append("{\"type\":\"text\",\"text\":\"" + message + "\"},");
		}
		replace("replaceMessage", sb.toString().substring(0, sb.toString().length()-1));
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	private void replace(String before , String after)
	{
		this.message = this.message.replace(before, after);
	}

}
