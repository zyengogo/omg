package com.omg.evn.action.search;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import com.omg.evn.entity.search.Inforadar;
import com.omg.xbase.struts.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport implements ServletRequestAware , ServletResponseAware{
	private int page;//页码数
	private int rows;//每页行数	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Inforadar inforadar;//搜索对象
	private String searchScope;
	private String timeScope;
	private String classScope;
	private String searchType;
	private String id;
	private String siteId;
	private String isJiucuo;
	/**
	 * 下载压缩文件
	 * @return
	 */
	public String downloadZip(){
				
		String url = "";
		
		Struts2Utils.renderJson(url, null, "yyyy-MM-dd HH:MM",true);				 		
		return null;
	}		
	/**
	 * 获取搜索排行
	 * @return
	 *//*
	public String getRanklist(){
				
		List list = searchService.getRanklist(); 
		Struts2Utils.renderJson(list, null, "yyyy-MM-dd HH:MM",true);				 		
		return null;
	}*/
	/**
	 * 记录搜索排行
	 * @return
	 */
	/*public String recordRanklist(){
		
		 if(inforadar!=null){
			 String	searchKeys = inforadar.getIR_URLTITLE();
			 if(searchKeys!=null&&!"".equals(searchKeys)){
				 searchService.recordRanklist(searchKeys); 
			 }
			 }
		
		return null;
	}*/
	
	
//-------------------------------------------------------------------------	
	
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getClassScope() {
		return classScope;
	}
	public void setClassScope(String classScope) {
		this.classScope = classScope;
	}
	public String getSearchScope() {
		return searchScope;
	}
	public void setSearchScope(String searchScope) {
		this.searchScope = searchScope;
	}

	public String getTimeScope() {
		return timeScope;
	}

	public void setTimeScope(String timeScope) {
		this.timeScope = timeScope;
	}
	public Inforadar getInforadar() {
		return inforadar;
	}
		public void setInforadar(Inforadar inforadar) {
			this.inforadar = inforadar;
		}
	 public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	public String getIsJiucuo() {
		return isJiucuo;
	}
	public void setIsJiucuo(String idJiucuo) {
		this.isJiucuo = idJiucuo;
	}
	
}
