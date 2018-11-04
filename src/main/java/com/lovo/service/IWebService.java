package com.lovo.service;

import javax.jws.WebService;

@WebService
public interface IWebService {

	public String getStudentById(final int id);

}
