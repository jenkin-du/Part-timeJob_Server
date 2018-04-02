package com.stitp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stitp.daoInterface.RecruitStatusDaoI;
import com.stitp.db.DBLinker;
import com.stitp.model.RecruitStatus;
import com.stitp.model.ReturnSet;
import com.stitp.util.Status;

public class RecruitStatusDao implements RecruitStatusDaoI{

	@Override
	public ReturnSet query(String pId,String status) {

		ReturnSet returnSet=new ReturnSet();
		ArrayList<RecruitStatus> arr=new ArrayList<>();
		
		String sql="select information_id from t_recruit "
				+ "where pluralist_id= '"+pId+"'";
		
	Connection conn=DBLinker.getConnection();
	
	try {
		Statement state=conn.createStatement();
		ResultSet set=state.executeQuery(sql);
		
		while(set.next()){
			RecruitStatus recruitStatus=new RecruitStatus();
			
			String infoId=set.getString("information_id");
//			String status=set.getString("recruit_status");
			
			recruitStatus.setInfoId(infoId);
			recruitStatus.setStatus(status);
			
			arr.add(recruitStatus);
		}
		
		returnSet.setStatus(Status.SUCCESSFUL);
		returnSet.setRecruitStatus(arr);
	} catch (SQLException e) {
		e.printStackTrace();
		returnSet.setStatus(Status.FAILING);
	}

		return returnSet;
	}

	@Override
	public boolean update(RecruitStatus recruitStatus) {
		// TODO Auto-generated method stub
		return false;
	}

}
