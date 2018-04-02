package com.stitp.model;

public class InfoAbstract {
	
	private String iId;
	
	private String description;
	private String salary;
	private workAddress address;
	private String startWorkTime;
	private String updateTime;
	private int workDays ;
	private String category;
	

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getiId() {
		return iId;
	}
	public void setiId(String iId) {
		this.iId = iId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getStartWorkTime() {
		return startWorkTime;
	}
	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getWorkDays() {
		return workDays;
	}
	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}
	
	
	public workAddress getAddress() {
		return address;
	}
	public void setAddress(workAddress address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "InfoAbstract [iId=" + iId + ", description=" + description
				+ ", salary=" + salary + ", address=" + address.toString()
				+ ", startWorkTime=" + startWorkTime.toString() + ", updateTime="
				+ updateTime + ", workDays=" + workDays + ", category="
				+ category + "]";
	}
	public InfoAbstract() {
	
	}
	
	
	
	
}
