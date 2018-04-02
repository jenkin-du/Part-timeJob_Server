package com.stitp.daoInterface;

import java.util.ArrayList;

import com.stitp.model.Recruit;

public interface RecruitDaoI {

	String insert(Recruit recruit);

	void update(Recruit id);

	void update(String id);

	void delete();

	void delete(Recruit recruit);

	void delete(String id);

	ArrayList<Recruit> query();

	Recruit query(String id);

	boolean check(String pluralist, String infoId);// 查询是否已经存在记录
	
	

}
