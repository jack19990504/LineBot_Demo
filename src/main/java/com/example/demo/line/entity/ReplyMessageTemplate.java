package com.example.demo.line.entity;

public class ReplyMessageTemplate {

	private String message = "{\"replyToken\":\"replaceReplyToken\",\"messages\":[replaceMessage]}";

	// for single reply
	public void setMessage(String message, String replyToken) {

		replace("replaceReplyToken", replyToken);
		replace("replaceMessage", "{\"type\":\"text\",\"text\":\""+message+"\"}");
	}

	// for multi reply
	public void setMessage(String[] messages, String replyToken) {
		replace("replaceReplyToken", replyToken);
		final StringBuilder sb = new StringBuilder();

		for (String message : messages) {
			sb.append("{\"type\":\"text\",\"text\":\"" + message + "\"},");
		}
		replace("replaceMessage", sb.toString().substring(0, sb.toString().length() - 1));
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
