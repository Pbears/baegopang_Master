<%@page import="sist.spring.bean.MasteraskadminBean"%>
<%@page import="sist.spring.dao.MasterAskDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
/* UI Object */
legend{display:none}
.tbl_type,.tbl_type th,.tbl_type td{border:0}
.tbl_type{width:100%;border-top:1px solid #999;border-bottom:1px solid #999;color:#666;font-size:12px;table-layout:fixed}
.tbl_type caption{display:none}
.tbl_type th{padding:5px 0 4px;border-bottom:solid 1px #d2d2d2;background-color:#f1f1f4;color:#333;font-weight:bold;line-height:18px;vertical-align:top}
.tbl_type td{padding:8px 0 5px 10px;border-bottom:solid 1px #d2d2d2;text-align:left}
.tbl_type td.cont{padding:20px;line-height:1.4em}
.tbl_type2{width:100%;margin-top:15px;border-top:1px solid #999;border-bottom:1px solid #999;color:#666;font-size:12px;table-layout:fixed}
.tbl_type2,.tbl_type2 th,.tbl_type2 td{border:0}
.tbl_type2 caption{display:none}
.tbl_type2 td{padding:5px 0 3px 10px;line-height:1.4em;text-align:left}
.tbl_type2 .input_txt td{padding:5px 0 5px 10px;border-bottom:solid 1px #d2d2d2}
.tbl_type2 .input_txt td input,.tbl_type2 .input_txt td textarea{vertical-align:middle}
.tbl_type2 .input_txt input.name{width:100px;padding:2px 0 1px;border:solid 1px #d2d2d2}
.tbl_type2 .input_txt textarea.comment{width:80%;height:14px;padding:2px 0 1px;border:solid 1px #d2d2d2}
.tbl_type2 .input_txt input.submit{width:60px;border:solid 1px #666;background-color:#f2f2f2;font-family:'����';font-size:12px;line-height:normal}
/* //UI Object */
</style>
<script type="text/javascript">
	function insertSubmit(){
		var obj=document.getElementById("frm");
		obj.submit();
	}
</script>
</head>
<body>
<%-- 	<%
		String title=request.getParameter("title");
		MasterAskDao mdao=new MasterAskDao();
		MasteraskadminBean mbean =null;
		mbean = mdao.selQuestionOne(title);
		System.out.print(mbean);
	%> --%>
	<!-- UI Object -->
	<h1>���� �ϱ�</h1>
<table cellspacing="0" border="1" summary="�� ������ ǥ��" class="tbl_type">
<caption>�� �б�</caption>
<colgroup>
<col width="80"><col><col width="80">
<col width="150"><col width="80"><col width="150">
</colgroup>
<thead>
<tr>
<th scope="row">����</th>
<td colspan="5">${bean.title }</td>
</tr>
</thead>
<tbody>
<tr>
<th scope="row">�ۼ���</th>
<td>${bean.masterid }</td>
<th scope="row">�ۼ���</th>
<td>${bean.regdate }</td>
</tr>
<tr>
<td colspan="6" class="cont" style="padding-bottom: 150px;">
	${bean.masterinfo }
</td>
</tr>
</tbody>
</table>
	<table cellspacing="0" border="1" class="tbl_type">
	<colgroup>
	<col width="110">
	<col>
	</colgroup>
	<tbody>
	<tr scope="row" class="input_txt">
	<th>admin</th>
	<td>${bean.admininfo }</td>
	</tr>
	</tbody>
	</table>

<!-- //UI Object -->
</body>
</html>