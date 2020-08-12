package com.example.demo.mybatis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mybatis.entity.Registration;
import com.example.demo.mybatis.mapper.RegistrationMapper;

@Service
public class RegistrationService {
  private static final Logger LOG = LoggerFactory.getLogger(RegistrationService.class);
  {
    LOG.info("init :\t" + this.getClass().getSimpleName());
  }
  @Autowired
  private RegistrationMapper registrationMapper;
  
  public Registration getRegistrationById(String registrationId)
  {
    return registrationMapper.getRegistrationById(registrationId);
  }
  
  public Registration getRegistrationListByMemberEmail(String member_Email)
  {
    return registrationMapper.getRegistrationByMemberEmail(member_Email);
  }
  

  
}
