package com.stitp.model;


/**
 * 单个招聘信息
 * Created by D on 2016/7/13.
 */
public class RecruitInfo  {

    private String description;       //招聘简介
    private String workTime;          //工作时间段（每天）
    private String startWorkTime;     //开始工作时间
    private String workDays;          //工作天数
    private String workDetail;        //工作详情

    private String distance;          //距离
    private String workPlace;         //工作地点（区/县）

    private String salary;            //薪资
    private int category;             //工作类别
    private String updateTime;        //更新时间

    private int recruitNumber;        //招聘数量
    private int recruitedNumber;      //已招聘数量

    private String genderRequest;    //性别要求

    private String contactName;      //联系人姓名
    private String contactPhone;      //联系人电话


    public RecruitInfo(String contactPhone, String description, String workTime,
                       String startWorkTime, String workDays, String workDetail,
                       String distance, String workPlace, String salary, int category,
                       String updateTime, int recruitNumber, int recruitedNumber,
                       String genderRequest, String contactName) {
        this.contactPhone = contactPhone;
        this.description = description;
        this.workTime = workTime;
        this.startWorkTime = startWorkTime;
        this.workDays = workDays;
        this.workDetail = workDetail;
        this.distance = distance;
        this.workPlace = workPlace;
        this.salary = salary;
        this.category = category;
        this.updateTime = updateTime;
        this.recruitNumber = recruitNumber;
        this.recruitedNumber = recruitedNumber;
        this.genderRequest = genderRequest;
        this.contactName = contactName;
    }


    public RecruitInfo(String description, String startWorkTime, String workDays,
                       String distance, String workPlace, String salary,
                       int category, String updateTime) {
        this.description = description;
        this.startWorkTime = startWorkTime;
        this.workDays = workDays;
        this.distance = distance;
        this.workPlace = workPlace;
        this.salary = salary;
        this.category = category;
        this.updateTime = updateTime;
    }

    public RecruitInfo() {

    }


    public String getDescription() {
        return description;
    }

    public String getWorkTime() {
        return workTime;
    }

    public String getDistance() {
        return distance;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public String getSalary() {
        return salary;
    }

    public int getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }

    public String getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(String workDetail) {
        this.workDetail = workDetail;
    }

    public int getRecruitNumber() {
        return recruitNumber;
    }

    public void setRecruitNumber(int recruitNumber) {
        this.recruitNumber = recruitNumber;
    }

    public int getRecruitedNumber() {
        return recruitedNumber;
    }

    public void setRecruitedNumber(int recruitedNumber) {
        this.recruitedNumber = recruitedNumber;
    }

    public String getGenderRequest() {
        return genderRequest;
    }

    public void setGenderRequest(String genderRequest) {
        this.genderRequest = genderRequest;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }


  
}
