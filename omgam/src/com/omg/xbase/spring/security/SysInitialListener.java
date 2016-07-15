package com.omg.xbase.spring.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.omg.evn.action.SystemConstants;
import com.omg.evn.entity.system.Sysuser;
import com.omg.evn.util.fileutil.ReadProperties;
import com.omg.evn.util.sysutil.MD5;
import com.omg.xbase.spring.security.utils.SysInitialPara;

/**
 * @Title: SysInitialListener.java
 * @Description: SysInitialListener TODO(这里用一句话描述这个类的作用)
 * @author  zyen(zyengogo@163.com)
 * @date  2014-7-28 下午4:28:22
 * @最后修改人：zyen 
 * @最后修改时间：2014-7-28 下午4:28:22
 * @version  V1.0
 * @copyright: 小火炉技术团队
 */
public class SysInitialListener implements ServletContextListener{

	private ServletContext context = null;  
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("###########################################################\n");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时间："+df.format(new Date()));
		System.out.println("服务停止......");
		System.out.println("###########################################################");
	}
	
	public void contextInitialized(ServletContextEvent event) {
	    SysInitialPara sysInitialPara =new SysInitialPara();
	    sysInitialPara.setPowerself(ReadProperties.getSystemValue("XCDMSPOWER"));//自定义权限读取
		System.out.println("服务启动......"+"\n");
	}
	
	

}

	
	