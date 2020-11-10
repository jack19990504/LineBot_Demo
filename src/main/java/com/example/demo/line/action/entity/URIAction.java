package com.example.demo.line.action.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "URIAction")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class URIAction extends Action{
	
	private String type;
	private String label;
	private String uri;
	private AltUri altUri;
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
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public AltUri getAltUri() {
		return altUri;
	}
	public void setAltUri(AltUri altUri) {
		this.altUri = altUri;
	}
	public URIAction(String type, String label, String uri, AltUri altUri) {
		super();
		this.type = type;
		this.label = label;
		this.uri = uri;
		this.altUri = altUri;
	}
	
	public URIAction() {
		super();
	}

}
