package com.omg.evn.service.baseservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.omg.evn.dao.BaseDao;



/**
 * @description 
 * @version 1.0
 * @author 
 * @update 2013-08-05
 */
@Service
public class BaseService{

	@Resource
	private BaseDao baseDao;
	
	
	
	

	/**
	 * 保存一个实体对象
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @return: 无
	 */
	public void save(Object o) {
		baseDao.save(o);
	}

	
	
	/**
	 * 修改一个实体对象
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @return: 
	 */
	public void update(Object o) {
		baseDao.update(o);
	}

	/**
	 * 保存或者更新
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @return: 
	 */
	public void saveOrUpdate(Object o)  {
		baseDao.saveOrUpdate(o);
	}
	
	/**
	 * 删除
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @return: 
	 */
	public void delete(Object o) {
		baseDao.delete(o);
	}

	/**
	 * 根据id查询
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @param: id 主键id
	 * @return: 
	 */
	public Object findById(Class str, Integer id){
		return baseDao.findById(str, id);
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

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
}
