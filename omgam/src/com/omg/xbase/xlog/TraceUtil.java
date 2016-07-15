package com.omg.xbase.xlog;

import java.util.Random;

import org.apache.log4j.NDC;

/**
 * LOG4J的trace工具, 通过这个工具,同一个trace里面的所有日志都带有一个traceID 这样方便使用grep命令等,提取一次的日志
 * 这个需要在log4j的log4j.appender.stdout.layout.ConversionPattern里面加上%x
 * 比如:log4j.appender.stdout.layout.ConversionPattern=[%p] %d %c{1} %x - %m%n
 * 
 * @author SUN
 * @Date 2010-1-17
 * @version 1.0
 * 
 */
public class TraceUtil {

	/**
	 * 开始Trace, 默认生成本次Trace的ID并放入NDC.
	 */
	public static void beginTrace() {
		/* 通过系统时间和1000的随机数生成traceId */
		String traceId = "" + System.currentTimeMillis()
				+ new Random().nextInt(1000);
		NDC.push(traceId);
	}

	/**
	 * 开始Trace, 将traceId放入NDC.
	 */
	public static void beginTrace(String traceId) {
		NDC.push(traceId);
	}

	/**
	 * 结束一次Trace. 清除traceId.
	 */
	public static void endTrace() {
		NDC.pop();
	}
}
