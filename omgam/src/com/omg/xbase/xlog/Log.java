package com.omg.xbase.xlog;

/**
 * 日志处理的接口,其他模块只需依赖这个模块就可以了
 * 
 * @author SUN
 * @Date 2010-1-19 下午01:29:53
 * @version 1.0
 * 
 */
public interface Log {

	/**
	 * 判断是否能输出Fatal信息
	 * 
	 * @return 如果能 返回true,不能 返回false
	 */
	public boolean isFatalEnabled();

	/**
	 * 输出fatal信息
	 * 
	 * @param message
	 *            输出的对象
	 */
	public void fatal(Object message);
	
	/**
	 * 输出fatal信息
	 * 
	 * @param fmt
	 *            字符串信息
	 * @param args
	 *            参数
	 */
	public void fatal(String fmt, Object[] args);

	/**
	 * 输出fatal信息
	 * 
	 * @param message
	 *            输出的对象
	 * @param t
	 *            可能需要输出的异常
	 */
	public void fatal(Object message, Throwable t);

	/**
	 * 判断是否能输出错误信息
	 * 
	 * @return 如果能,返回true,如果不能 返回false
	 */
	public boolean isErrorEnabled();

	/**
	 * 输出error信息
	 * 
	 * @param message
	 *            输出的对象
	 */
	public void error(Object message);

	/**
	 * 输出error信息
	 * 
	 * @param fmt
	 *            输出的信息
	 * @param args
	 *            参数
	 */
	public void error(String fmt, Object[] args);

	/**
	 * 输出error信息
	 * 
	 * @param message
	 *            输出的对象
	 * @param t
	 *            可能输出的异常
	 */
	public void error(Object message, Throwable t);

	/**
	 * 判断能否打印warn信息
	 * 
	 * @return 如果能 返回true,不能 返回false
	 */
	public boolean isWarnEnabled();

	/**
	 * 输出warn信息
	 * 
	 * @param message
	 *            输出对象
	 */
	public void warn(Object message);

	/**
	 * 输出warn信息
	 * 
	 * @param fmt
	 *            输出的信息
	 * @param args
	 *            参数
	 */
	public void warn(String fmt, Object[] args);

	/**
	 * 输出warn信息
	 * 
	 * @param message
	 *            输出的对象
	 * @param t
	 *            可能输出的异常
	 */
	public void warn(Object message, Throwable t);

	/**
	 * 判断能否输出Info信息
	 * 
	 * @return 如果能 输出true,不能 输出false;
	 */
	public boolean isInfoEnabled();

	/**
	 * 输出info信息
	 * 
	 * @param message
	 *            输出对象
	 */
	public void info(Object message);

	/**
	 * 输出Info信息
	 * 
	 * @param fmt
	 *            输出的信息
	 * @param args
	 *            参数
	 */
	public void info(String fmt, Object[] args);

	/**
	 * 输出Info信息
	 * 
	 * @param message
	 *            输出对象
	 * @param t
	 *            可能输出的异常
	 */
	public void info(Object message, Throwable t);

	/**
	 * 判断是否输出Debug信息
	 * 
	 * @return 如果能,输出true,不能 输出false
	 */
	public boolean isDebugEnabled();

	/**
	 * 输出debug信息
	 * 
	 * @param message
	 *            输出的对象
	 */
	public void debug(Object message);

	/**
	 * 输出debug信息
	 * 
	 * @param fmt
	 *            输出的信息
	 * @param args
	 *            参数
	 */
	public void debug(String fmt, Object[] args);

	/**
	 * 输出debug信息
	 * 
	 * @param message
	 *            输出的对象
	 * @param t
	 *            可能输出的异常
	 */
	public void debug(Object message, Throwable t);
}