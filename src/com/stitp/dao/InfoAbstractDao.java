package com.stitp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.stitp.daoInterface.InfoAbstractDaoI;
import com.stitp.db.DBLinker;
import com.stitp.model.InfoAbstract;
import com.stitp.model.workAddress;
import com.stitp.util.Status;

public class InfoAbstractDao implements InfoAbstractDaoI {

	public void add(InfoAbstract infoAbstract) {
		// TODO Auto-generated method stub

	}

	public void update(InfoAbstract infoAbstract) {
		// TODO Auto-generated method stub

	}

	/**
	 * 查询所有信息
	 */
	public ArrayList<InfoAbstract> query() {
		ArrayList<InfoAbstract> arr = new ArrayList<InfoAbstract>();

		Connection connection = DBLinker.getConnection();
		String sql = "select i.information_id,description,salary,"
				+ "category,start_work_time,work_days,release_time,district,"
				+ "longitude,latitude "
				+ "from t_information as i left join t_release as r "
				+ "on i.information_id = r.information_id "
				+ "left join t_address as a "
				+ "on a.address_id=i.address_id ";

		Timestamp updateTime = null;
		Timestamp startTime = null;
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
		String currentTimeStr = dateFormater.format(date);

		try {
			Statement state = connection.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				InfoAbstract info = new InfoAbstract();

				workAddress address = new workAddress();
				address.setDistrict(set.getString("district"));
				address.setLongitude(set.getString("longitude"));
				address.setLatitude(set.getString("latitude"));
				info.setAddress(address);

				info.setiId(set.getString("information_id"));
				info.setDescription(set.getString("description"));
				info.setSalary(set.getString("salary"));
				info.setCategory(set.getString("category"));

				startTime = set.getTimestamp("start_work_time");
				String startTimeStr = dateFormater2.format(startTime)
						.toString();
				info.setStartWorkTime(startTimeStr);

				info.setWorkDays(set.getInt("work_days"));

				// 计算更新时间
				updateTime = set.getTimestamp("release_time");
				String timeStr = dateFormater.format(updateTime).toString();
				String updateTimeStr = calculateUpdateTime(currentTimeStr,
						timeStr);
				info.setUpdateTime(updateTimeStr);

				arr.add(info);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	/**
	 * 根据城市选择消息
	 */
	public ArrayList<InfoAbstract> query(String city) {

		ArrayList<InfoAbstract> arr = new ArrayList<InfoAbstract>();

		Connection connection = DBLinker.getConnection();
		String sql = "select i.information_id,description,salary,"
				+ "category,start_work_time,work_days,release_time,district,"
						+ "longitude,latitude "
				+ "from t_information as i left join t_release as r "
				+ "on i.information_id = r.information_id "
				+ "left join t_address as a "
				+ "on a.address_id=i.address_id " + "where a.city='" +city+ "'";

		System.out.println(sql);
		
		Timestamp updateTime = null;
		Timestamp startTime = null;
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
		String currentTimeStr = dateFormater.format(date);

		try {
			Statement state = connection.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				InfoAbstract info = new InfoAbstract();

				workAddress address = new workAddress();
				address.setDistrict(set.getString("district"));
				address.setLongitude(set.getString("longitude"));
				address.setLatitude(set.getString("latitude"));
				info.setAddress(address);

				info.setiId(set.getString("information_id"));
				info.setDescription(set.getString("description"));
				info.setSalary(set.getString("salary"));
				info.setCategory(set.getString("category"));

				startTime = set.getTimestamp("start_work_time");
				String startTimeStr = dateFormater2.format(startTime)
						.toString();
				info.setStartWorkTime(startTimeStr);

				info.setWorkDays(set.getInt("work_days"));

				// 计算更新时间
				updateTime = set.getTimestamp("release_time");
				if(updateTime!=null) {
					String timeStr = dateFormater.format(updateTime).toString();
					String updateTimeStr = calculateUpdateTime(currentTimeStr,
							timeStr);
					info.setUpdateTime(updateTimeStr);
				}
				
				arr.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	/**
	 * 根据兼职者id号和兼职状态选择兼职信息
	 */
	public ArrayList<InfoAbstract> query(String pluralistId,String status) {

		ArrayList<InfoAbstract> arr = new ArrayList<InfoAbstract>();

		Connection connection = DBLinker.getConnection();
		String sql = "select i.information_id,description,salary,"
				+ "category,start_work_time,work_days,release_time,district,"
				+ "longitude,latitude "
				+ "from t_information as i left join t_release as r "
				+ "on i.information_id = r.information_id "
				+ "left join t_address as a "
				+ "on a.address_id=i.address_id " 
				+"left join t_recruit as t "
				+" on t.information_id=i.information_id "
				+ "where t.pluralist_id='"+pluralistId+"' "
				+ "and t.recruit_status='"+status+"'";
		
		System.out.println(sql);
		
		Timestamp updateTime = null;
		Timestamp startTime = null;
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
		String currentTimeStr = dateFormater.format(date);

		try {
			Statement state = connection.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				InfoAbstract info = new InfoAbstract();

				workAddress address = new workAddress();
				address.setDistrict(set.getString("district"));
				address.setLongitude(set.getString("longitude"));
				address.setLatitude(set.getString("latitude"));
				info.setAddress(address);

				info.setiId(set.getString("information_id"));
				info.setDescription(set.getString("description"));
				info.setSalary(set.getString("salary"));
				info.setCategory(set.getString("category"));

				startTime = set.getTimestamp("start_work_time");
				String startTimeStr = dateFormater2.format(startTime)
						.toString();
				info.setStartWorkTime(startTimeStr);

				info.setWorkDays(set.getInt("work_days"));

				// 计算更新时间
				updateTime = set.getTimestamp("release_time");
				String timeStr = dateFormater.format(updateTime).toString();
				String updateTimeStr = calculateUpdateTime(currentTimeStr,
						timeStr);
				info.setUpdateTime(updateTimeStr);

				arr.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	/**
	 * 根据兼职者收藏的信息
	 */
	public ArrayList<InfoAbstract> queryCollectedInfo(String pluralistId) {

		ArrayList<InfoAbstract> arr = new ArrayList<InfoAbstract>();

		Connection connection = DBLinker.getConnection();
		String sql = "select i.information_id,description,salary,"
				+ "category,start_work_time,work_days,release_time,district,"
				+ "longitude,latitude "
				+ "from t_information as i left join t_release as r "
				+ "on i.information_id = r.information_id "
				+ "left join t_address as a "
				+ "on a.address_id=i.address_id " 
				+"left join t_recruit as t "
				+" on t.information_id=i.information_id "
				+ "where t.pluralist_id='"+pluralistId+"' "
				+ "and t.collection_status='"+Status.COLLECTED+"'";
		
		System.out.println(sql);
		
		Timestamp updateTime = null;
		Timestamp startTime = null;
		Date date = new Date();
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");
		String currentTimeStr = dateFormater.format(date);

		try {
			Statement state = connection.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				InfoAbstract info = new InfoAbstract();

				workAddress address = new workAddress();
				address.setDistrict(set.getString("district"));
				address.setLongitude(set.getString("longitude"));
				address.setLatitude(set.getString("latitude"));
				info.setAddress(address);

				info.setiId(set.getString("information_id"));
				info.setDescription(set.getString("description"));
				info.setSalary(set.getString("salary"));
				info.setCategory(set.getString("category"));

				startTime = set.getTimestamp("start_work_time");
				String startTimeStr = dateFormater2.format(startTime)
						.toString();
				info.setStartWorkTime(startTimeStr);

				info.setWorkDays(set.getInt("work_days"));

				// 计算更新时间
				updateTime = set.getTimestamp("release_time");
				String timeStr = dateFormater.format(updateTime).toString();
				String updateTimeStr = calculateUpdateTime(currentTimeStr,
						timeStr);
				info.setUpdateTime(updateTimeStr);

				arr.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	/**
	 * 计算更新时间
	 * 
	 * @param currentTimeStr
	 * @param timeStr
	 * @return
	 */
	private static String calculateUpdateTime(String currentTimeStr,
			String timeStr) {

		String updateTimeStr = "";

		int cYear = getOkNumber(currentTimeStr.substring(0, 4));
		int cMonth = getOkNumber(currentTimeStr.substring(5, 7));
		int cDay = getOkNumber(currentTimeStr.substring(8, 10));
		int cHour = getOkNumber(currentTimeStr.substring(11, 13));
		int cMinute = getOkNumber(currentTimeStr.substring(14, 16));
		int cSecond = getOkNumber(currentTimeStr.substring(17));

		int year = getOkNumber(timeStr.substring(0, 4));
		int month = getOkNumber(timeStr.substring(5, 7));
		int day = getOkNumber(timeStr.substring(8, 10));
		int hour = getOkNumber(timeStr.substring(11, 13));
		int minute = getOkNumber(timeStr.substring(14, 16));
		int second = getOkNumber(timeStr.substring(17));

		if (cYear - year > 0) {
			updateTimeStr = String.valueOf(cYear - year) + "年前";
			return updateTimeStr;
		} else if (cMonth - month > 0) {
			updateTimeStr = String.valueOf(cMonth - month) + "月前";
			return updateTimeStr;
		} else if (cDay - day > 0) {
			updateTimeStr = String.valueOf(cDay - day) + "天前";
			return updateTimeStr;
		} else if (cHour - hour > 0) {
			updateTimeStr = String.valueOf(cHour - hour) + "小时前";
			return updateTimeStr;
		} else if (cMinute - minute > 0) {
			updateTimeStr = String.valueOf(cMinute - minute) + "分钟前";
			return updateTimeStr;
		} else if (cSecond - second > 0) {
			updateTimeStr = "刚刚";
			return updateTimeStr;
		}
		return updateTimeStr;
	}

	/**
	 * 根据字符串数字获得正确的int型数字
	 * @param numStr
	 * @return
	 */
	private static int getOkNumber(String numStr) {

		String str = numStr;
		if (numStr.substring(0, 1).equals("0")) {
			str = numStr.substring(1);
		}
		return Integer.parseInt(str);
	}

	

	public void delete() {
		// TODO Auto-generated method stub

	}

	public void delete(InfoAbstract infoAbstract) {
		// TODO Auto-generated method stub

	}

	public void delete(String nameOrID) {
		// TODO Auto-generated method stub

	}

	

}
