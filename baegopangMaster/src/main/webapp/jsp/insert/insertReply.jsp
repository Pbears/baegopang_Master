<%@page import="sist.spring.dao.MasterReplyDao"%>
<%@page import="sist.spring.bean.MasterBean"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%-- 	<%
		MasterBean mbean = (MasterBean) session.getAttribute("master");
		String storename = mbean.getStorename();
		String id=mbean.getId();
		int pnum= Integer.parseInt(request.getParameter("pnum")); 
		String contents = request.getParameter("contents");
		HashMap<String,Object>map = new HashMap<String,Object>();
		map.put("storename",storename);
		map.put("pnum",pnum);
		map.put("contents",contents);
		map.put("id",id);
		MasterReplyDao dao=new MasterReplyDao();
		dao.insertReply(map);
		dao.updateReply(map);
		dao.getPoint(id);
		
		response.sendRedirect("../ReplyManage.jsp");
	%> --%>
</body>
</html>