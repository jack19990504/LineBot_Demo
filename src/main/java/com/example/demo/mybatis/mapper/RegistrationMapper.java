package com.example.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.mybatis.entity.Registration;

@Repository
public interface RegistrationMapper {
  @Select("SELECT * FROM registration WHERE registrationId = #{registrationId}")
  Registration getRegistrationById(@Param("registrationId") String registrationId);
  
  @Select("SELECT * FROM registration WHERE member_Email = #{member_Email} ORDER BY registrationDate LIMIT 1")
  Registration getRegistrationByMemberEmail(@Param("member_Email") String member_Email);
  
  
}
