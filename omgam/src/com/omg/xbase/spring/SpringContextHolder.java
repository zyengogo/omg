package com.omg.xbase.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder  implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
//	@Autowired
//	private UserManageService userManageService;
	
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
		applicationContext.getClassLoader();
//		CheckInputRegisterUtil cu=new CheckInputRegisterUtil();
//		cu.run();
//		java.net.URL url = this.getClass().getClassLoader().getResource("WEB-INF/web.xml");
////		这是获取web-inf/classes 目录下的文件 
//		IsCancleNoteOut.isCancle(url);
//		System.out.println("-------------加载SpringContextHolder-----------------" );
//		UserInfor po=new UserInfor();
//		List<UserInfor> list = new ArrayList<UserInfor>();
//		list =  userManageService.getAllUserList(getUserStatusHQLStr(SystemConstants.INT_STATUS_OPEN), null, 0, 0);
		//TODO new 
//		System.out.println("-----------定时器开始启动------------");
//		Timer timer = new Timer();
//		timer.schedule(new CheckScheduleXmlUtil(),1,1);
//		System.out.println("-----------定时器启动结束------------");
		
		
		//启动定时器
//		CheckScheduleXmlUtil task = new CheckScheduleXmlUtil();
//		CheckScheduleXmlUtil.startTimerTask(task);
	
	}
	
	private String getUserStatusHQLStr(int _status) {
		String _HQL=null;
//		String _HQL = "from " + UserInfor.class.getName()
//		+ " pro  where  pro.status="+_status;
//		_HQL += "  order by pro.userName asc";
		return _HQL;
	}
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}
}
