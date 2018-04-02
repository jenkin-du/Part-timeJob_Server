package com.stitp.model;

public class OnlineRecord {

	private String id;
	private String ip;
	
	
	public OnlineRecord() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param ip
	 */
	public OnlineRecord(String id, String ip) {
		this.id = id;
		this.ip = ip;
	}
	
	public String getId() {
		return id;
	}
	public String getIp() {
		return ip;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "OnlineRecord [id=" + id + ", ip=" + ip + "]";
	}
	
	
}
