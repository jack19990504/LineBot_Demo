package com.example.demo.line.util;

import com.example.demo.keys.LineKeys;
import com.example.demo.line.entity.LineUserProfile;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.entity.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessageUtil implements LineKeys {

	private static final Logger LOG = LoggerFactory.getLogger(SendMessageUtil.class);
	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}

	private final HttpClientUtil httpClientUtil;
	private final JsonParserUtil jsonParserUtil;

	@Autowired
	public SendMessageUtil(HttpClientUtil httpClientUtil,JsonParserUtil jsonParserUtil){
		this.httpClientUtil = httpClientUtil;
		this.jsonParserUtil = jsonParserUtil;
	}

	public boolean sendReply(String uuid,String message) {

		LOG.info(message);

		HttpPost post = httpClientUtil.setMessage(URL_REPLY,accessToken,message,uuid);

		boolean isOK = httpClientUtil.doRequest(post).getStatusCode() == 200;

		if(!isOK){
			replyFailedHashMap.put(uuid, message);
		}

		LOG.info(isOK == true ? "成功回覆" : "回覆失敗");

		return isOK;
	}

	public boolean sendPost(String uuid,String message) {

		LOG.info(message);

		HttpPost post = httpClientUtil.setMessage(URL_PUSH,accessToken,message,uuid);

		boolean isOK = httpClientUtil.doRequest(post).getStatusCode() == 200;

		if(!isOK){
			pushFailedHashMap.put(uuid, message);
		}

		LOG.info(isOK == true ? "成功回覆" : "回覆失敗");

		return isOK;
	}

	public LineUserProfile getUserProfile(String userId){

		LOG.info("userId = {}",userId);

		HttpGet httpGet = httpClientUtil.setUserProfile(URL_GET_USER_PROFILE,accessToken,userId);

		HttpResponse response = httpClientUtil.doRequest(httpGet);

		LineUserProfile lineUserProfile = jsonParserUtil.stringToJson(response.getResponseBody(),LineUserProfile.class);

		return lineUserProfile;
	}
}
