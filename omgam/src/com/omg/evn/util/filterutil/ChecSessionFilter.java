package com.omg.evn.util.filterutil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.omg.evn.action.SystemConstants;

public class ChecSessionFilter implements Filter{    
    protected FilterConfig filterConfig = null;    
    private String redirectURL = null;    
    private List notCheckURLList = new ArrayList();    
    private String sessionKey = null;    

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException    
	{    
	  HttpServletRequest request = (HttpServletRequest) servletRequest;    
	  HttpServletResponse response = (HttpServletResponse) servletResponse;    
	   HttpSession session = request.getSession(); 
	   RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
	   Object userObject = session.getAttribute(SystemConstants.SYSTEM_USERINFO);
	   String path = request.getRequestURI();
//	   System.out.println("-----path------"+path);
//	   if(userObject==null){
//		   dispatcher .forward(request, response);
//	   System.out.println("------------");
//	   }else{
//		   filterChain.doFilter(servletRequest, servletResponse);
//	   }
	   
	   String url = request.getServletPath();  
	   System.out.println("------------");
       System.out.println(url);  
       //这里判断目录，后缀名，当然也可以写在web.xml中，用url-pattern进行拦截映射  
       if (!request.getServletPath().equals("/login.action")) {  
           if (session.getAttribute(SystemConstants.SYSTEM_USERINFO) == null) {  
               session.invalidate();  
               response.setContentType("text/html;charset=utf-8");  
               PrintWriter out = response.getWriter();  
               out.println("<script language='javascript' type='text/javascript'>");  
               out.println("alert('由于你长时间没有操作,导致Session失效!请你重新登录!');window.location.href='" + request.getContextPath() + "/index.jsp'");  
               out.println("</script>");  
           } else {  
        	   filterChain.doFilter(request, response);  
           }  
       } else {  
    	   filterChain.doFilter(request, response);  
       }  
	   
	   
//	   ServletResponse servletresponse, FilterChain chain)
//		throws IOException, ServletException {
//	// 根据使用的协议类型转换成相应的请求类型
//	HttpServletRequest req = (HttpServletRequest) servletrequest;
//	HttpServletResponse res = (HttpServletResponse) servletresponse;
//	HttpSession session = req.getSession();
//	 Enprouser user = (Enprouser)session.getAttribute("userSessionEnpro");
//	String path = req.getRequestURI();
//	System.out.println("--------------请求页面-------------\n"+path);
//	 boolean flag = false;
//	 if(user!=null&&user.getUserState()==10){
//		 flag = true;
//	 }
	// }else{
//		 if(path.endsWith(EnpConstant.ISCODE)||EnpConstant.ISFIRLOGIN.equals(path)||EnpConstant.LOGIN_SUMIT.equals(path)){
//			  flag = true;
//		 }
	// }
//	 if(flag){
//		 chain.doFilter(req, res);
//	 }else{
//			System.out.println("Filter启动了作用....");
//			 res.sendRedirect(req.getContextPath()+ "/");
//			return;
//	 }
	   
	   
	   
	   
	  
//	  if(sessionKey == null)    
//	  {    
//	   filterChain.doFilter(request, response);    
//	   return;    
//	  }    
//	  if((!checkRequestURIIntNotFilterList(request)) && session.getAttribute(sessionKey) == null)    
//	  {    
//	   response.sendRedirect(request.getContextPath() + redirectURL);    
//	   return;    
//	  }    
//	  filterChain.doFilter(servletRequest, servletResponse);    
	}    
	
	
	public void init(FilterConfig filterConfig) throws ServletException    
	{    
//	  this.filterConfig = filterConfig;    
//	  redirectURL = filterConfig.getInitParameter("redirectURL");    
//	  sessionKey = filterConfig.getInitParameter("checkSessionKey");    
//	 
//	  String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");    
//	 
//	  if(notCheckURLListStr != null)    
//	  {    
//	   StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");    
//	   notCheckURLList.clear();    
//	   while(st.hasMoreTokens())    
//	   {    
//	    notCheckURLList.add(st.nextToken());    
//	   }    
//	  }    
	}
	
	
	public void destroy()    
	{    
	  notCheckURLList.clear();    
	}    
	 
	private boolean checkRequestURIIntNotFilterList(HttpServletRequest request)    
	{    
	  String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());    
	  return notCheckURLList.contains(uri);    
	}    
	 
    
}    