<%@page import="sist.spring.bean.NoticeBean"%>
<%@page import="sist.spring.dao.NoticeDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="css/Notice.css" rel="stylesheet">
</head>
<body>
	<%--  <%
		request.setCharacterEncoding("euc-kr");
		String title = request.getParameter("title");
		System.out.print(title);
		NoticeDao dao = new NoticeDao();
		NoticeBean bean;
		bean = dao.selNoticeOne(title);
	%> --%>

	<!-- UI Object -->
	<h1>��������</h1>
	<table cellspacing="0" border="1" summary="�� ������ ǥ��" class="tbl_type">
		<caption>�� �б�</caption>
		<colgroup>
			<col width="80">
			<col>
			<col width="80">
			<col width="150">
			<col width="80">
			<col width="150">
		</colgroup>
		<thead>
			<tr>
				<th scope="row">����</th>
				<td colspan="5">${noticesel.title }</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th scope="row">�ۼ���</th>
				<td>Admin</td>
				<th scope="row">�ۼ���</th>
				<td>${noticesel.regdate }</td>
				<th scope="row">��ȸ</th>
				<td>19</td>
			</tr>
			<tr>
				<td colspan="6" rowspan="6" class="cont" style="padding-bottom: 200px;"><span style="font-size: 20px; line-height: 30px" >${noticesel.info }.</span>
				</td>
			</tr>
		</tbody>
	</table>
	<div>
	<form action="NoticeList.jsp">
		<input type="submit" value="��Ϻ���" style="float: right; margin-top:20px;">
	</form>
	</div> 
	<!-- //UI Object -->
</body>
</html>