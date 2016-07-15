package com.omg.evn.dao;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.omg.evn.util.dbutil.DBUtil;
import com.omg.xbase.hibernate.Page;
import com.omg.xbase.xexp.AppException;

/**
 * @description BaseDao 通用基础DAO
 * @version: 1.0
 * @author: xcdms
 */
public class BaseDao extends HibernateDaoSupport {
	private Log log = LogFactory.getLog(BaseDao.class);
	private Session daoSession;
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	/**********************************保存***************************************/
	
	
	/**
	 * @Description: 保存一个实体对象
	 * @param: Object实体对象
	 * @return: 无
	 */
	public void save(Object o) {
		Session tempSession = this.getDaoSession();
		tempSession.setFlushMode(FlushMode.COMMIT);
		getHibernateTemplate().save(o);
		tempSession.flush();
		tempSession.clear();
		
	}
	

	/**
	 * @Description:保存或者更新
	 * @param: Object实体对象
	 * @return: 
	 */

	public void saveOrUpdate(Object o){
		
			getHibernateTemplate().saveOrUpdate(o);
		
	}
	
	
	/**
	 * @Description: 保存一个实体对象
	 * @param: Object实体对象
	 * @return: 返回主键
	 */
	public Integer create(Object o) {
		
		Integer pk=(Integer)getHibernateTemplate().save(o);

		return pk;
	}
	
	
	/**********************************更新***************************************/
	/**
	 * 更新实体对象
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @return: 
	 */
	public void update(Object o) {
		Session tempSession = this.getDaoSession();
		tempSession.setFlushMode(FlushMode.COMMIT);
		this.getHibernateTemplate().update(o);
		tempSession.flush();
		tempSession.clear();
	}
	
	
	
	/**********************************删除***************************************/
	/**
	 * 删除
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @return: 
	 */
	public void delete(Object o) {
		
		Session tempSession = this.getDaoSession();
		tempSession.setFlushMode(FlushMode.COMMIT);
		this.getHibernateTemplate().delete(o);
		tempSession.flush();
		tempSession.clear();
		
	}
	
	/**
	 * 根据id删除
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @param: id 主键id
	 * @return: 
	 */
	public void delById(Class entityClass, int id) {
		Object obj = this.getHibernateTemplate().get(entityClass, id);
		this.delete(obj);
		
	}
	
	


	
	/**********************************批量增删改查***************************************/
	
	 
	public int bulkUpdate(String hql) {
		int num = this.getHibernateTemplate().bulkUpdate(hql);
		return num;
	}

	/**
	 * 批量删除或更新
	 * @Description: TODO()
	 * @param: hql hql语句
	 * @param: value 条件值
	 * @return: int 返回成功记录数
	 * @throws: 
	 */
	public int bulkUpdate(String hql, Object value) {
		
		int num = this.getHibernateTemplate().bulkUpdate(hql,value);
		this.getHibernateTemplate().flush();
		return num;
		
	}

	/**
	 * 批量删除或更新
	 * @Description: TODO()
	 * @param: hql hql语句
	 * @param: values 条件值
	 * @return: int 返回成功记录数
	 * @throws: 
	 */
	public int bulkUpdate(String hql, Object[] values) {
		
		int num = this.getHibernateTemplate().bulkUpdate(hql,values);
		this.getHibernateTemplate().flush();
		return num;
		
	}
	
	/**
	 * 批量保存实体
	 * @Description: TODO()
	 * @param: hql hql语句
	 * @param: values 条件
	 * @return: int 返回成功记录数
	 * @throws: 
	 */
	public void saveOrUpdateAll(Collection list){
		
		this.getHibernateTemplate().saveOrUpdateAll(list);
		this.getHibernateTemplate().flush();
		
	}
	
	
	
	/**********************************自定义查询***************************************/
	
