package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stitp.daoInterface.FriendRequestDaoI;
import com.stitp.db.DBLinker;
import com.stitp.model.FriendRequest;

public class FriendRequestDao implements FriendRequestDaoI {

	@Override
	public boolean insert(FriendRequest request) {

		boolean ok = false;

		String time = request.getTime();
		String myName = request.getMyName();
		String friendName = request.getFriendName();
		String myId = request.getMyId();
		String friendId = request.getFriendId();
		String requestReason = request.getRequestReason();
		String status = request.getStatus();

		String sql = "insert into  t_friend_request (my_id,friend_id,request_reason,status,my_name,friend_name,time)"
				+ " values(?,?,?,?,?,?,?)";

		Connection conn = DBLinker.getConnection();

		try {
			PreparedStatement state = conn.prepareStatement(sql);

			state.setString(1, myId);
			state.setString(2, friendId);
			state.setString(3, requestReason);
			state.setString(4, status);
			state.setString(5, myName);
			state.setString(6, friendName);
			state.setString(7, time);

			boolean isAdd = state.execute();
			if (!isAdd) {
				ok = true;
			} else {
				ok = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ok;
	}

	@Override
	public ArrayList<FriendRequest> query(String myId) {

		ArrayList<FriendRequest> friendRequestList = new ArrayList<>();

		String sql = "select * from t_friend_request " + "where friend_id='" + myId + "'";

		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {

				FriendRequest request = new FriendRequest();

				String myId1 = set.getString("my_id");
				String friendId = set.getString("friend_id");
				String myName = set.getString("my_name");
				String friendName = set.getString("friend_name");
				String requestReason = set.getString("request_reason");
				String status = set.getString("status");
				String time = set.getString("time");

				request.setMyId(myId1);
				request.setFriendId(friendId);
				request.setRequestReason(requestReason);
				request.setStatus(status);
				request.setTime(time);
				request.setMyName(myName);
				request.setFriendName(friendName);

				friendRequestList.add(request);
			}

			sql = "delete from t_friend_request where friend_id='" + myId + "'";
			state.execute(sql);
			
			set.close();
			state.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return friendRequestList;
	}

	@Override
	public boolean delete(String myId) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getNameById(String id) {

		String sql = "select name from t_pluralist " + "where pluralist_id='" + id + "'";
		String name = "";
		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				name = set.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return name;

	}

}
