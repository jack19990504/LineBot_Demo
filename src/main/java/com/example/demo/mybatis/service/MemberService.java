package com.example.demo.mybatis.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.mybatis.MemberDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mybatis.entity.Member;
import com.example.demo.mybatis.mapper.MemberMapper;

import java.util.Optional;

@Service
public class MemberService {
  private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
  {
    LOG.info("init :\t" + this.getClass().getSimpleName());
  }
  private MemberMapper memberMapper;
  private MemberDAO memberDAO;

  @Autowired
  public MemberService(MemberMapper memberMapper,MemberDAO memberDAO) {
    this.memberMapper = memberMapper;
    this.memberDAO = memberDAO;
  }

  public Member getMemberByEmail(String memberEmail) {
    return memberMapper.getMemberByEmail(memberEmail);
  }

  public Member getMemberByLineId(String memberLineId) {
    return memberMapper.getMemberByLineId(memberLineId);
  }

  public Member getMemberTest(String id){

    return memberDAO.find(id).orElseThrow( () -> new NotFoundException("member not found"));
  }

}
