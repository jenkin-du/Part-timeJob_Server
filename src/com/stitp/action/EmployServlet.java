package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stitp.dao.RecruitDao;

public class EmployServlet extends HttpServlet{
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
		
		PrintWriter pw = response.getWriter();
		
		String iId = request.getParameter("iID");
		String pId = request.getParameter("pID");
		String status = request.getParameter("status");
		RecruitDao rdao = new RecruitDao();
		if(rdao.updateStatus(iId, pId,status)){
			pw.write("success");
		}else{
			pw.write("failed");
		}
		
		
		
		
	}
}
