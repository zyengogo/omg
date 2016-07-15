package com.omg.evn.action.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.omg.evn.entity.system.Sysuser;
import com.omg.xbase.struts.Struts2Utils;
import com.omg.xbase.struts.WebUtils;
import com.omg.xbase.xlog.Log;
import com.omg.xbase.xlog.LogFactory;
import com.opensymphony.xwork2.ActionSupport;

/**
 * *@Title: UserAction.java
 * @Description: UserAction用户管理
 * @author zyen
 * @date 2014-06-20 下午02:25:24
 * @version V1.0
 * @copyright: 小火炉技术团队
 */
public class PersonAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	private Log log = LogFactory.getLogger();
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	 * @Description:初始化个人权限树,权限树级：系统--子系统（子权限（采编，审核））
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	
	public String findMenu() {
		Sysuser user = (Sysuser) WebUtils.getSession().getAttribute("user");
		try{
			if(user!=null) {
				int userId = user.getId();
				
//				List<SiteEntity> siteList = siteUserService.findSiteListByUserId(userId);
//				JSONArray jsonArray = new JSONArray();
//				if(siteList!=null) {
//					for(SiteEntity site:siteList) {
//						JSONObject jo = new JSONObject();
//						jo.set("id", site.getId());
//						jo.set("name", site.getSiteName());
//						jsonArray.put(jo);
//					}
//					Struts2Utils.renderJson(jsonArray.toString());
//				}
			}else {
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	private int getSelValue(){
		 String sel= request.getParameter("a");
		 return Integer.valueOf(sel);
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

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
		response=arg0;
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
}