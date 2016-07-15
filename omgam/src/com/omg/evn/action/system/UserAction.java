package com.omg.evn.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.omg.evn.action.SystemConstants;
import com.omg.evn.entity.system.Sysuser;
import com.omg.evn.service.system.UserService;
import com.omg.evn.util.sysutil.MD5;
import com.omg.xbase.struts.Struts2Utils;
import com.omg.xbase.struts.WebUtils;
import com.omg.xbase.xlog.Log;
import com.omg.xbase.xlog.LogFactory;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @Title: UserAction.java
 * @Description: UserAction 用户管理
 * @author  zyen(zyengogo@163.com)
 * @date  2014-8-4 下午4:20:40
 * @最后修改人：zyen 
 * @最后修改时间：2014-8-4 下午4:20:40
 * @version  V1.0
 * @copyright: 小火炉技术团队
 */
@Results({
	@Result(name = "usermanager" , location="/WEB-INF/content/system/user/index.jsp"),
	@Result(name = "loginreturn" , location = "/WEB-INF/content/homepage.jsp"),
	@Result(name = "returntop" , location = "/WEB-INF/content/homepage.jsp"),
	@Result(name = "login" , location = "/WEB-INF/content/login.jsp"),
	@Result(name = "mylogin" , location = "/WEB-INF/content/mylogin.jsp"),
	@Result(name = "error" , location = "/WEB-INF/content/error.jsp")
})
//@ExceptionMappings({
//    @ExceptionMapping(exception = "java.lang.NullPointerException", result = "error", params = {"message", "操作失败！"})
//})

