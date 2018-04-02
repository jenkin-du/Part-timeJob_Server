package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stitp.dao.InfoAbstractDao;
import com.stitp.dao.InfoDetailDao;
import com.stitp.model.InfoAbstract;
import com.stitp.model.InfoDetail;
import com.stitp.util.JSONParser;

public class InfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String os = "";

		String action = request.getParameter("action");
		String city = (request.getParameter("city"));
		String infoId = request.getParameter("infoId");

		String pluralistId = request.getParameter("pluralistId");
		String status = request.getParameter("status");

		String url = request.getRequestURI();
		System.out.println(url);
		System.out.println("action=" + action + ",city=" + city + ",infoId=" + infoId);
		System.out.println("pId=" + pluralistId + ",status=" + status);

		if (action != null) {
			switch (action) {
			case "abstract":
				if (city != null) {
					InfoAbstractDao dao = new InfoAbstractDao();
					ArrayList<InfoAbstract> arr = dao.query(city);
					os = JSONParser.toJSONString(arr);

				} else if (city == null) {
					InfoAbstractDao dao = new InfoAbstractDao();
					ArrayList<InfoAbstract> arr = dao.query();
					os = JSONParser.toJSONString(arr);
				}
				break;

			case "detail":
				if (infoId != null) {
					InfoDetailDao dao = new InfoDetailDao();
					InfoDetail info = dao.query(infoId);
					os = JSONParser.toJSONString(info);

				}
				break;

			case "recruitStatus":
				if (pluralistId != null && status != null) {
					InfoAbstractDao dao = new InfoAbstractDao();
					ArrayList<InfoAbstract> arr = dao.query(pluralistId, status);
					os = JSONParser.toJSONString(arr);
				}
				break;

			case "getCollectedInfo":
				if (pluralistId != null) {
					InfoAbstractDao dao = new InfoAbstractDao();
					ArrayList<InfoAbstract> arr = dao.queryCollectedInfo(pluralistId);
					os = JSONParser.toJSONString(arr);
				}
				break;
			default:
				break;
			}

		}

		System.out.println(os);
		PrintWriter out = response.getWriter();

		out.print(os);

		out.flush();
		out.close();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("init");
		// ServerLauncher.main(null);
	}

}
