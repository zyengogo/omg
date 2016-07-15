package com.omg.evn.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.omg.xbase.xlog.Log;
import com.omg.xbase.xlog.LogFactory;
import com.opensymphony.xwork2.ActionSupport;


public class SysAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	public Log log = LogFactory.getLogger();
	public HttpServletRequest request;
	public  HttpServletResponse response;
	
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
		this.response = arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
}
