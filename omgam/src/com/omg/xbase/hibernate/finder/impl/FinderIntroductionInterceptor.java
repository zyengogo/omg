package com.omg.xbase.hibernate.finder.impl;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

import com.omg.xbase.hibernate.annotations.HQL;
import com.omg.xbase.hibernate.annotations.SQL;
import com.omg.xbase.hibernate.finder.FinderExecutor;

public class FinderIntroductionInterceptor implements IntroductionInterceptor {

	@SuppressWarnings("unchecked")
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		FinderExecutor genericDao = (FinderExecutor) methodInvocation.getThis();
		Method method = methodInvocation.getMethod();
		String methodName = method.getName();
		if (method.isAnnotationPresent(HQL.class)) {
			HQL hql = method.getAnnotation(HQL.class);
			Object[] arguments = methodInvocation.getArguments();
			if (methodName.startsWith("countBy")) {
				return genericDao.countBy(hql.value(), arguments);
			} else if (methodName.startsWith("paginationBy")) {
				return genericDao.paginationBy(hql.value(), arguments);
			} else if (methodName.startsWith("findBy")) {
				return genericDao.findBy(hql.value(), arguments);
			} else if (methodName.startsWith("executeSQL")) {
				return genericDao.executeSQL(hql.value(), arguments);
			} else {
				return methodInvocation.proceed();
			}
		} else if (method.isAnnotationPresent(SQL.class)) {
			SQL sql = method.getAnnotation(SQL.class);
			Object[] arguments = methodInvocation.getArguments();
			if (methodName.startsWith("executeSQL")) {
				return genericDao.executeSQL(sql.value(), arguments);
			} else {
				return methodInvocation.proceed();
			}
		} else {
			return methodInvocation.proceed();
		}
	}

	@SuppressWarnings("unchecked")
	public boolean implementsInterface(Class intf) {
		return intf.isInterface() && FinderExecutor.class.isAssignableFrom(intf);
	}

}
