package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stitp.dao.CompanyDao;
import com.stitp.model.Company;
import com.stitp.util.Encode;



public class RegisterServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		CompanyDao c=new CompanyDao();
	
		String sr=request.getRequestURI();
		System.out.println(sr);
		
		int i = c.findMaxID(c.getIdList());
		String cid = "C"+(i+1);
		String companyName = request.getParameter("companyName");
		
		String s=new String(companyName.getBytes("utf-8"),"utf-8");
		
        String password = request.getParameter("password");
        String contactName = request.getParameter("contactName");
        String contactPhone = request.getParameter("contactPhone");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        System.out.println(s);
        Company company = new Company(cid,companyName,description,address,contactPhone,contactName,password);
       
        System.out.println(company.toString());
        PrintWriter pw = response.getWriter();
        boolean isSuccess = c.add(company);
        if(isSuccess){
        	  pw.write("SUCCEEDED");
        }else{
        	  pw.write("FAILED");
        }
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
}
