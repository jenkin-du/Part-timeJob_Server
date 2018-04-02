package com.stitp.daoInterface;

import java.util.ArrayList;

import com.stitp.model.Information;

public interface InformationDaoI {
	
	void add(Information information);//增加招聘信息
	
	void update(Information information);//更新招聘信息
	
	void update(String nameOrID);//更新招聘信息
	
	void delete();//删除所有招聘信息
	
	void delete(Information information);//删除单个招聘信息
	
	void delete(String nameOrId);//根据名字或ID删除招聘信息
	
	ArrayList< Information> query();//查询所有招聘信息
	
	Information query(String nameOrID);//根据名字或ID好查询招聘信息
	
	
}
