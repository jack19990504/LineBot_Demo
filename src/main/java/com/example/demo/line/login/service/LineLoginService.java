package com.example.demo.line.login.service;

import com.example.demo.feign.LineLoginClient;
import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.entity.LineUserDetail;
import com.example.demo.line.login.util.SendLoginAPIUtil;
import com.example.demo.properties.LineLoginProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class LineLoginService {

	// show spring init components and other tags at starting server
	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	private final LineLoginClient lineLoginClient;
	final SendLoginAPIUtil sendLoginAPIUtil;

	public LineLoginService(SendLoginAPIUtil sendLoginAPIUtil,LineLoginClient lineLoginClient) {
		this.lineLoginClient = lineLoginClient;
		this.sendLoginAPIUtil = sendLoginAPIUtil;
	}

	public AccessToken getUserAccessToken(String code) {

		Map<String,String> map = new HashMap<>(){{
			put("grant_type", LineLoginProperties.grant_type);
			put("code",code);
			put("redirect_uri",LineLoginProperties.redirect_uri);
			put("client_id",LineLoginProperties.client_id);
			put("client_secret",LineLoginProperties.client_secret);
		}};


		Optional<AccessToken> Optional_AccessToken = Optional.ofNullable(lineLoginClient.userAccessToken(map));

		return Optional_AccessToken
				.filter(e -> e.getId_token() != null && e.getAccess_token() != null).orElseGet(AccessToken::new);
	}

	public LineUserDetail getUserDetail(String idToken) {

		Optional<LineUserDetail> Optional_LineUserDetail = Optional
				.ofNullable(lineLoginClient.lineUserDetail(LineLoginProperties.client_id,idToken));
		return Optional_LineUserDetail
				.filter(user -> user.getName() != null && user.getSub() != null).orElseGet(LineUserDetail::new);
	}

	public LineUser getUser(String accessToken) {
		String header = "Bearer " + accessToken;
		Optional<LineUser> Optional_LineUser = Optional.ofNullable(lineLoginClient.lineUser(header));

		return Optional_LineUser.filter(e -> e.getDisplayName() != null && e.getUserId() != null)
				.orElseGet(LineUser::new);

	}
}
