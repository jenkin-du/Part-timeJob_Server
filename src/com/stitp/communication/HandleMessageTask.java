package com.stitp.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.stitp.dao.ChatRecordDao;
import com.stitp.dao.CompanyDao;
import com.stitp.dao.FriendDAO;
import com.stitp.dao.FriendRequestDao;
import com.stitp.dao.PluralistDao;
import com.stitp.dao.RecruitDao;
import com.stitp.model.ChatRecord;
import com.stitp.model.Contact;
import com.stitp.model.Datagram;
import com.stitp.model.FriendRequest;
import com.stitp.model.OnlineRecord;
import com.stitp.model.Recruit;
import com.stitp.util.Action;
import com.stitp.util.DatagramParser;
import com.stitp.util.JSONParser;
import com.stitp.util.Status;
import com.stitp.util.StreamWapper;

public class HandleMessageTask extends Thread {

	private Socket socket = null;
	private FriendRequestDao requestDao = new FriendRequestDao();
	private ChatRecordDao chatdao = new ChatRecordDao();

	private String id;
	private String ip;
	private boolean isOnline;
	private SendMessageTask task;
	private Datagram responseData;
	private ArrayList<String> myFriends;
	private OnlineRecord online;

	private String friendName;
	private String myName;

	/**
	 * @param socket
	 */
	public HandleMessageTask(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		BufferedReader br = null;

		try {

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			PrintWriter writer = StreamWapper.toPrintWriter(os);

			// 得到数据流輸入流
			br = StreamWapper.toBufferedReader(is);
			String jsonDatagram = br.readLine();

			System.out.println(jsonDatagram);

			// 获得数据包中的请求
			String request = DatagramParser.getRequest(jsonDatagram);

			switch (request) {
			/**
			 * 通信
			 */
			case Action.COMMUNICATE:
				// 將數據流轉換成對象
				ChatRecord record = DatagramParser.getEntity(jsonDatagram, new TypeToken<ChatRecord>() {
				}.getType());

				id = record.getFriendId();
				// 查看對方是否在線
				isOnline = Config.onlineMap.containsKey(id);
				if (isOnline) {

					// 開啟線程轉發消息
					ip = Config.onlineMap.get(id);
					SendMessageTask task = new SendMessageTask(ip, 5432, jsonDatagram);
					task.start();

					// 將信息記錄保存在數據庫中
					// dao.insert(record);

				} else {
					// 将消息记录存储在数据库
					chatdao.insert(record);

				}
				break;

			/**
			 * 上线
			 */
			case Action.ON_LINE:

				// 獲得上顯得記錄
				online = DatagramParser.getEntity(jsonDatagram, new TypeToken<OnlineRecord>() {
				}.getType());

				id = online.getId();
				ip = online.getIp();
				// 在上線好友列表中添加上線的用戶
				Config.onlineMap.put(id, ip);

				System.out.println(online.toString());

				// 上線之後通知已上線的好友我上線了，
				myFriends = FriendDAO.getAllMyFriendsId(id);

				for (int i = 0; i < myFriends.size(); i++) {

					String friendId = myFriends.get(i);
					// 找出在线的好友
					if (Config.onlineMap.containsKey(friendId)) {
						// 获得好友ip
						ip = Config.onlineMap.get(friendId);

						online = new OnlineRecord();
						online.setId(id);
						// 将上线消息封装成json格式
						String onlineJson = DatagramParser.toJsonDatagram(Action.ON_LINE, online);
						// 将上线消息传递出去
						SendMessageTask task = new SendMessageTask(ip, 5432, onlineJson);
						task.start();

					}
				}

				// 获得在线的朋友
				ArrayList<String> onlineFriendIds = new ArrayList<>();
				for (int i = 0; i < myFriends.size(); i++) {
					if (Config.onlineMap.containsKey(myFriends.get(i))) {
						onlineFriendIds.add(myFriends.get(i));
					}
				}
				// 返回数据
				responseData = new Datagram();
				responseData.setResponse(Status.SUCCESSFUL);
				responseData.setJsonStream(JSONParser.toJSONString(onlineFriendIds));

				writer.write(JSONParser.toJSONString(responseData));
				writer.flush();
				writer.close();

				// 並且將我好友發給我的离线消息轉發給我
				ArrayList<ChatRecord> records = chatdao.getOfflineRecord(id);
				if (records != null) {
					ip = Config.onlineMap.get(id);
					for (ChatRecord r : records) {
						// 開啟線程轉發消息
						jsonDatagram = DatagramParser.toJsonDatagram(Action.COMMUNICATE, r);
						SendMessageTask task = new SendMessageTask(ip, 5432, jsonDatagram);
						task.start();
					}
				}

				// 将朋友的离线请求发给我
				ArrayList<FriendRequest> requests = requestDao.query(id);
				if (requests != null) {
					for (FriendRequest friendRequest : requests) {
						// 開啟線程轉發消息
						jsonDatagram = DatagramParser.toJsonDatagram(Action.ADD_FRIEND, friendRequest);
						SendMessageTask task = new SendMessageTask(ip, 5432, jsonDatagram);
						task.start();
					}
				}
				System.out.println(Config.onlineMap.toString());
				break;

			/**
			 * 下線
			 */
			case Action.OFF_LINE:

				// 获得实体
				online = DatagramParser.getEntity(jsonDatagram, new TypeToken<OnlineRecord>() {
				}.getType());

				id = online.getId();
				// 從上線好友列表中刪除下線的用戶
				Config.onlineMap.remove(id);

				// 上線之後通知已上線的好友我下線了，
				myFriends = FriendDAO.getAllMyFriendsId(id);

				for (int i = 0; i < myFriends.size(); i++) {

					String friendId = myFriends.get(i);
					// 找出在线的好友
					if (Config.onlineMap.containsKey(friendId)) {
						// 获得好友ip
						ip = Config.onlineMap.get(friendId);

						online = new OnlineRecord();
						online.setId(id);
						// 将上线消息封装成json格式
						String onlineJson = DatagramParser.toJsonDatagram(Action.OFF_LINE, online);
						// 将上线消息传递出去
						SendMessageTask task = new SendMessageTask(ip, 5432, onlineJson);
						task.start();

					}
				}

				System.out.println(Config.onlineMap.toString());
				break;

			/**
			 * 好友申请
			 */

			case Action.ADD_FRIEND:

				String myId;
				String friendId;
				FriendRequest friendRequest = DatagramParser.getEntity(jsonDatagram, new TypeToken<FriendRequest>() {
				}.getType());

				String status = friendRequest.getStatus();
				switch (status) {
				// 申请
				case Action.REQUEST:

					myId = friendRequest.getMyId();
					friendId = friendRequest.getFriendId();

					friendName = requestDao.getNameById(myId);
					myName = requestDao.getNameById(friendId);
					friendRequest.setFriendName(friendName);
					friendRequest.setMyName(myName);

					String newjsonDatagram = DatagramParser.toJsonDatagram(request, friendRequest);

					isOnline = Config.onlineMap.containsKey(friendId);
					if (isOnline) {
						// 如果好友在线，就将好友申请发送给好友

						String friendIp = Config.onlineMap.get(friendId);

						task = new SendMessageTask(friendIp, 5432, newjsonDatagram);
						task.start();
					} else {
						// 將其存起來
						requestDao.insert(friendRequest);
					}

					break;

				// 同意添加好友
				case Action.AGREE:

					myId = friendRequest.getMyId();
					friendId = friendRequest.getFriendId();

					System.out.println("myId = " + myId);
					friendName = requestDao.getNameById(myId);
					myName = requestDao.getNameById(friendId);
					friendRequest.setFriendName(friendName);
					friendRequest.setMyName(myName);

					// 将好友关系存储起来
					FriendDAO.insert(myId, friendId);

					isOnline = Config.onlineMap.containsKey(myId);
					if (isOnline) {
						// 如果我在线，就将好友同意申请发送给我
						String myIp = Config.onlineMap.get(myId);
						// 转发消息
						task = new SendMessageTask(myIp, 5432, jsonDatagram);
						task.start();

					} else {
						// 將其存起來
						requestDao.insert(friendRequest);
					}

					// 获取好友信息
					PluralistDao dao = new PluralistDao();
					Contact contact = dao.queryFriend(myId);

					System.out.println("agree : " + contact.toString());

					writer = StreamWapper.toPrintWriter(os);
					// 返回嗎
					responseData = new Datagram();
					responseData.setResponse(Status.SUCCESSFUL);
					responseData.setJsonStream(JSONParser.toJSONString(contact));

					writer.write(JSONParser.toJSONString(responseData));
					writer.flush();
					writer.close();
					break;

				// 同意公司的请求
				case "OK":
					
					myId = friendRequest.getMyId();
					friendId = friendRequest.getFriendId();
					
					CompanyDao cdao=new CompanyDao();
				
					Contact c = cdao.getCompany(myId);


					writer = StreamWapper.toPrintWriter(os);
					// 返回嗎
					responseData = new Datagram();
					responseData.setResponse(Status.SUCCESSFUL);
					responseData.setJsonStream(JSONParser.toJSONString(c));

					writer.write(JSONParser.toJSONString(responseData));
					writer.flush();
					writer.close();
					
					

					break;

				// 删除好友
				case Action.DELETE:

					if (friendRequest.getFriendId() != null) {
						friendId = friendRequest.getFriendId();
						myId = friendRequest.getMyId();

						// 删除好友关系
						FriendDAO.deleteFriend(myId, friendId);
						// 好友在线
						if (Config.onlineMap.containsKey(friendId)) {
							String friendIp = Config.onlineMap.get(friendId);
							// 转发消息
							task = new SendMessageTask(friendIp, 5432, jsonDatagram);
							task.start();
						} else {
							// 好友离线
							requestDao.insert(friendRequest);
						}
					}
					break;

				// todo : 拒绝添加好友
				case Action.REFUSE:
					break;

				default:
					break;
				}

				break;

			/**
			 * 查询朋友
			 */
			case Action.QUERY_FRIEND:

				id = DatagramParser.getEntity(jsonDatagram, new TypeToken<String>() {
				}.getType());
				// 获取好友信息
				PluralistDao dao = new PluralistDao();
				Contact contact = dao.queryFriend(id);

				writer = StreamWapper.toPrintWriter(os);
				// 返回嗎
				responseData = new Datagram();
				responseData.setResponse(Status.SUCCESSFUL);
				responseData.setJsonStream(JSONParser.toJSONString(contact));

				writer.write(JSONParser.toJSONString(responseData));
				writer.flush();

				writer.close();

				break;

			/**
			 * 申请
			 */
			case "APPLY":

				Recruit r = DatagramParser.getEntity(jsonDatagram, new TypeToken<Recruit>() {
				}.getType());

				if (r != null) {
					RecruitDao rDao = new RecruitDao();
					status = rDao.insert(r);

					String iId = r.getInfoId();
					String cId = rDao.getIdByInformation(iId);

					if (Config.onlineMap.containsKey(cId)) {

						String cIp = Config.onlineMap.get(cId);
						SendMessageTask task = new SendMessageTask(cIp, 5432, jsonDatagram);
						task.start();
					}
				}

				break;

			default:
				break;
			}

			os.close();
			is.close();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
