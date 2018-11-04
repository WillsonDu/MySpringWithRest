package com.lovo.mongobd.bean;

public class MD_ClassBean {

	private int id;

	private String name;

	public MD_ClassBean() {
		super();
	}

	public MD_ClassBean(int id, String name) {
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

}
