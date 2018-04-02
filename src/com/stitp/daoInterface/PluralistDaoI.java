package com.stitp.daoInterface;

import java.util.ArrayList;

import com.stitp.model.Contact;
import com.stitp.model.Pluralist;
import com.stitp.model.ReturnSet;

public interface PluralistDaoI{

	 
	 ReturnSet insert(Pluralist pluralist);
	
	 void update(Pluralist pluralist);
	 
	 void update(String nameOrID);
	 
	 void delete();
	 
	 void delete(Pluralist pluralist);
	 
	 void delete(String nameOrID);
	 
	 ArrayList<Pluralist> query();
	 
	 Contact queryFriend(String nameOrID);
	 
	 
	 boolean checkPhone(String name);//检查时候具有相同的记录
	 
	 ReturnSet validate(String phone,String password);//验证密码是否正确
}