	/**
	 * 查询总数
	 * @Description: TODO()
	 * @param: hql hql语句
	 * @return:int 返回查询记录数
	 * @throws: 
	 */
	public Integer findCount(String hql){
		final String hql_1 = hql ;
		Number count = (Number)this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql_1);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});	
		if(count != null){
			return count.intValue();
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 查询总数,返回双精度
	 * @Description: TODO()
	 * @param: 参数
	 * @return: 返回
	 * @throws: 抛出异常
	 */
	public Double findCountDouble(String hql){
		final String hql_1 = hql ;
		Number count = (Number)this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql_1);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});	
		if(count != null){
			return count.doubleValue();
		}else{
			return null;
		}
	}
	
	/**
	 * 查询一条记录
	 * @Description: TODO()
	 * @param: hql hql语句
	 * @return: 返回查询结果的Object对象
	 * @throws: 
	 */
	
	public Object findObject(String hql){
		final String hql_1 = hql ;
		Object o = this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql_1);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});	
		return o;
	}
	
	/**
	 * 自定义hql(静态)查询
	 * @Description: TODO()
	 * @param: hql hql语句
	 * @return: List 返回查询结果的list集合
	 * @throws: 
	 */
	public List findByHQL(String hql){
		return this.getHibernateTemplate().find(hql);
		
	}
	
	/**
	 * 自定义hql(动态)查询
	 * @Description: TODO()
	 * @param: hql hql语句
	 * @return: List 返回查询结果的list集合
	 * @throws: 
	 */
	public List findByHQL(String hql, Object[] values){
		
		return this.getHibernateTemplate().find(hql, values);
		
	}


	/**********************************根据实体属性查询***************************************/
	
	
	/**
	 * 根据id查询
	 * @Description: TODO()
	 * @param: o 实体对象
	 * @param: id 主键id
	 * @return: 
	 */
	public Object findById(Class entityClass, Serializable id) {
		
		return this.getHibernateTemplate().get(entityClass, id);
		
	}
	
	
	/**
	 * 通过实体Bean任意属性查询所有满足属性值的记录
	 * @param hql语句
	 * @param hql的条件
	 * @return 返回list
	 * @throws Exception
	 */
	public List findListByProperty(Class entityClass, String propertyName, Object value) {
		
		Assert.hasText(propertyName);
		return createCriteria(entityClass,
				new Criterion[] { Restrictions.eq(propertyName, value) }).list();
		
	}
	
	/**
	 * 通过实体Bean任意属性查询所有满足属性值的记录，增加了自定义排序规则
	 * @param hql语句
	 * @param hql的条件
	 * @return 返回list
	 * @throws Exception
	 */
	public List findListByProperty(Class entityClass, String propertyName, Object value,
			String orderBy, boolean isAsc) {
		
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(entityClass, orderBy, isAsc,
				new Criterion[] { Restrictions.eq(propertyName, value) }).list();
		
	}
	
	/**
	 * 通过实体Bean任意属性查询所有满足属性值的记录，返回uniqueResult
	 * @param hql语句
	 * @param hql的条件
	 * @return 返回uniqueResult
	 * @throws Exception
	 */
	public Object findUniqueByProperty(Class entityClass, String propertyName,Object value) {
		
		Assert.hasText(propertyName);
		return createCriteria(entityClass,new Criterion[] { Restrictions.eq(propertyName, value) }).uniqueResult();
		
	}
	
	
	/**********************************分页查询***************************************/
	
	/**
	 * 动态分页自定义hql查询
	 * @param page service自定义page,分页参数封装
	 * @param hql service自定义hql
	 * @return 返回Page对象
	 */
	public Page paginationByAll(Page page, String hql){
		
		log.debug("====================query-begin " +  new Date().toString()  + "====================");
		Session tempSession = getDaoSession();
		//总数
		long count =0;
		String fromHql = hql;
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");
		String countHql = "select count(*) " + fromHql;
		List result = tempSession.createQuery(countHql).list();
        if (result != null && result.size() > 0 && result.get(0)!=null) {
            count = ((Long) result.get(0)).longValue();
        }	
		int startIndex = page.getPageNo()<=1 ? 0 : (page.getPageNo()-1)*page.getPageSize();
		List list = new ArrayList(); 
		Query query  = tempSession.createQuery(hql);
		query.setMaxResults(page.getPageSize());
		query.setFirstResult(startIndex);
		list = query.list();
		page.setTotalCount(Long.valueOf(count));
		page.setResult(list);
		log.debug("====================query-end " +  new Date().toString()  + "====================");
		return page;
		
	}
	
	/**
	 * 查询第N页数据
	 * @Description: TODO()
	 * @param pageNumber 当前页数（第pageNumber页）
	 * @param pageSize   当前页条数（本页pageSize条）
	 * @param hqlParams  查询条件
	 * @param shql  eg:(String shql = " from entity order by KeyID desc";)
	 * @return list 返回查询结果的list集合
	 * @throws: 
	 */
	public List findNowPageList(int pageNumber, int pageSize, String hql, Object[] hqlParams) {
		final int pageNumber1 = pageNumber;
		final int pageSize1 = pageSize;
		final String hql1 = hql;
		final Object[] hqlParams1 = hqlParams;
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createQuery(hql1);
				//条件
				if (hqlParams1 != null && hqlParams1.length > 0) {
					for (int i = 0; i < hqlParams1.length; i++) {
						query.setParameter(i, hqlParams1[i]);
					}
				}
				query.setMaxResults(pageSize1);
				query.setFirstResult((pageNumber1 - 1) * pageSize1);
				List result = query.list();
				if (!Hibernate.isInitialized(result)) Hibernate.initialize(result);
				return result;
			}
		});		
	}
	
	
	/**
	 * 查询第N页数据
	 * @Description: TODO()
	 * @param pageNumber 当前页数（第pageNumber页）
	 * @param pageSize 当前页条数（本页pageSize条）
	 * @param shql eg:(String shql = " from entity order by KeyID desc";)
	 * @return list 返回查询结果的list集合
	 * @throws: 
	 */
	public List findNowPageList(int pageNumber, int pageSize, String hql) {
		
		return this.findNowPageList(pageNumber, pageSize, hql, null);
		
	}
	
	
	/**********************************整表查询***************************************/
	
	/**
	 * 查询所有实体对象
	 * @Description: TODO()
	 * @param: entityClass 实体类
	 * @return: List 返回查询对象的list集合
	 * @throws: 
	 */
	public List findAll(Class entityClass) {
		
		return this.getHibernateTemplate().loadAll(entityClass);
		
	}
	
	/**
	 * 查询所有实体对象 并排序
	 * @Description: TODO()
	 * @param: entityClass 实体类
	 * @param: orderBy     排序
	 * @param: isAsc       是否升序
	 * @return: List 返回查询对象的list集合
	 * @throws: 
	 */
	public List findAll(Class entityClass, String orderBy, boolean isAsc) {
		
		Assert.hasText(orderBy);
		
		if (isAsc){
			
			return this.getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
			
			}else{
				
			return this.getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
			}
		
	}
	
	
	
	
	
	
	/**********************************SQL查询，更新***************************************/
	
	
	
	
	/**
	 * 
	 * @Description: 通过sql语句查询记录数
	 * @param:  sql sql语句
	 * @return: int 返回记录数
	 * @throws: 
	 */
	public int findCountBySQL(String sql,Class entityClass){
		
         return findBySQL(sql,entityClass).size();
		
	}
	
	

	/**
	 * 自定义sql查询
	 * @Description: TODO()
	 * @param: sql sql查询语句
	 * @param: entityClass 实体 
	 * @return: List 返回查询结果的list集合
	 * @throws: 
	 */
	public List findBySQL(String sql, Class entityClass){
		
		final String sql1 = sql;
		final Class entity = entityClass;
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				List list = session.createSQLQuery(sql1).addEntity(entity).list();
				return list;
			}
		});
		
	}
	
	/**
	 * 通过SQL语句更新
	 * @Description: TODO()
	 * @param:  sql sql语句
	 * @return: int 更新成功记录数
	 * @throws: 
	 */
	public int updateBySQL(String sql) throws Exception{
		
		final String sql_1 = sql;
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql_1);
				return query.executeUpdate();
			}
		});
		
	}

	
	
	/**********************************Privete函数***************************************/

	/**
	 * BaseDao依赖的基础函数，客户端调用可以忽略
	 */
	public Criteria createCriteria(Class entityClass, Criterion[] criterions) {
		
		Criteria criteria = getSession().createCriteria(entityClass);
		prepareCriteria(criteria);

		for (int i = 0, j = criterions.length; i < j; i++) {
			criteria.add(criterions[i]);
		}
		return criteria;
		
	}
	public Criteria createCriteria(Class entityClass, String orderBy,
			boolean isAsc, Criterion[] criterions) {
		
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));
		return criteria;
		
	}
	
	protected void prepareCriteria(Criteria criteria) {
		
		if (this.getHibernateTemplate().isCacheQueries()) {
			criteria.setCacheable(true);
			if (this.getHibernateTemplate().getQueryCacheRegion() != null) {
				criteria.setCacheRegion(this.getHibernateTemplate()
						.getQueryCacheRegion());
			}
		}
		if (this.getHibernateTemplate().getFetchSize() > 0) {
			criteria.setFetchSize(this.getHibernateTemplate().getFetchSize());
		}
		if (this.getHibernateTemplate().getMaxResults() > 0) {
			criteria.setMaxResults(this.getHibernateTemplate().getMaxResults());
		}
		SessionFactoryUtils.applyTransactionTimeout(criteria,
				getSessionFactory());
		
	}
	public Session getDaoSession(){
		daoSession = this.getSession();
		return daoSession;
	}
	
	/*********************************测试***************************************/
	
	
