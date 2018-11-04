package com.lovo.mongodb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lovo.mongobd.bean.MD_StudentBean;
import com.lovo.mongodb.dao.MD_StudentDao;

@Repository
public class MD_StudentDaoImpl extends MongoBaseDao implements MD_StudentDao {

	@Override
	public void addStudent(MD_StudentBean bean) {
		getMongoTemplate().save(bean);
	}

	@Override
	public void deleteStudent(MD_StudentBean bean) {
		MD_StudentBean bean2 = getMongoTemplate().findById(bean.getId(), MD_StudentBean.class);
		if (bean2 != null) {
			getMongoTemplate().remove(bean2);
		}
	}

	@Override
	public void updateStudent(MD_StudentBean bean) {
		getMongoTemplate().save(bean);
	}

	@Override
	public MD_StudentBean getById(int id) {
		return getMongoTemplate().findById(id, MD_StudentBean.class);
	}

	@Override
	public List<MD_StudentBean> getAll() {
		return getMongoTemplate().findAll(MD_StudentBean.class);
	}

}
