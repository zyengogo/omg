package com.omg.evn.action.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 
 * @author ZhaoYongEn.zhaoye@strongit.com.cn 2016-4-5 上午9:04:22
 *
 */
public class TestAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	 * 测试接收
	 * 
	 * @author ZhaoYongEn.zhaoye@strongit.com.cn
	 * 2016-4-5 上午10:10:03 
	 * @return
	 */
	public String addForm(){
		
		System.out.println("adddd");
		
		return null;
	}
	
	
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

}