//	自己处理异常
	public void deleteExceptionTest(Object entity){
		
		if (entity == null) {
			log.error("删除对象为空");
		}
		log.debug("删除一个:" + entity.getClass());
		try {
			getSession().delete(entity);
		} catch (HibernateException e) {
			log.error("删除记录:" + entity + "失败", e);
			e.printStackTrace();
		}
		
	}
	
//	向上抛出异常
	public void deleteExceptionTest2(Object entity) throws AppException,HibernateException{
		
		if (entity == null) {
			log.error("删除对象为空");
			throw new AppException("删除对象为空！");
		}
		log.debug("删除一个:" + entity.getClass());
		try {
			getSession().delete(entity);
		} catch (HibernateException e) {
//			log.error("删除记录:" + entity + "失败"+e.getMessage());
			log.error("删除记录:" + entity + "失败", e);
			throw e;
		}
		
	}

	
	/**
	 * 通过sql语句查询
	 * @Description: TODO()
	 * @param sql sql语句
	 * @return 返回由Map对象组成的list
	 * @throws: 
	 */
	public List findByDynamicSQL(String sql){
		return this.findByDynamicSQL(sql,new Object[]{});
	}
	
	/**
	 * 通过sql语句查询
	 * @Description: TODO()
	 * @param: sql sql语句
	 * @param: sqlParams sql的条件
	 * @return: 返回由Map对象组成的list
	 * @throws: 
	 */
	public List findByDynamicSQL(String sql,Object[] sqlParams){
		final String sql_1 = sql;
		final Object[] sqlParams_1 = sqlParams;
		List resultList = (List) this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql_1);
				/*
				//参数
				for (int i = 0; i < sqlParams_1.length; i++) {
					query.setParameter(i, sqlParams_1[i]);
				}
				//非oracle Clob 字段处理：
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();*/
				//oracle Clob字段处理
				Connection conn = null;
				PreparedStatement ps =null;
				ResultSet rs = null;
				List list = new ArrayList();
				try {
					conn = session.connection();
					ps = conn.prepareStatement(sql_1);
					if(sqlParams_1 != null){
						for (int i = 0; i < sqlParams_1.length; i++) {
							Object param = sqlParams_1[i];
							ps.setObject(i + 1, param);
						}
					}
					rs = ps.executeQuery();
					ResultSetMetaData md = rs.getMetaData();
					int columnCount = md.getColumnCount();
					Map map = null;
					while (rs.next()) {
						map = new HashMap();
						for (int i = 1; i <= columnCount; i++) {
							//对clob字段的处理wt 2013-12-10
							Object obj = rs.getObject(md.getColumnName(i));
							if (obj == null) {
								map.put(md.getColumnName(i), "");//为空时 返回字符串空""
							} else if ("CLOB".equals(obj.getClass().getSimpleName())) {
								Clob clob = (Clob) obj;
								map.put(md.getColumnName(i), clob.getSubString(1, (int)clob.length()));//clob字段的处理
							} else {
								map.put(md.getColumnName(i), obj);
							}
						}
						list.add(map);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBUtil.close(rs);//关闭ResultSet
					DBUtil.close(ps);//关闭Statement
					DBUtil.close(conn);//关闭数据库连接
					//DBUtil.close(session);//关闭hibernate session
					//DBUtil.close(this.sessionFactory);//关闭SessionFactory
				}
				return list;
			}
		});
		return resultList;	
	}
	
