package com.omg.evn.entity.app;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @Company: OMG Technology co.Ltd. (c) copyright
 * @Title: AppCode.java
 * @Package com.omg.evn.entity.app
 * @discription 编码类型-实体类
 * @author zyen
 * @date 2015-5-1 下午7:04:43 
 * @version V1.0 
 * @editor:
 * @editDate: 
 * @editInfo:
 */
@Entity
@Table(name = "app_code", catalog = "omgam")
public class AppCode implements java.io.Serializable {

	// Fields

	private Integer codeId;
	private String codeType;
	private String codeName;
	private Integer userId;
	private Date createDate;
	private Date lastDate;
	
	private String createDates;

	// Constructors

	/** default constructor */
	public AppCode() {
	}

	/** full constructor */
	public AppCode(String codeType, String codeName, Integer userId,
			Date createDate, Date lastDate) {
		this.codeType = codeType;
		this.codeName = codeName;
		this.userId = userId;
		this.createDate = createDate;
		this.lastDate = lastDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CODE_ID", unique = true, nullable = false)
	public Integer getCodeId() {
		return this.codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	@Column(name = "CODE_TYPE", length = 16)
	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	@Column(name = "CODE_NAME", length = 64)
	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@Column(name = "USER_ID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "LAST_DATE", length = 19)
	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

}