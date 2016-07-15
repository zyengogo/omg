package com.omg.xbase.struts;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * @author bromon
 */
public class BeanUtil {
	public static ServletContext sc = Struts2Utils.getSession().getServletContext();
	public static ApplicationContext ctx =  WebApplicationContextUtils.getWebApplicationContext(sc);
    public synchronized static Object getBean(String id){
        return ctx.getBean(id);
    }

    public static boolean containsBean(String id){
        return ctx.containsBean(id);
    }
}
