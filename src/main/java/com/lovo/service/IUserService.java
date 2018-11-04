package com.lovo.service;

import com.lovo.bean.UserBean;

public interface IUserService {

	public UserBean findUserByNameAndPwd(final UserBean pUserBean);

	public void test();
}
