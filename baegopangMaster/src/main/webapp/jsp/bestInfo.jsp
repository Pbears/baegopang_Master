<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="/BaegopangMaster/css/Main.css" rel="stylesheet" />
<link rel="stylesheet" href="/BaegopangMaster/css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	
<title>Insert title here</title>
											
</head>  

<body style="background-color: white;">
	 <jsp:include page="header.jsp"></jsp:include> 
	
	<div class="container-fluid">
		<div class="row">
		<div class="col-sm-12" style="height:100px;"></div>
			<div class="col-sm-1" ></div>
			<div class="col-sm-10">


				<!--����  -->

				<div class="container-fluid" align="center" >
					<div class="row">
						<!-- ��Ʈ1-->
						
						<div class="col-sm-12">						
						<div class="col-sm-12"><jsp:include page="genderBestChart.jsp"></jsp:include></div>
						<div class="col-sm-12"><jsp:include page="ageBestChart.jsp"></jsp:include></div>	
						<div class="col-sm-12"><jsp:include page="dayBestChart.jsp"></jsp:include></div>	
						<div class="col-sm-12"><jsp:include page="brandBestChart.jsp"></jsp:include></div>			
						
						</div>
						
						
						</div>
						<!--  -->						
					</div>
				</div>


				<!-- ���γ� -->


			</div>
			<div class="col-sm-1" ></div>
		</div>
		<div class="col-sm-12" style="height:100px;"></div>
 
 

	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>