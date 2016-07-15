package com.omg.xbase.xlog.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Log4j的日志实现
 * 
 * @author SUN
 * @Date 2010-1-19 下午02:03:33
 * @version 1.0
 * 
 */
public class Log4jLog extends AbstractLog {

	/* Log4j的类路径 */
	public static final String LOG4J_CLASS_NAME = "org.apache.log4j.Logger";

	/* 真实的Log4j的对象 */
	private Log logger;

	public Log4jLog() {
		
	}

	/**
	 * 初始化方法,通过调用堆栈,获取调用它的真实的类,然后实例化LOG4J的log
	 */
	protected void init() {
		/* 获取调用这个LOG的类 */
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		int index = 0;
		/* 通过循环,获取堆栈中调用这个LOG的类 */
		for (index = 0; index < traceElements.length; index++) {
			if (traceElements[index].getClassName().equals(LOGPROXY_CLASS_NAME)) {
				break;
			}
		}

		logger = LogFactory.getLog(traceElements[++index].getClassName());
		isDebugEnabled = logger.isDebugEnabled();
		isErrorEnabled = logger.isErrorEnabled();
		isFatalEnabled = logger.isFatalEnabled();
		isInfoEnabled = logger.isInfoEnabled();
		isWarnEnabled = logger.isTraceEnabled();
	}

	/* ================以下方法就是调用LOG4J输出日志的实现============== */

	public void debug(Object message, Throwable t) {
		/* 如果logger不存在,则调用init方法初始化 */
		if (logger == null) {
			init();
		}

		if (isDebugEnabled()) {
			logger.debug(message, t);
		}
	}

	public void error(Object message, Throwable t) {
		if (logger == null) {
			init();
		}

		if (isErrorEnabled()) {
			logger.error(message, t);
		}

	}

	public void fatal(Object message, Throwable t) {
		if (logger == null) {
			init();
		}

		if (isFatalEnabled()) {
			logger.fatal(message, t);
		}
	}

	public void info(Object message, Throwable t) {
		if (logger == null) {
			init();
		}

		if (isInfoEnabled()) {
			logger.info(message, t);
		}
	}

	public void warn(Object message, Throwable t) {
		if (logger == null) {
			init();
		}

		if (isWarnEnabled()) {
			logger.warn(message, t);
		}
	}

	/**
	 * 实现验证方法
	 * 
	 * @return 如果能使用返回true 否则返回false
	 */
	public boolean canWork() {
		/* 如果能从classpath中能加载LOG4J的类,就表示基本能使用LOG4J的方式 */
		try {
			Class.forName(LOG4J_CLASS_NAME, true, Thread.currentThread()
					.getContextClassLoader());
		} catch (ClassNotFoundException e) {
			return false;
		}

		/* 继续验证配置文件是否正确,如果正确,就返回true */
		return isPropertyFileConfigured();
	}

	/**
	 * 判断配置文件是否能使用,模仿LOG4J的代码实现
	 * 
	 * @return 如果能使用 返回true,否则返回false
	 */
	final private boolean isPropertyFileConfigured() {

		String configureValue = System.getProperty("log4j.defaultInitOverride");

		if (configureValue != null && !"false".equalsIgnoreCase(configureValue))
			return false;

		if (System.getProperty("log4j.configuration") != null)
			return true;

		if (canFindInLog4jManner("log4j.properties"))
			return true;
		

		return canFindInLog4jManner("log4j.xml");
	}

	/**
	 * 查找LOG4J的配置文件
	 * 
	 * @param resourceName
	 *            配置文件的名称
	 * @return 如果能找到 返回true,否则返回false
	 */
	final private boolean canFindInLog4jManner(String resourceName) {

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();

		if (classLoader.getResource(resourceName) != null)
			return true;

		classLoader = Log4jLog.class.getClassLoader();

		if (classLoader==null) {
			return false;
		}
		
		if (classLoader.getResource(resourceName) != null)
			return true;

		return (ClassLoader.getSystemResource(resourceName) != null);
	}

	
	public boolean isDebugEnabled() {
		if (logger==null) {
			init();
		}
		return super.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		if (logger==null) {
			init();
		}
		return super.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		if (logger==null) {
			init();
		}
		return super.isFatalEnabled();
	}

	public boolean isInfoEnabled() {
		if (logger==null) {
			init();
		}
		return super.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		if (logger==null) {
			init();
		}
		return super.isWarnEnabled();
	}
	
}
