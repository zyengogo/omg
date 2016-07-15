package com.omg.xbase.xlog.impl;

import com.omg.xbase.xlog.ICheckWork;
import com.omg.xbase.xlog.Log;

/**
 * LOG接口的抽象实现,这里同时实现了ICheckWork接口
 * 
 * @author SUN
 * @Date 2010-1-19 下午01:57:51
 * @version 1.0
 * 
 */
public abstract class AbstractLog implements Log, ICheckWork {

	/* 常量,用于保存Log代理类的路径,用于从调用堆栈中找到真实调用LOG的类.如果修改了程序的结构,这个地方需要修改 */
	public static final String LOGPROXY_CLASS_NAME = "com.omg.xbase.xlog.LogProxy";

	/* 以下是用于保存输出范围的变量,默认是全输出 */
	protected boolean isFatalEnabled = true;
	protected boolean isErrorEnabled = true;
	protected boolean isWarnEnabled = true;
	protected boolean isInfoEnabled = true;
	protected boolean isDebugEnabled = true;

	/* 用来保存真实调用LOG的类 */
	protected Class entityClass = null;

	/**
	 * 抽象的初始化方法,用来初始化各种属性
	 */
	protected abstract void init();

	/* ==============以下方法是接口中部分方法的实现,而具体的逻辑实现,交由它的子类来实现============ */

	public void debug(Object message) {
		debug(message, null);
	}

	public void debug(String fmt, Object[] args) {
		debug(String.format(fmt, args));
	}

	public void error(Object message) {
		error(message, null);
	}

	public void error(String fmt, Object[] args) {
		error(String.format(fmt, args));
	}

	public void fatal(Object message) {
		fatal(message, null);
	}

	public void fatal(String fmt, Object[] args) {
		fatal(String.format(fmt, args));
	}

	public void info(Object message) {
		info(message, null);
	}

	public void info(String fmt, Object[] args) {
		info(String.format(fmt, args));
	}

	public void warn(Object message) {
		warn(message, null);
	}

	public void warn(String fmt, Object[] args) {
		warn(String.format(fmt, args));
	}
	
	/*============下面是获取输出权限的方法====================*/

	public boolean isDebugEnabled() {
		return isDebugEnabled;
	}

	public boolean isErrorEnabled() {
		return isErrorEnabled;
	}

	public boolean isFatalEnabled() {
		return isFatalEnabled;
	}

	public boolean isInfoEnabled() {
		return isInfoEnabled;
	}

	public boolean isWarnEnabled() {
		return isWarnEnabled;
	}

}
