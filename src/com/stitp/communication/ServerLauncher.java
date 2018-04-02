package com.stitp.communication;

public class ServerLauncher {

	public static void main(String[] args) {
		
		System.out.println("---------communicationServer starting---------");
		CommunicationServer server=new CommunicationServer();
		server.start();
	}
}
