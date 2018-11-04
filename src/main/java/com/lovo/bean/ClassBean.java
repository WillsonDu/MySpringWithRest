package com.lovo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "class")
public class ClassBean implements Serializable {

	private static final long serialVersionUID = 4226093073959416488L;
	@Id
	@GeneratedValue(generator = "mysql_native_generator")
	@GenericGenerator(name = "mysql_native_generator", strategy = "native")
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "des")
	private String des;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classBean")
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<StudentBean> students;

	public ClassBean() {

	}

	public ClassBean(String name) {
		super();
		this.name = name;
	}

	public ClassBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ClassBean(int id, String name, String des) {
		super();
		this.id = id;
		this.name = name;
		this.des = des;
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
		return "ClassBean [id=" + id + ", name=" + name + ", des=" + des + ", students=" + students + "]";
	}

}
