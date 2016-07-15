package com.omg.xbase.xlog;

/**
 * LOG的代理类,主要是为了Spring注入的时候方便统一的管理
 * 
 * @author SUN
 * @Date 2010-1-19 下午01:54:41
 * @version 1.0
 * 
 */
public class LogProxy implements Log {

	/* 真正的LOG */
	private Log logger;

	/**
	 * setter方法
	 * 
	 * @param logger
	 */
	public void setLogger(Log logger) {
		this.logger = logger;
	}

	/* ================以下一个方法全是代理真实LOG的调用==================== */

	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void debug(String fmt, Object[] args) {
		logger.debug(fmt, args);
	}

	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	public void error(Object message) {
		logger.error(message);
	}

	public void error(String fmt, Object[] args) {
		logger.error(fmt, args);
	}

	public void fatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	public void fatal(Object message) {
		logger.fatal(message);
	}

	public void fatal(String fmt, Object[] args) {
		logger.fatal(fmt, args);
	}

	public void info(Object message, Throwable t) {
		logger.info(message, t);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void info(String fmt, Object[] args) {
		logger.info(fmt, args);
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return logger.isFatalEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isInfoEnabled();
	}

	public void warn(Object message, Throwable t) {
		logger.warn(message, t);
	}

	public void warn(Object message) {
		logger.warn(message);
	}

	public void warn(String fmt, Object[] args) {
		logger.warn(fmt, args);
	}

}
