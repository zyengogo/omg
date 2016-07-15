package com.omg.xbase.xlog.impl;

/**
 * 不使用LOG的实现,用这个就可以取消LOG
 * 
 * @author SUN
 * @Date 2010-1-19 下午03:10:05
 * @version 1.0
 * 
 */
public class NoLog extends AbstractLog {

	public NoLog() {
		isDebugEnabled = false;
		isErrorEnabled = false;
		isFatalEnabled = false;
		isInfoEnabled = false;
		isWarnEnabled = false;
	}

	protected void init() {
	}

	public void debug(Object message, Throwable t) {
	}

	public void error(Object message, Throwable t) {
	}

	public void fatal(Object message, Throwable t) {
	}

	public void info(Object message, Throwable t) {
	}

	public void warn(Object message, Throwable t) {
	}

	public boolean canWork() {
		return true;
	}

}
