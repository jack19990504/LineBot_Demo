package com.example.demo.line.message.flex.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BubbleStyle {

	private Box header;
	private Box hero;
	private Box body;
	private Box footer;
	public Box getHeader() {
		return header;
	}
	public void setHeader(Box header) {
		this.header = header;
	}
	public Box getHero() {
		return hero;
	}
	public void setHero(Box hero) {
		this.hero = hero;
	}
	public Box getBody() {
		return body;
	}
	public void setBody(Box body) {
		this.body = body;
	}
	public Box getFooter() {
		return footer;
	}
	public void setFooter(Box footer) {
		this.footer = footer;
	}
	
	
}
