<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@page import="sist.spring.bean.GenderDataBean"%>
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
	DataDao dao = new DataDao();
	List<GenderDataBean> list = dao.selectGenderData(map);
	String man = null;
	String woman = null;
	
	String json = "[";

	for (int i = 0; i < list.size(); i++) {

		if (man == null) {
			if (((GenderDataBean) list.get(i)).getGender().equals("남")) {
				man = String.valueOf(((GenderDataBean) list.get(i)).getRatiogender());
	        json +="{" + "\"type\" : \"남자\"," + "\"percent\" : \"" + man + "\","
			+ "\"color\" : \"#ff9e01\"," + "\"subs\" : " + "[";
			}

		}
	}
	
			
	int metc=0;

	for (int i = 0; i < list.size(); i++) {
		GenderDataBean bean = ((GenderDataBean) list.get(i));

		if (bean.getGender().equals("남")&&(bean.getRank()==1||bean.getRank()==2||bean.getRank()==3)) {
			 json+="{" + "\"type\" : \""+bean.getMenuname()+"\"," + "\"percent\" : \""+bean.getRatiomenu()+"\"" + "},";
		}else if(bean.getGender().equals("남")&& bean.getRank() !=1 && bean.getRank() !=2 &&bean.getRank() != 3){
			metc+=bean.getRatiomenu();
		}

	}
	if(metc!=0){
		
		json+="{" + "\"type\" : \"기타\"," + "\"percent\" : \""+metc+"\"" + "},";
	}
	
	
	if(man!=null){
	json=(new String(json)).substring(0, json.lastIndexOf(","));
	json+="]" + "},";
	}
	
	
	for (int i = 0; i < list.size(); i++) {

		if (woman == null) {
			if (((GenderDataBean) list.get(i)).getGender().equals("여")) {
				woman = String.valueOf(((GenderDataBean) list.get(i)).getRatiogender());
				json +="{" + "\"type\" : \"여자\"," + "\"percent\" : \""+woman+"\"," + "\"color\" : \"#b0de09\","
						+ "\"subs\" : " + "[";
			}

		}

	}
	
	
	
	
			
	int wetc=0;

	for (int i = 0; i < list.size(); i++) {
		GenderDataBean bean = ((GenderDataBean) list.get(i));

		if (bean.getGender().equals("여")&&(bean.getRank()==1||bean.getRank()==2||bean.getRank()==3)) {
			 json+="{" + "\"type\" : \""+bean.getMenuname()+"\"," + "\"percent\" : \""+bean.getRatiomenu()+"\"" + "},";
		}else if(bean.getGender().equals("여")&& bean.getRank() !=1 && bean.getRank() !=2 &&bean.getRank() != 3){
			wetc+=bean.getRatiomenu();
		}

	}
	if(metc!=0){
		
		json+="{" + "\"type\" : \"기타\"," + "\"percent\" : \""+wetc+"\"" + "},";
	}
	
	json=(new String(json)).substring(0, json.lastIndexOf(","));
	
	if(man!=null && woman!=null){
		
		json+="]" + "}";
		}
	
	
	
	json+= "]";
	
	

	System.out.println(json);

	 	PrintWriter pw = response.getWriter();
		pw = response.getWriter();
		pw.print(json);
		pw.flush();
		pw.close(); 
%> --%>

${json}