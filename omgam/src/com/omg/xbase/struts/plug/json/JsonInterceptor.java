package com.omg.xbase.struts.plug.json;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.omg.xbase.struts.JsonUtil;
import com.omg.xbase.struts.Struts2Utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class JsonInterceptor implements Interceptor {
	private static final long serialVersionUID = 1L;
	private String reqParam;
	private String beanClass;
	private String root;

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext actionContext = arg0.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		String paramValue = request.getParameter(reqParam);
		if (paramValue != null) {
			if ((beanClass != null)&&(root != null)) {
				ValueStack stack = arg0.getStack();
				Class<?> c = Class.forName(beanClass);
				stack.setValue(root, JsonUtil.getObjectFromJson(paramValue, c));
			}
		}
		return arg0.invoke();
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}

	public String getReqParam() {
		return reqParam;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getRoot() {
		return root;
	}
}
