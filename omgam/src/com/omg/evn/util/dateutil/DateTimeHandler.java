package com.omg.evn.util.dateutil;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeHandler {

	/**
	 * 字符串格式的 日期转换成 DateTime
	 */
	public static DateTime dateStrToDateTime(String str){
		DateTime time=null;
		if(str!=null && !"".equals(str.trim())){
			time = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(str.trim());
		}
	    return time;
	}
	
	/**
	 * DateTime 类型转换为 要求的 String 类型
	 */
	public static String dateTimeToString(DateTime d){
		String dateStr = null;
		String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter FORMATTER;
		FORMATTER = DateTimeFormat.forPattern(TIME_PATTERN);
		if(d!=null){
			dateStr = d.toString(FORMATTER);
		}
		return dateStr;
	}
}
