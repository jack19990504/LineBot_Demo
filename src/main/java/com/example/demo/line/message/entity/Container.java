package com.example.demo.line.message.entity;

import com.example.demo.line.action.entity.Action;
import com.example.demo.line.message.flex.entity.Box;
import com.example.demo.line.message.flex.entity.BubbleStyle;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Container {

	private String type;
	private Box header;
	private Box hero;
	private Box body;
	private Box footer;
	private BubbleStyle styles;
	private Action action;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	public BubbleStyle getStyles() {
		return styles;
	}
	public void setStyles(BubbleStyle styles) {
		this.styles = styles;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	
}
