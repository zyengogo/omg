package com.omg.evn.entity.app;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "app_user_info", catalog = "omgam")
public class AppUserInfo implements java.io.Serializable {

	// Fields

	private Integer userInfoId;
	private String userName;
	private String idcard;
	private String email;
	private String domicile;
	private String recruitment;
	private String property;
	private String interests;
	private String monologue;
	private Timestamp birthday;
	private Integer starId;
	private Integer rise;
	private Integer weight;
	private Integer bloodId;
	private Integer educationId;
	private Integer occupationId;
	private Integer incomeId;
	private Integer charmId;
	private Integer marriageId;
	private Integer housingId;
	private Integer distanceId;
	private Integer oppositeSexId;
	private Integer intimacyId;
	private Integer parentsId;
	private Integer childId;
	private String taLocation;
	private Integer taAge;
	private Integer taRise;
	private Integer taEducationId;
	private Integer taIncomeId;
	private String status;
	private Long gold;

	// Constructors

	/** default constructor */
	public AppUserInfo() {
	}

	/** full constructor */
	public AppUserInfo(String userName, String idcard, String email,
			String domicile, String recruitment, String property,
			String interests, String monologue, Timestamp birthday,
			Integer starId, Integer rise, Integer weight, Integer bloodId,
			Integer educationId, Integer occupationId, Integer incomeId,
			Integer charmId, Integer marriageId, Integer housingId,
			Integer distanceId, Integer oppositeSexId, Integer intimacyId,
			Integer parentsId, Integer childId, String taLocation,
			Integer taAge, Integer taRise, Integer taEducationId,
			Integer taIncomeId, String status, Long gold) {
		this.userName = userName;
		this.idcard = idcard;
		this.email = email;
		this.domicile = domicile;
		this.recruitment = recruitment;
		this.property = property;
		this.interests = interests;
		this.monologue = monologue;
		this.birthday = birthday;
		this.starId = starId;
		this.rise = rise;
		this.weight = weight;
		this.bloodId = bloodId;
		this.educationId = educationId;
		this.occupationId = occupationId;
		this.incomeId = incomeId;
		this.charmId = charmId;
		this.marriageId = marriageId;
		this.housingId = housingId;
		this.distanceId = distanceId;
		this.oppositeSexId = oppositeSexId;
		this.intimacyId = intimacyId;
		this.parentsId = parentsId;
		this.childId = childId;
		this.taLocation = taLocation;
		this.taAge = taAge;
		this.taRise = taRise;
		this.taEducationId = taEducationId;
		this.taIncomeId = taIncomeId;
		this.status = status;
		this.gold = gold;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_INFO_ID", unique = true, nullable = false)
	public Integer getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	@Column(name = "USER_NAME", length = 16)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "IDCARD", length = 18)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "EMAIL", length = 34)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "DOMICILE", length = 128)
	public String getDomicile() {
		return this.domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	@Column(name = "RECRUITMENT", length = 32)
	public String getRecruitment() {
		return this.recruitment;
	}

	public void setRecruitment(String recruitment) {
		this.recruitment = recruitment;
	}

	@Column(name = "PROPERTY", length = 128)
	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Column(name = "INTERESTS", length = 128)
	public String getInterests() {
		return this.interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	@Column(name = "MONOLOGUE", length = 200)
	public String getMonologue() {
		return this.monologue;
	}

	public void setMonologue(String monologue) {
		this.monologue = monologue;
	}

	@Column(name = "BIRTHDAY", length = 19)
	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	@Column(name = "STAR_ID")
	public Integer getStarId() {
		return this.starId;
	}

	public void setStarId(Integer starId) {
		this.starId = starId;
	}

	@Column(name = "RISE")
	public Integer getRise() {
		return this.rise;
	}

	public void setRise(Integer rise) {
		this.rise = rise;
	}

	@Column(name = "WEIGHT")
	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Column(name = "BLOOD_ID")
	public Integer getBloodId() {
		return this.bloodId;
	}

	public void setBloodId(Integer bloodId) {
		this.bloodId = bloodId;
	}

	@Column(name = "EDUCATION_ID")
	public Integer getEducationId() {
		return this.educationId;
	}

	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}

	@Column(name = "OCCUPATION_ID")
	public Integer getOccupationId() {
		return this.occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	@Column(name = "INCOME_ID")
	public Integer getIncomeId() {
		return this.incomeId;
	}

	public void setIncomeId(Integer incomeId) {
		this.incomeId = incomeId;
	}

	@Column(name = "CHARM_ID")
	public Integer getCharmId() {
		return this.charmId;
	}

	public void setCharmId(Integer charmId) {
		this.charmId = charmId;
	}

	@Column(name = "MARRIAGE_ID")
	public Integer getMarriageId() {
		return this.marriageId;
	}

	public void setMarriageId(Integer marriageId) {
		this.marriageId = marriageId;
	}

	@Column(name = "HOUSING_ID")
	public Integer getHousingId() {
		return this.housingId;
	}

	public void setHousingId(Integer housingId) {
		this.housingId = housingId;
	}

	@Column(name = "DISTANCE_ID")
	public Integer getDistanceId() {
		return this.distanceId;
	}

	public void setDistanceId(Integer distanceId) {
		this.distanceId = distanceId;
	}

	@Column(name = "OPPOSITE_SEX_ID")
	public Integer getOppositeSexId() {
		return this.oppositeSexId;
	}

	public void setOppositeSexId(Integer oppositeSexId) {
		this.oppositeSexId = oppositeSexId;
	}

	@Column(name = "INTIMACY_ID")
	public Integer getIntimacyId() {
		return this.intimacyId;
	}

	public void setIntimacyId(Integer intimacyId) {
		this.intimacyId = intimacyId;
	}

	@Column(name = "PARENTS_ID")
	public Integer getParentsId() {
		return this.parentsId;
	}

	public void setParentsId(Integer parentsId) {
		this.parentsId = parentsId;
	}

	@Column(name = "CHILD_ID")
	public Integer getChildId() {
		return this.childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	@Column(name = "TA_LOCATION", length = 32)
	public String getTaLocation() {
		return this.taLocation;
	}

	public void setTaLocation(String taLocation) {
		this.taLocation = taLocation;
	}

	@Column(name = "TA_AGE")
	public Integer getTaAge() {
		return this.taAge;
	}

	public void setTaAge(Integer taAge) {
		this.taAge = taAge;
	}

	@Column(name = "TA_RISE")
	public Integer getTaRise() {
		return this.taRise;
	}

	public void setTaRise(Integer taRise) {
		this.taRise = taRise;
	}

	@Column(name = "TA_EDUCATION_ID")
	public Integer getTaEducationId() {
		return this.taEducationId;
	}

	public void setTaEducationId(Integer taEducationId) {
		this.taEducationId = taEducationId;
	}

	@Column(name = "TA_INCOME_ID")
	public Integer getTaIncomeId() {
		return this.taIncomeId;
	}

	public void setTaIncomeId(Integer taIncomeId) {
		this.taIncomeId = taIncomeId;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "GOLD", precision = 18, scale = 0)
	public Long getGold() {
		return this.gold;
	}

	public void setGold(Long gold) {
		this.gold = gold;
	}

}