package com.omg.evn.entity.app;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppReport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_report", catalog = "omgam")
public class AppReport implements java.io.Serializable {

	// Fields

	private Integer reportId;
	private Integer reportUserId;
	private Integer checkReportId;
	private Integer reportType;
	private Timestamp reportDate;

	// Constructors

	/** default constructor */
	public AppReport() {
	}

	/** full constructor */
	public AppReport(Integer reportUserId, Integer checkReportId,
			Integer reportType, Timestamp reportDate) {
		this.reportUserId = reportUserId;
		this.checkReportId = checkReportId;
		this.reportType = reportType;
		this.reportDate = reportDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "REPORT_ID", unique = true, nullable = false)
	public Integer getReportId() {
		return this.reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	@Column(name = "REPORT_USER_ID")
	public Integer getReportUserId() {
		return this.reportUserId;
	}

	public void setReportUserId(Integer reportUserId) {
		this.reportUserId = reportUserId;
	}

	@Column(name = "CHECK_REPORT_ID")
	public Integer getCheckReportId() {
		return this.checkReportId;
	}

	public void setCheckReportId(Integer checkReportId) {
		this.checkReportId = checkReportId;
	}

	@Column(name = "REPORT_TYPE")
	public Integer getReportType() {
		return this.reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	@Column(name = "REPORT_DATE", length = 19)
	public Timestamp getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}

}