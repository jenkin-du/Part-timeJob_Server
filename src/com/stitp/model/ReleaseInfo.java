package com.stitp.model;

public class ReleaseInfo {
	String companyid;
	String infoid;
	String time;
	@Override
	public String toString() {
		return "ReleaseInfo [companyid=" + companyid + ", infoid=" + infoid + ", time=" + time + "]";
	}
	public ReleaseInfo(String companyid, String infoid, String time) {
		super();
		this.companyid = companyid;
		this.infoid = infoid;
		this.time = time;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getInfoid() {
		return infoid;
	}
	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
