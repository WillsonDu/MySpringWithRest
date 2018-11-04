package com.lovo.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lovo.bean.CourseBean;
import com.lovo.bean.StudentBean;
import com.lovo.service.IStudentService;
import com.lovo.service.IWebService;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private IStudentService mStudentService;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public void queryAllStudent(final HttpServletResponse pResponse)
			throws JsonGenerationException, JsonMappingException, IOException {
		final Set<StudentBean> studentBeans = getStudentService().queryAllStudent();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(pResponse.getWriter(), studentBeans);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void addStudent(final StudentBean pStudentBean, final HttpServletResponse pResponse) throws IOException {
		getStudentService().addStudent(pStudentBean);
		pResponse.getWriter().write("true");
	}

	@RequestMapping(value = "addJson", method = RequestMethod.POST)
	public void addJsonStudent(@RequestBody final StudentBean pStudentBean, final HttpServletResponse pResponse)
			throws IOException {
		getStudentService().addStudent(pStudentBean);
		pResponse.getWriter().write("true");

	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable("id") final int pStuId, final HttpServletResponse pResponse)
			throws IOException {
		final StudentBean studentBean = new StudentBean();
		studentBean.setId(pStuId);
		getStudentService().deleteStudent(studentBean);
		pResponse.getWriter().write("true");
	}

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	public void findStudentById(@PathVariable("id") final String pId, final HttpServletResponse pResponse)
			throws JsonGenerationException, JsonMappingException, IOException {
		final StudentBean studentBean = getStudentService().findStudentById(pId);

//		for (final CourseBean courseBean : studentBean.getCourses()) {
//			courseBean.setStudents(null);
//		}

		studentBean.getClassBean().setStudents(null);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(pResponse.getWriter(), studentBean);
	}

	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public void updateStuden(final StudentBean pStudentBean, final HttpServletResponse pResponse) throws IOException {
		getStudentService().updateStudent(pStudentBean);
		pResponse.getWriter().write("true");
	}

	public IStudentService getStudentService() {
		return mStudentService;
	}

	public void setStudentService(IStudentService pStudentService) {
		mStudentService = pStudentService;
	}

}
