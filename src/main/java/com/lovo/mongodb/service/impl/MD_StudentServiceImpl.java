package com.lovo.mongodb.service.impl;

import java.util.List;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovo.mongobd.bean.MD_StudentBean;
import com.lovo.mongodb.dao.MD_StudentDao;
import com.lovo.mongodb.service.MD_StudentService;

@Service(value = "mD_StudentServiceImpl")
public class MD_StudentServiceImpl implements MD_StudentService {

	@Autowired
	private MD_StudentDao md_studentDao;

	@Override
	public void addStudent(MD_StudentBean bean) {
		md_studentDao.addStudent(bean);
	}

	@Override
	public void deleteStudent(MD_StudentBean bean) {
		md_studentDao.deleteStudent(bean);
	}

	@Override
	public void updateStudent(MD_StudentBean bean) {
		md_studentDao.updateStudent(bean);
	}

	@Override
	public MD_StudentBean getById(int id) {
		return md_studentDao.getById(id);
	}

	@Override
	public List<MD_StudentBean> getAll() {
		return md_studentDao.getAll();
	}

	public MD_StudentDao getMd_studentDao() {
		return md_studentDao;
	}

	public void setMd_studentDao(MD_StudentDao md_studentDao) {
		this.md_studentDao = md_studentDao;
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-base.xml");
		MD_StudentService service = (MD_StudentService) applicationContext.getBean("mD_StudentServiceImpl");

		// 添加
		MD_StudentBean bean = new MD_StudentBean(3, 30, "王五", 9);
//		service.addStudent(bean);
//		System.out.println("添加完成");

		// 查询
//		MD_StudentBean bean2 = service.getById(2);
//		System.out.println(bean2);
		
		//查询所有
		List<MD_StudentBean> all = service.getAll();
		if (all!=null) {
			for (final MD_StudentBean a : all) {
				System.out.println(a);
			}
		}
		//修改
//		MD_StudentBean new_bean = new MD_StudentBean(3, 30, "王二麻子", 9);
//		service.updateStudent(new_bean);
		
		//删除
//		service.deleteStudent(new MD_StudentBean(3));
		
		
	}

}
