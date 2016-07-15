package com.omg.evn.entity.app;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppLoveData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_love_data", catalog = "omgam")
public class AppLoveData implements java.io.Serializable {

	// Fields

	private Integer loveDataId;
	private Integer userId;
	private Integer loveUserId;
	private String loveDataContect;
	private Timestamp createDate;
	private String loveUserName;
	private Timestamp loveUserDate;

	// Constructors

	/** default constructor */
	public AppLoveData() {
	}

	/** full constructor */
	public AppLoveData(Integer userId, Integer loveUserId,
			String loveDataContect, Timestamp createDate, String loveUserName,
			Timestamp loveUserDate) {
		this.userId = userId;
		this.loveUserId = loveUserId;
		this.loveDataContect = loveDataContect;
		this.createDate = createDate;
		this.loveUserName = loveUserName;
		this.loveUserDate = loveUserDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LOVE_DATA_ID", unique = true, nullable = false)
	public Integer getLoveDataId() {
		return this.loveDataId;
	}

	public void setLoveDataId(Integer loveDataId) {
		this.loveDataId = loveDataId;
	}

	@Column(name = "USER_ID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "LOVE_USER_ID")
	public Integer getLoveUserId() {
		return this.loveUserId;
	}

	public void setLoveUserId(Integer loveUserId) {
		this.loveUserId = loveUserId;
	}

	@Column(name = "LOVE_DATA_CONTECT", length = 200)
	public String getLoveDataContect() {
		return this.loveDataContect;
	}

	public void setLoveDataContect(String loveDataContect) {
		this.loveDataContect = loveDataContect;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "LOVE_USER_NAME", length = 200)
	public String getLoveUserName() {
		return this.loveUserName;
	}

	public void setLoveUserName(String loveUserName) {
		this.loveUserName = loveUserName;
	}

	@Column(name = "LOVE_USER_DATE", length = 19)
	public Timestamp getLoveUserDate() {
		return this.loveUserDate;
	}

	public void setLoveUserDate(Timestamp loveUserDate) {
		this.loveUserDate = loveUserDate;
	}

}