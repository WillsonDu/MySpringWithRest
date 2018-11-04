package com.lovo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lovo.service.IStudentService;
import com.lovo.service.IWebService;

@Repository
public class MyWebServiceListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-base.xml");
		final IWebService webService = (IWebService) applicationContext.getBean("webServiceImpl");
		System.out.println("可以在MyWebServiceListener(实现ServletContextListener接口)做一些初始化的操作...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
