package com.stitp.daoInterface;

import java.util.ArrayList;

import com.stitp.model.FriendRequest;

public interface FriendRequestDaoI {

	boolean insert(FriendRequest request);//插入
	
	ArrayList<FriendRequest> query(String myId);//查询
	
	boolean delete(String myId);//删除
	
	
}
