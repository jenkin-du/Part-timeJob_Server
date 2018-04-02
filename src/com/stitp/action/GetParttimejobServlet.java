package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stitp.dao.CompanyDao;
import com.stitp.dao.InformationDao;
import com.stitp.model.company.Information;

public class GetParttimejobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse responce)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		responce.setContentType("text/html;charset=utf-8");
		PrintWriter pw = responce.getWriter();
		String phone = request.getParameter("phone");
		CompanyDao cdao = new CompanyDao();
		InformationDao idao = new InformationDao();
		
		
		ArrayList<Information> informationList = idao.query1(idao.getIdbyCId(cdao.getIDByPhone(phone)));
		Gson gson = new Gson();
		String info = gson.toJson(informationList);
			pw.write(info);
		
	

	}
}
