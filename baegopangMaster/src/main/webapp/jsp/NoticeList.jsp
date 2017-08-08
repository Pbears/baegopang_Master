<%@page import="sist.spring.bean.NoticeBean"%>
<%@page import="sist.spring.dao.NoticeDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	function NoticeOne(i){
		var obj=document.getElementById("notitle"+i).value;
		 window.location.href="Notice.jsp?title="+encodeURI(obj); 
	}
</script>
<body>
<%-- <%
	NoticeDao dao =new NoticeDao();
	List<NoticeBean>list = null;
	
	HashMap<String, Object> map = new HashMap<String, Object>();
	//map.put("masterid", masterid);
	int pageScale = 5;

	int currentPage = 0;
	int totalRow = dao.getNTotalRows();
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
	//   out.println(startPage+" "+endPage+ " "+currentBlock+" "+totalPage);
	if (totalPage <= endPage)
		endPage = totalPage;
	map.put("start", start);
	map.put("end", end);
%>
 --%>

<div class="container">
  <h2>공지사항</h2>          
  <table class="table table-hover">
    <thead>
      <tr>
        <th>No</th>
        <th>날짜</th>
        <th>글 제목</th>
      </tr>
    </thead>
    <tbody>
<%--     <%
    	list = dao.selectPage(map);
    	for(int i=0; i<list.size(); i++){
    		NoticeBean bean = list.get(i);
    %> --%>
    <c:forEach var="i" items="${notice }" varStatus="cnt">
      <tr tabindex="1">
        <td>${i.no }</td>
        <td>${i.regdate }</td>
        <td id="ntitle${cnt.count }" onclick="NoticeOne(${cnt.count})">${i.title }</td>
      </tr>
        <input type="hidden" id="notitle${cnt.count }" value="${i.title }">
     </c:forEach>
    </tbody>
  </table>
</div>
	<!-- 페이지이동페이징 -->
			<div class="paging" style="text-align: center;">
				<ul class="pagination">
					<ul class="pager">
						<li><a href="/web/NoticeList.do?page=1"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<li>
						<li>
							<%-- <%
								if (currentBlock > 1) {
									if (currentPage != startPage) {
							%> --%>
							<c:choose>
								<c:when test="${currentBlock > 1 }">
									<c:choose>
										<c:when test="${currentPage != startPage }">
							 <a
							href="/web/NoticeList.do?page=${startPage - 1 }">
								Previous </a> 
								<%-- <%
								 	} else {
								 %>  --%>
								 </c:when>
								 <c:otherwise>
									 <a href="#">Previous</a> 
								 </c:otherwise>
								</c:choose> 
								 <%-- <%
								 	}
								 	} else {
								 		if (currentPage != startPage) {
								 %> --%> 
								 </c:when>
								
								 <c:otherwise>
								  <c:choose>
								 <c:when test="${currentPage != startPage }">
								 <a
							href="/web/NoticeList.do?page=${currentPage - 1 }">
								Previous </a> <%-- <%
 	} else {
 %>  --%>
 	</c:when>
 	<c:otherwise>
 	<a href="#">Previous</a> <%-- <%
 	}
 	}
 %> --%>
 </c:otherwise>
 </c:choose>
 </c:otherwise>
 </c:choose>
						</li>
						<span>
<%--  <%


 	for (int i = startPage; i <= endPage; i++) {
 		if (i == currentPage) {
 %>  --%>
	<c:forEach var="i" begin="${startPage}" end="${endPage }"> 
		<c:choose>
			<c:when test="${i == currentPage }">

		<li><a href="#"><strong>${i }</strong></a></li> 
<%-- <%
 	} else {
 %> --%>
</c:when>
<c:otherwise>

							<li><a
								href="/web/NoticeList.do?page=${i }">
									${i }
							</a></li> 
<%-- <%
 		}
 	}
 %>
 --%> 
 	</c:otherwise>	
 	</c:choose>
  </c:forEach>
						</span>
						<li>
							<%-- <%
								if (totalPage > endPage) {
									if (currentPage != endPage) {
							%> --%>
							<c:choose>
								<c:when test="${totalPage > endPage }">
									<c:choose>
										<c:when test="${currentPage != endPage }">
							 <a href="/web/NoticeList.do?page=${currentPage + 1 }">
								Next </a> 
<%-- <%
 	} else {
 %>  --%>
  </c:when>
  <c:otherwise>
 <a href="#">Next</a>
 </c:otherwise>
<%--   <%
 	}
 	} else {
 		if (currentPage != endPage) {
 %>  --%>
 </c:choose>
 </c:when>
 <c:otherwise>
	<c:choose>
		<c:when test="${currentPage != endPage }">
	
	
 <a href="/web/NoticeList.do?page=${currentPage + 1 }">Next </a>
 </c:when>
 <c:otherwise> 
<%-- <%
 	} else {
 %> --%>
 	 <a href="#">Next</a>
 </c:otherwise>
 </c:choose>
<%--  <%
 	}
 	}
 %> --%>
 </c:otherwise>
  </c:choose>

						</li>

						<li><a
							href="/web/NoticeListn.do?page=${totalPage }"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span> 
						</a></li>
					</ul>
			</div>

		</div>
</body>
</html>