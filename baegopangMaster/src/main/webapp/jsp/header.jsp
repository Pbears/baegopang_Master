<%@page import="sist.spring.bean.MasterBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/Main.css" rel="stylesheet" />
<style>
html {
	margin: 10px;
}

header {
	padding: 1em 1em 0 1em;
	border: 1px;
	border-color: black;
	color: black;
	background-color: #FF8C00;
	clear: left;
	text-align: center;
	margin: auto;
	margin-bottom: 50px;
	width: 1600px;
}

#headerContainer{
	width: 100%
}

#mainTitle {
	margin-bottom: 50px;
}

#mainTitle img {
	width: 350px;
	height: 95px;
}

#loginBtn {
	width: 50px;
	height: 50px;
	margin-left: 200px;
}

button#loginBtn.btn btn-default btn-lg {
	width: 35px;
	height: 35px;
}
</style>
<header class="col-lg-12">
	
	<div id="headerContainer">
		<div id="mainTitle" align="center">
			<a href="main.do"> <img
				src="img/beagopangTitle.png">
			</a>
		</div>
		<div id="searchContainer">
			<table align="center">
				<tr>
					<td>
						<div class="dropdown1">
							<button class="dropbtn1">내 음식점</button>
							<div class="dropdown-content1">
								<a href="menuManager.do">메뉴 관리</a><a href="/web/order.do">주
									문</a><a href="ReplyManage.do?page=1">댓글 관리</a>

	

							</div>
						</div>

					</td>
					<td>
						<div class="dropdown2">
							<button class="dropbtn2">통 계</button>
							<div class="dropdown-content2">
								<a href="/web/salesInfo.do">매출 통계</a><a href="/web/bestInfo.do">베스트 통계</a>
							</div>
						</div>


					</td>
					<td>
						<div class="dropdown3">
							<button class="dropbtn3">포인트</button>
							<div class="dropdown-content3">
								<a href="Point.do">포인트</a>
							</div>
						</div>
					</td>
					<td>
						<div class="dropdown4">
							<button class="dropbtn4">마이페이지</button>
							<div class="dropdown-content4">
								<a href="/web/masterInfo.do">내정보 보기</a>
							</div>
						</div>
					</td>
					<td>
						<div class="dropdown5">
							<button class="dropbtn5">고객센터</button>
							<div class="dropdown-content5">
								<a href="question.do">문의</a>
							</div>
						</div>
					</td>
				</tr>
					<div id="tab_user" style="display: inline-block; float: right; margin-top: 30px; color: white; font-weight: bold;">
					${master.id} 님 환영합니다</div>
			</table>
		</div>
	</div>
</header>