package com.example.demo.line.login.service;

import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.entity.LineUserDetail;
import com.example.demo.line.login.util.SendLoginAPIUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LineLoginService {

	final SendLoginAPIUtil sendLoginAPIUtil;
	// show spring init components and other tags at starting server
	{
		log.info("init :\t" + this.getClass().getSimpleName());
	}

	public LineLoginService(SendLoginAPIUtil sendLoginAPIUtil) {
		this.sendLoginAPIUtil = sendLoginAPIUtil;
	}

	public AccessToken getUserAccessToken(String code) {

		Optional<AccessToken> Optional_AccessToken = Optional.ofNullable(sendLoginAPIUtil.getUserAccessToken(code));
		AccessToken accessToken = Optional_AccessToken
				.filter(e -> e.getId_token() != null && e.getAccess_token() != null).orElseGet(AccessToken::new);

		return accessToken;
	}

	public LineUserDetail getUserDetail(String idToken) {
		Optional<LineUserDetail> Optional_LineUserDetail = Optional
				.ofNullable(sendLoginAPIUtil.getLineUserDetail(idToken));
		LineUserDetail lineUserDetail = Optional_LineUserDetail
				.filter(user -> user.getName() != null && user.getSub() != null).orElseGet(LineUserDetail::new);
		return lineUserDetail;
	}

	public LineUser getUser(String accessToken) {
		Optional<LineUser> Optional_LineUser = Optional.ofNullable(sendLoginAPIUtil.getUser(accessToken));
		LineUser lineUser = Optional_LineUser.filter(e -> e.getDisplayName() != null && e.getUserId() != null)
				.orElseGet(LineUser::new);

		return lineUser;

	}
}
