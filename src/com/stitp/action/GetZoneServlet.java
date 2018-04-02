package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stitp.dao.AreaDao;

public class GetZoneServlet extends HttpServlet{

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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//Connection conn =  DBLinker.getConnection();
		PrintWriter pw = response.getWriter();
		AreaDao ad = new AreaDao();
		ArrayList<String> zonesList =null;//
       // HashMap<String ,ArrayList<String>> map1 = new HashMap<>();
		String cityName = request.getParameter("cityName");
		zonesList = ad.getZone(cityName);
		//map1 = ad.getCity(cityName);
			Gson gson=new Gson();
			String s=gson.toJson(zonesList);
			
			System.out.println("------------"+s);
			pw.write(s);
//				map1 = gson.fromJson(s, HashMap<>.class);
			zonesList=gson.fromJson(s, new TypeToken<ArrayList<String>>() {
		    }.getType());
			System.out.println(s);
			System.out.println(zonesList.toString());
	}

}
