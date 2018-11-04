package com.lovo.mongobd.bean;

public class MD_StudentBean {

	private int id;

	private int age;

	private String name;

	private int class_id;

	public MD_StudentBean() {
		super();
	}

	public MD_StudentBean(int id) {
		super();
		this.id = id;
	}

	public MD_StudentBean(int id, int age, String name) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
	}

	public MD_StudentBean(int id, int age, String name, int class_id) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.class_id = class_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	@Override
	public String toString() {
		return "MD_StudentBean [id=" + id + ", age=" + age + ", name=" + name + ", class_id=" + class_id + "]";
	}

}
