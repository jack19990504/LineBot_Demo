package com.example.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.mybatis.entity.User;

@Repository
public interface UserMapper {
  @Select("SELECT * FROM user WHERE userId = #{userId}")
  User findByState(@Param("userId") String userId);

}
