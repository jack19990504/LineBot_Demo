package com.example.demo.line.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonParserUtil {

	@Autowired
	private ObjectMapper objectMapper;
	
	public String jsonToString(Object o)
	{
		String jsonData = "";
		try {
			jsonData = objectMapper.writeValueAsString(o);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonData;
	}
	
	public Object stringToJson(String jsonString , Class<?> clazz)
	{
		Object returnObject = null;
		try {
			returnObject = objectMapper.readValue(jsonString, clazz);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	
	
}
