package com.omg.evn.entity.app;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppLetter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_letter", catalog = "omgam")
public class AppLetter implements java.io.Serializable {

	// Fields

	private Integer letterId;
	private Integer usetId;
	private Integer letterUserId;
	private String content;
	private Timestamp createDate;

	// Constructors

	/** default constructor */
	public AppLetter() {
	}

	/** full constructor */
	public AppLetter(Integer usetId, Integer letterUserId, String content,
			Timestamp createDate) {
		this.usetId = usetId;
		this.letterUserId = letterUserId;
		this.content = content;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LETTER_ID", unique = true, nullable = false)
	public Integer getLetterId() {
		return this.letterId;
	}

	public void setLetterId(Integer letterId) {
		this.letterId = letterId;
	}

	@Column(name = "USET_ID")
	public Integer getUsetId() {
		return this.usetId;
	}

	public void setUsetId(Integer usetId) {
		this.usetId = usetId;
	}

	@Column(name = "LETTER_USER_ID")
	public Integer getLetterUserId() {
		return this.letterUserId;
	}

	public void setLetterUserId(Integer letterUserId) {
		this.letterUserId = letterUserId;
	}

	@Column(name = "CONTENT", length = 256)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}