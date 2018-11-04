package com.lovo.service;

import java.util.List;

import com.lovo.bean.ClassBean;

public interface IClassService {

	void addClass(final ClassBean pClassBean);

	void deleteClass(final ClassBean pClassBean);

	void updateStudent(final ClassBean pClassBean);

	ClassBean findClassById(final int pId);

	List<ClassBean> findAllClasses();
}
