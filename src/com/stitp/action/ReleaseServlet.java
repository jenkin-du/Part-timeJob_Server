package com.stitp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.stitp.dao.AreaDao;
import com.stitp.dao.CompanyDao;
import com.stitp.dao.InformationDao;
import com.stitp.dao.ReleaseDao;
import com.stitp.model.Area;
import com.stitp.model.HttpClientHelper;
import com.stitp.model.ReleaseInfo;
import com.stitp.model.company.Information;

public class ReleaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String API = "http://restapi.amap.com/v3/geocode/geo?key=<key>&s=rsv3&address=<address>";

    private static String KEY = "aa4a48297242d22d2b3fd6eddfe62217";

    private static Pattern pattern = Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");

 
    public static double[] addressToGPS(String address) throws IOException {
        try {
            String requestUrl = API.replaceAll("<address>", URLEncoder.encode(address, "UTF-8"));
            System.out.println("请求地址: {}" + requestUrl);
            requestUrl = HttpClientHelper.INSTANCE.get(requestUrl);
           if (requestUrl != null ) {
                Matcher matcher = pattern.matcher(requestUrl);
                if (matcher.find() && matcher.groupCount() == 2) {
                    double[] gps = new double[2];
                    gps[0] = Double.valueOf(matcher.group(1));
                    gps[1] = Double.valueOf(matcher.group(2));
                    System.out.println("gps: {}" + Arrays.toString(gps));
                    return gps;
                }
            }
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse responce)
			throws ServletException, IOException {
        System.out.println("高德地图工具类初始化");
        System.out.println("api: {}"+API);
        System.out.println("key: {}"+KEY);
        API = API.replaceAll("<key>", KEY);
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		responce.setContentType("text/html;charset=utf-8");
		PrintWriter pw = responce.getWriter();
		String title = request.getParameter("title");// 兼职标题
		String type = request.getParameter("type");// 兼职类型
		String datePicker = request.getParameter("datePicker");// 选择的日期
		String startTime = request.getParameter("startTime");// 开始时间
		String endTime = request.getParameter("endTime");// 结束时间
		
		String lastTime = request.getParameter("lastTime");// 持续时间
		String numberOfEmployee = request.getParameter("numberOfEmployee");// 招工人数
		String salary = request.getParameter("salary");// 薪资
		String salaryUnit = request.getParameter("salaryUnit");// 薪资单位
		String gender = request.getParameter("gender");// 性别要求
		String experience = request.getParameter("experience");// 经验要求
		String height = request.getParameter("height");// 身高要求
		String description = request.getParameter("description");// 工作描述
		String detail = request.getParameter("detail");// 细节描述
		String contactNameOfRealease = request.getParameter("contactNameOfRealease");// 联系人姓名
		String contactPhoneOfRealease = request.getParameter("contactPhoneOfRealease");// 联系人号码
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String zone = request.getParameter("zone");
		
		
		String detailAddress = request.getParameter("detailAddress");
		String phone = request.getParameter("phone");
		
		CompanyDao cd = new CompanyDao();
		String cID = cd.getIDByPhone(phone);
		
		
		System.out.println("de="+description);
		 double[] gps = ReleaseServlet.addressToGPS(province+city+zone+detailAddress);
		 float longtitude =(float) gps[1];
		 float latitude=(float) gps[0];
		
		AreaDao ad = new AreaDao();
		String aID = "A"+(ad.findMaxID(ad.getIdList())+1);
		Area a = new Area(aID,province,city,zone,detailAddress,longtitude,latitude);
		System.out.println(a);
		InformationDao idao = new InformationDao();
		String iID = "I"+(idao.findMaxID(idao.getIdList())+1);
		if(datePicker!=null){
		java.sql.Date date = java.sql.Date.valueOf(datePicker);
		System.out.println(date);
		Information i = new Information(iID, description, 
				salary+salaryUnit, aID, date, startTime+"-"+endTime, Integer.parseInt(lastTime), type, 
				Integer.parseInt(numberOfEmployee), contactNameOfRealease, contactPhoneOfRealease,
				detail, height, gender, experience,title);
		
		ReleaseDao rd = new ReleaseDao();
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd kk:mm:ss");
		
		ReleaseInfo rinfo = new ReleaseInfo(cID, iID, sdf.format(time));
		if(ad.insert(a)&&idao.add(i)&&rd.insert(rinfo)){
			
				pw.write("success");
			
		}else{
			System.out.println("failed");
			pw.write("failed");
		}
		
		}
		// 工作地址
		System.out.println(province+","+city);
	}

}
