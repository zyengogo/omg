package com.omg.evn.entity.app;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppLove entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_love", catalog = "omgam")
public class AppLove implements java.io.Serializable {

	// Fields

	private Integer loveId;
	private Integer userId;
	private String loveContect;
	private String addNum;
	private String addName;
	private Timestamp createDate;

	// Constructors

	/** default constructor */
	public AppLove() {
	}

	/** full constructor */
	public AppLove(Integer userId, String loveContect, String addNum,
			String addName, Timestamp createDate) {
		this.userId = userId;
		this.loveContect = loveContect;
		this.addNum = addNum;
		this.addName = addName;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LOVE_ID", unique = true, nullable = false)
	public Integer getLoveId() {
		return this.loveId;
	}

	public void setLoveId(Integer loveId) {
		this.loveId = loveId;
	}

	@Column(name = "USER_ID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "LOVE_CONTECT", length = 200)
	public String getLoveContect() {
		return this.loveContect;
	}

	public void setLoveContect(String loveContect) {
		this.loveContect = loveContect;
	}

	@Column(name = "ADD_NUM", length = 200)
	public String getAddNum() {
		return this.addNum;
	}

	public void setAddNum(String addNum) {
		this.addNum = addNum;
	}

	@Column(name = "ADD_NAME", length = 200)
	public String getAddName() {
		return this.addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}