package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stitp.dao.RecruitDao;
import com.stitp.model.Recruit;
import com.stitp.util.JSONParser;
import com.stitp.util.Status;

/**
 * Servlet implementation class RecruitServlet
 */
public class RecruitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status = null;
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter writer=response.getWriter();
		
		String action=request.getParameter("action");
		String applyReason=request.getParameter("applyReason");
		String infoId=request.getParameter("infoId");
		String pluralistId=request.getParameter("pluralistId");
		
		System.out.println("action="+action+",reason="+applyReason+","
				+ "infoId="+infoId+",pId="+pluralistId);
		
		RecruitDao dao=new RecruitDao();
		if(action!=null){
			
			switch (action) {
			/*case "apply":
				
				if(applyReason!=null&&infoId!=null&&pluralistId!=null){
					Recruit r=new Recruit();
					r.setInfoId(infoId);
					r.setPluralistId(pluralistId);
					r.setApplyReason(applyReason);
					
					 status=dao.insert(r);
					writer.write(status);
				}
				
				
				break;*/
				
			case "getEnrolledInfoList":
				
				if(pluralistId!=null){
					HashMap<String, String> map=dao.queryEnrolledInfo(pluralistId);
					
					String jsonStr=JSONParser.toJSONString(map);
					writer.print(jsonStr);
				}
				break;
				
			case "getCollectedInfoList":
				
				if(pluralistId!=null){
					ArrayList<String> list=dao.queryCollectededInfo(pluralistId);
					
					String jsonStr=JSONParser.toJSONString(list);
					writer.print(jsonStr);
				}
				break;

			//收藏
			case "collect":
				
				if(infoId!=null&&pluralistId!=null){
					
					Recruit r=new Recruit();
					r.setInfoId(infoId);
					r.setPluralistId(pluralistId);
					
					status=dao.collect(r);
					writer.write(status);
				}
				
				break;
				
				//取消收藏
			case "cancelCollect":
				
				if(infoId!=null&&pluralistId!=null){
					
					Recruit r=new Recruit();
					r.setInfoId(infoId);
					r.setPluralistId(pluralistId);
					
					status=dao.cancelCollect(r);
					writer.write(status);
				}
				
				break;
			default:
				break;
			}
			
			
		}else{
			status=Status.FAILING;
			writer.write(status);
		}
		System.out.println("status="+status);
		writer.flush();
		writer.close();
	}


}
