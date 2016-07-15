package com.omg.evn.entity.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppCodeDet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_code_det", catalog = "omgam")
public class AppCodeDet implements java.io.Serializable {

	// Fields

	private Integer codeDetId;
	private String codeNum;
	private String codeName;

	// Constructors

	/** default constructor */
	public AppCodeDet() {
	}

	/** full constructor */
	public AppCodeDet(String codeNum, String codeName) {
		this.codeNum = codeNum;
		this.codeName = codeName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CODE_DET_ID", unique = true, nullable = false)
	public Integer getCodeDetId() {
		return this.codeDetId;
	}

	public void setCodeDetId(Integer codeDetId) {
		this.codeDetId = codeDetId;
	}

	@Column(name = "CODE_NUM", length = 2)
	public String getCodeNum() {
		return this.codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	@Column(name = "CODE_NAME", length = 64)
	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

}