public class UserAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	private Log log = LogFactory.getLogger();
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String type;	//页面跳转类型
	private Sysuser user;	//用户
	private int id;
	private String param;
	private String realname;
	private String username;
	private int pageNumber;	//当前页码
	private int pageSize;	//每页记录数
	private String objs;

	@Resource
	private UserService userService;
	
	/**
	 * @Description:页面跳转
	 * @param: type 跳转类型
	 * @return: 返回
	 * @throws: 
	 */
	public String jump(){
		
	    String flagl=(String)WebUtils.getSession().getAttribute("flagl");
		
	    if(flagl==null){
	    	return "login";
	    }
	  	int a=Integer.valueOf(flagl);
	    
		if("manager".equals(type)){
			return "usermanager";//管理首页
		}else if ("loginout".equals(type)) {
			request.getSession().setAttribute("user", null);
//			if(a==1){
//				return "login";//管理首页
//			}
//			if(a==2){
//				return "mylogin";
//			}
			
			return "mylogin";
		}else if ("returntop".equals(type)) {
			return "returntop";//管理首页
		}
		return "login";
	}
	
	/**
	 * @Description: 用户搜索
	 * @param: pageNumber 当前页码
	 * @param: pageSize   每页显示记录数
	 * @param: realname	   姓名查询
	 * @return: 返回
	 * @throws: 
	 */
	public String findUserPageList() {
		String str ="";
		try {
			str = userService.findUserPageList(pageNumber,pageSize,realname);
		} catch (Exception e) {
			str = "error";
			log.error("#UserAction>>findUserPageList():"+e);
			e.printStackTrace();
		}
		Struts2Utils.renderJson(str);
		return null;
		
	}
	public void getAllUserList () {
		
	}
	/**
	 * @Description: 用户搜索+组过滤
	 * @param: pageNumber 当前页码
	 * @param: pageSize   每页显示记录数
	 * @param: realname	   姓名查询
	 * @return: 返回
	 * @throws: 
	 */
	public String findUserPageListNew() {
		String str ="";
		try {
			str = userService.findUserPageListNew(pageNumber,pageSize,realname);
		} catch (Exception e) {
			str = "error";
			log.error("#UserAction>>findUserPageList():"+e);
			e.printStackTrace();
		}
		Struts2Utils.renderJson(str);
		return null;
		
	}
	
	
	/**
	 * 根据id查询
	 * @Description: TODO()
	 * @param: id 主键id
	 * @return: 返回
	 * @throws: 
	 */
	public String findUserByPK() {
		
		String str ="";
		try {
			str = userService.findUserByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UserAction>>findUserByPK():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
		
	}
	
	/**
	 * 检查用户名是否存在
	 * @Description: TODO()
	 * @param: param 用户名
	 * @return: 返回
	 * @throws: 
	 */
	public String checkUserName(){
		Object str;
		try {
			int sum = userService.findUsernameCount(0,param);//检验是否存在
			Map mesage = new HashMap();
			if(sum<1){
				mesage.put("info", "通过验证！");
				mesage.put("status", "y");
			}else{
				mesage.put("info", "用户名已存在！");
				mesage.put("status", "n");
			}
			str =mesage;
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UserAction>>checkUserName():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
	}
	
	/**
	 * 检查密码是否正确
	 * @Description: TODO()
	 * @param: 检查
	 * @return: 返回
	 * @throws: 
	 */
	public String checkUserPassword(){
		Object str;
		try {
			Sysuser user = (Sysuser) request.getSession().getAttribute("user");
			int userId = user.getId();
			int sum = userService.checkUserPassword(userId,param);//检验是否存在
			Map mesage = new HashMap();
			if(sum == 1){
				mesage.put("info", "通过验证！");
				mesage.put("status", "y");
			}else{
				mesage.put("info", "密码错误！");
				mesage.put("status", "n");
			}
			str =mesage;
			
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UserAction>>checkUserPassword():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
	}
	
	/**
	 * @Title: saveUser 
	 * @Description:  用户 新增
	 * @return void 	返回类型
	 * @throws
	 */
	public String saveUser(){
		String str ="";
		Integer areaid=Integer.valueOf(Struts2Utils.getRequest().getParameter("objs"));
		try {
			if (!"".equals(user.getName())) {
				int sum = userService.findUsernameCount(user.getId(),user.getName());//检验是否存在
				if(sum < 1){

					Integer userpk=userService.createUser(user);
					/*List<SiteEntity> siteList = this.siteService.getList(SiteEntity.class);
					if(user.getIsManager().equals("1")){
						for(SiteEntity site:siteList) {
							SiteUserEntity sue = new SiteUserEntity();
							sue.setSiteId(Integer.valueOf(site.getId()));
							sue.setUserId(userpk);
							this.baseDao.save(sue);
						}
					}*/
					str = "ok";
				}else {
					str = "username";
				}
			}else {
				str = "username";
			}
			
//			throw new NullPointerException();
		} catch (Exception e) {
			str = "error";
			log.error("#UserAction>>saveUser():"+e);
			e.printStackTrace();
			
		}
		Struts2Utils.renderJson(str);
		return str;
		
	}
	
	/**
	 * @Description: 用户 删除
	 * @return void 返回类型
	 * @throws
	 */
	public String deleteUser(){
		String str ="";
		int result = 0;
		try {
			String ids = request.getParameter("ids");
			if (ids != null && !"".equals(ids)) {
				//不能删除管理员
				boolean existAdmin = false;
				String [] idArr = ids.split(",",-1);
				for (String id : idArr) {
					if (SystemConstants.SYSTEM_ADMIN_NO.equals(id)) {
						existAdmin = true;
					}
				}
				//不存在管理员
				if (!existAdmin) {
					result = userService.deleteUser(ids);
					//siteService.delUserFromSite(ids);
				}
			}
			if (result > 0) {
				str = "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
			str = "error";
			log.error("#UserAction>>deleteUser():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
		
	}
	
	/**
	 * 重置用户密码
	 * @Description: TODO() 
	 * @return
	 * @throws
	 */
	public String resetPassUser(){
		
		String str ="";
		try {
			String id = request.getParameter("id");
			boolean flag = userService.resetPassUser(id);
			if (flag) {
				str = "ok";
			}else {
				str = "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("#UserAction>>resetPassUser():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;
		
	}
	
	/**
	 * @throws Exception 
	 * 用户 修改
	 * @Description: TODO() 
	 * @return
	 * @throws
	 */
	public String updateUser(){
		//System.out.println("---------------------------");
		String str ="";
//		Integer areaid=Integer.valueOf(Struts2Utils.getRequest().getParameter("objs"));
	
		Integer areaid=0;
		if(Struts2Utils.getRequest().getParameter("objs")!=null&&!"".equals(Struts2Utils.getRequest().getParameter("objs"))){
			areaid=Integer.valueOf(Struts2Utils.getRequest().getParameter("objs"));
		}
		try {
			Sysuser bean = null;
			bean = (Sysuser) userService.findById(Sysuser.class, user.getId());
			if(bean!=null){			
				//bean.setUsername(user.getUsername());//用户名
				
				if(user.getPassword()!=null&&!"".equals(user.getPassword())){
					bean.setPassword(MD5.crypt(user.getPassword()));//密码	
				}
				if(user.getPhone()!=null&&!"".equals(user.getPhone())){
					bean.setPhone(user.getPhone());//联系电话
				}
				if(user.getDepart()!=null&&!"".equals(user.getDepart())){
					bean.setDepart(user.getDepart());//联系电话
				}
				if(user.getRealname()!=null&&!"".equals(user.getRealname())){
					bean.setRealname(user.getRealname());//真实姓名
				}
				if(user.getIsManager()!=null){
					bean.setIsManager(user.getIsManager());
				}
				int b=areaid;
				
				
				userService.update(bean);
				WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_USERINFO, bean);
				str =SystemConstants.SYSTEM_OK;
			}
			
		} catch (Exception e) {
			str="error";
			e.printStackTrace();
			log.error("#UserAction>>updateUser():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;	
		
	}
	public String updateUserNew(){
		
		String str ="";
		Integer areaid=0;
		if(Struts2Utils.getRequest().getParameter("objs")!=null&&!"".equals(Struts2Utils.getRequest().getParameter("objs"))){
			areaid=Integer.valueOf(Struts2Utils.getRequest().getParameter("objs"));
		}
		
		
		try {
			Sysuser bean = null;
			bean = (Sysuser) userService.findById(Sysuser.class, user.getId());
			if(bean!=null){			
				//bean.setUsername(user.getUsername());//用户名
				
				if(user.getPassword()!=null&&!"".equals(user.getPassword())){
					bean.setPassword(MD5.crypt(user.getPassword()));//密码	
				}
				if(user.getPhone()!=null&&!"".equals(user.getPhone())){
					bean.setPhone(user.getPhone());//联系电话
				}
				if(user.getDepart()!=null&&!"".equals(user.getDepart())){
					bean.setDepart(user.getDepart());//联系电话
				}
				if(user.getRealname()!=null&&!"".equals(user.getRealname())){
					bean.setRealname(user.getRealname());//真实姓名
				}
				
				int b=areaid;
				
				
				
				userService.update(bean);
				WebUtils.getSession().setAttribute(SystemConstants.SYSTEM_USERINFO, bean);
				str =SystemConstants.SYSTEM_OK;
			}
			
		} catch (Exception e) {
			str="error";
			e.printStackTrace();
			log.error("#UserAction>>updateUser():"+e);
		}
		Struts2Utils.renderJson(str);
		return null;	
	}
	
	

	
	
	

	//***********************************ROLE/USER*****************************************
	
		
	
	
	
	
	
	
	
	
	
	
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Sysuser getUser() {
		return user;
	}
	public void setUser(Sysuser user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public String getObjs() {
		return objs;
	}

	public void setObjs(String objs) {
		this.objs = objs;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}