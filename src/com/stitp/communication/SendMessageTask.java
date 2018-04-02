package com.stitp.communication;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class SendMessageTask extends Thread{

	private Socket socket=null;
	
	private String ip;
	private	int port;
	private	String jsonDatagram;
	
	
	/**
	 * @param ip
	 * @param port
	 * @param jsonDatagram
	 */
	public SendMessageTask(String ip, int port, String jsonDatagram) {
		this.ip = ip;
		this.port = port;
		this.jsonDatagram = jsonDatagram;
	}


	@Override
	public void run() {
	
		try {		
			 socket=new Socket(ip, port);
			 
			 OutputStream os=socket.getOutputStream();
			 OutputStreamWriter writer=new OutputStreamWriter(os);
			 
			  //将消息转发出去
			
			 writer.write(jsonDatagram);
			 writer.flush();
			 
			 os.close();
			 writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(socket!=null){					
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
