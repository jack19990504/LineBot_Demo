package com.example.demo.line.login.util;

import com.example.demo.keys.LineKeys;
import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.entity.LineUserDetail;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.entity.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendLoginAPIUtil implements LineKeys {

	private final JsonParserUtil jsonParserUtil;
	private final HttpClientUtil httpClientUtil;

	// show spring init components and other tags at starting server
	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	public SendLoginAPIUtil(JsonParserUtil jsonParserUtil,HttpClientUtil httpClientUtil) {
		this.jsonParserUtil = jsonParserUtil;
		this.httpClientUtil = httpClientUtil;
	}

	public AccessToken getUserAccessToken(String code) {

		HttpPost loginRequest = httpClientUtil.setLoginAPI(code);
		HttpResponse response = httpClientUtil.doRequest(loginRequest);

		if(response.getStatusCode() != 200){
			log.error("request failed");
			return null;
		}

		return jsonParserUtil.stringToJson(response.getResponseBody(),AccessToken.class);

	}

	public LineUserDetail getLineUserDetail(String idToken) {

		HttpPost getDetailRequest = httpClientUtil.setGetUserDetail(idToken);
		HttpResponse response = httpClientUtil.doRequest(getDetailRequest);

		if(response.getStatusCode() != 200){
			log.error("request failed");
			return null;
		}

		return jsonParserUtil.stringToJson(response.getResponseBody(),LineUserDetail.class);
	}

	public LineUser getUser(String accessToken) {

		HttpGet getUser = httpClientUtil.setUserProfile_login(accessToken);
		HttpResponse response = httpClientUtil.doRequest(getUser);

		if(response.getStatusCode() != 200){
			log.error("request failed");
			return null;
		}

		return jsonParserUtil.stringToJson(response.getResponseBody(),LineUser.class);
	}
}
