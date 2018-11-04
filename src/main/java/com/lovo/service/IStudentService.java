package com.lovo.service;

import java.util.Set;

import com.lovo.bean.StudentBean;

public interface IStudentService {

	void addStudent(final StudentBean pStudentBean);

	void deleteStudent(final StudentBean pStudentBean);

	void updateStudent(final StudentBean pStudentBean);

	StudentBean findStudentById(final String pSrudentId);

	Set<StudentBean> queryStudentsByCondition(final String pHql);

	Set<StudentBean> queryAllStudent();
}
