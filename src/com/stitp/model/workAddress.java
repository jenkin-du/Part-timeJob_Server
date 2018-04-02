package com.stitp.model;

public class workAddress {
	
	private String aId;
	private String province;
	private String city;
	private String district;
	private String detailAddr;
	
	private String longitude;
	private String latitude;
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDetailAddr() {
		return detailAddr;
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	@Override
	public String toString() {
		return "InfoAddress [aId=" + aId + ", province=" + province + ", city="
				+ city + ", district=" + district + ", detailAddr="
				+ detailAddr + "]";
	}
	public workAddress(String aId, String province, String city,
			String district, String detailAddr) {
		this.aId = aId;
		this.province = province;
		this.city = city;
		this.district = district;
		this.detailAddr = detailAddr;
	}
	public workAddress() {
	}

}
