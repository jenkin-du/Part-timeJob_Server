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
import com.stitp.util.JSONParser;

public class CompanyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CompanyDao dao=new CompanyDao();
     
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String action=request.getParameter("action");
		String companyId=request.getParameter("companyId");
		String name = request.getParameter("nameCode");
		String pwd = request.getParameter("pwd");
		
		System.out.println("action="+action+",companyId="+companyId);
	
		if(action!=null){
			switch (action) {
			case "company":
				
				if(companyId!=null){
					Company company=dao.query(companyId);
					String jsonStr=JSONParser.toJSONString(company);
					out.print(jsonStr);
					System.out.println(jsonStr);
				}
				
				break;

			default:
				break;
			}
		}
		
		if(name!=null&&pwd!=null){
			ArrayList<Company> arr=dao.querybyPhone(name);

			if(dao.check(arr)){
				  
				Company company = arr.get(0);
				if(name.equals(company.getPhone()))
				{	
					if(pwd.equals(company.getPassword())){
						System.out.println("Suceess");
					
						out.write(company.getId());
						  
					}else
					{
						System.out.println("Wrong password");

						out.write("Wrong password");		
					}
				}
			}else {
				System.out.println("NO user");
				out.write("NO user");
			}
		}
		
		out.flush();
		out.close();
	}

	
}
