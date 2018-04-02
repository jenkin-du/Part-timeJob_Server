package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.stitp.daoInterface.PluralistDaoI;
import com.stitp.db.DBLinker;
import com.stitp.model.Contact;
import com.stitp.model.ContactSearchedPerson;
import com.stitp.model.Datagram;
import com.stitp.model.Friend;
import com.stitp.model.Pluralist;
import com.stitp.model.ResponseSet;
import com.stitp.model.ReturnSet;
import com.stitp.util.Action;
import com.stitp.util.JSONParser;
import com.stitp.util.Status;

public class PluralistDao implements PluralistDaoI {

	/**
	 * 添加记录
	 */

	@Override
	public ReturnSet insert(Pluralist pluralist) {

		String name = pluralist.getName();
		String phone = pluralist.getPhone();
		String password = pluralist.getPassword();

		ReturnSet returnSet = new ReturnSet();

		boolean isExist = checkPhone(phone);
		String status = "";

		if (!isExist) {

			String sql = "insert into  t_pluralist (pluralist_id,phone,password,name)" + " values(?,?,?,?)";

			Connection conn = DBLinker.getConnection();

			String pluralistId = calculateNewId(conn);

			Pluralist p = new Pluralist();
			p.setId(pluralistId);
			returnSet.setPluralist(p);
			// 将id好返回，用于客户端有关操作

			try {
				PreparedStatement state = conn.prepareStatement(sql);

				state.setString(1, pluralistId);
				state.setString(2, phone);
				state.setString(3, password);
				state.setString(4, name);

				boolean isAdd = state.execute();
				if (!isAdd) {
					status = Status.SUCCESSFUL;
				} else {
					status = Status.FAILING;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			status = Status.EXISTENT;
		}

		returnSet.setStatus(status);

		return returnSet;
	}

	public void update(Pluralist pluralist) {

		String sql = "update t_pluralist set phone=?,gender=?,age=?,height=?,school=?,"
				+ "education_background=?,feature=?,experience=?,email=?,head_img_name=? " + "where pluralist_id=?";
		Connection conn = DBLinker.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, pluralist.getPhone());
			ps.setString(2, pluralist.getGender());
			ps.setInt(3, pluralist.getAge());
			ps.setInt(4, pluralist.getHeight());
			ps.setString(5, pluralist.getSchool());
			ps.setString(6, pluralist.getEducationBackground());
			ps.setString(7, pluralist.getFeature());
			ps.setString(8, pluralist.getExperience());
			ps.setString(9, pluralist.getEmail());
			ps.setString(10, pluralist.getImageName());
			ps.setString(11, pluralist.getId());

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(String nameOrID) {
		// TODO Auto-generated method stub

	}

	public void delete() {
		// TODO Auto-generated method stub

	}

	public void delete(Pluralist pluralist) {
		// TODO Auto-generated method stub

	}

	public void delete(String nameOrID) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Pluralist> query() {

		return null;
	}

	// public Pluralist query(String nameOrID) {
	//
	// return null;
	// }

	/**
	 * 验证密码
	 */
	@Override
	public ReturnSet validate(String phone, String password) {
		ReturnSet returnSet = new ReturnSet();

		returnSet.setStatus(Status.WRONG);

		String sql = "select * from t_pluralist " + "where phone='" + phone + "'";
		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				String pwd = set.getString("password");
				String pId = set.getString("pluralist_id");
				if (password.equals(pwd)) {

					returnSet.setStatus(Status.RIGHT);

					Pluralist p = new Pluralist();
					p.setId(pId);
					p.setAge(set.getInt("age"));
					p.setPhone(phone);
					p.setEducationBackground(set.getString("education_background"));
					p.setEmail(set.getString("email"));
					p.setExperience(set.getString("experience"));
					p.setFeature(set.getString("feature"));
					p.setGender(set.getString("gender"));
					p.setHeight(set.getInt("height"));
					p.setImageName(set.getString("head_img_name"));
					p.setName(set.getString("name"));
					p.setSalary(set.getFloat("salary"));
					p.setSchool(set.getString("school"));
					returnSet.setPluralist(p);

					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnSet;
	}

	/**
	 * 验证密码
	 */
	public boolean validatePassword(String phone, String password) {

		boolean isRight = false;

		String sql = "select password from t_pluralist " + "where phone='" + phone + "'";
		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				String pwd = set.getString("password");
				if (password.equals(pwd)) {

					isRight = true;
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isRight;
	}

	public void updatePhone(String id, String phone) {

		String sql = "update t_pluralist set phone=? where pluralist_id=?";

		Connection conn = DBLinker.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, id);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void changePassword(String id, String password) {

		String sql = "update t_pluralist set password=? where pluraist_id=?";
		Connection conn = DBLinker.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, id);
			ps.setString(2, password);
			ps.execute();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检验是否有相同的记录
	 */
	@Override
	public boolean checkPhone(String phone) {

		boolean isExist = false;

		String sql = "select 1 as count from t_pluralist " + "where phone='" + phone + "'";

		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				int count = set.getInt("count");
				if (count == 1) {
					isExist = true;
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isExist;
	}

	/**
	 * 获得兼职者的薪水
	 */

	public ResponseSet querySalary(String pId) {

		ResponseSet responseSet = new ResponseSet();

		String sql = "select salary from t_pluralist " + "where pluralist_id='" + pId + "'";
		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {

				float salary = set.getFloat("salary");
				responseSet.setObj(salary);
			}
			responseSet.setStatus(Status.SUCCESSFUL);

		} catch (SQLException e) {
			responseSet.setStatus(Status.FAILING);
			e.printStackTrace();
		}

		return responseSet;

	}

	/**
	 * 获得兼职者的薪水
	 */

	public Datagram querySearchedPluralist(String nameOrphone) {
		Datagram datagram = new Datagram();

		boolean isNumber = checkIsNumber(nameOrphone);

		String sql = "";
		if (!isNumber) {
			sql = "select pluralist_id,name,head_img_name,school from t_pluralist " + "where name='" + nameOrphone
					+ "'";
		} else {
			sql = "select pluralist_id,name,head_img_name,school from t_pluralist " + "where phone='" + nameOrphone
					+ "'";
		}

		System.out.println(sql);

		Connection conn = DBLinker.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			ArrayList<ContactSearchedPerson> arrayList = new ArrayList<>();

			while (set.next()) {

				ContactSearchedPerson person = new ContactSearchedPerson();

				String pId = set.getString("pluralist_id");
				String name = set.getString("name");
				String headImgURL = set.getString("head_img_name");
				String school = set.getString("school");

				person.setpId(pId);
				person.setName(name);
				person.setImageName(headImgURL);
				person.setSchool(school);

				arrayList.add(person);

			}
			datagram.setJsonStream(JSONParser.toJSONString(arrayList));
			datagram.setResponse(Status.SUCCESSFUL);

		} catch (SQLException e) {
			datagram.setResponse(Status.FAILING);
			e.printStackTrace();
		}
		System.out.println(datagram);

		return datagram;

	}

	/**
	 * 获得兼职者的薪水
	 */
	public Contact queryFriend(String friendId) {

		String sql = "";
		sql = "select * from t_pluralist where pluralist_id='" + friendId + "'";

		System.out.println(sql);

		Connection conn = DBLinker.getConnection();

		Contact contact = new Contact();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {

				String id = set.getString("pluralist_id");
				String name = set.getString("name");
				String imageName = set.getString("head_img_name");

				contact.setId(id);
				contact.setName(name);
				contact.setImageName(imageName);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contact;

	}

	/**
	 * 获得兼职者的薪水
	 */
	public Friend findFriend(String friendId) {

		String sql = "";
		sql = "select * from t_pluralist " + "where pluralist_id='" + friendId + "'";

		System.out.println(sql);

		Connection conn = DBLinker.getConnection();

		Friend friend = new Friend();

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {

				String pId = set.getString("pluralist_id");
				String name = set.getString("name");
				String headImgURL = set.getString("head_img_name");

				String school = set.getString("school");
				String age = set.getString("age");
				String height = set.getString("height");
				String educationBackground = set.getString("education_background");
				String email = set.getString("email");
				String gender = set.getString("gender");
				String phone = set.getString("phone");

				friend.setId(pId);
				friend.setName(name);

				friend.setAge(age);
				friend.setHeight(height);
				friend.setEducation(educationBackground);
				friend.setEmail(email);
				friend.setGender(gender);
				friend.setPhone(phone);
				friend.setSchool(school);

				friend.setImageName(headImgURL);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return friend;

	}

	/**
	 * 插入或更新图片
	 * 
	 * @param imageName
	 * @param imageCode
	 */
	public void insertOrUpdateImage(String imageName, String imageCode, String id) {

		Connection conn = DBLinker.getConnection();

		String sql = "select i.name as image_name from t_image as i,t_pluralist as p where p.pluralist_id = '" + id
				+ "' and p.head_img_name = i.name";
		Statement state = null;
		try {
			state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);

			if (rs.next()) {
				String oldImageName = rs.getString("image_name");
				sql = "delete from t_image where name ='" + oldImageName + "'";
				state.execute(sql);
			}

			sql = "insert t_image(name,code)values('" + imageName + "','" + imageCode + "')";
			state.execute(sql);

			sql = "update t_pluralist set head_img_name = '" + imageName + "' where pluralist_id = '" + id + "'";
			state.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据名字获取照片
	 */
	public String getImageByName(String imageName) {

		Connection conn = DBLinker.getConnection();

		String sql = "select code from t_image where name = '" + imageName + "'";
		System.out.println(sql);
		Statement state = null;

		String imageCode = "";

		try {
			state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);

			if (rs.next()) {
				imageCode = rs.getString("code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return imageCode;
	}

	/**
	 * 根据名字获取照片
	 */
	public String getImageByPhone(String phone) {

		Connection conn = DBLinker.getConnection();

		String sql = "select i.code from t_image as i,t_pluralist as p where p.phone = '" + phone
				+ "' and i.name = p.head_img_name";
		Statement state = null;

		String imageCode = "";

		try {
			state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);

			if (rs.next()) {
				imageCode = rs.getString("code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return imageCode;
	}

	/**
	 * 算出新的的编号
	 * 
	 * @return
	 */
	private String calculateNewId(Connection conn) {
		String maxId = "";
		int MId = 0;
		String sql = "select pluralist_id from t_pluralist ";

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				String pId = set.getString("pluralist_id");
				int tempId = Integer.parseInt(pId.substring(1));
				if (tempId > MId) {
					MId = tempId;
				}
			}

			maxId = "P" + String.valueOf(MId + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;

	}

	/**
	 * 判断字符串中是否为数字
	 */
	private boolean checkIsNumber(String s) {

		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(s).matches();
	}
}
