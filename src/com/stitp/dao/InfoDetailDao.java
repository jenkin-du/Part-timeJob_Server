package com.stitp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stitp.daoInterface.InfoDetailDaoI;
import com.stitp.db.DBLinker;
import com.stitp.model.InfoDetail;
import com.stitp.model.workAddress;


public class InfoDetailDao implements InfoDetailDaoI{

	public void add(InfoDetail infoDetail) {
		// TODO Auto-generated method stub
		
	}

	public void update(InfoDetail infoDetail) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<InfoDetail> query() {
		// TODO Auto-generated method stub
		return null;
	}

	public InfoDetail query(String id) {
		InfoDetail info=new InfoDetail();
		
		String sql1="select i.information_id,work_days,recruit_number,contact_phone," +
				"contact_name,work_detail,gender_request,c.company_id,c.name," +
				"province,detail_address,a.address_id,work_time " +
				"from t_information as i " +
				"left join t_address as a " +
				"on a.address_id=i.address_id " +
				"left join t_release as r " +
				"on i.information_id = r.information_id " +
				"left join t_company as c " +
				"on c.company_id=r.company_id " +
				"where r.information_id='"+id+"'";
		
		
		
		String sql2="select count(*) as recruited_number from t_recruit " +
				"where information_id ='"+id+"'";
		
		Connection conn=DBLinker.getConnection();
		try {
			Statement state=conn.createStatement();
			ResultSet set=state.executeQuery(sql1);
			set.next();
			workAddress address=new workAddress();
			
			address.setaId(set.getString("address_id"));
			address.setProvince(set.getString("province"));
			address.setDetailAddr(set.getString("detail_address"));
			
			info.setWorkTime(set.getString("work_time"));
			info.setAddress(address);
			info.setCompany(set.getString("name"));
			info.setCompanyId(set.getString("company_id"));
			info.setContactName(set.getString("contact_name"));
			info.setContactPhone(set.getString("contact_phone"));
			info.setGenderRequest(set.getString("gender_request"));
			info.setRecruitNumber(set.getInt("recruit_number"));
			info.setWorkDetail(set.getString("work_detail"));
			
			ResultSet set2=state.executeQuery(sql2);
			set2.next();
			info.setRecruitedNumber(set2.getInt("recruited_number"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return info;
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void delete(InfoDetail infoDetail) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String nameOrID) {
		// TODO Auto-generated method stub
		
	}

}
