package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.stitp.db.DBLinker;
import com.stitp.model.company.*;;

public class InformationDao {
	

	public int findMaxID(ArrayList<Integer> arr) {
		int max = (int) arr.get(0);
		for (int i = 0; i < arr.size(); i++) {

			if (max < (int) arr.get(i)) {
				max = (int) arr.get(i);
			}
		}
		return max;

	}

	public ArrayList<Integer> getIdList() {
		String sql = "select information_id from  t_information ";
		Connection conn = DBLinker.getConnection();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				String myId1 = set.getString("information_id");

				int id = Integer.parseInt(myId1.substring(1));
				idList.add(id);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return idList;
	}

	public boolean add(Information information) {
		// TODO Auto-generated method stub
		
		boolean isExe=false,ok = false;
		String title = information.getTitle();
		String iId = information.getId();
		String description = information.getDescription();
		String salary = information.getSalary();
		String workAddress = information.getWorkAddress();
		Date startWorkTime = information.getStartWorkTime();
		
		String workTime = information.getWorkTime();
		int workDays = information.getWorkDays();
		String category = information.getCategory();
		int recruitNumber = information.getRecruitNumber();
		String contactName = information.getContactName();
		String contactPhone = information.getContactPhone();
		
		String workDetail = information.getWorkDetail();
		String heightRequest = information.getHeightRequest();
		String genderRequest = information.getGenderRequest();
		String experienceRequest = information.getExperienceRequest();
		String sql = "insert into t_information (information_id,description,salary,category,"
				+ "start_work_time,work_days,work_time,recruit_number,"
				+ "contact_name,contact_phone,work_detail,gender_request,height_request,experience_request,address_id,title)"
		        + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = DBLinker.getConnection();
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, iId);
			state.setString(2, description);
			state.setString(3, salary);
			state.setString(4, category);
			java.sql.Date sqlDate=new java.sql.Date(startWorkTime.getTime());
			state.setDate(5, sqlDate);
			
			state.setInt(6, workDays);	
			state.setString(7, workTime);
			state.setInt(8, recruitNumber);	
			
			state.setString(9, contactName);
			state.setString(10, contactPhone);
			state.setString(11, workDetail);
			state.setString(12, genderRequest);
			state.setString(13, heightRequest);
			state.setString(14, experienceRequest);
			state.setString(15, workAddress);
			state.setString(16, title);
			isExe = state.execute();
			
			if (!isExe) {
				ok = true;
			} else {
				ok = false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	public void update(Information information) {
		// TODO Auto-generated method stub

	}
	
	public void delete() {
		// TODO Auto-generated method stub

	}

	public void delete(Information information) {
		// TODO Auto-generated method stub

	}

	public void delete(String nameOrId) {
		// TODO Auto-generated method stub

	}

	

	public ArrayList<Information> query1(ArrayList<String> id) {
	
		
		AreaDao ad = new AreaDao();
		String sql = "select * from  t_information"
				+ " where information_id = '";
		System.out.println(sql);
		Connection conn = DBLinker.getConnection();
		ArrayList<Information> aList = new ArrayList<Information>();
		for (String s : id) {
			String newsql = sql +s+"' ";
			try {
				Statement state = conn.createStatement();
				System.out.println(newsql);
				ResultSet set = state.executeQuery(newsql);
				
				while (set.next()) {
					Information i = new Information();
					i.setDescription(set.getString("description"));
					i.setSalary(set.getString("salary"));
					i.setWorkAddress(ad.query(set.getString("address_id")));

					//java.util.Date date = set.getDate("start_work_time");
					java.util.Date  d=new java.util.Date (set.getDate("start_work_time").getTime());
					i.setStartWorkTime(d);
					i.setWorkTime(set.getString("work_time"));
					i.setWorkDays(set.getInt("work_days"));
					i.setCategory(set.getString("category"));
					i.setRecruitNumber(set.getInt("recruit_number"));
					i.setContactName(set.getString("contact_name"));
					i.setContactPhone(set.getString("contact_phone"));
					
					i.setWorkDetail(set.getString("work_detail"));
					i.setHeightRequest(set.getString("height_request"));
					i.setGenderRequest(set.getString("gender_request"));
					i.setExperienceRequest(set.getString("experience_request"));
					i.setTitle(set.getString("title"));
					i.setiId(set.getString("information_id"));
				
					aList.add(i);
				
				}
		
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return aList;
	}

	public void update(String nameOrID) {
		// TODO Auto-generated method stub

	}
	public ArrayList<String> getIdbyCId(String Cid) {
		Connection connection=DBLinker.getConnection();
		ArrayList<String> iIdlist = new ArrayList<>();
		String id = null;
		String sql="select information_id from t_release where company_id = '"
				+Cid+"' ";
		try {
			
			Statement state=connection.createStatement();
		//	for (String Cid : CidList) {
			//	String newsql= sql +Cid+"' ";
				ResultSet set=state.executeQuery(sql);
				
				while(set.next()){
					//System.out.println(newsql);
					iIdlist.add(set.getString("information_id"));
				}
			System.out.println(iIdlist);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return iIdlist;
		
	}

	
	public ArrayList<Pluralist> getPIdbyIId(String Iid) {
		Connection connection=DBLinker.getConnection();
		ArrayList<Pluralist> plist = null;	
		String id = null;
		String sql="select pluralist_id from t_recruit"
				+ " where information_id = '"+Iid+"' ";
		try {


		plist = new ArrayList<>();
		
		Statement state=connection.createStatement();
		
		ResultSet set=state.executeQuery(sql);
		
		while(set.next()){
			String pid = set.getString("pluralist_id");
			Statement state1=connection.createStatement();
			System.out.println(pid);
			String sql1="select * from t_pluralist"
					+ " where pluralist_id = '"+pid+"' ";
			ResultSet set1=state1.executeQuery(sql1);
			
			while(set1.next()){
			Pluralist p = new Pluralist();
			p.setName(set1.getString("name"));
			p.setPhone(set1.getString("phone"));
			p.setGender(set1.getString("gender"));
			p.setAge(set1.getInt("age"));
			p.setEducationBackground(set1.getString("education_background"));
			p.setpId(set.getString("pluralist_id"));
			plist.add(p);
			}
		}
	
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plist;
		
	}

	
}
