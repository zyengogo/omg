package com.omg.xbase.struts.plug.json;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;
import com.omg.xbase.struts.JsonUtil;
import com.omg.xbase.struts.Struts2Utils;

public class JsonResult implements Result {

	private static final long serialVersionUID = 1L;

	private final static Logger logger = LoggerFactory.getLogger(JsonResult.class);
	private static final String DEFAULT_CONTENT_TYPE = "application/json";
	private static final String DEFAULT_CONTENT_ENCODING = "UTF-8";
	private boolean enableGZIP = false;
	private boolean noCache = false;
	private String root;
	private String excludeFilter;

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	private static boolean isGzipInRequest(HttpServletRequest request) {
		String header = request.getHeader("Accept-Encoding");
		return header != null && header.indexOf("gzip") >= 0;
	}

	public void execute(ActionInvocation arg0) throws Exception {
		ActionContext actionContext = arg0.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
		try {
			String json;
			Object rootObject;
			if (this.root != null) {
				ValueStack stack = arg0.getStack();
				rootObject = stack.findValue(this.root);
			} else {
				rootObject = arg0.getAction();
			}
			json = JsonUtil.getJsonFromObject(rootObject,excludeFilter);
			boolean writeGzip = enableGZIP && isGzipInRequest(request);
			writeToResponse(json, response, writeGzip);
		} catch (IOException exception) {
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
	}

	protected void writeToResponse(String json, HttpServletResponse response, boolean writeGzip) throws IOException {
		logger.debug("[JSON]" + json);
		response.setContentType(DEFAULT_CONTENT_TYPE + ";charset=" + DEFAULT_CONTENT_ENCODING);
		if (isNoCache()) {
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
			response.setHeader("Pragma", "No-cache");
		}
		if (writeGzip) {
			response.addHeader("Content-Encoding", "gzip");
			GZIPOutputStream out = null;
			InputStream in = null;
			try {
				out = new GZIPOutputStream(response.getOutputStream());
				in = new ByteArrayInputStream(json.getBytes());
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
			}
		} else {
			response.setContentLength(json.getBytes(DEFAULT_CONTENT_ENCODING).length);
			PrintWriter out = response.getWriter();
			out.print(json);
		}
	}

	public void setEnableGZIP(boolean enableGZIP) {
		this.enableGZIP = enableGZIP;
	}

	public boolean isEnableGZIP() {
		return enableGZIP;
	}

	public void setNoCache(boolean noCache) {
		this.noCache = noCache;
	}

	public boolean isNoCache() {
		return noCache;
	}

	public void setExcludeFilter(String excludeFilter) {
		this.excludeFilter = excludeFilter;
	}

	public String getExcludeFilter() {
		return excludeFilter;
	}
}
