package com.example.demo.line.action.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type",
		  visible = true)
		@JsonSubTypes({ 
		  @Type(value = URIAction.class, name = "uri")
		})
public abstract class Action {
	// action that is used to inherit by other actions
}
