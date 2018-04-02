package com.stitp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stitp.daoInterface.CompanyDaoI;
import com.stitp.db.DBLinker;
import com.stitp.model.Company;
import com.stitp.model.Contact;
import com.stitp.model.ContactSearchedPerson;

public class CompanyDao implements CompanyDaoI {

	public int findMaxID(ArrayList<Integer> arr) {
		int max = (int) arr.get(0);
		for (int i = 0; i < arr.size(); i++) {

			if (max < (int) arr.get(i)) {
				max = (int) arr.get(i);
			}
		}
		return max;

	}

	@Override
	public boolean add(Company company) {
		String companyName = company.getName();
		String password = company.getPassword();

		String contactName = company.getLegalPerson();
		String contactPhone = company.getPhone();
		String address = company.getAddress();
		String description = company.getDescription();
		String id = company.getId();
		boolean isExe = false, ok = false;
		// TODO Auto-generated method stub
		String sql = "insert into  t_company (name,password,legal_person,phone,address,company_description,company_id)"
				+ " values(?,?,?,?,?,?,?)";

		Connection conn = DBLinker.getConnection();
		try {
			PreparedStatement state = conn.prepareStatement(sql);

			state.setString(1, companyName);
			state.setString(2, password);
			state.setString(3, contactName);
			state.setString(4, contactPhone);
			state.setString(5, address);
			state.setString(6, description);
			state.setString(7, id);
			isExe = state.execute();
			if (!isExe) {
				ok = true;
			} else {
				ok = false;
			}
			// state.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;

	}

	public ArrayList<Company> query() {

		ArrayList<Company> array = new ArrayList<Company>();

		Connection connection = DBLinker.getConnection();

		String sql = "select * from T_company";

		try {

			Statement state = connection.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				Company company = new Company();

				company.setId(set.getString("company_id"));
				company.setName(set.getString("name"));
				company.setAddress(set.getString("address"));
				company.setDescription(set.getString("company_description"));
				company.setPhone(set.getString("phone"));
				company.setLegalPerson(set.getString("legal_person"));
				company.setPassword(set.getString("password"));
				company.setSatisfaction(set.getInt("satisfaction"));

				array.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return array;
	}

	/**
	 * 根据id号查询公司信息
	 */
	public Company query(String ID) {

		Company company = new Company();

		Connection connection = DBLinker.getConnection();

		String sql = "select name,address,company_description,satisfaction " + " from T_company " + "where company_id='"
				+ ID + "'";

		try {

			Statement state = connection.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {

				company.setName(set.getString("name"));
				company.setAddress(set.getString("address"));
				company.setDescription(set.getString("company_description"));
				company.setSatisfaction(set.getFloat("satisfaction"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return company;
	}

	public void delete() {
		// TODO Auto-generated method stub

	}

	public void delete(Company company) {
		// TODO Auto-generated method stub

	}

	public void delete(String nameOrID) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Company> querybyPhone(String phone) {

		ArrayList<Company> array = new ArrayList<Company>();

		Connection connection = DBLinker.getConnection();

		String sql = "select * from T_company where phone =" + phone;

		try {

			Statement state = connection.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				Company company = new Company();

				company.setId(set.getString("company_id"));
				company.setName(set.getString("name"));
				company.setAddress(set.getString("address"));
				company.setDescription(set.getString("company_description"));
				company.setPhone(set.getString("phone"));
				company.setLegalPerson(set.getString("legal_person"));
				company.setPassword(set.getString("password"));
				company.setSatisfaction(set.getInt("satisfaction"));

				array.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return array;
	}

	public boolean check(ArrayList<Company> arr) {
		boolean isExist;
		if (arr.size() == 1) {
			isExist = true;
		} else {
			isExist = false;

		}
		return isExist;
	}

	public boolean updateDescription(String description, String phone) {
		int updatenumber = 0;
		boolean isupdate = false;
		String sql = "update  t_company set company_description = '" + description + "' where phone = '" + phone + "'";
		System.out.println(sql);
		Connection conn = DBLinker.getConnection();
		try {
			Statement state = conn.createStatement();
			// ResultSet set=state.executeQuery(sql);
			updatenumber = state.executeUpdate(sql);
			if (updatenumber >= 1) {
				isupdate = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return isupdate;
		// TODO Auto-generated method stub

	}

	public boolean updatePassword(String password, String phone) {
		int updatenumber = 0;
		boolean isupdate = false;
		String sql = "update  t_company set password = '" + password + "' where phone = '" + phone + "'";
		System.out.println(sql);
		Connection conn = DBLinker.getConnection();
		try {
			Statement state = conn.createStatement();
			// ResultSet set=state.executeQuery(sql);

			updatenumber = state.executeUpdate(sql);
			if (updatenumber >= 1) {
				isupdate = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return isupdate;
		// TODO Auto-generated method stub

	}

	public String getIDByPhone(String phone) {
		Connection connection = DBLinker.getConnection();
		String id = null;
		String sql = "select company_id from T_company where phone =" + phone;

		try {

			Statement state = connection.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				System.out.println(set.getString("company_id"));
				// idList.add(set.getString("company_id"));

				id = set.getString("company_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public ArrayList<Integer> getIdList() {
		String sql = "select company_id from  t_company ";
		Connection conn = DBLinker.getConnection();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				String myId1 = set.getString("company_id");

				int id = Integer.parseInt(myId1.substring(1));
				idList.add(id);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return idList;
	}

	public ArrayList<ContactSearchedPerson> getPluralist(String cId) {

		ArrayList<ContactSearchedPerson> arrayList = new ArrayList<>();

		Connection conn = DBLinker.getConnection();
		String sql = "select p.pluralist_id,name,head_img_name,school"
				+ " from t_release as r,t_recruit as t,t_pluralist as p "
				+ "where r.information_id = t.information_id and t.pluralist_id = p.pluralist_id "
				+ "and r.company_id = '" + cId + "' " + "and ( t.recruit_status = 'ENROLLED' "
				+ "or t.recruit_status = 'WORKED' or t.recruit_status = 'EMPLOYED'  )";

		try {
			Statement state = conn.createStatement();
			ResultSet set = state.executeQuery(sql);

			while (set.next()) {
				ContactSearchedPerson person = new ContactSearchedPerson();

				String pId = set.getString("p.pluralist_id");
				String name = set.getString("name");
				String headImgURL = set.getString("head_img_name");
				String school = set.getString("school");

				person.setpId(pId);
				person.setName(name);
				person.setImageName(headImgURL);
				person.setSchool(school);

				arrayList.add(person);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	@Override
	public void update(Company company) {
		// TODO Auto-generated method stub

	}

	public Contact getCompany(String cId) {

		String sql = "select * from t_company where company_id = '" + cId + "'";
		
		Connection conn=DBLinker.getConnection();
		Contact contact=new Contact();
		
		try {
			Statement state=conn.createStatement();
			ResultSet set=state.executeQuery(sql);
			
			while(set.next()){
				contact.setId(cId);
				contact.setName(set.getString("legal_person"));
				contact.setOnline(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contact;

	}

}
