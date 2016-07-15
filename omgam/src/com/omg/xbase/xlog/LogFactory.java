package com.omg.xbase.xlog;

import com.omg.xbase.xlog.impl.Log4jLog;
import com.omg.xbase.xlog.impl.NoLog;
import com.omg.xbase.xlog.impl.SystemLog;

/**
 * 生成Logger的工厂,主要用于脱离Spring的环境下实例化Logger
 * 
 * 调用方法,Log log=LogFactory.getLogger();
 * 
 * @author SUN
 * @Date 2010-1-19 下午01:46:40
 * @version 1.0
 * 
 */
public class LogFactory {

	/* 静态的单例,用于保存需要使用的LOG的impl的CLASS */
	private static Class logger;

	/* 常量,用于保存可以使用的LOG实现类的CLASS,这个地方暂时只写了3种,以后还可以扩展,注意,这个里面存在顺序 */
	public final static Class[] LOGGER_CLASS = new Class[] { Log4jLog.class,
			SystemLog.class, NoLog.class };

	/* 静态方法块,在加载LogFactory的时候判断当前系统能使用的LOG的实现类 */
	static {
		try {

			for (int i = 0; i < LOGGER_CLASS.length; i++) {
				Class clazz = LOGGER_CLASS[i];
				ICheckWork temp = (ICheckWork) clazz.newInstance();
				if (temp.canWork()) {
					logger = clazz;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 2010.01.20 18:25 sx 提供用户手动选取logger的方法
	 * 
	 * 静态方法,根据传入的LOGGER类型 获取一个新的LOG实例
	 * @return	新的LOG实例
	 */
	public static Log getLogger(Class loggerClass){
		try {
			/* 注意,这里是用LogProxy代理了一层,主要是为了反射的时候和使用Spring的时候相统一 */
			LogProxy proxy = new LogProxy();
			proxy.setLogger((Log) loggerClass.newInstance());
			return proxy;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 静态方法,获取一个新的LOG实例
	 * 
	 * @return 新的LOG实例
	 */
	public static Log getLogger() {
		try {
			/* 注意,这里是用LogProxy代理了一层,主要是为了反射的时候和使用Spring的时候相统一 */
			LogProxy proxy = new LogProxy();
			proxy.setLogger((Log) logger.newInstance());
			return proxy;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
