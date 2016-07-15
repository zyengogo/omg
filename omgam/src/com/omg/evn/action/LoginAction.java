package com.omg.evn.action;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.omg.evn.entity.system.Sysuser;
import com.omg.evn.service.system.UserService;
import com.omg.xbase.struts.WebUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Company: OMG Technology co.Ltd. (c) copyright
 * @Title: LoginAction.java
 * @Package com.omg.evn.action
 * @discription LoginAction系统用户入口验证
 * @author zyen
 * @date 2015-5-1 下午6:38:38 
 * @version V1.0 
 * @editor:
 * @editDate: 
 * @editInfo:
 */
@Results({
	@Result(name = "usermanager" , location="/WEB-INF/content/system/user/index.jsp"),
	@Result(name = "login" , location = "/WEB-INF/content/login.jsp"),
	@Result(name = "mylogin" , location = "/WEB-INF/content/mylogin.jsp"),
	@Result(name = "errorurls" , location="/WEB-INF/content/error.jsp")

})

public class LoginAction extends  ActionSupport implements ServletRequestAware,ServletResponseAware  {
	private Logger log= Logger.getLogger(getClass());
//	private HttpServletResponse response = Struts2Utils.getResponse();  
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private String type;//页面跳转类型
	private Sysuser user;//用户
	private int id;
	private String param;
	private String realname;
	private int pageNumber;//当前页码
	private int pageSize;//每页记录数
	
	@Resource
	private UserService userService;
	
	/**
	 * @Description: 用户登录
	 * @author zyen
	 * @date 2015-5-1 下午6:38:54 
	 * @param @throws Exception     
	 * @return void    
	 * @throws
	 */
	public void login() throws Exception{
		
		try {
			String flagl=request.getParameter("flagl");
			int fint=Integer.valueOf(flagl);
			WebUtils.getSession().setAttribute("flagl", flagl);
			String username = request.getParameter("username");
			String password = request.getParameter("password")+"$sccptss#";//密码加盐
			Sysuser upo = userService.login(username, password);
			
			/************匹配验证码***************/
			if(fint==2){
				if(!isValidateCode()){
					return;
				}
			}
			if(upo.getIsManager().equals("1")) {
				WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_ISADMIN, SystemConstants.SYSTEM_OK);
				WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_ISMANAGER, SystemConstants.SYSTEM_OK);
			}else{
				WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_ISADMIN, SystemConstants.SYSTEM_NO);
				WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_ISMANAGER, SystemConstants.SYSTEM_NO);
			}
			/***********用户密码验证**************/
			if(!isUserInfor(upo)){
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("#UserAction>>login():"+e);
		}
		return;
	}
	
	
	/**
	 * 
	 * @Description: 匹配验证码
	 * @author zyen
	 * @date 2015-5-1 下午6:40:00 
	 * @param @return     
	 * @return boolean    
	 * @throws
	 */
	private boolean isValidateCode(){
		String validateCode = request.getParameter("validateCode");
		String validateCodeServer = (String) request.getSession().getAttribute("randCode");
		if (validateCode!=null && validateCode.equals(validateCodeServer)) {
			 return true;
	    }else{
	    	try {
	    		response.getWriter().print(SystemConstants.SYSTEM_LOIGN_ISCODE);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	return false;
	    }
		
	}

	/**
	 * @Description: 用户密码验证
	 * @author zyen
	 * @date 2015-5-1 下午6:40:17 
	 * @param @param upo
	 * @param @return     
	 * @return boolean    
	 * @throws
	 */
	private boolean isUserInfor(Sysuser upo){
		
		boolean boo=false;
		try {
			if (upo!= null) {
				WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_USERINFO, upo);
				WebUtils.getSession().setAttribute( SystemConstants.SYSTEM_NO_HREF, SystemConstants.SYSTEM_PRIVI_NOURLS);
				if(upo.getName().equals(SystemConstants.SYSTEM_ADMIN)){
					WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_ISADMIN, SystemConstants.SYSTEM_OK);
				}
				response.getWriter().print(SystemConstants.SYSTEM_LOIGN_SUCCESS);
				boo=true;
			}else {
				WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_USERINFO, null);
				response.getWriter().print(SystemConstants.SYSTEM_LOIGN_ERROR);
				boo= false;
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boo;
	
	}
	
	public String  removeLoginInfo() {
		    String flagl=(String)request.getSession().getAttribute("flagl");
		    WebUtils.getSession().setAttribute("common", null);
			WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_AREA, null);
			WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_USERINFO, null);
			WebUtils.getSession().setAttribute( SystemConstants.SYSTEM_NO_HREF, null);
			WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_ISADMIN,null);
			WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_USERINFO, null);
			WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_USERINFO, null);
			WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_ISMANAGER, null);
			WebUtils.getSession().invalidate();
			if(flagl==null||"".equals(flagl.trim())){
				return "errorurls";
			}
			int a=Integer.valueOf(flagl);
			if(a==1){
				return "login";
			}
			if(a==2){
				return "mylogin";
			}
			return "login";
	}
	
	
	
	
	/**
	 * 页面跳转
	 * @Description: TODO()
	 * @param: type 跳转类型
	 * @return: 返回
	 * @throws: 
	 */
	public String jump(){
		if("manager".equals(type)){
			return "usermanager";//管理首页
		}else if ("loginout".equals(type)) {
			request.getSession().setAttribute("user", null);
		}
		return "login";
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
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Sysuser getUser() {
		return user;
	}
	public void setUser(Sysuser user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}