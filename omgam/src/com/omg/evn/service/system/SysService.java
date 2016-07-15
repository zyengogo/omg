package com.omg.evn.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omg.evn.dao.BaseDao;
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
public class SysService {

	@Resource
	public BaseDao baseDao;
	public Log log = LogFactory.getLogger();
	
	/**
	*@Description: 保存对象
	*@param o
	*@throws Exception
	*@date 2014-8-1 上午10:33:16
	*@author zyen
	 */
	public void saveObject(Object o) throws Exception{
		baseDao.save(o);
	}
	
	public void saveList(List<Class> list){
		try {
			baseDao.saveOrUpdateAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	
    /**
    *@Description:创建对象同时返回该对象的主键
    *@param o
    *@return
    *@date 2014-8-1 上午10:34:15
    *@author zyen
     */
	public int saveByCreateObject(Object o){
		int num=baseDao.create(o);
		return num;
	}
	
	
  /**
    *@Description:根据bean的id查找实体
    *@param o
    *@return
    *@date 2014-8-1 上午10:34:15
    *@author zyen
     */
	public List findAllObject(Class entityClass){
		List list= this.baseDao.findAll(entityClass);
		return list;
	}
	
	
  /**
    *@Description:根据bean的id查找实体
    *@param o
    *@return
    *@date 2014-8-1 上午10:34:15
    *@author zyen
     */
	public Object findObjectByPK(Class entityClass,int id){
		Object user = baseDao.findById(entityClass, id);
		return user;
	}
	
	
	public List findByHQL(String hql, Object[] hqlParams){
		return baseDao.findByHQL(hql, hqlParams);
	}

	public List findByHQL(String hql) {
		return baseDao.findByHQL(hql);
	}

	public List findBySQL(String sql,Class entity) {
		return baseDao.findBySQL(sql,entity);
	}

	public Integer findCount(String hql) {
		return baseDao.findCount(hql);
	}
	
	public int bulkUpdate(String hql){
		return baseDao.bulkUpdate(hql);
	}

}
