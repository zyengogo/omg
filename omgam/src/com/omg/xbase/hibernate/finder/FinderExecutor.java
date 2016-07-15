package com.omg.xbase.hibernate.finder;

import java.util.List;

import com.omg.xbase.hibernate.Page;

public interface FinderExecutor<T> {

	long countBy(final String hql, final Object[] queryArgs);

	Page<T> paginationBy(final String hql, final Object[] queryArgs);

	List<T> findBy(final String hql, final Object[] queryArgs);
	
	int executeSQL(final String sql, final Object[] queryArgs,final Class<?> ... entities);
	
}
