package com.omg.evn.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.omg.evn.dao.BaseDao;
import com.omg.evn.service.common.CommonServiceI;
/**
 * CommonService接口实现
 */
@Service
@Transactional
public class CommonServiceImpl implements CommonServiceI{
	@Resource
	private BaseDao baseDao;
	
	public void saveEntity(Object o) {
		// TODO Auto-generated method stub
		baseDao.save(o);
	}

	public void delEntity(Object o) {
		// TODO Auto-generated method stub
		baseDao.delete(o);
	}

	public void updateEntity(Object o) {
		// TODO Auto-generated method stub
		baseDao.update(o);
	}
	
	public List getList(Class entityClass) {
		// TODO Auto-generated method stub
		List list = baseDao.findAll(entityClass);
		return list;
	}

}
