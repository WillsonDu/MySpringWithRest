package com.lovo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lovo.bean.UserBean;
import com.lovo.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService mUserService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login(final UserBean pUserBean, final HttpServletResponse pRes) {
		final UserBean userBean = getUserService().findUserByNameAndPwd(
				pUserBean);
		String returnStr = null;
		if (userBean == null) {
			returnStr = "false";
		} else {
			returnStr = "true";
		}
		try {
			pRes.getWriter().write(returnStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test(final HttpServletResponse pRes) throws IOException {
		getUserService().test();
	}

	public IUserService getUserService() {
		return mUserService;
	}

	public void setUserService(IUserService pUserService) {
		mUserService = pUserService;
	}

}
