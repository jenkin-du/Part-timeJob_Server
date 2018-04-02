package com.stitp.action;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.stitp.communication.CommunicationServer;

public class ContextListener implements ServletContextListener {
	
	
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/*System.out.println("ServletContext创建了...");  
		CommunicationServer server=new CommunicationServer();
		server.start();*/
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContext销毁了...");  
		
	}


}
