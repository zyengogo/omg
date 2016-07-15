package com.omg.evn.action;



/**
 * @Description:常量类
 * @author:zyen
 * @date : 2014-2-14 下午02:45:07
 */
public class SystemConstants {
	//用户权限常量
	public static final String LOGIN= "login.action";
	public static  final String SYSTEM_USERINFO ="user";//session中用户信息
	public static  final String SYSTEM_AREA="uarea";//session中用户地区
	public static  final String SYSTEM_ROLE="urole";//session中用户角色
	
	public static  final String SYSTEM_ADMIN="admin";//超级管理员
	public static  final String SYSTEM_ISADMIN="isAdmin";//是否是超级管理员
	public static  final String SYSTEM_ISMANAGER="isManager";//是否是超级管理员
	public static  final String SYSTEM_COMMON="common";//是否是超级管理员
	public static  final String SYSTEM_OK="ok";
	public static  final String SYSTEM_NO="no";
	public static  final String SYSTEM_ADMIN_NO="1";
	public static  final String SYSTEM_LOIGN_ISCODE="codeerror";
	static public final String SYSTEM_LOIGN_SUCCESS="success";
	static public final String SYSTEM_LOIGN_ERROR="ERROR";
	
	static public final String SYSTEM_NO_HREF="nohref";
	static public final String SYSTEM_ZYK_AUDING="A";//资源库审核
	static public final String SYSTEM_ZYK_EDITOR="B";//资源库采编
	
	//权限常量
	public static final int SYSTEM_PRIVI_CLASS=4;
	public static final String SYSTEM_PRIVI_NOURLS="'1,3,6,8,18'";
	
	
	//CheckPrivilegeFilter权限参数对照
	public static final String DEBUG_MAP = "DEBUG_MAP";
	public static final String PRIV_SET = "PRIV_SET";
	public static final String FILE_WRITER = "FILE_WRITER";
	public static final String ITEM_TAG = "node1";
	public static final String fileName = "D:\\omgamlog\\PRIVILEGE_TEST_LOG.txt";
	public static final String REDIRECTURL = "/search/common/sysframe/quitaccess.jsp";
	public static final String REDIRECTURL2 = "/search/common/sysframe/quitaccess.jsp";
	
	
	//组织常量
	public static final int SYSTEM_GROUP_LEVEL=3;
	
	
	//模块配置列表
	public static  final String SYSTEM_MODULE = "SYSTEM_MODULE";
	public static  final String LOGIN_ACCESS_MODULE = "LOGIN_ACCESS_MODULE";
	public static  final int SYSTEM_MWZYKID=118;//民族资源信息库
	public static  final int SYSTEM_MWSWID=119;//民族事务管理与服务

	
	//----------------------------地区常量常量-----------------------------
	static public final Long    AREA_CODE_SHENG=510000L;
	static public final String  AREA_KUOQUANXIAN="扩权县";
	static public final String  AREA_NAME_PANZHIHUA="攀枝花市";
	static public final Integer AREA_ROOT_ID=224;//四川省
	static public final String  AREA_CACHE="areas";

	
	//------------------------------通用类-----------------------------
	static public final String DateFormat = "yyyy-MM-dd";
	static public final String TimeFormat = "yyyy-MM-dd HH:mm:ss";
	static public final String LONGIN_ERROR_MESSAGE = "login_error_message";
	
	//------------------------- 语言类型-----------------------------
	static public final String  LANGUAGE_ZH_CN = "zh_CN";
	static public final String  LANGUAGE_ZH_TW = "zh_TW";
	static public final String  LANGUAGE_EN = "en";
	

	
	//--------------------检索条件-------------------
	public static final String STATUS_AUD= "待开通";//1
	public static final String STATUS_OPEN = "已开通";//2
	public static final String STATUS_STOP = "已停用";//3
	public static final String STATUS_DEL = "已删除";//4
	
	public static final int INT_STATUS_AUD= 1;//1
	public static final int INT_STATUS_OPEN =2;//2
	public static final int INT_STATUS_STOP =3;//3
	public static final int  INT_STATUS_MOVE =4;//4
	
	//----------------------------交互应用检索状态-----------------------------
	public static final int  NO_HANDLE_STATUS=0;//0: 未审核
	public static final int  HAS_PUBLISH_STATUS=1;//1：已发布
	public static final int  INTO_GARBAGE_STATUS=2;//2: 回收站

	

	// ------------------------------权限类------------------------------------------------------------
	static public final String SYSTEM_PPMSColumnManage = "EVN";// 本系统栏目维护权限类型
	static public final String SYSTEM_PPMSColumnManage_NAME = "EVN";// 本系统栏目维护权限显示名称
	public static final String SYSTEM_POPEDOMTYPE = "EVN"; // 本系统功能权限类型,在LoginAction中会被调用
	public static final Long SECURITY_CLASS = 1L; // 安全分类
	public static final String ISCHARGE_YES = "是";//1，是否组长
	public static final String ISCHARGE_NO = "否";//0
	

	
	// ------------------------------LOG_USER错误-------------------------------------------------------
	public static final String USER_LOGIN_FAILED_1 = "验证码错误"; 
	public static final String USER_LOGIN_FAILED_2 = "用户不存在"; 
	public static final String USER_LOGIN_FAILED_3 = "密码错误"; 
	public static final String USER_LOGIN_FAILED_4 = "用户未开通"; 
	public static final String USER_LOGIN_FAILED_5 = "用户已停用"; 
	public static final String USER_LOGIN_FAILED_6 = "用户已删除"; 
	public static final String USER_LOGIN_FAILED_7 = "用户已在异地登录"; 
	
	// ------------------------------LOG_LEVEL------------------------------------------------------------
	public static final String LOG_LEVEL_1 = "信息"; 
	public static final String LOG_LEVEL_2 = "调试"; 
	public static final String LOG_LEVEL_3 = "警告"; 
	public static final String LOG_LEVEL_4 = "错误"; 
	
	public static final int LOG_LEVEL_int_1= 1; 
	public static final int LOG_LEVEL_int_2 = 2; 
	public static final int LOG_LEVEL_int_3 = 3; 
	public static final int LOG_LEVEL_int_4 = 4; 
	
	// ------------------------------LOG_OPER_RESULT------------------------------------------------------------
	public static final int OPER_SUCCESS =1; 
	public static final int OPER_FAILED =2; 
	public static final String OPER_SUCCESS_STR ="成功";  
	public static final String OPER_FAILED_STR ="失败";
	public static final String IS_SUCCESS ="SUCCESS";  
	public static final String IS_ERROR="ERROR"; 

	
	
	
	//----------------------------用户登录安全策略配置相关参数(标准)---------------------------------------
	public final static Integer INTEGERNUM_ONE=1;	
	public final static Integer INTEGERNUM_FOUR=4;	
	public final static Integer INTEGERNUM_ERROR=1001;	
	public final static Long LONGNUM_ONE=1L;	
	public final static String USERNAME="用户";	
	public final static String PASSWORDERROR="密码错误.";


	//----------------------------异常信息--------------------------------------
//	public static final String ActionEXCEPTION=">>Action异常:";  //
//	public static final String ServiceEXCEPTION=">>Service异常:";  //
//	public static final String DaoEXCEPTION=">>Dao异常:";  //
//	public static final String AppEXCEPTION=">>异常:";  //

	
	

  
}
