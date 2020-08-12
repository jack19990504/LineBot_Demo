package com.example.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.mybatis.entity.Member;

@Repository
public interface MemberMapper {
  

  @Select("SELECT * FROM member WHERE memberEmail = #{memberEmail}")
  Member getMemberByEmail(@Param("memberEmail") String memberEmail);
  
  @Select("SELECT * FROM member WHERE memberLineUserId = #{memberLineUserId}")
  Member getMemberByLineId(@Param("memberLineUserId") String memberLineUserId);
}
