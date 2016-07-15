package com.omg.evn.entity.search;

public class Inforadar {
	private String IR_URLTITLE;//标题
	private String IR_CONTENT;//内容
	private String IR_SITENAME;//来源
	private String IR_URLDATE;//发布日期
	private String IR_SRCNAME;//发布人
	private String IR_URLNAME;//文章地址
	private String id;//主键
	private String fileId;
	private String sname;//系统名
	private String fileUrl;//附件地址
	
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getIR_URLNAME() {
		return IR_URLNAME;
	}
	public void setIR_URLNAME(String iRURLNAME) {
		IR_URLNAME = iRURLNAME;
	}
	public String getIR_URLTITLE() {
		return IR_URLTITLE;
	}
	public void setIR_URLTITLE(String iRURLTITLE) {
		IR_URLTITLE = iRURLTITLE;
	}
	public String getIR_CONTENT() {
		return IR_CONTENT;
	}
	public void setIR_CONTENT(String iRCONTENT) {
		IR_CONTENT = iRCONTENT;
	}
	public String getIR_SITENAME() {
		return IR_SITENAME;
	}
	public void setIR_SITENAME(String iRSITENAME) {
		IR_SITENAME = iRSITENAME;
	}
	public String getIR_URLDATE() {
		return IR_URLDATE;
	}
	public void setIR_URLDATE(String iRURLDATE) {
		IR_URLDATE = iRURLDATE;
	}
	public String getIR_SRCNAME() {
		return IR_SRCNAME;
	}
	public void setIR_SRCNAME(String iRSRCNAME) {
		IR_SRCNAME = iRSRCNAME;
	}
	
}
