package com.example.demo.line.util;

import com.example.demo.keys.LineKeys;
import com.example.demo.keys.MessageAPIProperties;
import com.example.demo.line.entity.LineUserProfile;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.JsonParserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LineMessageAPIUtil implements LineKeys {

	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	private final HttpClientUtil httpClientUtil;
	private final JsonParserUtil jsonParserUtil;

	@Autowired
	public LineMessageAPIUtil(HttpClientUtil httpClientUtil, JsonParserUtil jsonParserUtil){
		this.httpClientUtil = httpClientUtil;
		this.jsonParserUtil = jsonParserUtil;
	}

	public boolean sendReply(String uuid,String message) {

		var reply = httpClientUtil.messageAPI_reply(MessageAPIProperties.REPLY, MessageAPIProperties.ACCESS_TOKEN,message);

		var isOK = httpClientUtil.doRequest(reply).getStatusCode() == 200;

		if(!isOK && replyFailedHashMap.containsValue(message)){
			replyFailedHashMap.put(uuid, message);
		}

		log.info(isOK ? "成功回覆" : "回覆失敗");

		return isOK;
	}

	public boolean sendPush(String uuid, String message) {

		var push = httpClientUtil.messageAPI_push(MessageAPIProperties.PUSH,MessageAPIProperties.ACCESS_TOKEN,message,uuid);

		var isOK = httpClientUtil.doRequest(push).getStatusCode() == 200;

		if(!isOK && pushFailedHashMap.containsValue(message)){
			pushFailedHashMap.put(uuid, message);
		}

		log.info(isOK ? "成功回覆" : "回覆失敗");

		return isOK;
	}

	public LineUserProfile getUserProfile(String userId){

		var httpGet = httpClientUtil.getUserProfile(MessageAPIProperties.GET_USER_PROFILE,MessageAPIProperties.ACCESS_TOKEN,userId);

		var response = httpClientUtil.doRequest(httpGet);

		return jsonParserUtil.stringToJson(response.getResponseBody(),LineUserProfile.class);
	}
}
