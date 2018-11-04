package com.lovo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lovo.activemq.sender.MessageSenderService;
import com.lovo.bean.ClassBean;
import com.lovo.service.IClassService;

@Controller
@RequestMapping("class")
public class ClassController {

	@Autowired
	public IClassService mClassService;

	@Resource(name = "messageSenderService")
	private MessageSenderService messageSenderService;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public void queryAllClass(final HttpServletResponse pRes)
			throws JsonGenerationException, JsonMappingException, IOException {

		final List<ClassBean> list = getClassService().findAllClasses();
		// 閽堝鐢╤ibernate鏌ヨ鏃朵娇鐢�
		// if (list != null && !list.isEmpty()) {
		// for (final ClassBean classBean : list) {
		// final Set<StudentBean> students = classBean.getStudents();
		// if (students != null && !students.isEmpty()) {
		// for (final StudentBean studentBean : students) {
		// studentBean.setClassBean(null);
		// }
		// }
		// }
		// }
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(pRes.getWriter(), list);
	}

	@RequestMapping(value = "addClass", method = RequestMethod.POST)
	public void addClass(final ClassBean pClassBean, final HttpServletResponse pResponse) throws IOException {
		String addSuccess = Boolean.toString(false);
		try {
			getClassService().addClass(pClassBean);
			addSuccess = Boolean.toString(true);
		} catch (final Exception e) {
			addSuccess = Boolean.toString(false);
		}
		final String json = JSON.toJSONString(pClassBean);
		//发送jms消息
		getMessageSenderService().sendMessage(json);
		
		pResponse.getWriter().write(addSuccess);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void deleteClass(@PathVariable("id") final int pClassId, final HttpServletResponse pResponse)
			throws IOException {
		final ClassBean classBean = new ClassBean();
		classBean.setId(pClassId);
		getClassService().deleteClass(classBean);
		pResponse.getWriter().write("true");
	};

	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public void updateClass(final ClassBean pClassBean, final HttpServletResponse pResponse) throws IOException {
		getClassService().updateStudent(pClassBean);
		pResponse.getWriter().write("true");
	}

	@RequestMapping(value = "find/{id}", method = RequestMethod.GET)
	public void findClassById(@PathVariable("id") final int pClassId, final HttpServletResponse pResponse)
			throws JsonGenerationException, JsonMappingException, IOException {
		final ClassBean classBean = getClassService().findClassById(pClassId);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(pResponse.getWriter(), classBean);
	}

	@RequestMapping(value = "find11/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ClassBean findClassById11(@PathVariable("id") final int pClassId, final HttpServletResponse pResponse)
			throws JsonGenerationException, JsonMappingException, IOException {
		final ClassBean classBean = getClassService().findClassById(pClassId);
		return classBean;
	}

	@RequestMapping("json")
	public void getJson(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
			throws JsonGenerationException, JsonMappingException, IOException {
		final Map<String, Object> model = new HashMap<String, Object>();
		final List<ClassBean> list = new ArrayList<ClassBean>();
		for (int i = 0; i < 5; i++) {
			final ClassBean c = new ClassBean();
			c.setId(i);
			c.setName("name " + i);
			list.add(c);
		}
		model.put("status", 1);
		model.put("list", list);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(pResponse.getWriter(), model);
	}

	@InitBinder
	public void myInitBinder(final WebDataBinder pBinder) {

	}

	public IClassService getClassService() {
		return mClassService;
	}

	public void setClassService(IClassService pClassService) {
		mClassService = pClassService;
	}

	public IClassService getmClassService() {
		return mClassService;
	}

	public void setmClassService(IClassService mClassService) {
		this.mClassService = mClassService;
	}

	public MessageSenderService getMessageSenderService() {
		return messageSenderService;
	}

	public void setMessageSenderService(MessageSenderService messageSenderService) {
		this.messageSenderService = messageSenderService;
	}

	
}
