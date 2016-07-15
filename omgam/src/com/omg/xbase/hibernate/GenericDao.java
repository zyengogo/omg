package com.omg.xbase.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface GenericDao<T,PK extends Serializable> {

	PK create(final T newInstance);

	T read(final PK id);

	void update(final T transientObject);

	/**
	 * 删除对象.
	 * 
	 * @param persistentObject 对象必须是session中的对象或含id属性的transient对象.
	 */
	void delete(final T persistentObject);

	/**
	 * 按id删除对象.
	 */
	void delete(final PK id) ;
	

	/**
	 *	获取全部对象.
	 */
	List<T> getAll();
	/**
	 *	获取全部对象.
	 */
	List<T> getAll(String orderBy, boolean isAsc);
	
	/**
	 * 按id列表获取对象.
	 */
	List<T> findByIds(List<PK> ids);

	/**
	 * 初始化对象.
	 * 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性.
	 * 如需初始化关联属性,可实现新的函数,执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	void initEntity(T entity);

	/**
	 * @see #initEntity(Object)
	 */
	void initEntity(List<T> entityList);

	/**
	 * 取得对象的主键名. 
	 */
	String getKeyName();
	
	T changeInheritanceSubclass(final Class<?> from,final Class<?> to,final PK id);
	
	T findUniqueBy(final String propertyName, final Object value);
	
	public Page<T> findPage(final Page<T> page, final Criterion... criterions);
	
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue);

	public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters);

}
