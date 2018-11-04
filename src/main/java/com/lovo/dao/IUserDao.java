package com.lovo.dao;

import com.lovo.bean.UserBean;

public interface IUserDao {

	UserBean findUserByNameAndPwd(final UserBean pUserBean);
}
