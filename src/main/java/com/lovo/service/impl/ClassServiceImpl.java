package com.lovo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lovo.bean.ClassBean;
import com.lovo.dao.IClassDao;
import com.lovo.service.IClassService;

@Service
@Transactional(readOnly = true)
public class ClassServiceImpl implements IClassService {

	@Autowired
	private IClassDao mClassDao;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addClass(ClassBean pClassBean) {
		getClassDao().addClass(pClassBean);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteClass(ClassBean pClassBean) {
		getClassDao().deleteClass(pClassBean);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateStudent(ClassBean pClassBean) {
		getClassDao().updateStudent(pClassBean);
	}

	@Override
	public ClassBean findClassById(int pId) {
		return getClassDao().findClassById(pId);
	}

	@Override
	public List<ClassBean> findAllClasses() {
		return getClassDao().findAllClasses();
	}

	public IClassDao getClassDao() {
		return mClassDao;
	}

	public void setClassDao(IClassDao pClassDao) {
		mClassDao = pClassDao;
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		final ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-base.xml");
		final IClassService classServiceImpl = (IClassService) context
				.getBean("classServiceImpl");

//		final ClassBean c = new ClassBean();
//		c.setId(8100);
//		c.setName("tttttest");
//		classServiceImpl.deleteClass(c);

		final ClassBean classBean = classServiceImpl.findClassById(38);
		System.out.println(classBean);
	}
}
