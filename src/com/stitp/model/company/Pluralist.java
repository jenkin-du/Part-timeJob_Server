package com.stitp.model.company;

public class Pluralist {

	private String pId;
	private String name;
	private String phone;
	private String password;
	private String gender;
	private int age;
	private int height;
	private String educationBackground; 
	

	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getEducationBackground() {
		return educationBackground;
	}
	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}
	@Override
	public String toString() {
		return "Pluralist [pId=" + pId + ", name=" + name + ", phone=" + phone
				+ ", password=" + password + ", gender=" + gender + ", age="
				+ age + ", height=" + height + ", educationBackground="
				+ educationBackground + "]";
	}
	public Pluralist(String pId, String name, String phone, String password,
			String gender, int age, int height, String educationBackground) {
	
		this.pId = pId;
		this.name = name;
		this.phone = phone;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.educationBackground = educationBackground;
	}
	public Pluralist() {
	
	}
	
	
}
