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
<link rel="stylesheet" href="css/bootstrap.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
.panel-title {
	font-size: 13px;
	padding: initial;
}
</style>
<script>
	function sendCheck() {
		var obj = document.ast; //form까지의 주소
		if (obj.query.value == 'empty' || !obj.data.value) {
			alert('Not Search!');
			obj.query.selectedIndex = 0;

			obj.data.value = '';
			obj.data.focus();
		} else {
			obj.submit();
		}
	}

	function update(index) {
		var obj = document.getElementById("orderfrm" + index);

		obj.submit();

	}

	
</script>
</head>
<body style="background-color: white;">
	<%-- <%
		request.setCharacterEncoding("UTF-8");
		List<OrderBean> list = null;
		String query = request.getParameter("query");
		String data = request.getParameter("data");
		OrderDao dao = new OrderDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
		MasterBean storename = (MasterBean) request.getSession().getAttribute("master");

		map.put("storename", storename.getStorename());
		//map.put("storename", "굽네치킨-역삼1동점");

		int pageScale = 10;
		map.put("Q", query);
		map.put("D", data);
		int currentPage = 0;
		int totalRow = dao.getTotalRow(map);
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
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
		//out.println(startPage+" "+endPage+ " "+currentBlock+" "+totalPage);
		if (totalPage <= endPage)
			endPage = totalPage;
		map.put("start", start);
		map.put("end", end);

		if (query != null && data != null) {
			map.put("query", query);
			map.put("data", data);
			list = dao.selectOrder(map);
		} else {
			list = dao.selectOrder(map);
		}
	%> --%>

	 <jsp:include page="header.jsp" />





	<div class="container-fluid">
		<div class="col-sm-12" style="height: 100px;"></div>
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">

				<h1>주문 내역</h1>

				<p>사장님께 알려드리는 주문내역입니다. 꼭확인해주세요~</p>
				<br> <br> <br>


				<!-- 검색바 -->


				<div id="searcher" class="row">
					<div class="input-group">

						<form action="/web/order.do" name="ast" method="post">
							<table class="bbsWrite mgb35" align="center">
								<colgroup>
									<col width="30" />
									<col width="400" />
									<col width="50" />
								</colgroup>
								<tbody>
									<tr>
										<td height="50"><select name="query" size="1"
											style="height: 34px;">
												<option value="empty" selected="selected">선택하세요</option>
												<option value="ordernumber">주문번호</option>
												<option value="membername">이름</option>
												<option value="membertel">전호번호</option>
										</select></td>
										<td><input type="text" class="form-control"
											placeholder="Search for..." name="data"></td>
										<td><span class="input-group-btn">
												<button class="btn btn-default" type="button">
													<a href="javascript:sendCheck()"> Search</a>
												</button>
										</span></td>
										<div style="postion: relative; left: 100px;">
											<td><a href="/web/order.do?page=1"><button
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



						<c:forEach var="i" items="${list}" varStatus="cnt">



							<tr>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse${cnt.count}">${i.ordernumber}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse${cnt.count}">${i.ordertime}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse${cnt.count}">${i.membername}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse${cnt.count}">${i.membertel}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse${cnt.count}">${i.memberaddress}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse${cnt.count}">${i.orderinfo}</a></td>

								<td><a class="panel-title" data-toggle="collapse"
									href="#collapse${cnt.count}">${i.state}</a></td>


								<c:choose>
									<c:when test="${i.state eq '승인대기' }">
										<td align="center"><a
											href="/web/orderupdate.do?flag=check&ordernumber=${i.ordernumber}"
											class="btn btn-primary btn-success"><span
												class="glyphicon glyphicon-ok"></span> 승인</a> <a
											href="/web/orderupdate.do?flag=refuse&ordernumber=${i.ordernumber}"
											class="btn btn-primary btn-danger"><span
												class="glyphicon glyphicon-remove"></span> 거절</a></td>

									</c:when>
									<c:when test="${i.state eq '승인완료'}">
										<td align="center"><a
											href="javascript:update(${cnt.count})"
											class="btn btn-block btn-primary"><span
												class="glyphicon glyphicon-ok"></span> 발송</a>
									</c:when>
									<c:when test="${i.state eq '배달완료'}">
										<td>&nbsp;</td>

									</c:when>
								</c:choose>

							</tr>

							<tr>
								<td colspan="8">
									<div id="collapse${cnt.count}" class="panel-collapse collapse">
										<ul class="list-group">
											<li class="list-group-item" style="background-color: #f1f0ef">메뉴
												: ${i.menuname}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 수량 :
												${i.amount}</li>
										</ul>
									</div>
								</td>
								<td>
									<form
										action="/web/orderupdate.do?flag=del&ordernumber=${i.ordernumber}"
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
							<li><a href="/web/order.do?page=1" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
							</a></li>
							<li>
							<li><c:choose>
									<c:when test="${currentBlock>1}">
										<c:choose>
											<c:when test="${currentPage != startPage}">
												<a
													href="/web/order.do?page=${startPage - 1}&query=${query }&data=${data }">
													Previous </a>
											</c:when>
											<c:otherwise>
												<a href="#">Previous</a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${currentPage != startPage}">
												<a
													href="/web/order.do?page=${currentPage - 1}&query=${query }&data=${data }">
													Previous </a>
											</c:when>
											<c:otherwise>

												<a href="#">Previous</a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose></li>
							<span> <c:forEach var="i" begin="${startPage}"
									end="${endPage}" step="1">
									<c:choose>
										<c:when test="${i==currentPage}">

											<li><a href="#"><strong>${i}</strong></a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="/web/order.do?page=${i}&query=${query}&data=${data}">
													${i} </a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</span>
							<li><c:choose>
									<c:when test="${totalPage > endPage}">
										<c:choose>
											<c:when test="${currentPage != endPage}">

												<a
													href="/web/order.do?page=${currentPage + 1}&query=${query}&data=${data}">
													Next </a>
											</c:when>
											<c:otherwise>
												<a href="#">Next</a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${currentPage != endPage}">
												<a
													href="/web/order.do?page=${currentPage + 1}&query=${query}&data=${data}">
													Next </a>
											</c:when>
											<c:otherwise>
												<a href="#">Next</a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose></li>

							<li><a
								href="/web/order.do?page=${totalPage}&query=${query}&data=${data}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</ul>
				</div>





			</div>
			<div class="col-sm-1"></div>
			<div class="col-sm-12" style="height: 100px;"></div>
		</div>
	</div>


	<jsp:include page="masterOrder2.jsp"></jsp:include>












	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script src="//code.jquery.com/jquery.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
</html>