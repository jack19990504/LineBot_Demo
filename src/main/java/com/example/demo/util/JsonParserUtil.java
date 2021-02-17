package com.example.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JsonParserUtil {

	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	private final ObjectMapper objectMapper;

	public JsonParserUtil(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

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
