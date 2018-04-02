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

public class GetReleaseDefaultServlet extends HttpServlet {

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String phone = request.getParameter("phone");

		PrintWriter pw = response.getWriter();

		System.out.println(phone);

		CompanyDao c = new CompanyDao();
		ArrayList<Company> arr = c.querybyPhone(phone);

		if (c.check(arr)) {

			Company company = arr.get(0);
			if (phone.equals(company.getPhone())) {
				pw.write(company.getLegalPerson() + "," + company.getAddress());
			}
		}
	}

}
