package com.omg.xbase.spring.security.utils;

import javax.persistence.Column;

public class SysInitialPara {
	private String loginName;//用户名
	private String userName;//用户真实姓名
	private String orgname;//组织id
	private String orgid;//组织名称
	private String loginPassWord;
	private String newpassword;//新密码

	private String  powerself;//群组名称
	
		
	
	
	public String getPowerself() {
		return powerself;
	}
	public void setPowerself(String powerself) {
		this.powerself = powerself;
	}
	
	
	private  int status;//启用，未启用
	private  String  statuss;//启用，未启用
	private  String  userStatus;//开通.未开通....
	private  String  power;//开通.未开通....
	private  String  ischarges;//是否组长.
	public String getIscharges() {
		return ischarges;
	}
	public void setIscharges(String ischarges) {
		this.ischarges = ischarges;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassWord() {
		return loginPassWord;
	}
	public void setLoginPassWord(String loginPassWord) {
		this.loginPassWord = loginPassWord;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatuss() {
		return statuss;
	}
	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
