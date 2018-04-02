package com.stitp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBLinker {
	
	private static final String sqlName="parttimejob";
	
	private static final String URL="jdbc:mysql://127.0.0.1:3306/"+sqlName+"?useUnicode=true&characterEncoding=UTF-8";
	private static final String USER="root";
	private static final String PASSWORD="root";
	private static Connection connection;
	
	
	
	
	static{
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			connection=DriverManager.getConnection(URL,USER,PASSWORD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	public static Connection getConnection(){
		return connection;
	}
	
}
