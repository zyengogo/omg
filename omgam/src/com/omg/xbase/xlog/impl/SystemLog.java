package com.omg.xbase.xlog.impl;

/**
 * 使用系统的System.out.Println 实现日志的输出
 * 
 * @author SUN
 * @Date 2010-1-19 下午03:10:44
 * @version 1.0
 * 
 */
public class SystemLog extends AbstractLog {

	/**
	 * 初始化LOG,采用调用堆栈获取调用它的真实的类
	 */
	protected void init() {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		int index = 0;
		for (index = 0; index < traceElements.length; index++) {
			if (traceElements[index].getClassName().equals(LOGPROXY_CLASS_NAME)) {
				break;
			}
		}
		try {
			entityClass = Class.forName(traceElements[++index].getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			entityClass = SystemLog.class;
		}
	}

	/* ================以下方法就是调用系统IO输出日志的实现============== */

	public void debug(Object message, Throwable t) {
		if (entityClass == null) {
			init();
		}

		if (isDebugEnabled())
			printOut(message, t);
	}

	public void error(Object message, Throwable t) {
		if (entityClass == null) {
			init();
		}

		if (isErrorEnabled())
			errorOut(message, t);
	}

	public void fatal(Object message, Throwable t) {
		if (entityClass == null) {
			init();
		}

		if (isFatalEnabled())
			errorOut(message, t);
	}

	public void info(Object message, Throwable t) {
		if (entityClass == null) {
			init();
		}

		if (isInfoEnabled())
			printOut(message, t);
	}

	public void warn(Object message, Throwable t) {
		if (entityClass == null) {
			init();
		}

		if (isWarnEnabled())
			errorOut(message, t);
	}

	/**
	 * 输出正常信息,包括 debug info warn
	 * 
	 * @param message
	 *            输出信息
	 * @param t
	 *            可能的异常
	 */
	private void printOut(Object message, Throwable t) {
		System.out.println(entityClass.getName() + " : " + message);
		if (t != null) {
			t.printStackTrace(System.out);
		}
	}

	/**
	 * 输出异常信息,包括error fatal
	 * 
	 * @param message
	 *            输出信息
	 * @param t
	 *            可能的异常
	 */
	private void errorOut(Object message, Throwable t) {
		System.err.println(entityClass.getName() + " : " + message);
		if (t != null) {
			t.printStackTrace(System.err);
		}
	}

	/**
	 * 实现验证方法
	 * 
	 * @return 如果能使用返回true 否则返回false
	 */
	public boolean canWork() {
		return true;
	}

}