/**
 * 通过sql语句查询
 * @Description: TODO()
 * @param  sql sql语句
 * @return 返回由String[]数组对象组成的list
 * @throws Exception
 */
public List findByDynamicSQLToArr(String sql)throws Exception{
	final String sql_1 = sql;
	@SuppressWarnings("unchecked")
	List resultList = (List) this.getHibernateTemplate().execute(new HibernateCallback(){
		public Object doInHibernate(Session session) throws HibernateException{
			Connection conn = session.connection();
			List list = new ArrayList();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(sql_1);
				rs = ps.executeQuery();
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount();
				while (rs.next()) {
					String[] strs = new String[columnCount];
					for (int i = 0; i < columnCount; i++) {
						//对clob字段的处理wt 2013-12-10
						Object obj = rs.getObject(md.getColumnName(i+1));
						if (obj == null) {
							strs[i] = "";//为空时 返回字符串空""
						} else if ("CLOB".equals(obj.getClass().getSimpleName())) {
							Clob clob = (Clob) obj;
							strs[i] = clob.getSubString(1, (int)clob.length());//clob字段的处理
						} else {
							strs[i] = obj+"";
						}
					}
					list.add(strs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.close(rs);//关闭记录集
				DBUtil.close(ps);//关闭声明
				DBUtil.close(conn);//关闭连接对象
				//DBUtil.close(session);//关闭session
			}
			return list;
		}
	});
	return resultList;
}
/**
 * 通过sql语句查询记录数
 * @Description: TODO()
 * @param:  sql sql语句
 * @return: int 返回记录数
 * @throws: 
 */
public int findCountByDynamicSQL(String sql){
	final String sql_1 = sql;
	@SuppressWarnings("unchecked")
	int count = (Integer) this.getHibernateTemplate().execute(new HibernateCallback(){
		public Object doInHibernate(Session session) throws HibernateException{
			Connection conn = session.connection();
			int count = 0;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(sql_1);
				rs = ps.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.close(rs);//关闭记录集
				DBUtil.close(ps);//关闭声明
				DBUtil.close(conn);//关闭连接对象
				//DBUtil.close(session);//关闭session
			}
			return count;
		}
	});
	return count;
}

/**
 * 
*@Description: 
*@param hql
*@param values
*@date 2014-7-24 上午11:19:29
*@author zyen
*/
	
public Query createQuery(String hql, Object[] values) {
	Assert.hasText(hql);
	Query query = getSession().createQuery(hql);
	if (values != null && values.length != 0) {
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
	}
	return query;
}

}
