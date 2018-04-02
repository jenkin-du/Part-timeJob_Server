package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.stitp.db.DBLinker;
import com.stitp.model.ReleaseInfo;

public class ReleaseDao {
	public boolean insert(ReleaseInfo rinfo){
		boolean isOK = false;
		String companyid = rinfo.getCompanyid();
		String infoid =rinfo.getInfoid();
		String time=rinfo.getTime();
		String sql = "insert into  t_release (company_id,information_id,release_time)" 
		+ " values(?,?,?)";
		Connection conn = DBLinker.getConnection();
		try {
			PreparedStatement state = conn.prepareStatement(sql);

			state.setString(1, companyid);
			state.setString(2, infoid);
			state.setString(3, time);
			
			isOK = !state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isOK;
	}
}
