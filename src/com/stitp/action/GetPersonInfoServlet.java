package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stitp.dao.CompanyDao;
import com.stitp.model.Company;

public class GetPersonInfoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		CompanyDao cd = new CompanyDao();
		String phone = request.getParameter("phone");
		ArrayList<Company> cList = cd.querybyPhone(phone);
		for (Company company : cList) {
			pw.write(company.getLegalPerson()+",");
			pw.write(company.getName()+",");
			pw.write(company.getDescription()+",");
			pw.write(company.getAddress()+",");
			pw.write(company.getSatisfaction()+"");
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
