package com.zero.studentmonitor;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private int stuId = -1;
	private String stuName = "";
	private String stuPhone = "";
	public Student() {
	}
	public Student(int stuId, String stuName, String stuPhone) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuPhone = stuPhone;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}
	
	
}
