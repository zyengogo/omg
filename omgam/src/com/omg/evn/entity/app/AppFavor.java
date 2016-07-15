package com.omg.evn.entity.app;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppFavor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_favor", catalog = "omgam")
public class AppFavor implements java.io.Serializable {

	// Fields

	private Integer favorId;
	private Integer usetId;
	private String userName;
	private Integer favorUserId;
	private Timestamp createDate;

	// Constructors

	/** default constructor */
	public AppFavor() {
	}

	/** full constructor */
	public AppFavor(Integer usetId, String userName, Integer favorUserId,
			Timestamp createDate) {
		this.usetId = usetId;
		this.userName = userName;
		this.favorUserId = favorUserId;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FAVOR_ID", unique = true, nullable = false)
	public Integer getFavorId() {
		return this.favorId;
	}

	public void setFavorId(Integer favorId) {
		this.favorId = favorId;
	}

	@Column(name = "USET_ID")
	public Integer getUsetId() {
		return this.usetId;
	}

	public void setUsetId(Integer usetId) {
		this.usetId = usetId;
	}

	@Column(name = "USER_NAME", length = 16)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "FAVOR_USER_ID")
	public Integer getFavorUserId() {
		return this.favorUserId;
	}

	public void setFavorUserId(Integer favorUserId) {
		this.favorUserId = favorUserId;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}