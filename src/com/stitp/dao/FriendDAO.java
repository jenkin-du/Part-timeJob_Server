package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stitp.db.DBLinker;

public class FriendDAO {

	/**
	 * 获取所有好友id
	 * 
	 * @param myId
	 * @return
	 */
	public static ArrayList<String> getAllMyFriendsId(String myId) {

		ArrayList<String> friends = new ArrayList<>();
		Connection conn = DBLinker.getConnection();

		String friendId;

		String sql = "select * from t_friend where my_id ='" + myId + "' or friend_id='" + myId + "'";
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {

				friendId = rs.getString("my_id");
				if (friendId.equals(myId)) {
					friendId = rs.getString("friend_id");
				}

				friends.add(friendId);
			}

			state.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return friends;
	}

	/**
	 * 插入好友信息
	 * 
	 * @param myId
	 * @param friendId
	 */
	public static void insert(String myId, String friendId) {

		Connection conn = DBLinker.getConnection();
		String sql = "insert into t_friend(my_id,friend_id)values(?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, myId);
			ps.setString(2, friendId);

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除好友
	 * 
	 * @param myId
	 * @param friendId
	 */
	public static void deleteFriend(String myId, String friendId) {

		Connection conn = DBLinker.getConnection();
		String sql = "delete from t_friend where ( my_id='" + myId + "' and friend_id='" + friendId
				+ "' ) or ( friend_id='" + myId + "' and my_id='" + friendId + "' ) ";

		try {
			Statement state=conn.createStatement();
			state.execute(sql);
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
