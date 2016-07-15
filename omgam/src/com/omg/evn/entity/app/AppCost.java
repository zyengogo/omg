package com.omg.evn.entity.app;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppCost entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_cost", catalog = "omgam")
public class AppCost implements java.io.Serializable {

	// Fields

	private Integer costId;
	private Integer usetId;
	private Integer costType;
	private Timestamp createDate;

	// Constructors

	/** default constructor */
	public AppCost() {
	}

	/** full constructor */
	public AppCost(Integer usetId, Integer costType, Timestamp createDate) {
		this.usetId = usetId;
		this.costType = costType;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "COST_ID", unique = true, nullable = false)
	public Integer getCostId() {
		return this.costId;
	}

	public void setCostId(Integer costId) {
		this.costId = costId;
	}

	@Column(name = "USET_ID")
	public Integer getUsetId() {
		return this.usetId;
	}

	public void setUsetId(Integer usetId) {
		this.usetId = usetId;
	}

	@Column(name = "COST_TYPE")
	public Integer getCostType() {
		return this.costType;
	}

	public void setCostType(Integer costType) {
		this.costType = costType;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}