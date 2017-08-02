<%@page import="sist.spring.bean.WeekdaySalesDataBean"%>
<%@page import="sist.spring.dao.DataDao"%>
<%@page import="sist.spring.bean.MasterBean"%>
<%@page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.PrintWriter"%>
<%
    MasterBean master=(MasterBean)request.getSession().getAttribute("master");
	String storename = master.getStorename();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("storename", storename);
	//map.put("storename", "교동짬뽕-선릉점");

	DataDao dao = new DataDao();
	List<WeekdaySalesDataBean> list = dao.selectWeekDaySalesData(map);
	//out.print(list);
	
	JSONArray totalArray=new JSONArray();
	String[] color={"#FF0F00","#FF9E01","#F8FF01","#04D215","#0D8ECF","#2A0CD0","#8A0CCF"}; 
	

	for (int i = 0; i < list.size(); i++) {
		WeekdaySalesDataBean bean = list.get(i);
		
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("country", bean.getWeekday()+"요일");
		jsonObj.put("visits", bean.getAvgsales());
		jsonObj.put("color",color[i]);
		totalArray.add(jsonObj);
	}

	//현재페이지의 목록데이터
	out.print(totalArray.toString());

	 	PrintWriter pw = response.getWriter();
		pw.print(totalArray.toString());
		pw.flush();
		pw.close();  
%>