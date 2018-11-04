package com.lovo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lovo.bean.ClassBean;
import com.lovo.bean.StudentBean;
import com.lovo.bean.UserBean;
import com.lovo.dao.IUserDao;
import com.lovo.dao.impl.UserDaoImpl;
import com.lovo.service.IUserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao mUserDao;

	@Override
	public UserBean findUserByNameAndPwd(UserBean pUserBean) {
		return getUserDao().findUserByNameAndPwd(pUserBean);
	}

	public IUserDao getUserDao() {
		return mUserDao;
	}

	public void setUserDao(IUserDao pUserDao) {
		mUserDao = pUserDao;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void test() {
		final SessionFactory sessionFactory = ((UserDaoImpl) getUserDao()).getHibernateSsessionFactory();

		final StudentBean studentBean = new StudentBean();
		studentBean.setName("God");

		final List<StudentBean> list = new ArrayList<StudentBean>();
		list.add(studentBean);

		ClassBean classBean = new ClassBean();
		classBean.setId(1);
		classBean.setName("测试参数");
		classBean.setStudents(list);

		studentBean.setClassBean(classBean);

		// sessionFactory.getCurrentSession().save(studentBean);
		sessionFactory.getCurrentSession().save(classBean);

	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		final ApplicationContext context = new ClassPathXmlApplicationContext("spring-orm.xml");
		final IUserService userServiceImpl = (IUserService) context.getBean("userServiceImpl");
		// final UserBean userBean = new UserBean();
		// userBean.setName("wilsonDu");
		// userBean.setPassword("dx198909261");
		// System.out.println(userServiceImpl.findUserByNameAndPwd(userBean));
		userServiceImpl.test();
	}
}
