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

public class ChangeDescriptionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String description = request.getParameter("description");
	
		String phone = request.getParameter("phone");
		CompanyDao cd = new CompanyDao();
		PrintWriter pw = response.getWriter();
		ArrayList<Company> cList = cd.querybyPhone(phone);
		if(cd.check(cList)){
			Company company = cList.get(0);
			if(cd.updateDescription(description, phone)){
				pw.write("success");
			}else{
				pw.write("failed");
			}
			
		}
		
	}
}
