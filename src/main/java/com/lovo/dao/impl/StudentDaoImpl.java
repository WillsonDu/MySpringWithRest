package com.lovo.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lovo.bean.StudentBean;
import com.lovo.dao.IStudentDao;

@Repository
public class StudentDaoImpl extends BaseDaoImp implements IStudentDao {

	@Override
	public void addStudent(StudentBean pStudentBean) {
		getHibernateSession().save(pStudentBean);
	}

	@Override
	public void deleteStudent(StudentBean pStudentBean) {
		getHibernateSession().delete(pStudentBean);
	}

	@Override
	public void updateStudent(StudentBean pStudentBean) {
		getHibernateSession().update(pStudentBean);
	}

	@Override
	public StudentBean findStudentById(final String pSrudentId) {
		 final Map<String, Object> map = new HashMap<String, Object>();
		 map.put("id", pSrudentId);
		 return getMybatisSession().selectOne("StudentBean.findById", map);

//		final Query query = getHibernateSession().createQuery(
//				"from StudentBean where id=:id");
//		query.setParameter("id", Integer.parseInt(pSrudentId));
//		return (StudentBean) query.uniqueResult();
	}

	@Override
	public Set<StudentBean> queryStudentsByCondition(String pHql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<StudentBean> queryAllStudent() {
		final List<StudentBean> beans = getMybatisSession().selectList(
				"StudentBean.selectAllStudent");
		final Set<StudentBean> set = new HashSet<StudentBean>();
		set.addAll(beans);
		return set;
	}
}
