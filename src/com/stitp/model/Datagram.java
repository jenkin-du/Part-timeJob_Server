package com.stitp.model;

public class Datagram {

	
	private String request;
	private String response;
	private String jsonStream;
	
	public String getRequest() {
		return request;
	}
	public String getJsonStream() {
		return jsonStream;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public void setJsonStream(String jsonStream) {
		this.jsonStream = jsonStream;
	}
	
	
	@Override
	public String toString() {
		return "Datagram [request=" + request + ", response=" + response + ", jsonStream=" + jsonStream + "]";
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	/**
	 * 
	 */
	public Datagram() {
	}
	
}
