<%@page import="sist.spring.dao.MenuManageDao"%>
<%@page import="sist.spring.bean.MenuBean"%>
<%@page import="sist.spring.bean.MasterBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="../css/MenuManager.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>

<style type="text/css">
#menu_img, #insertimg {
	width: 250px;
	margin-left: 50px
}
</style>

</head>
<body>
	<%
		MasterBean mbean = (MasterBean)session.getAttribute("master");
		String id = mbean.getId();
		String storename = mbean.getStorename();
		String picture = "../img/noimage.jpg";
		List<MenuBean> list = null;
		List<MenuBean> selList = null;
		MenuManageDao dao = new MenuManageDao();
	%>

	<jsp:include page="header.jsp"></jsp:include>
	<div class="mid_MenuContents">
		<div id="f_contents">
			<%
				list = dao.selectMenu(id);
				for (int i = 0; i < list.size(); i++) {
					MenuBean bean = list.get(i);
			%>
			<div id="selMenu" style="display: inline-block;">
				<img src="/BaegopangMaster<%=bean.getPicture()%>" id="menu_img" name="insert_menu<%=i%>" 
				onmouseover="imgOver(this)" onmouseout="imgOut(this)" onclick="imgCk(<%=i%>)"/>
				<p><%=bean.getMenuname()%></p>
				<input type="hidden" value="<%=bean.getMenuname()%>" id="menu_name" name="insert_menu<%=i%>"> 
				<input type="hidden" value="<%=bean.getPrice()%>" id="menu_price" name="insert_menu<%=i%>"> 
				<input type="hidden" value="<%=bean.getInfo()%>" id="menu_info" name="insert_menu<%=i%>">
			</div>
			<%
				}
			%>
		</div>
		<div class="selMarket">
			<div id="menupan">
				<p>우리가게 메뉴판</p>
			</div>
			<div id="s_contents">
					<form action="/BaegopangMaster/jsp/delete/deleteProcess.jsp" id="frm">
				<%
					List<MenuBean> mbslist = null;
					mbslist = dao.myStoreMenu(storename);
					for (int i = 0; i < mbslist.size(); i++) {
						MenuBean mbsbean = mbslist.get(i);
				%>
				<div id="menu_one"
					style="display: inline-block; text-align: center;">
					<img src="/BaegopangMaster<%=mbsbean.getPicture()%>">
						<div>
							<p style="font-family: sans-serif; font-size: 15px; display: inline-block; margin-bottom: 20px; margin-top: 10px;">
								<%=mbsbean.getMenuname() %></p>
							<input class="btn btn-default" type="button" value="메뉴 삭제"
								onclick="deleteMenu(<%=i %>)"
								style="float: right; margin-right: 20px; background: #FF8C00; color: white; font-weight: bold;">
							<input type="hidden" id="menuname<%=i %>" name="menuname" value="<%=mbsbean.getMenuname() %>"> 
						</div>
				</div>
				<%
					}
				%>
					</form>
			</div>
		</div>
	</div>
	<div id="insertMenu">
		<form action="/BaegopangMaster/jsp/insert/insertMenuProcess.jsp">
			<div id="insertTop">
				<p>메뉴 추가</p>
			</div>

			<table>
				<%
					MenuBean istbean = (MenuBean) selList;
				%>

				<tr>
					<td>
						<div id="menu">
							<img src="../img/noimage.jpg" id=insertimg name="receive_menu">
						</div>
					</td>
					<td style="text-align: right;">
						<div id="Menu2">
							<%
								selList = dao.selectMenuOne(picture);
							%>
							<div class="form-group">
								<label for="usr">이름:</label> <input type="text"
									class="form-control" id="insertname" name="receive_menu"
									readonly="readonly"> <input type="hidden"
									name="menuname" id="menuname">
							</div>
							<div class="form-group">
								<label for="pwd">가격:</label> <input type="text"
									class="form-control" id="insertprice" name="receive_menu"
									readonly="readonly">
							</div>
							<div class="form-group">
								<label for="comment">설명:</label>
								<textarea class="form-control" rows="5" id="insertinfo"
									name="receive_menu" readonly="readonly"></textarea>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<div id="btn">
				<input type="submit" class="insertBtn" value="추가하기">
			</div>
		</form>
	</div>

</body>

<script type="text/javascript">

	function insertProc() {
		var obj = document.getElementById("btn");
		insertMenu.style.display = "inline-block";
	}
	function imgOver(obj) {
		obj.style.opacity = "0.5";
	}
	function imgOut(obj) {
		obj.style.opacity = "1.0";
	}
	 function imgCk(i){
	    var irt = document.getElementsByName(("insert_menu"+i));
	    var rcv = document.getElementsByName("receive_menu");
		var menuname = document.getElementById("menuname");
	    rcv[0].src=irt[0].src; 
		rcv[1].value=irt[1].value;
		rcv[2].value=irt[2].value;
		rcv[3].value=irt[3].value; 
		
		menuname.value=rcv[1].value;
	} 
	function btnOver(obj) {
		obj.style.opacity = "0.5";
		infobtn.style.display = "inline-block";
	}
	function btnOut(obj) {
		obj.style.opacity = "1.0";
		infobtn.style.display = "none";
	}

	function closeSrc() {
		insertMenu.style.display = "none";
	}
	function deleteMenu(i) {
		var menuname=document.getElementById("menuname"+i).value;
		
		document.location="/BaegopangMaster/jsp/delete/deleteProcess.jsp?menuname="+menuname;
	}
</script>
</html>