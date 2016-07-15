package com.omg.xbase.xlog.spring;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ThrowsAdvice;

import com.omg.xbase.xlog.Log;

/**
 * 采用Spring AOP管理的日志处理,主要用于处理运行日志和异常日志
 * 
 * @author SUN
 * @Date 2010-1-20 下午07:01:42
 * @version 1.0
 * 
 */
public class EventLogger implements MethodInterceptor, ThrowsAdvice {

	private Log log; // LOG执行器

	public void setLog(Log log) {
		this.log = log;
	}

	/**
	 * 方法包装执行器
	 * 
	 * @param invocation
	 *            方法执行的一个代理
	 * @return 方法的执行结果
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		/* 获取方法的入参 */
		Object[] args = invocation.getArguments();

		log.info("=================" + invocation.getThis()+ "===============");

		log.info("开始执行:" + invocation.getMethod().getName() + "方法");

		StringBuilder builder = new StringBuilder("输入方法参数:");
		for (int i = 0; i < args.length; i++) {
			Object object = args[i];
			builder.append("第" + i + "个参数:");
			builder.append(object);
			builder.append("\n");
		}

		log.info(builder.toString());

		/* 执行方法 */
		Object result = invocation.proceed();

		log.info("方法执行的结果:" + result);
		log.info("=================" + invocation.getThis()+ "===============");
		return result;
	}

	/**
	 * 输出异常的方法
	 * 
	 * @param method
	 * @param args
	 * @param target
	 * @param ex
	 */
	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) {
		log.error("=================" + target.getClass() + "===============");
		log.error("执行方法:" + method.getName() + "ִ出错");

		StringBuilder builder = new StringBuilder("方法执行参数:");
		for (int i = 0; i < args.length; i++) {
			Object object = args[i];
			builder.append("第" + i + "个参数:");
			builder.append(object);
			builder.append("\n");
		}
		log.error(builder.toString());

		log.error("错误堆栈:" + ex.getMessage());
		log.error("=================" + target.getClass() + "===============");
	}

}
