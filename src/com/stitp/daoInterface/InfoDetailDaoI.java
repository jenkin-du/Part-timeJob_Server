package com.stitp.daoInterface;

import java.util.ArrayList;

import com.stitp.model.InfoDetail;

public interface InfoDetailDaoI {

	void add(InfoDetail infoDetail);

	void update(InfoDetail infoDetail);

	ArrayList<InfoDetail> query();

	InfoDetail query(String nameOrID);

	void delete();

	void delete(InfoDetail infoDetail);

	void delete(String nameOrID);
}
