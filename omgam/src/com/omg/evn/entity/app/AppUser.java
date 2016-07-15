package com.omg.evn.entity.app;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_user", catalog = "omgam")
public class AppUser implements java.io.Serializable {

	// Fields

	private Integer userId;//主键ID
	private String telephone;//电话号码
	private String passWord;//密码-MD5加密加盐-盐值【$sccptss#】
	private String nickName;//昵称
	private String sex;//性别
	private int age;//年龄
	private String status;//  1——正常，-1——禁用，2——被举报
	private Date createDate;//申请时间
	private Date lastDate;//最后登录时间
	private String grade;//用户级别
	private String speiis;//

	// Constructors

	/** default constructor */
	public AppUser() {
	}

	/** full constructor */
	public AppUser(String telephone, String passWord, String nickName,
			String sex, Integer age, String status, Date createDate,
			Date lastDate, String grade, String speiis) {
		this.telephone = telephone;
		this.passWord = passWord;
		this.nickName = nickName;
		this.sex = sex;
		this.age = age;
		this.status = status;
		this.createDate = createDate;
		this.lastDate = lastDate;
		this.grade = grade;
		this.speiis = speiis;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "PASS_WORD")
	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name = "NICK_NAME")
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "SEX")
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "LAST_DATE")
	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@Column(name = "GRADE")
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "SPEIIS")
	public String getSpeiis() {
		return this.speiis;
	}

	public void setSpeiis(String speiis) {
		this.speiis = speiis;
	}

}