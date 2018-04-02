package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stitp.dao.AreaDao;

public class GetCityServlet  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("city");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//Connection conn =  DBLinker.getConnection();
		PrintWriter pw = response.getWriter();
		AreaDao ad = new AreaDao();
		ArrayList<String> citiesList =null;//
       // HashMap<String ,ArrayList<String>> map1 = new HashMap<>();
		String provinceName = request.getParameter("provinceName");
		System.out.println(provinceName);
		citiesList = ad.getCity(provinceName);
			Gson gson=new Gson();
			String s=gson.toJson(citiesList);
			
			System.out.println("------------"+s);
			pw.write(s);
//				map1 = gson.fromJson(s, HashMap<>.class);
			citiesList=gson.fromJson(s, new TypeToken<ArrayList<String>>() {
		    }.getType());
			System.out.println(s);
			//System.out.println(citiesList.toString());
//					String zones = set2.getString("areaname");
//					System.out.println(provinces+","+cities+","+zones);
		
		
	
	
	}

}
