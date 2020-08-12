package com.example.demo.mybatis.entity;

import java.sql.Timestamp;

public class Registration {
  
  private String registrationId;
  private String activity_Id;
  private String member_Email;
  private Timestamp registrationDate;
  public String getRegistrationId() {
    return registrationId;
  }
  public void setRegistrationId(String registrationId) {
    this.registrationId = registrationId;
  }
  public String getActivity_Id() {
    return activity_Id;
  }
  public void setActivity_Id(String activity_Id) {
    this.activity_Id = activity_Id;
  }
  public String getMember_Email() {
    return member_Email;
  }
  public void setMember_Email(String member_Email) {
    this.member_Email = member_Email;
  }
  public Timestamp getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(Timestamp registrationDate) {
    this.registrationDate = registrationDate;
  }
  
  

}
