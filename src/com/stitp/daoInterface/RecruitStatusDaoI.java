package com.stitp.daoInterface;

import com.stitp.model.RecruitStatus;
import com.stitp.model.ReturnSet;

public interface RecruitStatusDaoI {

	ReturnSet query(String pId,String status);//查询
	
	boolean update(RecruitStatus recruitStatus);//更新
}
