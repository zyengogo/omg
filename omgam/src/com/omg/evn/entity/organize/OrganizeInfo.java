package com.omg.evn.entity.organize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrganizeInfo")
public class OrganizeInfo {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String orgName;
	private String orgAdress;
	private String postCode;
	private String phone;
	private String email;
	private String webSiteUrl;
	private String orgFunction;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAdress() {
		return orgAdress;
	}
	public void setOrgAdress(String orgAdress) {
		this.orgAdress = orgAdress;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebSiteUrl() {
		return webSiteUrl;
	}
	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}
	
	public String getOrgFunction() {
		return orgFunction;
	}
	public void setOrgFunction(String orgFunction) {
		this.orgFunction = orgFunction;
	}
	
	
}
