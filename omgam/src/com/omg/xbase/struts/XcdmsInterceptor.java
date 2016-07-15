package com.omg.xbase.struts;

import java.util.Map;
import java.util.Set;

import com.omg.evn.action.SystemConstants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;
/**
 * 
 * @author zyen
 *
 */
public class XcdmsInterceptor extends AbstractInterceptor{  
  
    private static final long serialVersionUID = 1L;  
    private String excludeActionName;
    private String sessionName;
      
	@Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
        String actionName = invocation.getProxy().getActionName();  
        Set<String> set = TextParseUtil.commaDelimitedStringToSet(excludeActionName);  
        boolean flag = false;  
        System.out.println("set.toString-"+set.toString());
        System.out.println("---"+actionName);
        if(set.contains(actionName)){  
        	flag = true;
        }
        Map<String, Object> sessions = invocation.getInvocationContext().getSession();  
        System.out.println("---"+sessions.get(SystemConstants.SYSTEM_USERINFO));
        if(sessions.get(SystemConstants.SYSTEM_USERINFO)!= null){ 
        	flag = true;
        }
        return flag?invocation.invoke():"error";
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

}