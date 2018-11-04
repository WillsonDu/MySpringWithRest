package com.lovo.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.lovo.bean.ClassBean;
import com.lovo.dao.IClassDao;
import com.lovo.service.IClassService;

@Repository
public class ClassDaoImpl extends BaseDaoImp implements IClassDao {

	// 测试调用带参存储过程
	public Map<String, Object> testStudentProceduer(final long pId, final int pAge) throws SQLException {

		return getHibernateTemplate().execute(new HibernateCallback<Map<String, Object>>() {

			@Override
			public Map<String, Object> doInHibernate(final Session pSession) throws HibernateException, SQLException {
				SQLQuery query = pSession.createSQLQuery("{call pro_student_age(?,?)}");
				query.setParameter(1, pId);
				query.setParameter(2, pAge);

				query.list();
				return new HashMap<>();
			}

		});

	}

	@Override
	public void addClass(ClassBean pClassBean) {
		getHibernateSession().save(pClassBean);
		// 测试 save �? persist方法的不�?(save方法会立即执行insert语句，persist则不�?)
		// addClasses();

		// 测试对带参存储过程的调用
		// try {
		// testStudentProceduer(7100, 99);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

	public void addClasses() {
		for (int i = 0; i < 5; i++) {
			final ClassBean c = new ClassBean();
			c.setName("name " + i);
			getHibernateSession().persist(c);
		}
		getHibernateSession().flush();
		getHibernateSession().clear();
	}

	@Override
	public void deleteClass(ClassBean pClassBean) {
		getHibernateSession().delete(pClassBean);
	}

	@Override
	public void updateStudent(ClassBean pClassBean) {
		getHibernateSession().update(pClassBean);
	}

	@Override
	public ClassBean findClassById(@Param(value = "pid") int pId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", pId);
		return getMybatisSession().selectOne("ClassBean.selectClassById", map);
	}

	@Override
	public List<ClassBean> findAllClasses() {
		return getMybatisSession().selectList("ClassBean.selectAllClass");
		// Query query = getHibernateSession().createQuery("from ClassBean");
		// return query.list();
	}

	public static void main(String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("spring-base.xml");
		final IClassDao classDaoImpl = (IClassDao) context.getBean("classDaoImpl");
		System.out.println(classDaoImpl);
	}
}
