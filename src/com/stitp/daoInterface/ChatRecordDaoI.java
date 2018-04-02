package com.stitp.daoInterface;

import com.stitp.model.ChatRecord;

public interface ChatRecordDaoI {

	boolean insert(ChatRecord record);
	
	void query(String id);
	
	
}
