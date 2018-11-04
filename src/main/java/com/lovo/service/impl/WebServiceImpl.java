package com.lovo.service.impl;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lovo.bean.StudentBean;
import com.lovo.service.IStudentService;
import com.lovo.service.IWebService;

@WebService
public class WebServiceImpl implements IWebService {

	private IStudentService studentService;

	@Override
	public String getStudentById(int id) {
		final StudentBean s = studentService.findStudentById(id + "");
		if (s == null) {
			return StringUtils.EMPTY;
		}
		return JSON.toJSONString(s);
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

}
