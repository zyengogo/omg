package com.omg.evn.entity.system;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * @Title: Sysuser.java
 * @Description: Sysuser 用户实体表实体映射
 * @author zyen
 * @date 2014-05-16 上午10:14:24
 * @version V1.0
 * @copyright: 小火炉技术团队
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sysuser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;	//用户名
	private String password;//密码
	private String realname;//真实姓名
	private Integer age;		//年龄
	private Integer sex;		//性别
	private String phone;	//联系电话
	private String depart;	//所属部门	
	private String isManager;

//	private Set<Sysgroup> sysgroups = new LinkedHashSet<Sysgroup>(); //角色具有的权限

	/** default constructor */
	public Sysuser() {
	}

	/** full constructor */
	public Sysuser(int userid, String username, String userpassword,
			String userphone, String realname) {
		super();
		this.id = userid;
		this.name = username;
		this.password = userpassword;
		this.phone = userphone;
		this.realname = realname;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
	

}