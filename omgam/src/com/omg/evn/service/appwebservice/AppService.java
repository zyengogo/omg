package com.omg.evn.service.appwebservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.omg.evn.dao.BaseDao;
import com.omg.evn.entity.app.AppUser;
import com.omg.evn.util.sysutil.MD5;
import com.omg.evn.util.sysutil.PojoMapper;
import com.omg.xbase.xlog.Log;
import com.omg.xbase.xlog.LogFactory;

/**
 * @Title: SysService.java
 * @Description: SysService 系统管理基础Service
 * @author  zyen(zyengogo@163.com)
 * @date  2014-8-1 上午10:32:38
 * @最后修改人：zyen 
 * @最后修改时间：2014-8-1 上午10:32:38
 * @version  V1.0
 * @copyright: 小火炉技术团队
 */
@Service
@Transactional
public class AppService {

	@Resource
	public BaseDao baseDao;
	public Log log = LogFactory.getLogger();
	
	/**
	 * 
	 * @Description: 新增用户
	 * @author zyen
	 * @date 2015-5-2 上午11:27:25 
	 * @param @param o
	 * @param @throws Exception     
	 * @return void    
	 * @throws
	 */
	public void saveObject(Object o) throws Exception{
		baseDao.save(o);
	}
	
	/**
	 * 
	 * @Description: 检查是否有重复的手机号账号
	 * @author zyen
	 * @date 2015-5-2 上午10:32:26 
	 * @param @param userid
	 * @param @param username
	 * @param @return     
	 * @return int    
	 * @throws
	 */
	public int findtelephoneidCount(int userid,String telephoneid){
		String hqlCount="";
		//如果id为0时，新增数据时查询
		if(userid == 0){
			hqlCount = "select count(*)  from AppUser where telephone = '"+telephoneid+"'";
		//修改数据时查询
		}else {
			hqlCount = "select count(*)  from AppUser where telephone = '"+telephoneid+"'";
			hqlCount += " and userId <> "+userid;//排除自己
			log.info("findtelephoneidCount::::修改数据是，查询是否手机号重复，基本原则是手机号不会更改.");
		}
		return this.baseDao.findCount(hqlCount);//总数
	}
	
	/**
	 * 
	 * @Description: 用户登陆
	 * @author zyen
	 * @date 2015-5-2 上午10:52:22 
	 * @param @param telephoneid
	 * @param @param pwd
	 * @param @return
	 * @param @throws Exception     
	 * @return AppUser    
	 * @throws
	 */
	public AppUser loginapp(String telephoneid,String pwd) throws Exception{
		pwd = MD5.crypt(pwd);//密码加密
		String hql = "from AppUser u where u.telephone= :telephoneid and u.password=:pwd";
		Query q=this.baseDao.createQuery(hql,null);
		q.setString("telephone", telephoneid);
		q.setString("password", pwd);
		List<AppUser> beanList = (List<AppUser>)q.list();;
		if (beanList.size() > 0) {
			return beanList.get(0);
		}else {
			log.info("用户登陆失败，用户名或者密码错误.");
			return null;
		}
	}
	
	/**
	 * 
	 * @Description: 重置密码
	 * @author zyen
	 * @date 2015-5-2 上午11:27:05 
	 * @param @param telephoneid
	 * @param @param omgpwdset
	 * @param @return
	 * @param @throws Exception     
	 * @return String    
	 * @throws
	 */
	public String resetPassUserapp(String telephoneid,String omgpwdset) throws Exception {
		AppUser appUser = (AppUser) baseDao.findById(AppUser.class, Integer.parseInt(telephoneid));
		
		String _pwd="";
		if(omgpwdset.equals("1")){
			//如果是1，就系统默认密码
			_pwd="p123456";
		}else{
			_pwd="p"+Math.random()*900000+100000;
			_pwd=(String) _pwd.subSequence(0, _pwd.indexOf(".")-1);//随机密码
			log.info(appUser.getTelephone()+"用户名，更改为随机密码.");
		}
		appUser.setPassWord(MD5.crypt(_pwd+"$sccptss#"));//密码加密
		baseDao.update(appUser);
		return _pwd;
	}
	
	
	/**
	 * 
	 * @Description: 查询用户分页
	 * @author zyen
	 * @date 2015-5-2 上午11:34:53 
	 * @param @param pageNumber
	 * @param @param pageSize
	 * @param @return
	 * @param @throws Exception     
	 * @return String    
	 * @throws
	 */
	public String findpagelist(int pageNumber,int pageSize) throws Exception {
		String hqlData = "from AppUser  where 1=1 ";
		String hqlCount = "select count(*)  from AppUser  where 1=1 ";
		String condition = "";
		 
		hqlData  += condition;// 添加条件
		hqlCount += condition;
		hqlData  += " order by lastDate desc";//排序
		List<AppUser> appuserList = this.baseDao.findNowPageList(pageNumber, pageSize,hqlData);
		List list = new ArrayList();
		for(AppUser appUser : appuserList){
			list.add(appUser);
		}
		//总数
		int sum = this.baseDao.findCount(hqlCount);
		
		//将list数组转换成json对象
		String str = PojoMapper.toJsonArray(list, sum);
		return str;
	}
	
	/**
	 *@Description: 更新对象
	 *@param o
	 *@throws Exception
	 *@date 2014-8-1 上午10:33:16
	 *@author zyen
	 */
	public void upObject(Object o) throws Exception{
		baseDao.update(o);
	}

}
