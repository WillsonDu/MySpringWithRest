package com.lovo.dao.impl;

import org.springframework.stereotype.Repository;

import com.lovo.bean.UserBean;
import com.lovo.dao.IUserDao;

@Repository
public class UserDaoImpl extends BaseDaoImp implements IUserDao {

	@Override
	public UserBean findUserByNameAndPwd(final UserBean pUserBean) {
		// final Session session = getHibernateSsessionFactory()
		// .getCurrentSession();
		// final Query query = session
		// .createQuery("from UserBean where name=:name and pwd=:pwd");
		// query.setParameter("name", pUserBean.getName());
		// query.setParameter("pwd", pUserBean.getPassword());
		// final UserBean userBean = (UserBean) query.uniqueResult();
		// return userBean;
		return getMybatisSession().selectOne("userBean.fidnUsrByNameAndPwd",
				pUserBean);
	}

}
