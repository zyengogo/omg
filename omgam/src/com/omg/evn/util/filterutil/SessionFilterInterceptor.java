package com.omg.evn.util.filterutil;
import java.io.PrintWriter;  

import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import org.apache.struts2.ServletActionContext;  
  
import com.opensymphony.xwork2.Action;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;  
public class SessionFilterInterceptor extends AbstractInterceptor {  
	  
    @Override  
    public String intercept(ActionInvocation invocation) throws Exception {  
        HttpSession session = ServletActionContext.getRequest().getSession();  
        if(session.getAttribute("userInfo") == null){  
//          HttpServletResponse response = ServletActionContext.getResponse();  
//          ServletActionContext.getResponse().sendRedirect(Action.INPUT);  
//          session.invalidate();  
//          response.setContentType("text/html;charset=gb2312");  
//          PrintWriter out = response.getWriter();  
//          out.println("<script language='javascript' type='text/javascript'>");  
//          out.println("alert('由于你长时间没有操作,导致Session失效!请你重新登录!');window.location.href='/login.jsp'");  
//          out.println("</script>");  
//          return "none";  
            return Action.INPUT;  
        }else{  
            return invocation.invoke();  
        }  
    }  
  
}  