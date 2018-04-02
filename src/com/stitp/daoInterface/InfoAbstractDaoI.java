package com.stitp.daoInterface;

import java.util.ArrayList;

import com.stitp.model.InfoAbstract;

public interface InfoAbstractDaoI {

	void add(InfoAbstract infoAbstract);

	void update(InfoAbstract infoAbstract);

	ArrayList<InfoAbstract> query();
	
	ArrayList<InfoAbstract> query(String city);
	
	

	void delete();

	void delete(InfoAbstract infoAbstract);

	void delete(String nameOrID);
}
