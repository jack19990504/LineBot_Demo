package com.example.demo.line.util;

import com.example.demo.mybatis.entity.Member;
import com.fasterxml.jackson.databind.JavaType;
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
	
	public <T> T stringToJson(String jsonString , Class<?> clazz)
	{
		T returnObject = null;
		try {
			returnObject = (T)objectMapper.readValue(jsonString, clazz);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	
	
}
