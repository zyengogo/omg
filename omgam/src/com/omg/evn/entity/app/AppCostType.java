package com.omg.evn.entity.app;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppCostType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_cost_type", catalog = "omgam")
public class AppCostType implements java.io.Serializable {

	// Fields

	private Integer costTypeId;
	private String costTypeName;
	private String costTypeCont;
	private Timestamp createDate;

	// Constructors

	/** default constructor */
	public AppCostType() {
	}

	/** full constructor */
	public AppCostType(String costTypeName, String costTypeCont,
			Timestamp createDate) {
		this.costTypeName = costTypeName;
		this.costTypeCont = costTypeCont;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "COST_TYPE_ID", unique = true, nullable = false)
	public Integer getCostTypeId() {
		return this.costTypeId;
	}

	public void setCostTypeId(Integer costTypeId) {
		this.costTypeId = costTypeId;
	}

	@Column(name = "COST_TYPE_NAME", length = 128)
	public String getCostTypeName() {
		return this.costTypeName;
	}

	public void setCostTypeName(String costTypeName) {
		this.costTypeName = costTypeName;
	}

	@Column(name = "COST_TYPE_CONT", length = 256)
	public String getCostTypeCont() {
		return this.costTypeCont;
	}

	public void setCostTypeCont(String costTypeCont) {
		this.costTypeCont = costTypeCont;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}