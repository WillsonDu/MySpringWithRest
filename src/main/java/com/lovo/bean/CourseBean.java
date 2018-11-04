package com.lovo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "course")
public class CourseBean implements Serializable {

	private static final long serialVersionUID = -5968166232511702025L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "des")
	private String des;
	
	@ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<StudentBean> students;

	public CourseBean() {

	}

	public CourseBean(int id, String name) {
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public List<StudentBean> getStudents() {
		return students;
	}

	public void setStudents(List<StudentBean> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "CourseBean [id=" + id + ", name=" + name + ", des=" + des + ", students=" + students + "]";
	}

}
