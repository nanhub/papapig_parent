package com.pppig.user.pojo;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "user_main")
public class User {

 private String userId;
 private String email;
 private String nikeName;
 private String realName;
 private String idCardNo;
 private String passwd;
 private String payPassword;
 private String isPwdSet;
 private String mobile;
 private String jxmobile;
 private String city;
 private String registerTime;
 private String roles;
 private String cash;
 private String currBal;
 private String frozenWithDrawCash;
 private String frozenBiddingCash;
 private String idVerifyLimit;
 private String portrait;
 private String status;
 private String forbidStatus;
 private String albumCapacity;
 private String securityLevel;
 private String weiboUId;
 private String weiboAccessToken;
 private String qqUId;
 private String qqAccessToken;
 private String origin;
 private String staffId;
 private String userCode;
 private String referee;
 private String level_update_time;
 private String level;
 private String bindtime;
 private String loginkey;
 private String userkey;
 private String cashChl;


}
