package com.example.demo.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.example.demo.mybatis.entity.Activity;

@Repository
public interface ActivityMapper {

  @Select("SELECT * FROM activity WHERE activityId = #{activityId}")
  Activity getActivityById(@Param("activityId") String activityId);
  
}
