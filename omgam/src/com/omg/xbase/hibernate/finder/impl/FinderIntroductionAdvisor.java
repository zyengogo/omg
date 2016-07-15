package com.omg.xbase.hibernate.finder.impl;


import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import com.omg.xbase.hibernate.finder.FinderExecutor;

public class FinderIntroductionAdvisor extends DefaultIntroductionAdvisor {
	private static final long serialVersionUID = 1962983854004100921L;

	private static final ClassFilter filter = new ClassFilter() {
		@SuppressWarnings("unchecked")
		public boolean matches(Class clazz) {
			return FinderExecutor.class.isAssignableFrom(clazz);
		}
	};
	public FinderIntroductionAdvisor() {
		super(new FinderIntroductionInterceptor());
	}

	@Override
	public ClassFilter getClassFilter() {
		return filter;
	}

}
