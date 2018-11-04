package com.lovo.mongodb.service;

import java.util.List;

import com.lovo.mongobd.bean.MD_StudentBean;

public interface MD_StudentService {

	public void addStudent(final MD_StudentBean bean);

	public void deleteStudent(final MD_StudentBean bean);

	public void updateStudent(final MD_StudentBean bean);

	public MD_StudentBean getById(final int id);

	public List<MD_StudentBean> getAll();

}
