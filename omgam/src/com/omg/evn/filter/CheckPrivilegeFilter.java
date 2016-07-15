package com.omg.evn.filter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omg.evn.action.SystemConstants;
import com.omg.evn.entity.system.Sysuser;
import com.omg.xbase.struts.WebUtils;

/**
 * @Title: CheckPrivilegeFilter.java
 * @Description: TODO
 * @author zyen
 * @date 2014-06-24 上午11:02:26
 * @最后修改人：zyen
 * @最后修改时间：2014-06-24 上午11:02:26
 * @version V1.0
 * @copyright: 小火炉技术团队
 */
@SuppressWarnings("serial")
public class CheckPrivilegeFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**测试中**/
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//防中文乱码
		arg0.setCharacterEncoding("UTF-8");
		//arg2.doFilter(arg0, arg1);if(true)return;
		boolean debugModel = true;       //是否启用admin帐户。当为true时，不对admin帐户进行权限验证直接放行；
		boolean isEnableAdmin = false;	 //当为false时，admin作为普通帐户登录
									  	
		if(debugModel){
			debugModeChecke(arg0, arg1, arg2, isEnableAdmin);
		}else{
			
		}
	}
	

	private void debugModeChecke(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2, boolean isEnableAdmin) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
	//	String privilegeUrl = Privilege.parsePrivilegeUrl(req.getServletPath());
		String redirectUrl = SystemConstants.REDIRECTURL;
		Sysuser curLoginUser = WebUtils.getCurLoginUser(req.getSession());
	    String url = req.getRequestURI(); 
//	    System.out.println("##:"+url+"\n");
//	    String ctx_path = req.getContextPath(); 
	    if(curLoginUser==null){
			res.sendRedirect(redirectUrl);
			return ;
		}else{
			if(url.indexOf("<")>=0||url.indexOf("%3C")>=0){
				res.sendRedirect(SystemConstants.REDIRECTURL2);
				return ;
			}
		}
		
		//调试代码用于捕获未添加到权限树的权限
		/** debug code begin */
		FileWriter writer = null;
		
		Map<String, Object> debugMap = (Map<String, Object>) req.getSession().getServletContext().getAttribute(SystemConstants.DEBUG_MAP);
		if(debugMap == null){
			writer = new FileWriter(SystemConstants.fileName, true);
			writer.write("\r\n-----------------------------------------\r\n");
			debugMap = new HashMap<String, Object>();
			debugMap.put(SystemConstants.PRIV_SET, new HashSet<String>());
			debugMap.put(SystemConstants.FILE_WRITER, writer);
			req.getSession().getServletContext().setAttribute(SystemConstants.DEBUG_MAP, debugMap);
		}
		writer = (FileWriter) debugMap.get(SystemConstants.FILE_WRITER);
		arg2.doFilter(arg0, arg1);
	}
	

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
