package com.example.demo.line.action.entity;

public class LocationAction extends Action{
	
	private String type;
	private String label;
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
	public LocationAction(String type, String label) {
		super();
		this.type = type;
		this.label = label;
	}
	public LocationAction() {
		super();
	}
	
	
	
	
}
