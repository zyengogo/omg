package com.omg.evn.service.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omg.evn.dao.BaseDao;
import com.omg.evn.entity.system.Sysuser;
import com.omg.evn.service.baseservice.BaseService;
import com.omg.evn.util.strutil.StrUtil;
import com.omg.evn.util.sysutil.MD5;
import com.omg.evn.util.sysutil.PojoMapper;

/**
 * @Title: UserService.java
 * @Description: UserService 用户管理
 * @author  zyen(zyengogo@163.com)
 * @date  2014-8-1 上午10:29:55
 * @最后修改人：zyen 
 * @最后修改时间：2014-8-1 上午10:29:55
 * @version  V1.0
 * @copyright: 小火炉技术团队
 */
@Service
@Transactional
public class UserService extends BaseService{
	
	@Resource
	private BaseDao baseDao;

	/**
	*@Description: 用户查询
	*@param pageNumber
	*@param pageSize
	*@param _str
	*@return
	*@throws Exception
	*@date 2014-8-1 上午10:30:41
	*@author zyen
	 */
	public String findUserPageList(int pageNumber,int pageSize,String _str) throws Exception {
		String hqlData = "from Sysuser  where 1=1 ";
		String hqlCount = "select count(*)  from Sysuser  where 1=1 ";
		String condition = "";
		// 查询条件
		if (_str != null && !"".equals(_str)) {
			condition += " and  id not in(" + _str + ")";
		}
		hqlData  += condition;// 添加条件
		hqlCount += condition;
		hqlData  += " order by id asc";//排序
		List<Sysuser> userList = this.baseDao.findNowPageList(pageNumber, pageSize,hqlData);
		List list = new ArrayList();
		for(Sysuser user : userList){
			list.add(user);
		}
		//总数
		int sum = this.baseDao.findCount(hqlCount);
		
		//将list数组转换成json对象
		String str = PojoMapper.toJsonArray(list, sum);
		return str;
	}

	public String findUsersByExclude(int pageNumber,int pageSize,String username,String _str) throws Exception {
		String hqlData = "from Sysuser  where 1=1 ";
		String hqlCount = "select count(*)  from Sysuser  where 1=1 ";
		String condition = "";
		// 查询条件
		if (username != null && !"".equals(username.trim())&&!"undefined".equals(username.trim())) {
//			condition += " and realname like '%" + username.trim() + "%'";
			condition += " and name like '%" + username.trim() + "%'";
		}
		if (_str != null && !"".equals(_str)) {
			condition += " and  id not in(" + _str + ")";
		}
		hqlData  += condition;// 添加条件
		hqlCount += condition;
		hqlData  += " order by id desc";//排序
		List<Sysuser> userList = this.baseDao.findNowPageList(pageNumber, pageSize,hqlData);
		List list = new ArrayList();
		for(Sysuser user : userList){
			list.add(user);
		}
		//总数
		int sum = this.baseDao.findCount(hqlCount);
		
		//将list数组转换成json对象
		String str = PojoMapper.toJsonArray(list, sum);
		return str;
	}
	
	
	public String findUserPageListNew(int pageNumber,int pageSize,String _str) throws Exception {
		String hqlData = "from Sysuser  s  where 1=1 ";
		String hqlCount = "select count(*)  from Sysuser   s where 1=1 ";
		String condition = "";
		if (StrUtil.isNotEmpty(_str)) {
//			condition += " and  s.realname  like '%" + _str.trim() + "%'";
			condition += " and  s.name  like '%" + _str.trim() + "%'";
		}
		hqlData  += condition;// 添加条件
		hqlCount += condition;
		hqlData  += " order by s.id desc";//排序
		List<Sysuser> userList = this.baseDao.findNowPageList(pageNumber, pageSize,hqlData);
		List list = new ArrayList();
		for(Sysuser user : userList){
			list.add(user);
		}
		//总数
		int sum = this.baseDao.findCount(hqlCount);
		
		//将list数组转换成json对象
		String str = PojoMapper.toJsonArray(list, sum);
		return str;
	}
	/**
	 * 
	 * 用户 根据id查询
	 * @Title: getUserById 
	 * @Description: TODO()
	 * @param id
	 * @return 
	 * @return User 返回类型
	 * @throws Exception 
	 */
	public String findUserByPK(int id) throws Exception {
		Sysuser user = (Sysuser) baseDao.findById(Sysuser.class, id);
		List list = new ArrayList();
		list.add(user);
		String str = PojoMapper.toJsonArray(list);
		return str;
	}

