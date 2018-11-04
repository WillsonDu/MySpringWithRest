package com.lovo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoImp {

	@Autowired
	private HibernateTemplate mHibernateTemplate;

	@Autowired
	private SessionFactory mHibernateSsessionFactory;

	@Autowired
	private SqlSessionTemplate mSqlSessionTemplate;

	public SessionFactory getHibernateSsessionFactory() {
		return mHibernateSsessionFactory;
	}

	public void setHibernateSsessionFactory(
			SessionFactory pHibernateSsessionFactory) {
		mHibernateSsessionFactory = pHibernateSsessionFactory;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return mSqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate pSqlSessionTemplate) {
		mSqlSessionTemplate = pSqlSessionTemplate;
	}

	protected Session getHibernateSession() {
		return getHibernateSsessionFactory().getCurrentSession();
	}

	protected SqlSessionTemplate getMybatisSession() {
		return getSqlSessionTemplate();
	}

	public HibernateTemplate getHibernateTemplate() {
		return mHibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate pHibernateTemplate) {
		mHibernateTemplate = pHibernateTemplate;
	}

}
