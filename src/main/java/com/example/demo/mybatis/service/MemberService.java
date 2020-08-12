package com.example.demo.mybatis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mybatis.entity.Member;
import com.example.demo.mybatis.mapper.MemberMapper;

@Service
public class MemberService {
  private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
  {
    LOG.info("init :\t" + this.getClass().getSimpleName());
  }
  @Autowired
  private MemberMapper memberMapper;

  public Member getMemberByEmail(String memberEmail) {
    return memberMapper.getMemberByEmail(memberEmail);
  }

  public Member getMemberByLineId(String memberLineId) {
    return memberMapper.getMemberByLineId(memberLineId);
  }

}
