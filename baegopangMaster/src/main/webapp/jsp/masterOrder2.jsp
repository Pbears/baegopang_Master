<%@page import="sist.spring.bean.MasterBean"%>
<%@page import="sist.spring.dao.OrderDao"%>
<%@page import="sist.spring.bean.OrderBean"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.panel-title {
	font-size: 13px;
	padding: initial;
}
</style>
<script>
	function sendCheck2() {
		var obj = document.ast2; //form까지의 주소
		if (obj.query2.value == 'empty' || !obj.data2.value) {
			alert('Not Search!');
			obj.query2.selectedIndex = 0;

			obj.data2.value = '';
			obj.data2.focus();
		} else {
			obj.submit();
		}
	}
</script>
</head>
<body style="background-color: white;">
	<%-- <%
		request.setCharacterEncoding("UTF-8");
		List<OrderBean> list2 = null;
		String query2 = request.getParameter("query2");
		String data2 = request.getParameter("data2");
		OrderDao dao = new OrderDao();
		HashMap<String, Object> map2 = new HashMap<String, Object>();

		MasterBean storename1 = (MasterBean) request.getSession().getAttribute("master");

		map2.put("storename", storename1.getStorename());

		int pageScale = 10;
		map2.put("Q2", query2);
		map2.put("D2", data2);
		int currentPage = 0;
		int totalRow = dao.getTotalRowCom(map2);
		try {
			currentPage = Integer.parseInt(request.getParameter("page2"));
		} catch (Exception e) {
			currentPage = 1;
		}
		int totalPage = totalRow % pageScale == 0 ? totalRow / pageScale : totalRow / pageScale + 1;
		if (totalRow == 0)
			totalPage = 1;
		int start = 1 + (currentPage - 1) * pageScale;
		int end = pageScale + (currentPage - 1) * pageScale;
		//out.print(query+"   "+data ); //출력확인
		int currentBlock = currentPage % pageScale == 0 ? (currentPage / pageScale) : (currentPage / pageScale + 1);
		int startPage = 1 + (currentBlock - 1) * pageScale;
		int endPage = pageScale + (currentBlock - 1) * pageScale;
		//   out.println(startPage+" "+endPage+ " "+currentBlock+" "+totalPage);
		if (totalPage <= endPage)
			endPage = totalPage;
		map2.put("start2", start);
		map2.put("end2", end);

		if (query2 != null && data2 != null) {
			map2.put("query2", query2);
			map2.put("data2", data2);

			list2 = dao.selectOrderCom(map2);

		} else {
			list2 = dao.selectOrderCom(map2);
		}
	%> --%>

	${currentBlock2} ${startPage2}
	${currentPage2}





	<div class="container-fluid">
		<div class="col-sm-12" style="height: 100px;"></div>
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">

				<h1>주문 완료된 내역</h1>

				<br> <br> <br>


				<!-- 검색바 -->


				<div id="searcher" class="row">
					<div class="input-group">

						<form action="masterOrder.jsp" name="ast2" method="post">
							<table class="bbsWrite mgb35" align="center">
								<colgroup>
									<col width="30" />
									<col width="400" />
									<col width="50" />
								</colgroup>
								<tbody>
									<tr>
										<td height="50"><select name="query2" size="1"
											style="height: 34px;">
												<option value="empty" selected="selected">선택하세요</option>
												<option value="ordernumber">주문번호</option>
												<option value="membername">이름</option>
												<option value="membertel">전호번호</option>
										</select></td>
										<td><input type="text" class="form-control"
											placeholder="Search for..." name="data2"></td>
										<td><span class="input-group-btn">
												<button class="btn btn-default" type="button">
													<a href="javascript:sendCheck2()"> Search</a>
												</button>
										</span></td>
										<div style="postion: relative; left: 100px;">
											<td><a href="masterOrder.jsp?page=1"><button
														type="button" class="btn btn-default">새로고침</button></a></td>
											<td>
										</div>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<!-- /input-group -->
				</div>
				<!--  -->

				<table class="table table-condensed">
					<thead>
						<tr>
							<th>주문번호</th>
							<th>주문시간</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>주소</th>
							<th>요청</th>
							<th>상태</th>
							<th></th>

						</tr>
					</thead>
					<tbody>

						<!-- 한블럭 -->

						<c:forEach var="i" items="${list2}" varStatus="cnt">



							<tr>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse2${cnt.count}">${i.ordernumber}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse2${cnt.count}">${i.ordertime}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse2${cnt.count}">${i.membername}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse2${cnt.count}">${i.membertel}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse2${cnt.count}">${i.memberaddress}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse2${cnt.count}">${i.orderinfo}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse2${cnt.count}">${i.state}</a></td>


								

							</tr>

							<tr>
								<td colspan="8">
									<div id="collapse2${cnt.count}" class="panel-collapse collapse">
										<ul class="list-group">
											<li class="list-group-item" style="background-color: #f1f0ef">메뉴
												: ${i.menuname}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 수량 :
												${i.amount}</li>
										</ul>
									</div>
								</td>
								<td>
									<form
										action="/BaegopangMaster/jsp/update/orderUpdate.jsp?flag=del&ordernumber=${i.ordernumber}"
										method="post" id="orderfrm${cnt.count}"
										name="orderfrm${cnt.count}">
										<input type="hidden" name="amount" value="${i.amount}">
										<input type="hidden" name="price" value="${i.price}">
										<input type="hidden" name="menuname" value="${i.menuname}">
										<input type="hidden" name="storename" value="${i.storename}">
										<input type="hidden" name="ordertime" value="${i.ordertime}">
										<input type="hidden" name="memberid" value="${i.memberid}">
									</form>
								</td>
							</tr>

							<%-- <%
							}
						%> --%>
						</c:forEach>
						<!--for문 끝  -->

					</tbody>
				</table>



				<!-- 페이지이동페이징 -->
				<div class="paging" style="text-align: center;">
					<ul class="pagination">
						<ul class="pager">
							<li><a href="/web/order.do?page2=1"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
							<li>
							<li><c:choose>
									<c:when test="${currentBlock2>1}">
										<c:choose>
											<c:when test="${currentPage2 != startPage2}">
												<a
													href="/web/order.do?page2=${startPage2 - 1}&query2=${query2 }&data2=${data2 }">
													Previous </a>
											</c:when>
											<c:otherwise>
												<a href="#">Previous</a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${currentPage2 != startPage2}">
												<a
													href="/web/order.do?page2=${currentPage2 - 1}&query2=${query2 }&data2=${data2 }">
													Previous </a>
											</c:when>
											<c:otherwise>

												<a href="#">Previous</a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose></li>
							<span> <c:forEach var="i" begin="${startPage2}"
									end="${endPage2}" step="1">
									<c:choose>
										<c:when test="${i==currentPage2}">

											<li><a href="#"><strong>${i}</strong></a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="/web/order.do?page2=${i}&query2=${query2}&data2=${data2}">
													${i} </a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</span>
							<li><c:choose>
									<c:when test="${totalPage2 > endPage2}">
										<c:choose>
											<c:when test="${currentPage2 != endPage2}">

												<a
													href="/web/order2.do?page2=${currentPage2 + 1}&query2=${query2}&data2=${data2}">
													Next </a>
											</c:when>
											<c:otherwise>
												<a href="#">Next</a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${currentPage2 != endPage2}">
												<a
													href="/web/order.do?page2=${currentPage2 + 1}&query2=${query2}&data2=${data2}">
													Next </a>
											</c:when>
											<c:otherwise>
												<a href="#">Next</a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose></li>

							<li><a
								href="/web/order.do?page2=${totalPage2}&query2=${query2}&data2=${data2}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</ul>
				</div>





			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-12" style="height: 100px;"></div>
		</div>
	</div>












</body>
</html>