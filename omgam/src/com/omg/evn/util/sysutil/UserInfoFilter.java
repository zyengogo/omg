package com.omg.evn.util.sysutil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.omg.evn.action.SystemConstants;


/**
 * Filter class
 * 
 * @web.filter name="UserInfo" display-name="Name for UserInfo"
 *             description="Description for UserInfo"
 * @web.filter-mapping url-pattern="/UserInfo"
 * @web.filter-init-param name="A parameter" value="A value"
 */
public class UserInfoFilter implements Filter {
	private FilterConfig config;

	private String loginUri = "/login.jsp";

	private String excludeFilters = "/login.action,/index.jsp";

	private String CONFIG_LOCATION_DELIMITERS = ",; \t\n";
	
	private List excludeFiltersList = new ArrayList();

	public UserInfoFilter() {
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = ((HttpServletRequest) request);

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String servletPath = httpServletRequest.getServletPath().toLowerCase()
				.trim();
		
		HttpSession session = httpServletRequest.getSession(false);
		if (session == null || session.getAttribute(SystemConstants.SYSTEM_USERINFO) == null) {
			if (servletPath.endsWith(".jsp") || servletPath.endsWith(".action")) {
				if (!excludeFiltersList.contains(servletPath)) {
						httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + loginUri);
						return;
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.loginUri = this.config.getInitParameter("loginUri");
		String _excludeFilters = this.config.getInitParameter("excludeFilters");
		if (org.apache.commons.lang.StringUtils.isNotEmpty(_excludeFilters)) {
			this.excludeFilters = _excludeFilters;
		}
		String[] excludeFiltersArray = StringUtils.tokenizeToStringArray(
				excludeFilters, CONFIG_LOCATION_DELIMITERS);
		for (int i = 0, j = excludeFiltersArray.length; i < j; i++) {
			excludeFiltersList.add(excludeFiltersArray[i]);
		}

	}

}
