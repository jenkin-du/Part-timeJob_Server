package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;
import com.stitp.dao.CompanyDao;
import com.stitp.dao.PluralistDao;
import com.stitp.model.ContactSearchedPerson;
import com.stitp.model.Datagram;
import com.stitp.model.Friend;
import com.stitp.model.Pluralist;
import com.stitp.model.ResponseSet;
import com.stitp.model.ReturnSet;
import com.stitp.util.JSONParser;
import com.stitp.util.Status;

/**
 * Servlet implementation class PluralistServlet
 */
public class PluralistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PluralistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		ReturnSet returnSet = new ReturnSet();
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String pId = request.getParameter("pluralistId");
		String condition = request.getParameter("condition");
		String friendId = request.getParameter("friendId");
		String pluralistJson = request.getParameter("pluralistJson");
		String imageName = request.getParameter("imageName");
		String imageCode = request.getParameter("imageCode");

		System.out.println("action=" + action + ",phone=" + phone + ",pwd=" + password + ",name=" + name + ",condition="
				+ condition + ",friendId=" + friendId);

		String jsonString = "";
		PluralistDao dao = new PluralistDao();

		if (action != null) {

			switch (action) {
			// 验证
			case "validate":

				if (phone != null && password != null) {

					boolean isExist = dao.checkPhone(phone);
					if (isExist) {
						// 如果存在，验证密码是否正确
						returnSet = dao.validate(phone, password);

					} else {
						returnSet.setStatus(Status.NON_EXISTENT);
					}
					jsonString = JSONParser.toJSONString(returnSet);
				}

				break;

			// 验证
			case "validate2":

				boolean isRight;
				if (phone != null && password != null) {

					boolean isExist = dao.checkPhone(phone);
					if (isExist) {
						// 如果存在，验证密码是否正确
						isRight = dao.validatePassword(phone, password);
						if (isRight) {
							returnSet.setStatus(Status.RIGHT);
						} else {
							returnSet.setStatus(Status.WRONG);
						}

					} else {
						returnSet.setStatus(Status.NON_EXISTENT);
					}
					jsonString = JSONParser.toJSONString(returnSet);
				}

				break;
			// 检查电话是否存在
			case "check":

				if (phone != null) {
					boolean isExist = dao.checkPhone(phone);
					if (isExist) {
						returnSet.setStatus(Status.EXISTENT);
					} else {
						returnSet.setStatus(Status.NON_EXISTENT);
					}
					jsonString = JSONParser.toJSONString(returnSet);
				}

				break;
			case "insert":

				if (phone != null && password != null && name != null) {

					Pluralist p = new Pluralist();
					p.setPassword(password);
					p.setPhone(phone);
					p.setName(name);

					returnSet = dao.insert(p);

					jsonString = JSONParser.toJSONString(returnSet);
				}

				break;

			// 获得薪水
			case "getSalary":

				if (pId != null) {

					ResponseSet set = dao.querySalary(pId);
					jsonString = JSONParser.toJSONString(set);
				}

			case "search":

				if (condition != null) {

					Datagram datagram = dao.querySearchedPluralist(condition);
					jsonString = JSONParser.toJSONString(datagram);
				}

				break;

			case "findFriend":

				if (friendId != null) {

					Friend friend = dao.findFriend(friendId);

					Datagram datagram = new Datagram();
					datagram.setResponse(Status.SUCCESSFUL);
					datagram.setJsonStream(JSONParser.toJSONString(friend));

					jsonString = JSONParser.toJSONString(datagram);
				}

				break;

			case "update":

				if (pluralistJson != null) {

					Pluralist p = JSONParser.toJavaBean(pluralistJson, new TypeToken<Pluralist>() {
					}.getType());
					dao.update(p);
				}
				break;

			// 修改电话
			case "updatePhone":

				if (pId != null && pId != "") {
					dao.updatePhone(pId, phone);
				}
				break;

			// 修改密码
			case "changePassword":

				if (password != null && pId != null) {

					dao.changePassword(pId, password);
				}
				break;

			// 发送照片
			case "sendImage":

				if (imageCode != null && imageName != null && pId != null) {
					dao.insertOrUpdateImage(imageName, imageCode, pId);
				}
				break;

			// 获取照片
			case "getImage":

				if (imageName != null) {
					jsonString = dao.getImageByName(imageName);
				}
				if (phone != null) {
					jsonString = dao.getImageByPhone(phone);
				}
				break;

			// 获取朋友
			case "getFriend":

				if (pId != null) {

				}
				break;

			//
			case "getPluralist":

				if (condition != null) {

					CompanyDao CDao = new CompanyDao();
					ArrayList<ContactSearchedPerson> list = CDao.getPluralist(condition);
					jsonString=JSONParser.toJSONString(list);
				}
				break;
			default:
				break;
			}
		}

		 System.out.println(jsonString);
		out.print(jsonString);
		out.flush();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
