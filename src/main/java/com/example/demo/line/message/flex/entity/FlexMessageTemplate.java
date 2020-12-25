package com.example.demo.line.message.flex.entity;

import com.example.demo.line.message.entity.Container;
import com.example.demo.line.message.entity.EntityMessage;

public class FlexMessageTemplate extends EntityMessage {
	
	private String type;
	
	private String altText;
	
	private Container contents;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public Container getContents() {
		return contents;
	}

	public void setContents(Container contents) {
		this.contents = contents;
	}


	
	

}
