package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stitp.dao.InformationDao;
import com.stitp.model.company.Pluralist;

public class GetPluralistInfoServlet extends HttpServlet{
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
		
		String iID = request.getParameter("iID");
		InformationDao idao = new InformationDao();
		PrintWriter pw = response.getWriter();
		ArrayList<Pluralist> map = idao.getPIdbyIId(iID);
		Gson gson = new Gson();
		String result = gson.toJson(map, ArrayList.class);
		pw.write(result);
		//map = gson.fromJson(result, new TypeToken<ArrayList<Pluralist>>(){}.getType());
		//System.out.println(result);
	}
}
