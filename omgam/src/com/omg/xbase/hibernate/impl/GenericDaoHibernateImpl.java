package com.omg.xbase.hibernate.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.omg.xbase.hibernate.GenericDao;
import com.omg.xbase.hibernate.Page;
import com.omg.xbase.hibernate.PropertyFilter;
import com.omg.xbase.hibernate.ReflectionUtils;
import com.omg.xbase.hibernate.PropertyFilter.MatchType;
import com.omg.xbase.hibernate.finder.FinderExecutor;

@SuppressWarnings("unchecked")
public class GenericDaoHibernateImpl<T, PK extends Serializable> implements GenericDao<T, PK>, FinderExecutor {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> type;

	public GenericDaoHibernateImpl(Class<T> type) {
		this.type = type;
	}

	public GenericDaoHibernateImpl() {
		this.type = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public PK create(final T o) {
		 PK tPK = (PK)getSession().save(o);
		 getSession().flush();
		 return tPK;
	}

	public T read(final PK id) {
		return (T)getSession().get(type, id);
	}

	public void update(final T o) {
		Session session = getSession();
		session.flush();
		session.update(o);
	}

	public void delete(final T o) {
		Session session = getSession();
		session.delete(o);
		session.flush();
	}

	private Query prepareQuery(final String hql,final Object[] queryArgs) {
		final Query query = getSession().createQuery(hql);
		String[] namedParameters = query.getNamedParameters();
		if (namedParameters.length == 0) {
			setPositionalParams(queryArgs, query);
		} else {
			setNamedParams(namedParameters, queryArgs, query);
		}
		return query;
	}
	private SQLQuery prepareSQLQuery(final String sql,final Object[] queryArgs,final Class ... entities) {
		final SQLQuery query = getSession().createSQLQuery(sql);
		if (entities != null) {
			for (Class entity:entities) {
				query.addEntity(entity);
			}
		}
		String[] namedParameters = query.getNamedParameters();
		if (namedParameters.length == 0) {
			setPositionalParams(queryArgs, query);
		} else {
			setNamedParams(namedParameters, queryArgs, query);
		}
		return query;
	}

	private void setPositionalParams(Object[] queryArgs, Query namedQuery) {
		if (queryArgs != null) {
			for (int i = 0; i < queryArgs.length; i++) {
				Object arg = queryArgs[i];
				namedQuery.setParameter(i, arg);
			}
		}
	}

	private void setNamedParams(String[] namedParameters, Object[] queryArgs, Query namedQuery) {
		if (queryArgs != null) {
			for (int i = 0; i < queryArgs.length; i++) {
				Object arg = queryArgs[i];
				if (arg instanceof Collection<?>) {
					namedQuery.setParameterList(namedParameters[i], (Collection<?>) arg);
				} else {
					namedQuery.setParameter(namedParameters[i], arg);
				}
			}
		}
	}


	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void delete(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(read(id));
		logger.debug("delete entity {},id is {}", type.getSimpleName(), id);
	}

	public List<T> findByIds(List<PK> ids) {
		Criteria criteria = createCriteria();
		Criterion c = Restrictions.in(getKeyName(), ids);
		criteria.add(c);
		return criteria.list();
	}



	public List<T> getAll() {
		Criteria criteria = getSession().createCriteria(type);
		return criteria.list();
	}

	public String getKeyName() {
		ClassMetadata meta = sessionFactory.getClassMetadata(type);
		return meta.getIdentifierPropertyName();
	}

	public void initEntity(T entity) {
		Hibernate.initialize(entity);
	}

	public void initEntity(List<T> entityList) {
		for (T entity : entityList) {
			Hibernate.initialize(entity);
		}
	}

	public long countBy(String hql, Object[] queryArgs) {
		String fromHql = hql;
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = StringUtils.substringBefore(fromHql, "order by");
		String countHql = "select count(*) " + fromHql;
		return (Long) prepareQuery(countHql, queryArgs).uniqueResult();
	}

	public List<T> findBy(final String hql, final Object[] queryArgs) {
		final Query query = prepareQuery(hql, queryArgs);
		return (List<T>) query.list();
	}

	public Page<T> paginationBy(String hql, Object[] queryArgs) {
		Assert.notEmpty(queryArgs,"分页参数不能为空！");
		Object[] params = new Object[queryArgs.length-1];
		Page<T> page = null;
		int j=0;
		for (int i = 0; i < queryArgs.length; i++) {
			if (queryArgs[i] instanceof Page<?>) {
				page = (Page<T>)queryArgs[i];
				j = 1;
			}else{
				params[i-j]=queryArgs[i];
			}
		}
		if (page.isAutoCount()) {
			int totalCount = (int)countBy(hql, params);
			page.setTotalCount(totalCount);
		}
		final Query query = prepareQuery(hql, params);
		query.setFirstResult(page.getFirst() - 1);
		query.setMaxResults(page.getPageSize());
		List result = query.list();
		page.setResult(result);
		return page;
	}

	public int executeSQL(final String sql, final Object[] queryArgs,final Class ... entities) {
		final SQLQuery query = prepareSQLQuery(sql,queryArgs,entities);
		return query.executeUpdate();
	}

	public T changeInheritanceSubclass(Class<?> from, Class<?> to, PK id) {
		if (from.isAnnotationPresent(DiscriminatorValue.class) && to.isAnnotationPresent(DiscriminatorValue.class)) {
			DiscriminatorValue discriminatorFrom = from.getAnnotation(DiscriminatorValue.class);
			DiscriminatorValue discriminatorTo = to.getAnnotation(DiscriminatorValue.class);
			Class superClass = null;
			if (from.isAssignableFrom(to)) {
				superClass = from;
			}else if (to.isAssignableFrom(from)) {
				superClass = to;
			}else{
				superClass = from.getSuperclass();
			}
			String tableName = null;
			if (superClass.isAnnotationPresent(Table.class)){
				Table table = (Table)superClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			if (tableName == null || "".equals(tableName)){
				tableName = superClass.getName().toUpperCase();
			}
			String discriminatorField = null;
			if (superClass.isAnnotationPresent(DiscriminatorColumn.class)){
				DiscriminatorColumn discriminatorColumn = (DiscriminatorColumn)superClass.getAnnotation(DiscriminatorColumn.class);
				discriminatorField = discriminatorColumn.name();
			}
			if (discriminatorField == null || "".equals(discriminatorField)){
				discriminatorField = "TYPE";
			}
			String keyName = getKeyName();
			String sql = "UPDATE " + tableName + " SET " + discriminatorField + "='" + discriminatorTo.value() + "' WHERE " + keyName + "=? AND " + discriminatorField + "='" + discriminatorFrom.value() +"'";
			Object[] args = new Object[1];
			args[0] = id;
			if (executeSQL(sql,args) > 0){
				return read(id);
			}
		}
		return null;
	}
	
	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}
	
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(type);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public List<T> getAll(String orderBy, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}
	
	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return findPage(page, criterions);
	}

	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	protected Criterion[] buildPropertyFilterCriterions(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			if (!filter.isMultiProperty()) { //只有一个属性需要比较的情况.
				Criterion criterion = buildPropertyFilterCriterion(filter.getPropertyName(), filter.getPropertyValue(),
						filter.getMatchType());
				criterionList.add(criterion);
			} else {//包含多个属性需要比较的情况,进行or处理.
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildPropertyFilterCriterion(param, filter.getPropertyValue(), filter
							.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	protected Criterion buildPropertyFilterCriterion(final String propertyName, final Object propertyValue,
			final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;
		try {

			//根据MatchType构造criterion
			if (MatchType.EQ.equals(matchType)) {
				criterion = Restrictions.eq(propertyName, propertyValue);
			} else if (MatchType.LIKE.equals(matchType)) {
				criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			} else if (MatchType.LE.equals(matchType)) {
				criterion = Restrictions.le(propertyName, propertyValue);
			} else if (MatchType.LT.equals(matchType)) {
				criterion = Restrictions.lt(propertyName, propertyValue);
			} else if (MatchType.GE.equals(matchType)) {
				criterion = Restrictions.ge(propertyName, propertyValue);
			} else if (MatchType.GT.equals(matchType)) {
				criterion = Restrictions.gt(propertyName, propertyValue);
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
		return criterion;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}
	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page 分页参数.
	 * @param criterions 数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	public Page<T> findPage(final Page<T> page, final Criterion... criterions) {
		Assert.notNull(page, "page不能为空");

		Criteria c = createCriteria(criterions);

		if (page.isAutoCount()) {
			int totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}
	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	protected int countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		// 执行Count查询
		int totalCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}
	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		//hibernate的firstResult的序号从0开始
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());
		return c;
	}
}
