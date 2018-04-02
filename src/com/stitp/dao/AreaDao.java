package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.stitp.model.*;
import com.stitp.db.DBLinker;

public class AreaDao {
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
		String sql = "select address_id from  t_address ";
		Connection conn = DBLinker.getConnection();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				String myId1 = set.getString("address_id");

				int id = Integer.parseInt(myId1.substring(1));
				idList.add(id);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return idList;
	}

	public ArrayList<String> getProvince() throws Exception {
		String sql = " select areaname from area where level=1 ";
		Connection conn = DBLinker.getConnection();
		Statement state = conn.createStatement();
		ResultSet set = state.executeQuery(sql);
		ArrayList<String> pList = new ArrayList<String>();
		while (set.next()) {
			String provinces = set.getString("areaname");// 省或直辖市

			pList.add(provinces);

		}
		return pList;
	}

	public ArrayList<String> getCity(String pName) {
		Connection conn = DBLinker.getConnection();
		ArrayList<String> citiesList = null;
		// HashMap<String ,ArrayList<String>> map1 = new HashMap<>();
		try {
			Statement state1 = conn.createStatement();

			String sql1 = " select id from area where area.areaname = '" + pName + "'";
			// Statement state1;
			// state1 = conn.createStatement();
			ResultSet set1 = state1.executeQuery(sql1);
			while (set1.next()) {
				int pID = set1.getInt("id");
				Statement state2 = conn.createStatement();

				String sql2 = " select areaname from area where parentid = " + pID;
				ResultSet set2 = state2.executeQuery(sql2);
				citiesList = new ArrayList<String>();
				while (set2.next()) {

					String cities = set2.getString("areaname");
					citiesList.add(cities);
					// map1.put(provinceName, citiesList);
					// System.out.println(citiesList);
				}
				// map1.put(cityName, citiesList);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return citiesList;

	}

	public ArrayList<String> getZone(String cityName) {
		Connection conn = DBLinker.getConnection();
		ArrayList<String> zonesList = null;
		// HashMap<String ,ArrayList<String>> map1 = new HashMap<>();
		try {
			Statement state1 = conn.createStatement();

			String sql1 = " select id from area where area.areaname = '" + cityName + "'";
			// Statement state1;
			// state1 = conn.createStatement();
			ResultSet set1 = state1.executeQuery(sql1);
			while (set1.next()) {
				int pID = set1.getInt("id");
				Statement state2 = conn.createStatement();

				String sql2 = " select areaname from area where parentid = " + pID;
				ResultSet set2 = state2.executeQuery(sql2);
				zonesList = new ArrayList<String>();
				while (set2.next()) {

					String cities = set2.getString("areaname");
					zonesList.add(cities);
					// map1.put(provinceName, citiesList);
					// System.out.println(citiesList);
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return zonesList;

	}

	public boolean insert(Area a) {
		String province = a.getProvince();
		String city = a.getCity();
		String zone = a.getZone();
		String detail = a.getDetail();
		String id = a.getId();
		float longtitude = a.getLongtitude();
		float latitude = a.getLatitude();
		String sql = "insert into  t_address (address_id,province,city,district,detail_address,longtitude,latitude)" + " values(?,?,?,?,?,?,?)";
		boolean isOK = false;
		Connection conn = DBLinker.getConnection();
		try {
			PreparedStatement state = conn.prepareStatement(sql);

			state.setString(1, id);
			state.setString(2, province);
			state.setString(3, city);
			state.setString(4, zone);
			state.setString(5, detail);
			state.setFloat(6, longtitude);
			state.setFloat(7, latitude);
			isOK = !state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("-----------------"+isOK);
		return isOK;
	}
	
	public String query(String aid){
		Connection conn = DBLinker.getConnection();
		StringBuilder sb = new StringBuilder();
		String district = null;
		try {
			Statement state1 = conn.createStatement();
			String sql1 = " select * from t_address"
					+ " where address_id = '" + aid + "'";
			ResultSet set1 = state1.executeQuery(sql1);
			while (set1.next()) {
				district = set1.getString("district");
				sb.append(set1.getString("province")).append(set1.getString("city"))
				.append(district).append(set1.getString("detail_address"));
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return sb.toString()+","+district;
		
	}

}
