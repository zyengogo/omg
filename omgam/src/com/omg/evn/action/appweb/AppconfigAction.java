package com.omg.evn.action.appweb;

import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.omg.evn.bean.CallMsg;
import com.omg.evn.entity.app.AppUser;
import com.omg.evn.service.appwebservice.AppService;
import com.omg.evn.util.sysutil.MD5;
import com.omg.xbase.struts.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Company: OMG Technology co.Ltd. (c) copyright
 * @Title: AppAction.java
 * @Package com.omg.evn.action.amomgweb
 * @discription 用户基本信息填写类
 * @author zyen
 * @date 2015-4-26 下午1:40:54 
 * @version V1.0 
 * @editor:
 * @editDate: 
 * @editInfo:
 */
@Results({
	@Result(name = "login" , location = "/WEB-INF/content/login.jsp"),
})

public class AppconfigAction extends  ActionSupport implements ServletRequestAware,ServletResponseAware  {
	
	/**
	 *描述   (@author: zyen)
	 */
	private static final long serialVersionUID = 1L;
	private Logger log= Logger.getLogger(getClass());
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	 
	private AppUser appUser;
	private CallMsg callmsg;
	
	@Resource
	private AppService appService;
	
	
	/**
	 * 
	 * @Description: 注册
	 * @author zyen
	 * @date 2015-4-26 下午1:35:09 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void con(){
		callmsg=new CallMsg();
		
		for(int i=18;i<20;i++){
			AppUser _appUser=new AppUser();
			_appUser.setTelephone("15928718"+i);
			_appUser.setPassWord(MD5.crypt("123456"+"$sccptss#"));//密码，加盐加密
			_appUser.setNickName("测试人员"+i);
			_appUser.setSex(""+1);
			_appUser.setAge(i-90);
			_appUser.setStatus(""+1);
			_appUser.setCreateDate(new Date());
			_appUser.setLastDate(new Date());
			_appUser.setGrade("grade"+i);
			_appUser.setSpeiis("Speiis"+i);
			
			try {
				appService.saveObject(_appUser);
				callmsg.setMessage("注册成功"+i);
				callmsg.setStatus("success");
			} catch (Exception e) {
				callmsg.setMessage("注册失败"+e.getMessage());
				callmsg.setStatus("fail");
				e.printStackTrace();
			}
			Struts2Utils.renderJson(callmsg);
		}
		
		
	}
	
	
	
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
}