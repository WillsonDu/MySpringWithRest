package com.lovo.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lovo.bean.StudentBean;
import com.lovo.dao.IStudentDao;
import com.lovo.service.IStudentService;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentDao mStudentDao;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addStudent(StudentBean pStudentBean) {
		getStudentDao().addStudent(pStudentBean);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteStudent(StudentBean pStudentBean) {
		getStudentDao().deleteStudent(pStudentBean);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateStudent(StudentBean pStudentBean) {
		getStudentDao().updateStudent(pStudentBean);
	}

	@Override
	public StudentBean findStudentById(String pSrudentId) {
		return getStudentDao().findStudentById(pSrudentId);
	}

	@Override
	public Set<StudentBean> queryStudentsByCondition(String pHql) {
		return getStudentDao().queryStudentsByCondition(pHql);
	}

	@Override
	public Set<StudentBean> queryAllStudent() {
		return getStudentDao().queryAllStudent();
	}

	public IStudentDao getStudentDao() {
		return mStudentDao;
	}

	public void setStudentDao(IStudentDao pStudentDao) {
		mStudentDao = pStudentDao;
	}

}
