package com.example.demo.line.login.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.util.SendLoginAPIUtil;

@Service
public class LineLoginService {
	
	@Autowired
	SendLoginAPIUtil sendLoginAPIUtil;
	private static final Logger LOG = LoggerFactory.getLogger(LineLoginService.class);
	// show spring init components and other tags at starting server
	{
		LOG.info("init :\t" + this.getClass().getSimpleName());
	}
	
	public AccessToken getUserAccessToken(String code)
	{
		
		Optional<AccessToken> accessToken= Optional.ofNullable(sendLoginAPIUtil.getUserAccessToken(code));
		AccessToken accessTokenn = accessToken.filter(e->e.getId_token()!=null && e.getAccess_token() !=null).orElse(new AccessToken());
		
		return accessTokenn;
	}
	
	public LineUser getUserDetail(String idToken)
	{
		Optional<LineUser> Optional_LineUser = Optional.ofNullable(sendLoginAPIUtil.getLineUserDetail(idToken));
		LineUser lineUser = Optional_LineUser.filter(user -> user.getName() != null && user.getSub() != null).orElse(new LineUser());
		return lineUser;
	}
}
