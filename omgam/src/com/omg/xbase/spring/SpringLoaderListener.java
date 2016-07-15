package com.omg.xbase.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;


public class SpringLoaderListener extends ContextLoaderListener implements  ServletContextListener {  
//	private ContextLoader contextLoader;  
//	 /** 
//     * Initialize the root web application context. 
//     */  
//    public void contextInitialized(ServletContextEvent event) {  
//        this.contextLoader = createContextLoader();  
//        if (this.contextLoader == null) {  
//            this.contextLoader = this;  
//        }  
//        this.contextLoader.initWebApplicationContext(event.getServletContext());  
//    }  
//    
//    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {  
//        if (servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null) {  
//            throw new IllegalStateException(  
//                    "Cannot initialize context because there is already a root application context present - " +  
//                    "check whether you have multiple ContextLoader* definitions in your web.xml!");  
//        }  
//      
//        try {  
//            // 第一步：Determine parent for root web application context, if any.  
//            ApplicationContext parent = loadParentContext(servletContext);  
//      
//            // 第二步：Store context in local instance variable, to guarantee that  
//            // it is available on ServletContext shutdown.  
//            this.context = createWebApplicationContext(servletContext, parent);  
//      
//                    //第三步  
//            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);  
//                     
//            ClassLoader ccl = Thread.currentThread().getContextClassLoader();  
//            if (ccl == ContextLoader.class.getClassLoader()) {  
//                currentContext = this.context;  
//            }  
//            else if (ccl != null) {  
//                currentContextPerThread.put(ccl, this.context);  
//            }  
//      
//            return this.context;  
//        }  
//        catch (RuntimeException ex) {  
//            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ex);  
//            throw ex;  
//        }  
//        catch (Error err) {  
//            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, err);  
//            throw err;  
//        }  
//        }  
//    
//    protected WebApplicationContext createWebApplicationContext(ServletContext sc, ApplicationContext parent) {  
//        //第一步  
//                Class<?> contextClass = determineContextClass(sc);  
//  
//                //第二步   
//        if (!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {  
//            throw new ApplicationContextException("Custom context class [" + contextClass.getName() +  
//                    "] is not of type [" + ConfigurableWebApplicationContext.class.getName() + "]");  
//        }  
//  
//                //第三步  
//        ConfigurableWebApplicationContext wac =  
//                (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);  
//  
//        //Assign the best possible id value.  
//        if (sc.getMajorVersion() == 2 && sc.getMinorVersion() < 5) {  
//            // Servlet <= 2.4: resort to name specified in web.xml, if any.  
//            String servletContextName = sc.getServletContextName();  
//            wac.setId(ConfigurableWebApplicationContext.APPLICATION_CONTEXT_ID_PREFIX +  
//                    ObjectUtils.getDisplayString(servletContextName));  
//        }  
//        else {  
//            // Servlet 2.5's getContextPath available!  
//            try {  
//                String contextPath = (String) ServletContext.class.getMethod("getContextPath").invoke(sc);  
//                wac.setId(ConfigurableWebApplicationContext.APPLICATION_CONTEXT_ID_PREFIX +  
//                        ObjectUtils.getDisplayString(contextPath));  
//            }  
//            catch (Exception ex) {  
//                throw new IllegalStateException("Failed to invoke Servlet 2.5 getContextPath method", ex);  
//            }  
//        }  
//  
//        wac.setParent(parent);  
//        wac.setServletContext(sc);  
//        wac.setConfigLocation(sc.getInitParameter(CONFIG_LOCATION_PARAM));  
//        customizeContext(sc, wac);  
//        wac.refresh();  
//        return wac;  
//    }  
    


}