package com.lovo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "student")
public class StudentBean implements Serializable {

	private static final long serialVersionUID = -4741831376816654754L;

	@Id
	@GenericGenerator(name = "test_generator", strategy = "native")
	@GeneratedValue(generator = "test_generator")
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToOne()
	@JoinColumn(name = "class_id")
	private ClassBean classBean;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<CourseBean> courses;

	public StudentBean() {

	}

	public StudentBean(String name) {
		super();
		this.name = name;
	}

	public StudentBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClassBean getClassBean() {
		return classBean;
	}

	public void setClassBean(ClassBean classBean) {
		this.classBean = classBean;
	}

	public List<CourseBean> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseBean> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "StudentBean [id=" + id + ", name=" + name + ", classBean=" + classBean + ", courses=" + courses + "]";
	}

}
