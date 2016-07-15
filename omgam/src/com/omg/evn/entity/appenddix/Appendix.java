package com.omg.evn.entity.appenddix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附件实体
 * MwZykAppendix entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mw_zyk_appendix", schema = "dbo", catalog = "minwei")
public class Appendix implements java.io.Serializable {

	// Fields

	private Integer id;			// 附件ID
	private String  name;		// 附件名称
	private String  type;		// 附件类型 0文件 1图片 2视频 8文章中的图片
	private String  videoimageurl;	// 相关视频图片 (视频相关图片,才有):
	private Integer classid;	// 所属6大类 分类ID
	private Integer formid;		// 当前数据所属分类
	private String  url;		// 附件URL
	private Integer orderid;	// 排序
	private Integer zykid;	    // 关联资源库数据id
	private String  zyktype;	// 资源库类型 "zyk":普通资源库的附件，"teacher":师资信息库 "cadres":干部人才资源库
	
	// Constructors

	/** default constructor */
	public Appendix() {
	}

	/** full constructor */
	
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "CLASSID")
	public Integer getClassid() {
		return this.classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	@Column(name = "FORMID")
	public Integer getFormid() {
		return this.formid;
	}

	public void setFormid(Integer formid) {
		this.formid = formid;
	}

	@Column(name = "URL")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "ORDERID")
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@Column(name = "ZYKID")
	public Integer getZykid() {
		return zykid;
	}

	public void setZykid(Integer zykid) {
		this.zykid = zykid;
	}
	
	@Column(name = "ZYKTYPE")
	public String getZyktype() {
		return zyktype;
	}

	public void setZyktype(String zyktype) {
		this.zyktype = zyktype;
	}

	@Column(name = "VIDEOIMAGEURL")
	public String getVideoimageurl() {
		return videoimageurl;
	}

	public void setVideoimageurl(String videoimageurl) {
		this.videoimageurl = videoimageurl;
	}
	
}