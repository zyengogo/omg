package com.omg.evn.service.common;

import java.util.List;

/**
 * 公用service接口
 * @author 魏正东
 *
 */
public interface CommonServiceI {
	/**
	 * 保存实体
	 * @param entityClass
	 * @return boolean
	 */
	public void saveEntity(Object o);
	/**
	 * 删除实体
	 * @param entityClass
	 * @return boolean
	 */
	public void delEntity(Object o);
	/**
	 * 修改实体
	 * @param entityClass
	 * @return boolean
	 */
	public void updateEntity(Object o);
	/**
	 * 查询所有list
	 * @param entityClass
	 * @return list
	 */
	public List getList(Class entityClass);

}
