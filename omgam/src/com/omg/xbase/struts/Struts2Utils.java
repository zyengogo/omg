/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Struts2Utils.java 763 2009-12-27 18:36:21Z calvinxiu $
 */
package com.omg.xbase.struts;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.opensymphony.xwork2.ActionContext;

/**
 * Struts2工具类.
 * 
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 * @author calvin
 */
public class Struts2Utils {

	//-- header 常量定义 --//
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	//-- content-type 常量定义 --//
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";
	private static final String XML_TYPE = "text/xml";
	private static final String HTML_TYPE = "text/html";
	private static final String JS_TYPE = "text/javascript";
	private static final String IMAGE_TYPE = "image/jpeg";

	//private static ObjectMapper mapper = new ObjectMapper();

	//-- 取得Request/Response/Session的简化函数 --//
	/**
	 * 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 传入参数ActionContext，得到一个Map<String,Object>
	 * @param ctx 
	 * @return
	 */
	public static Map<String,Object> getSession(ActionContext ctx){
		Map<String,Object> session;
		if (ctx!=null) {
			session = ctx.getSession();
			return session;
		}else {
			ctx = ActionContext.getContext();
			return ctx.getSession();
		}
	}
	/**
	 * 取得HttpRequest的简化函数.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpResponse的简化函数.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	//-- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数.

	 * eg.
	 * render("text/plain", "hello",);
	 * 
	 */
	public static void render(final String contentType, final String content, final boolean noCache) {
		HttpServletResponse response = initResponse(contentType, noCache);
		try {
			response.setContentLength(content.getBytes(DEFAULT_ENCODING).length);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	/**
	 * 直接输出内容的简便函数.

	 * eg.
	 * render("text/plain", "hello",);
	 * 
	 */
	public static void renderEnableGZIP(final String contentType, final String content, final boolean noCache) {
		HttpServletResponse response = initResponse(contentType, noCache);
		try {
			response.addHeader("Content-Encoding", "gzip");
			ServletOutputStream sos = response.getOutputStream();
			GZIPOutputStream out = null;
			InputStream in = null;
			try {
				out = new GZIPOutputStream(sos);
				in = new ByteArrayInputStream(content.getBytes(DEFAULT_ENCODING));
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			} finally {
				if (in != null)
					in.close();
				if (out != null) {
					out.finish();
					out.close();
				}
				if (sos != null) {
					sos.flush();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	/**
	 * 直接输出内容的简便函数.
	 * eg.
	 * render("text/plain", "hello",);
	 * 
	 */
	public static void render(final String contentType, final String content) {
		render(contentType,content,DEFAULT_NOCACHE);
	}
	
	/**
	 * 直接输出内容的简便函数,启用GZIP压缩.
	 * eg.
	 * render("text/plain", "hello",);
	 * 
	 */
	public static void renderEnableGZIP(final String contentType, final String content) {
		renderEnableGZIP(contentType,content,DEFAULT_NOCACHE);
	}

	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final boolean noCache) {
		render(TEXT_TYPE, text, noCache);
	}
	/**
	 * 直接输出文本,启用GZIP压缩.
	 * @see #render(String, String, String...)
	 */
	public static void renderTextEnableGZIP(final String text, final boolean noCache) {
		renderEnableGZIP(TEXT_TYPE, text, noCache);
	}
	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text) {
		render(TEXT_TYPE, text);
	}
	/**
	 * 直接输出文本,启用GZIP压缩.
	 * @see #render(String, String, String...)
	 */
	public static void renderTextEnableGZIP(final String text) {
		renderEnableGZIP(TEXT_TYPE, text);
	}

	/**
	 * 直接输出HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final boolean noCache) {
		render(HTML_TYPE, html, noCache);
	}
	/**
	 * 直接输出HTML,启用GZIP压缩.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtmlEnableGZIP(final String html, final boolean noCache) {
		renderEnableGZIP(HTML_TYPE, html, noCache);
	}
	/**
	 * 直接输出HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html) {
		render(HTML_TYPE, html);
	}
	/**
	 * 直接输出HTML,启用GZIP压缩.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtmlEnableGZIP(final String html) {
		renderEnableGZIP(HTML_TYPE, html);
	}

	/**
	 * 直接输出XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final boolean noCache) {
		render(XML_TYPE, xml, noCache);
	}
	/**
	 * 直接输出XML,启用GZIP压缩.
	 * @see #render(String, String, String...)
	 */
	public static void renderXmlEnableGZIP(final String xml, final boolean noCache) {
		renderEnableGZIP(XML_TYPE, xml, noCache);
	}
	/**
	 * 直接输出XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml) {
		render(XML_TYPE, xml);
	}
	/**
	 * 直接输出XML,启用GZIP压缩.
	 * @see #render(String, String, String...)
	 */
	public static void renderXmlEnableGZIP(final String xml) {
		renderEnableGZIP(XML_TYPE, xml);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString, final boolean noCache) {
		render(JSON_TYPE, jsonString, noCache);
	}
	
	/**
	 * 直接输出JSON,启用GZIP压缩.
	 * 
	 * @param jsonString json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJsonEnableGZIP(final String jsonString, final boolean noCache) {
		renderEnableGZIP(JSON_TYPE, jsonString, noCache);
	}
	
	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString) {
		render(JSON_TYPE, jsonString);
	}
	
	/**
	 * 直接输出JSON,启用GZIP压缩.
	 * 
	 * @param jsonString json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJsonEnableGZIP(final String jsonString) {
		renderEnableGZIP(JSON_TYPE, jsonString);
	}

	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object data, final String excludeFilter,final String jodaDateTimeFormat, final boolean noCache) {
		HttpServletResponse response = initResponse(JSON_TYPE, noCache);
		try {
			ObjectMapper mapper = new ObjectMapper();
			if ((excludeFilter != null)&&!"".equals(excludeFilter)) {
				FilterSerializerFactory csf = new FilterSerializerFactory();
				csf.addExcludeFilters(excludeFilter);
				if ((jodaDateTimeFormat != null)&&!"".equals(jodaDateTimeFormat)) {
					csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer(jodaDateTimeFormat));
				}
				mapper.setSerializerFactory(csf);
			}else{
				if ((jodaDateTimeFormat != null)&&!"".equals(jodaDateTimeFormat)) {
					FilterSerializerFactory csf = new FilterSerializerFactory();
					csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer(jodaDateTimeFormat));
					mapper.setSerializerFactory(csf);
				}
			}
			mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
			mapper.writeValue(response.getWriter(), data);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJsonEnableGZIP(final Object data, final String excludeFilter,final String jodaDateTimeFormat, final boolean noCache) {
		HttpServletResponse response = initResponse(JSON_TYPE, noCache);
		response.addHeader("Content-Encoding", "gzip");
		GZIPOutputStream out = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			if ((excludeFilter != null)&&!"".equals(excludeFilter)) {
				FilterSerializerFactory csf = new FilterSerializerFactory();
				csf.addExcludeFilters(excludeFilter);
				if ((jodaDateTimeFormat != null)&&!"".equals(jodaDateTimeFormat)) {
					csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer(jodaDateTimeFormat));
				}
				mapper.setSerializerFactory(csf);
			}else{
				if ((jodaDateTimeFormat != null)&&!"".equals(jodaDateTimeFormat)) {
					FilterSerializerFactory csf = new FilterSerializerFactory();
					csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer(jodaDateTimeFormat));
					mapper.setSerializerFactory(csf);
				}
			}
			mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
			out = new GZIPOutputStream(response.getOutputStream());
			mapper.writeValue(out, data);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		} 
	}
	
	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object data, final boolean noCache) {
		renderJson(data,null,null,noCache);
	}
	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJsonEnableGZIP(final Object data, final boolean noCache) {
		renderJsonEnableGZIP(data,null,null,noCache);
	}
	
	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object data) {
		renderJson(data,null,null,DEFAULT_NOCACHE);
	}
	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJsonEnableGZIP(final Object data) {
		renderJsonEnableGZIP(data,null,null,DEFAULT_NOCACHE);
	}
	
	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数名.
	 * @param object Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonP(final String callbackName,final Object data, final String excludeFilter,final String jodaDateTimeFormat, final boolean noCache) {
		String jsonString = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			if ((excludeFilter != null)&&!"".equals(excludeFilter)) {
				FilterSerializerFactory csf = new FilterSerializerFactory();
				csf.addExcludeFilters(excludeFilter);
				if ((jodaDateTimeFormat != null)&&!"".equals(jodaDateTimeFormat)) {
					csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer(jodaDateTimeFormat));
				}
				mapper.setSerializerFactory(csf);
			}else{
				if ((jodaDateTimeFormat != null)&&!"".equals(jodaDateTimeFormat)) {
					FilterSerializerFactory csf = new FilterSerializerFactory();
					csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer(jodaDateTimeFormat));
					mapper.setSerializerFactory(csf);
				}
			}
			mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
			jsonString = mapper.writeValueAsString(data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();

		//渲染Content-Type为javascript的返回内容,输出结果为javascript语句, 如callback197("{html:'Hello World!!!'}");
		render(JS_TYPE, result, noCache);
	}
	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数名.
	 * @param object Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonPEnableGZIP(final String callbackName,final Object data, final String excludeFilter,final String jodaDateTimeFormat, final boolean noCache) {
		String jsonString = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			if ((excludeFilter != null)&&!"".equals(excludeFilter)) {
				FilterSerializerFactory csf = new FilterSerializerFactory();
				csf.addExcludeFilters(excludeFilter);
				if ((jodaDateTimeFormat != null)&&!"".equals(jodaDateTimeFormat)) {
					csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer(jodaDateTimeFormat));
				}
				mapper.setSerializerFactory(csf);
			}else{
				if ((jodaDateTimeFormat != null)&&!"".equals(jodaDateTimeFormat)) {
					FilterSerializerFactory csf = new FilterSerializerFactory();
					csf.addSpecificMapping(org.joda.time.DateTime.class, new DateTimeSerializer(jodaDateTimeFormat));
					mapper.setSerializerFactory(csf);
				}
			}
			mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
			jsonString = mapper.writeValueAsString(data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();

		//渲染Content-Type为javascript的返回内容,输出结果为javascript语句, 如callback197("{html:'Hello World!!!'}");
		renderEnableGZIP(JS_TYPE, result, noCache);
	}
	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数名.
	 * @param object Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonP(final String callbackName, final Object object, final boolean noCache) {
		renderJsonP(callbackName,object,null,null,noCache);
	}
	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数名.
	 * @param object Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonP(final String callbackName, final Object object) {
		renderJsonP(callbackName,object,DEFAULT_NOCACHE);
	}
	
	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数名.
	 * @param object Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonPEnableGZIP(final String callbackName, final Object object, final boolean noCache) {
		renderJsonPEnableGZIP(callbackName,object,null,null,noCache);
	}
	
	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数名.
	 * @param object Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonpEnableGZIP(final String callbackName, final Object object) {
		renderJsonPEnableGZIP(callbackName, object,DEFAULT_NOCACHE);
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponse(final String contentType, final boolean noCache) {
		//分析headers参数
		HttpServletResponse response = ServletActionContext.getResponse();

		//设置headers参数
		String fullContentType = contentType + ";charset=" + DEFAULT_ENCODING;
		response.setContentType(fullContentType);
		if (noCache) {
			WebUtils.setNoCacheHeader(response);
		}

		return response;
	}
	
	public static void renderImage(byte[] images, boolean noCache){
		HttpServletResponse response = initResponse(IMAGE_TYPE, noCache);
		try {
			OutputStream os = response.getOutputStream();
			os.write(images);
			response.flushBuffer();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取客户端IP
	 */
	public static String getIpAddrByRequest(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
