<%@page import="sist.spring.bean.PointBean"%>
<%@page import="sist.spring.dao.PointDao"%>
<%@page import="sist.spring.bean.NoticeBean"%>
<%@page import="sist.spring.dao.NoticeDao"%>
<%@page import="sist.spring.bean.MasterBean"%>
<%@page import="sist.spring.dao.MemberDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="css/Main.css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function mouseOv(obj) {
		obj.style.background = "#84D5CE";
	}
	function mouseOt(obj) {
		obj.style.background = "#44BFB7";
	}
	function noticeView(i){
		var obj=document.getElementById("li"+i).value; 
		//window.open("selectQuestion.jsp?title="+obj,"noticeCk","width=400, height=300")
	}
	function replyPage() {
		location.href="ReplyManage	.jsp";
	}
	function questionPage() {
		location.href="question.jsp";
	}
</script>
</head>
<body>
	<%
		String id = (String) session.getAttribute("id");
		MemberDao dao = new MemberDao();
		MasterBean bean = (MasterBean) session.getAttribute("master");
		String storename = bean.getStorename();
		NoticeDao ndao = new NoticeDao();
		/* session에 넣지말고 
		 * (MasterBean)session.getAttribute("master") 안에 정보 다 넣어뒀으니까 
		 * 여기서 뽑아서 쓰세용 ~ 확인하시면 이 주석 삭제부탁드립니다.
		 */
		session.setAttribute("storename", storename);
	%>
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	<div class="col-lg-12" id="mainTopDiv">
		<div id="myCarousel" class="container carousel col-lg-9"
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">

				<div class="item active">
					<img src="img/main_img.jpg" alt="Los Angeles"
						style="width: 100%;">
				</div>

				<div class="item">
					<img src="img/main_img2.jpg" alt="Chicago" style="width: 100%;">
				</div>

				<div class="item">
					<img src="img/main_img3.jpg" alt="New York" style="width: 100%;">
				</div>

			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		
		<div class="panel panel-default col-lg-2">
			<div class="panel-heading">
				<h3 class="panel-title">회원정보</h3>
			</div>
			<div class="panel-body">
				<div class="info_user">
					<img src="img/noimage.jpg" width="120px" height="120px" />
					<div class="myinfo">
						<p>
							이름 :
							<%=bean.getName()%></p>
						<p>
							지점 :
							<%=bean.getStorename()%></p>
						<p>
							포인트 :
							<%=bean.getPoint()%>팡</p>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="col-lg-12" id="mainDownDiv">
		<div class="panel panel-default col-lg-4">
			<div class="panel-heading">
				<h3 class="panel-title" style="font-weight: bold;">공지사항</h3>
			</div>
			<div class="panel-body">
				<div id="notice">
					<div id="notice_list">
						<ui> <%
							 	List<NoticeBean> list = null;
							 	list = ndao.noticeSel();
							 	for (int i = 0; i < list.size(); i++) {
							 		NoticeBean nbean = list.get(i);
							 %> <a id="noticeAll"
							onclick="window.open('Notice.jsp?title=<%=nbean.getTitle()%>',target='_blank','width=300', 'height=200')">
							<li style="font-size: 18px; margin-bottom: 15px; color: black;"
							id="li<%=i%>" onclick="noticeView(<%=i%>)"
							value="<%=nbean.getTitle()%>"><%=nbean.getTitle()%></li>
							</a> <%
						 	}
						 %> </ui>
					</div>
					<a href="#"
						onclick="window.open('NoticeList.jsp','_blank','width=400', 'height=500')"
						style="padding: 0px;" class="noti_title">
						<p
							style="display: inline-block; font-size: 15px; color: #646464; margin-top: 15px;">더보기</p>
					</a>
				</div>
			</div>
		</div>
		<%
			PointDao bdao= new PointDao();
			PointDao gdao= new PointDao();
			PointBean bbean = bdao.mybrandPoint(storename);
			
		%>
		<div class="panel panel-default col-lg-4">
			<div class="panel-heading">
				<h3 class="panel-title"  style="font-weight: bold;">내가게 순위</h3>
			</div>
			<div class="panel-body">
				<div id="brand_rank"
					style="display: inline-block; float: left; padding: 0 40px; margin:0 30px; text-align: center;">
					<h4 style="font-weight: bold;">브랜드 별</h4>
					<img src="/BaegopangMaster/img/noimg.jpg" width="80px"
						height="80px" style="border-radius: 50px;">
					<h2><%=bbean.getNum() %>등</h2>
				</div>
				<%
				PointBean gbean = gdao.myguPoint(storename);
				%>
				<div id="gu_rank"
					style="display: inline-block; float: left; padding: 0 40px; text-align: center; margin:0 30px;">
					<h4 style="font-weight: bold;">구 별</h4>
					<img src="/BaegopangMaster/img/noimg.jpg" width="80px"
						height="80px" style="border-radius: 50px;">
						<h2><%=gbean.getNum() %>등</h2>
				</div>
			</div>
		</div>
	
	
		<div class="panel panel-default col-lg-3">
			<div class="panel-heading">
				<h3 class="panel-title"  style="font-weight: bold;">문의</h3>
			</div>
			<div class="panel-body">
				<h5 onclick="replyPage()" style="margin-top: 30px;">댓글 확인하러 가기</h5>
				<h5 onclick="questionPage()" style="margin-top: 20px;">문의 하러 가기</h5>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>