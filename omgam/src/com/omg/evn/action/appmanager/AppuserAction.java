package com.omg.evn.action.appmanager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.omg.evn.action.OmgamConstants;
import com.omg.evn.bean.CallMsg;
import com.omg.evn.entity.app.AppUser;
import com.omg.evn.service.appmanager.AppuserService;
import com.omg.xbase.struts.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @Company: OMG Technology co.Ltd. (c) copyright
 * @Title: AppuserAction.java
 * @Package com.omg.evn.action.appmanager
 * @discription 注册用户类
 * @author zyen
 * @date 2015-5-2 上午9:37:11 
 * @version V1.0 
 * @editor:
 * @editDate: 
 * @editInfo:
 */
@Results({
	@Result(name = "login" , location = "/WEB-INF/content/login.jsp"),
	@Result(name = "addoredit" , location = "/WEB-INF/content/appmanager/addoredit.jsp")
})

public class AppuserAction extends  ActionSupport implements ServletRequestAware,ServletResponseAware  {
	
	/**
	 *描述   (@author: zyen)
	 */
	private static final long serialVersionUID = 1L;
	private Logger log= Logger.getLogger(getClass());
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private int pageNumber;//当前页码
	private int pageSize;//每页记录数
	private CallMsg callmsg;//返回状态
	
	private AppUser appUser;
	
	@Resource
	private AppuserService appuserService;
	
	
	/**
	 * 
	 * @Description: 查询列表分页
	 * @author zyen
	 * @date 2015-5-2 上午9:31:07 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void getPageList(){
		String str ="";
		try {
			if((Object)pageNumber == null){
				pageNumber=1;
			}
			if((Object)pageSize == null){
				pageNumber=20;
			}
			
			str = appuserService.findpagelist(pageNumber,pageSize,appUser);
		} catch (Exception e) {
			str = "error";
			e.printStackTrace();
		}
		Struts2Utils.renderJson(str);
	}
	
	/**
	 * 
	 * @Description: 新增
	 * @author zyen
	 * @date 2015-5-2 上午9:35:05 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void addobj(){
		
		
	}
	
	/**
	 * 
	 * @Description: 删除
	 * @author zyen
	 * @date 2015-5-2 上午9:34:33 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void delobj(){
		callmsg=new CallMsg();
		
		String str = "";
		if (appUser != null) {
			try {
				int id = appUser.getUserId();
				appuserService.delobjById(id);
				str = "ok";
			} catch (Exception e) {
				e.printStackTrace();
				str = "error";
			}
		}
		Struts2Utils.renderJson(str);
	}
	
	
	/**
	 * 
	 * @Description: 设定账户状态
	 * @author zyen
	 * @date 2015-5-10 上午10:33:56 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void setstatus(){
		callmsg=new CallMsg();
		
		if (appUser != null) {
			try {
				int id = appUser.getUserId();
				String status=appUser.getStatus();
				
				AppUser _appUser=new AppUser();
				_appUser=appuserService.findById(id);
				_appUser.setStatus(status);//定义需要改变的状态
				
			
				
				appuserService.upObject(_appUser);
				
				callmsg.setMessage("禁用成功.");
				callmsg.setStatus(OmgamConstants.STATUS_SUCCESS);
			} catch (Exception e) {
				log.info("用户禁用异常::::"+e.getMessage());
				callmsg.setMessage("处理失败.");
				callmsg.setStatus(OmgamConstants.STATUS_FAIL);
			}
		}
		Struts2Utils.renderJson(callmsg);
	}
	
	
	/**
	 * 
	 * @Description: 批量删除
	 * @author zyen
	 * @date 2015-5-2 下午3:47:37 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void deleteobj(){
		String te= this.request.getParameter("te");
		String str[]=te.split("_");
		String _str="";
		try {
			for(int i=0;i<str.length;i++){
				int id = Integer.valueOf(str[i]+"");
				appuserService.delobjById(id);
			}
			_str = "ok";
		} catch (Exception e) {
			_str = "error";
			log.error(AppuserAction.class+">>deleteobj:\n"+e.getMessage());
		}
		Struts2Utils.renderJson(_str);
	}
	
	/**
	 * 
	 * @Description: 编辑
	 * @author zyen
	 * @date 2015-5-2 上午9:34:14 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void editobj(){
		
		
	}
	
	
	/**
	 * 
	 * @Description: 根据主键查找
	 * @author zyen
	 * @date 2015-5-2 上午9:33:32 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public String findbykid(){
		
		System.out.println(appUser.getUserId()+"跳转");
		
		return "addoredit";
	}
	
	/**
	 * 
	 * @Description: 页面跳转(需特殊处理的可放这里)
	 * @author zyen
	 * @date 2015-5-2 上午9:35:56 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void jumpurl(){
		
		
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
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	public CallMsg getCallmsg() {
		return callmsg;
	}
	public void setCallmsg(CallMsg callmsg) {
		this.callmsg = callmsg;
	}
	
}