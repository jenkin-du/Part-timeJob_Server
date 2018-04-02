package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.stitp.daoInterface.RecruitDaoI;
import com.stitp.db.DBLinker;
import com.stitp.model.Recruit;
import com.stitp.util.Status;




public class RecruitDao implements RecruitDaoI {

	@Override
	public String insert(Recruit recruit) {

		boolean isExist = check(recruit.getPluralistId(), recruit.getInfoId());
		String status = "";

		if (!isExist) {
			String sql = "insert into t_recruit " + "(pluralist_id,information_id,apply_reason,recruit_status) "
					+ "values(?,?,?,?)";

			Connection conn = DBLinker.getConnection();
			try {
				PreparedStatement state = conn.prepareStatement(sql);
				state.setString(1, recruit.getPluralistId());
				state.setString(2, recruit.getInfoId());
				state.setString(3, recruit.getApplyReason());
				state.setString(4, Status.ENROLLED);

				state.execute();
				status = Status.SUCCESSFUL;
			} catch (SQLException e) {
				e.printStackTrace();
				status = Status.FAILING;
			}
		} else {
			status = Status.EXISTENT;
		}

		return status;
	}

	@Override
	public void update(Recruit recruit) {

	}

	@Override
	public void update(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Recruit recruit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Recruit> query() {

		return null;
	}

	@Override
	public Recruit query(String id) {

		return null;
	}

	/**
	 * 检查是否存在相同的记录
	 */
	@Override
	public boolean check(String pluralistId, String infoId) {

		boolean isExist = false;

		String sql = "select 1 as count from t_recruit" + " " + "where pluralist_id ='" + pluralistId + "'"
				+ " and information_id='" + infoId + "'" + " limit 1";

		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();

			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				int count = set.getInt("count");
				if (count == 1) {
					isExist = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isExist;
	}

	public HashMap<String, String> queryEnrolledInfo(String PId) {

		String sql = "select information_id,recruit_status from t_recruit" + " where pluralist_id='" + PId + "'";

		HashMap<String, String> map = new HashMap<>();
		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				String infoId = set.getString("information_id");
				String status = set.getString("recruit_status");

				map.put(infoId, status);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}

	
	/**
	 * 获得收藏的info id
	 * @param PId
	 * @return
	 */
	public ArrayList<String> queryCollectededInfo(String PId) {

		String sql = "select information_id from t_recruit" 
		+ " where collection_status='" + Status.COLLECTED + "' "
				+ " and pluralist_id='"+PId+"'";

		ArrayList<String> list=new ArrayList<>();
		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				String infoId = set.getString("information_id");

				list.add(infoId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	
	/**
	 * 收藏
	 * @param recruit
	 * @return
	 */
	public String collect(Recruit recruit) {

		boolean isExist = check(recruit.getPluralistId(), recruit.getInfoId());
		String status = "";

		if (!isExist) {
			String sql = "insert into t_recruit " 
		            + "(pluralist_id,information_id,collection_status) "
					+ "values(?,?,?)";

			Connection conn = DBLinker.getConnection();
			try {
				PreparedStatement state = conn.prepareStatement(sql);
				state.setString(1, recruit.getPluralistId());
				state.setString(2, recruit.getInfoId());
				state.setString(3, Status.COLLECTED);

				state.execute();
				status = Status.SUCCESSFUL;
			} catch (SQLException e) {
				e.printStackTrace();
				status = Status.FAILING;
			}
		} else {
			String sql = "update t_recruit " 
					+" set collection_status='"+Status.COLLECTED +"'"
					+" where pluralist_id='"+recruit.getPluralistId() +"'"
					+" and information_id='"+recruit.getInfoId() +"'";

			Connection conn = DBLinker.getConnection();
			try {

				Statement state=conn.createStatement();
				
				boolean right=state.execute(sql);
				
				if(!right){
					status = Status.SUCCESSFUL;					
				}else{
					status=Status.FAILING;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				status = Status.FAILING;
			}
		}

		return status;
	}
	
	
	/**
	 * 取消收藏
	 * @param recruit
	 * @return
	 */
	public String cancelCollect(Recruit recruit) {

		boolean isExist = check(recruit.getPluralistId(), recruit.getInfoId());
		String status = "";

		if (!isExist) {
				status = Status.FAILING;
		} else {
			String sql1 = "update t_recruit " 
					+" set collection_status=''"
					+" where pluralist_id='"+recruit.getPluralistId() +"'"
					+" and information_id='"+recruit.getInfoId() +"'"
					+" and recruit_status IS NOT NULL";
			
			String sql2 = "delete from t_recruit " 
					+" where pluralist_id='"+recruit.getPluralistId() +"'"
					+" and information_id='"+recruit.getInfoId() +"'"
					+" and recruit_status IS  NULL";

			Connection conn = DBLinker.getConnection();
			try {

				Statement state=conn.createStatement();
				
				boolean right=state.execute(sql1);
				boolean right2=state.execute(sql2);
				
//				if(!right&&!right2){
//					status = Status.SUCCESSFUL;					
//				}else{
//					status=Status.FAILING;
//				}
				status = Status.SUCCESSFUL;	
			} catch (SQLException e) {
				e.printStackTrace();
				status = Status.FAILING;
			}
		}

		return status;
	}

	public boolean updateStatus(String iId,String pId,String status) {
		// TODO Auto-generated method stub

		String sql = "update  t_recruit" 
		+ " set recruit_status='" + status + "' "
				+ " where information_id = '"+iId+"'"
		+" and pluralist_id='"+pId+"'";

		
		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			int count = state.executeUpdate(sql);
			if(count == 1){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

		
	}
	
	public String queryStatus(String iId,String pId) {
		String sql = "select recruit_status from  t_recruit " 
				+ " where information_id = '"+iId+"'"
				+" and pluralist_id='"+pId+"'";
		Connection conn=DBLinker.getConnection();
		
		try {
			Statement state=conn.createStatement();
			ResultSet set=state.executeQuery(sql);
			
			while(set.next()){
				String recruit_status=set.getString("recruit_status");
				return recruit_status;
				
			
			}
			
			
		}catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		return null;
	}
	
	
	
	public String getIdByInformation(String iId){
		
		String sql = "select company_id from  t_release" 
				+ " where information_id = '"+iId;
		
		String cId="";
				
		Connection conn=DBLinker.getConnection();
		
		try {
			Statement state=conn.createStatement();
			ResultSet set=state.executeQuery(sql);
			
			while(set.next()){
				cId=set.getString("company_id");
			}
			
			
		}catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		
		return cId;
	}


}
