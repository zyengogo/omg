/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: WebUtils.java 763 2009-12-27 18:36:21Z calvinxiu $
 */
package com.omg.xbase.struts;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.omg.evn.entity.system.Sysuser;

/**
 * @Title: WebUtils.java
 * @Description: TODO
 * @author zyen
 * @date 2013-11-18 下午05:58:31
 * @最后修改人：zyen
 * @最后修改时间：2013-11-18 下午05:58:31
 * @version V1.0
 * @copyright: 小火炉技术团队
 */
public class WebUtils {

	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	// 当前登录用户存放 "域" 标识
	// private static final String CUR_LOGIN_USER = "CUR_LOGIN_USER";
	private static final String CUR_LOGIN_USER = "user";
	// 系统所有权限存放 "域" 标识
	private static final String ALL_PRIVILEGE = "ALL_PRIVILEGE";
	// 当前登录用户权限存放"域" 标识
	private static final String CUR_USER_PRIVILEGE = "CUR_USER_PRIVILEGE";
	// 管理员名称
	private static final String ADMIN = "admin";
	// 系统提供可访问的资源标识
	private static final String ALL_MODULE = "ALL_MODULE";
	// 可以自由访问的资源标识
	private static final String FREE_PRIVILEGE = "FREE_PRIVILEGE";
	// 登录后可访问的资源标识
	private static final String LOGIN_ACCESS_PRIVILEGE = "LOGIN_ACCESS_PRIVILEGE";
	
	/**
	 * 设置客户端缓存过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		//Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		//Http 1.1 header
		response.setHeader("Cache-Control", "max-age=" + expiresSeconds);
	}

	/**
	 * 设置客户端无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		//Http 1.0 header
		response.setDateHeader("Expires", 0);
		//Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "No-cache");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified 内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
			long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag 内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查浏览器客户端是否支持gzip编码.
	 */
	public static boolean checkAccetptGzip(HttpServletRequest request) {
		//Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");

		if (StringUtils.contains(acceptEncoding, "gzip")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置Gzip Header并返回GZIPOutputStream.
	 */
	public static OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName 下载后的文件名.
	 */
	public static void setDownloadableHeader(HttpServletResponse response, String fileName) {
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	}

	

	/**
	 * @Description: 获取当前登录用户(filter中调用该方法)
	 * @param ：参数
	 * @return:返回
	 * @throws :抛出异常
	 */
	public static Sysuser getCurLoginUser(HttpSession session) {
		return (Sysuser) session.getAttribute(CUR_LOGIN_USER);
	}
	/**
	 * 取得带相同前缀的Request Parameters.
	 * 
	 * 返回的结果Parameter名已去除前缀.
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParametersStartingWith(HttpServletRequest request, String prefix) {
		return org.springframework.web.util.WebUtils.getParametersStartingWith(request, prefix);
	}
	
	// ----统一 request、session、appliction 域获取方法---------
	public static HttpServletRequest getRequset() {
		return Struts2Utils.getRequest();
	}

	public static HttpSession getSession() {
		return Struts2Utils.getSession();
	}

	public static ServletContext getApplication() {
		return Struts2Utils.getRequest().getSession().getServletContext();
	}
}
