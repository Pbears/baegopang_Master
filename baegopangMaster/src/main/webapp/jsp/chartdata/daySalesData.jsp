<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="sist.spring.bean.DaySalesDataBean"%>
<%@page import="sist.spring.dao.DataDao"%>
<%@page import="sist.spring.bean.MasterBean"%>
<%@page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.PrintWriter"%>
<%
    MasterBean master=(MasterBean)request.getSession().getAttribute("master");
	String storename = master.getStorename();
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("storename", storename);
	//map.put("storename", "굽네치킨-역삼1동점");

	DataDao dao = new DataDao();
	List<DaySalesDataBean> list = dao.selectDaySalesData(map);
	String[] color={"#FF0F00","#FF9E01","#F8FF01","#04D215","#0D8ECF","#2A0CD0","#8A0CCF"}; 
	//out.print(list);
	
	JSONArray totalArray=new JSONArray();
	

	for (int i = 0; i < list.size(); i++) {
		DaySalesDataBean bean = list.get(i);
		
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("date", bean.getOrderday());
		jsonObj.put("value", bean.getDaysale());
		//System.out.println(jsonObj.toString());
		totalArray.add(jsonObj);
	}

	//현재페이지의 목록데이터
	//out.print(totalArray.toString());

	 	PrintWriter pw = response.getWriter();
		pw.print(totalArray.toString());
		pw.flush();
		pw.close();  
%> --%>

${json}