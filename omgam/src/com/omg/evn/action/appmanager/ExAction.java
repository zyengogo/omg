package com.omg.evn.action.appmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * action示例类
 */
@Results({
	@Result(name = "login" , location = "/WEB-INF/content/login.jsp"),
})

public class ExAction extends  ActionSupport implements ServletRequestAware,ServletResponseAware  {
	
	/**
	 *描述   (@author: zyen)
	 */
	private static final long serialVersionUID = 1L;
	private Logger log= Logger.getLogger(getClass());
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	private int pageNumber;//当前页码
	private int pageSize;//每页记录数
	
	
	
	
	
	
	
	
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
	 * @Description: 查询列表分页
	 * @author zyen
	 * @date 2015-5-2 上午9:31:07 
	 * @param      
	 * @return void    
	 * @throws
	 */
	public void getPageList(){
		
		
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
	public void findbykid(){
		
		
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
	
}