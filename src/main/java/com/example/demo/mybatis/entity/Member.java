package com.example.demo.mybatis.entity;

import java.sql.Timestamp;

public class Member {
  
  private String memberEmail;
  private String memberName;
  private String memberGender;
  private Timestamp memberBirthday;
  private String memberAddress;
  private String memberLineUserId;
  public String getMemberEmail() {
    return memberEmail;
  }
  public void setMemberEmail(String memberEmail) {
    this.memberEmail = memberEmail;
  }
  public String getMemberName() {
    return memberName;
  }
  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }
  public String getMemberGender() {
    return memberGender;
  }
  public void setMemberGender(String memberGender) {
    this.memberGender = memberGender;
  }
  public Timestamp getMemberBirthday() {
    return memberBirthday;
  }
  public void setMemberBirthday(Timestamp memberBirthday) {
    this.memberBirthday = memberBirthday;
  }
  public String getMemberAddress() {
    return memberAddress;
  }
  public void setMemberAddress(String memberAddress) {
    this.memberAddress = memberAddress;
  }
  public String getMemberLineUserId() {
    return memberLineUserId;
  }
  public void setMemberLineUserId(String memberLineUserId) {
    this.memberLineUserId = memberLineUserId;
  }
  
  
}
