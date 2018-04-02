package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stitp.daoInterface.ChatRecordDaoI;
import com.stitp.db.DBLinker;
import com.stitp.model.ChatRecord;

public class ChatRecordDao implements ChatRecordDaoI {

	@Override
	public boolean insert(ChatRecord record) {
		boolean ok = false;

		String myselfId = record.getMyId();
		String opposingId = record.getFriendId();
		String message = record.getMessage();
		String time = record.getTime();

		String sql = "insert into t_chat_record(my_id,friend_id,message,time)" + " values(?,?,?,?)";

		Connection conn = DBLinker.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, myselfId);
			statement.setString(2, opposingId);
			statement.setString(3, message);
			statement.setString(4, time);

			ok = !statement.execute();

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;

	}

	@Override
	public void query(String id) {
	
	}

	/**
	 * 获得离线消息
	 * 
	 * @return
	 */
	public ArrayList<ChatRecord> getOfflineRecord(String myId) {
		ArrayList<ChatRecord> records = new ArrayList<>();
		ChatRecord record;

		Connection conn = DBLinker.getConnection();
		String sql = "select * from t_chat_record where friend_id='" + myId + "'";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {

				record=new ChatRecord();
				record.setTime(rs.getString("time"));
				record.setMessage(rs.getString("message"));
				record.setFriendId(rs.getString("friend_id"));
				record.setMyId(rs.getString("my_id"));
				
				records.add(record);
			}
			
			rs.close();
			//删除
			sql = "delete from t_chat_record where friend_id='" + myId + "'";
			state.execute(sql);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

}
