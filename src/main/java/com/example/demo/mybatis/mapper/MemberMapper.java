package com.example.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.mybatis.entity.Member;

@Repository
public interface MemberMapper {
  

  @Select("SELECT * FROM member WHERE memberEmail = #{memberEmail}")
  public Member getMemberByEmail(@Param("memberEmail") String memberEmail);
  
  @Select("SELECT * FROM member WHERE memberLineUserId = #{memberLineUserId}")
  public Member getMemberByLineId(@Param("memberLineUserId") String memberLineUserId);
  
  @Update("Update member SET memberName = 'test' WHERE memberEmail = #{memberEmail}")
  public int updateMemberByEmail(@Param("memberEmail") String memberEmail);
  
  @Delete("DELETE FROM member WHERE memberEmail = #{memberEmail}")
  public int deleteMemberByEmail(@Param("memberEmail") String memberEmail);
  
  @Insert("INSERT INTO member VALUES( #{memberEmail} , #{memberName} , '男' , NOW() , '123' , '456' )")
  public int insertMember(@Param("memberEmail") String memberEmail,@Param("memberName") String memberName);
}
