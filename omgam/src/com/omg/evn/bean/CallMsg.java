package com.omg.evn.bean;

public class CallMsg {
	
	private String message;//提示信息
	private String status;//约定状态值
	private String opt;//自定义参数
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	

}
