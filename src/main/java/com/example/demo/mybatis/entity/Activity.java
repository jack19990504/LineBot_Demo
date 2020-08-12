package com.example.demo.mybatis.entity;

import java.sql.Timestamp;

public class Activity {

  private String activityId;
  private String activityName;
  private String activityAddress;
  private Timestamp activityStartTime;
  private Timestamp activityEndTime;
  private String activityHost;
  public String getActivityId() {
    return activityId;
  }
  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }
  public String getActivityName() {
    return activityName;
  }
  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }
  public String getActivityAddress() {
    return activityAddress;
  }
  public void setActivityAddress(String activityAddress) {
    this.activityAddress = activityAddress;
  }
  public Timestamp getActivityStartTime() {
    return activityStartTime;
  }
  public void setActivityStartTime(Timestamp activityStarttime) {
    this.activityStartTime = activityStarttime;
  }
  public Timestamp getActivityEndTime() {
    return activityEndTime;
  }
  public void setActivityEndTime(Timestamp activityEndTime) {
    this.activityEndTime = activityEndTime;
  }
  public String getActivityHost() {
    return activityHost;
  }
  public void setActivityHost(String activityHost) {
    this.activityHost = activityHost;
  }
  
  
  
}
