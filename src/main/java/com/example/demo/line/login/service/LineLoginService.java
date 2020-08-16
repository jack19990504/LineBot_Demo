package com.example.demo.line.login.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.line.login.entity.AccessToken;
import com.example.demo.line.login.entity.LineUser;
import com.example.demo.line.login.entity.LineUserDetail;
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
	
	public LineUserDetail getUserDetail(String idToken)
	{
		Optional<LineUserDetail> Optional_LineUserDetail = Optional.ofNullable(sendLoginAPIUtil.getLineUserDetail(idToken));
		LineUserDetail lineUserDetail = Optional_LineUserDetail.filter(user -> user.getName() != null && user.getSub() != null).orElse(new LineUserDetail());
		return lineUserDetail;
	}
	
	public LineUser getUser(String accessToken)
	{
		Optional<LineUser> Optional_LineUser = Optional.ofNullable(sendLoginAPIUtil.getUser(accessToken));
		LineUser lineUser = Optional_LineUser.filter(e -> e.getDisplayName() !=null && e.getUserId() !=null ).orElseGet(LineUser::new);
		
		return lineUser;
		
	}
}