	/**
	 * @Title: saveUser 
	 * @Description:用户 新增
	 * @param ut
	 * @return 
	 * @return boolean 返回类型
	 * @throws
	 */
	public void saveUser(Sysuser bean) throws Exception {
		bean.setPassword(MD5.crypt(bean.getPassword()+"$sccptss#"));//密码加密
		baseDao.save(bean);
	}
	
	
	/**
	 * @Title: createUser 
	 * @Description:用户 新增
	 * @param ut
	 * @return 
	 * @return boolean 返回类型
	 * @throws
	 */
	public Integer createUser(Sysuser bean) throws Exception {
		bean.setPassword(MD5.crypt(bean.getPassword()+"$sccptss#"));//密码加密
		return baseDao.create(bean);
	}

	/**
	 * 用户 删除
	 * @Title: deleteUser 
	 * @Description: TODO()
	 * @param ids
	 * @return 
	 * @return boolean 返回类型
	 * @throws
	 */
	public int deleteUser(String ids) throws Exception {
		int result = 0;
		if (ids!=null && !"".equals(ids)) {
			result = baseDao.bulkUpdate("delete from Sysuser u where u.id in("+ids+")");
		}
		return result;
	}
	
	/**
	 * 重置用户密码
	 * @Title: resetPassUser 
	 * @Description: TODO()
	 * @param id
	 * @return 
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean resetPassUser(String id) throws Exception {
		Sysuser bean = (Sysuser) baseDao.findById(Sysuser.class, Integer.parseInt(id));
		bean.setPassword(MD5.crypt("123456"+"$sccptss#"));//密码加密
		baseDao.update(bean);
		return true;
	}

	/**
	 * 用户 修改
	 * @Title: updateUser 
	 * @Description: TODO()
	 * @param ut
	 * @return 
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean updateUser(Sysuser bean) throws Exception {
		Sysuser bean_1 = (Sysuser) baseDao.findById(Sysuser.class, bean.getId());
		//username;//用户名
		//userpassword;//密码
		/*sysuser.setUsername(bean.getUsername())
		sysuser.set
		sysuser.set
		sysuser.set
		sysuser.set
		userphone;//手机
		telephone;//联系电话
		email;//邮箱
		realname;//真实姓名
		department;//部门名称
		bean.setUserpassword(bean_1.getUserpassword());//不修改密码
		bean.setUsername(bean_1.getUsername());*/
		baseDao.saveOrUpdate(bean);
		return true;
	}
		
	/**
	 * 查询用户名 是否存在
	 * @Description: TODO() 
	 * @param userid
	 * @param username
	 * @return 当前用户名存在数量
	 * @throws
	 */
	public int findUsernameCount(int userid,String username){
		String hqlCount="";
		//如果id为0时，新增数据时查询
		if(userid == 0){
			hqlCount = "select count(*)  from Sysuser where name = '"+username+"'";
		//修改数据时查询
		}else {
			hqlCount = "select count(*)  from Sysuser where name = '"+username+"'";
			hqlCount += " and id <> "+userid;//排除自己
		}
		return this.baseDao.findCount(hqlCount);//总数
	}
	public int countuseer(String sql) throws Exception{
	    int i=baseDao.findCount(sql);
		return i;
	}
	
	/**
	 * 查询登录用户的密码是否正确
	 * @Description: TODO()
	 * @param  password
	 * @return
	 * @throws Exception 
	 * @return int 返回类型
	 * @throws
	 */
	public int checkUserPassword(int userid,String password) throws Exception{
		String hqlCount="";
		//如果id为0时，新增数据
		String md5password = MD5.crypt(password);//密码加密
		hqlCount = "select count(*)  from Sysuser where password= '"+md5password+"'";
		hqlCount += " and id= "+userid;//排除自己
		return this.baseDao.findCount(hqlCount);//总数
	}
	
	/**
	 * 
	*@Description: 	 用户登录
	*@param username
	*@param password
	*@return
	*@throws Exception
	*@date 2014-7-25 上午10:19:36
	*@author zyen
	 */
	public Sysuser login(String username,String password) throws Exception{
		try {
			//第一次使用创建默认管理员
			String hqlCount = "select count(*)  from Sysuser where name='admin'";
			int count = this.baseDao.findCount(hqlCount);//总数
			if (count < 1) {
				Sysuser sysuser = new Sysuser();
				sysuser.setName("admin");
				sysuser.setRealname("管理员");
				sysuser.setPassword(MD5.crypt("123456"+"$sccptss#"));
				sysuser.setIsManager("1");
				baseDao.save(sysuser);
			}
		} catch (Exception e) { 
		}
		password = MD5.crypt(password);//密码加密
		//String hql = "from Sysuser u where u.name= :name and u.password=:password";
		String hql = "from Sysuser u where u.name= :name ";
		Query q=this.baseDao.createQuery(hql,null);
		q.setString("name", username);
		List<Sysuser> beanList = (List<Sysuser>)q.list();;
		if (beanList.size() > 0) {
			return beanList.get(0);
		}else {
			return null;
		}
	}
	
}
