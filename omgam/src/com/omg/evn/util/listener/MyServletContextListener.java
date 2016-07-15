package com.omg.evn.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
	private ServletContext context = null;

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("application is contextDestroyed......");
	}

	public void contextInitialized(ServletContextEvent event) {
		System.out.println("application is Initialized......");
		System.out.println("将站点信息放入 application");
	}

}
