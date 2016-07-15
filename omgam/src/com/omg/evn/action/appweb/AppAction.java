package com.omg.evn.action.appweb;

import java.text.SimpleDateFormat;
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

public class AppAction extends  ActionSupport implements ServletRequestAware,ServletResponseAware  {
	
	/**
	 *描述   (@author: zyen)
	 */
	private static final long serialVersionUID = 1L;
	private Logger log= Logger.getLogger(getClass());
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private int pageNumber;//当前页码
	private int pageSize;//每页记录数
	
	private String omgpwdset;//重置密码时，是使用系统默认密码，还是随机密码
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
	public void register(){
		log.info("register----start----");
		
		callmsg=new CallMsg();
		
		if(appUser != null){
			
			int sum = appService.findtelephoneidCount(0,appUser.getTelephone());//检验是否存在
			System.out.println(sum);
			if(sum < 1){
				appUser.setTelephone(appUser.getTelephone());
				appUser.setPassWord(MD5.crypt(appUser.getPassWord()+"$sccptss#"));//密码，加盐加密
//				appUser.setNickName(appUser.getNickName());
//				appUser.setSex(appUser.getSex());
//				appUser.setAge(appUser.getAge());
				appUser.setStatus("1");
				appUser.setCreateDate(new Date());
				appUser.setLastDate(new Date());
//				appUser.setGrade(appUser.getGrade());
//				appUser.setSpeiis(appUser.getSpeiis());
				try {
					appService.saveObject(appUser);
					callmsg.setMessage("注册成功");
					callmsg.setStatus("success");
				} catch (Exception e) {
					callmsg.setMessage("注册失败"+e.getMessage());
					callmsg.setStatus("fail");
					e.printStackTrace();
				}
			}else{
				callmsg.setMessage("此号码:"+appUser.getTelephone()+"已存在");
				callmsg.setStatus("fail");
			}
		}else{
			callmsg.setMessage("用户信息为空，注册失败.");
			callmsg.setStatus("fail");
		}
		
		
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String TimeString = time.format(new java.util.Date());
		log.info("用户注册####"+callmsg.getMessage()+"####"+TimeString);
		
		System.out.println("[status]"+callmsg.getStatus()+"[message]"+callmsg.getMessage());
		System.out.println(TimeString);
		System.out.println();
		Struts2Utils.renderJson(callmsg);
		
	}
	
	
	
	/**
	 * 
	 * @Description: 检查是否有重复的手机号
	 * @author zyen
	 * @date 2015-5-2 上午10:31:09 
	 * @param @return     
	 * @return String    
	 * @throws
	 */
	public String checkTelephone(){
		log.info("checkTelephone----start----");
		callmsg=new CallMsg();
		try {
			if(appUser != null){
				String telephoneid= appUser.getTelephone();
				int sum = appService.findtelephoneidCount(0,telephoneid);//检验是否存在
				if(sum<1){
					callmsg.setMessage("通过验证");
					callmsg.setStatus("success");
				}else{
					callmsg.setMessage("用户名已存在！");
					callmsg.setStatus("fail");
				}
			}
		} catch (Exception e) {
			callmsg.setMessage("错误:"+e.getMessage());
			callmsg.setStatus("fail");
		}
		Struts2Utils.renderJson(callmsg);
		return null;
	}
	
	/**
	 * 
	 * @Description: 登陆
	 * @author zyen
	 * @date 2015-4-26 上午10:34:10 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void login(){
		log.info("login----start----");
		callmsg=new CallMsg();
		if(appUser != null){
			String telephoneid=appUser.getTelephone();
			String pwd=appUser.getPassWord()+"$sccptss#";
			
			try {
				AppUser _appUser=appService.loginapp(telephoneid, pwd);
				
				/*登陆成功后，更新登陆时间*/
				_appUser.setLastDate(new Date());
				appService.upObject(_appUser);
				
				callmsg.setMessage("登陆成功");
				callmsg.setStatus("success");
			} catch (Exception e) {
				//e.printStackTrace();
				callmsg.setMessage("登陆失败"+e.getMessage());
				callmsg.setStatus("fail");
			}
		}
		log.info("用户登陆");
		Struts2Utils.renderJson(callmsg);
	}
	
	/**
	 * 
	 * @Description: 重置密码
	 * @author zyen
	 * @date 2015-5-2 上午11:22:42 
	 * @param @return     
	 * @return String    
	 * @throws
	 */
	public String resetUserPwd(){
		log.info("resetUserPwd----start----");
		callmsg=new CallMsg();
		if(appUser != null){
			String telephoneid=appUser.getTelephone();
			omgpwdset="1";//默认使用P123456
			if(omgpwdset!=null && omgpwdset.equals("")==false){
				omgpwdset="0";
			}
			try {
				String _pwd = appService.resetPassUserapp(telephoneid,omgpwdset);
				if (_pwd!=null) {
					callmsg.setMessage("重置成功，密码为:"+_pwd);
					callmsg.setStatus("success");
					callmsg.setOpt(_pwd);
				}else {
					callmsg.setMessage("重置密码失败");
					callmsg.setStatus("fail");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("#UserAction>>resetPassUser():"+e);
			}
		}	
		Struts2Utils.renderJson(callmsg);
		return null;
		
	}

	/**
	 * 
	 * @Description: 查询列表
	 * @author zyen
	 * @date 2015-4-26 下午1:35:28 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void findpagelist(){
		log.info("findpagelist----start----");
		String str ="";
		try {
			if((Object)pageNumber == null){
				pageNumber=1;
			}
			if((Object)pageSize == null){
				pageSize=20;
			}
			
			str = appService.findpagelist(pageNumber,pageSize);
		} catch (Exception e) {
			str = "error";
			log.error("#UserAction>>findUserPageList():"+e);
			e.printStackTrace();
		}
		Struts2Utils.renderJson(str);
	}
	
	
	
	/**
	 * 
	 * @Description: 基本信息填写(图片头像)
	 * @author zyen
	 * @date 2015-4-26 下午1:36:42 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void basicmsg(){
		log.info("basicmsg----start----");
		
		callmsg=new CallMsg();
		Struts2Utils.renderJson(callmsg);
		log.info("基本信息填写");
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
	public String getOmgpwdset() {
		return omgpwdset;
	}
	public void setOmgpwdset(String omgpwdset) {
		this.omgpwdset = omgpwdset;
	}
	
}