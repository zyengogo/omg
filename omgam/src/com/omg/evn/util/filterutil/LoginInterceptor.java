package com.omg.evn.util.filterutil;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.omg.evn.action.SystemConstants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;
  
public class LoginInterceptor extends AbstractInterceptor{  
  
    private static final long serialVersionUID = 1L;  
    private String excludeActionName;//剔除的拦截方法  
    private String sessionName;//用户名在session中存放的key值  
	private int page;					//页码数
	private int rows;					//每页行数
      


	@Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
        String actionName = invocation.getProxy().getActionName();//获取当前访问的action名字       
//        Set<String> set = TextParseUtil.commaDelimitedStringToSet(excludeActionName);  
        boolean flag = false;  
        boolean Debug=false;
        if(Debug){
        	System.out.println("---------actionName:-----"+actionName);
        }
//        if(set.contains(actionName)){  
//        	flag = true;
//        }
    /*    if(set.contains(actionName)){  
        	flag = true;
        }else{  
            Map<String, Object> sessions = invocation.getInvocationContext().getSession();  
            if(sessions.get(SystemConstants.SYSTEM_USERINFO)!= null){ 
            	flag = true;
            }
        } */
        Map<String, Object> sessions = invocation.getInvocationContext().getSession();  
        if(sessions.get(SystemConstants.SYSTEM_USERINFO)!= null){ 
        	flag = true;
        }
        return flag ? invocation.invoke() : "error";
    }  
      
      
    //get set  
    public String getExcludeActionName() {  
        return excludeActionName;  
    }  
    public void setExcludeActionName(String excludeActionName) {  
        this.excludeActionName = excludeActionName;  
    }  
    public String getSessionName() {  
        return sessionName;  
    }  
    public void setSessionName(String sessionName) {  
        this.sessionName = sessionName;  
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

}