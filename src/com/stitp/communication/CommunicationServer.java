package com.stitp.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommunicationServer extends Thread {

	ServerSocket server = null;
	Socket socket = null;
	ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

	/**
	 * 初始化
	 */
	public CommunicationServer() {
		Config.onlineMap = new HashMap<>();
	}

	@Override
	public void run() {

		try {

			boolean isFirst = true;
			Date now = new Date(); // 获取当前时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String time = dateFormat.format(now);
			server = new ServerSocket(2345);

			System.out.println("----------等待通信-------------" + time);
			while (true) {
				// 等待接收数据
				socket = server.accept();
				Date now1 = new Date(); // 获取当前时间
				String time1 = dateFormat.format(now1);

				if (isFirst) {
					System.out.println("----------开始通信-------------" + time1);
					isFirst = false;
				}

				System.out.println("接受一个信息-------" + time);
				cachedThreadPool.execute(new HandleMessageTask(socket));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
