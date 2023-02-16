<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html> <!-- html5 버전이라는 의미 -->
<html>
<head>
	<title>Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<c:import url="./template/common_css.jsp"></c:import>
</head>
<body>
	<!-- header를 직접 작성하지 말고, 공통적인 부분이기 때문에 따로 header.jsp로 만들고 이걸 가져온다 -->
	<!-- *주의 : 아래 경로는 wep url이 아니라 서버 내부의 주소를 찾아가야하므로 jsp의 위치를 봐야한다. -->
	<!-- 서버 내부 주소, 상대경로로 작성 -->
	<c:import url="./template/header.jsp"></c:import>

	<div class="container-fluid my-5">
		<div class="row col-md-6 offset-md-3">
			<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
			  <div class="carousel-inner">
			  	<!-- active : 처음 누구부터 보일거냐, alt : 이미지 없을 때 어떻게 할거냐 -->
			    <div class="carousel-item active">
			      <img src="/resources/images/s1.jpg" class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
			      <img src="/resources/images/s2.jpg" class="d-block w-100" alt="...">
			    </div>
			    <div class="carousel-item">
			      <img src="/resources/images/s3.jpg" class="d-block w-100" alt="...">
			    </div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
		</div>
	</div>

<div>
	<h1>${sessionScope.member.memberName}</h1>
</div>


<!-- script 파일은 </body>바로 위에 많이들 써둔다 -->
<c:import url="./template/common_js.jsp"></c:import>
</body>
</html>
