<%@page import="java.util.List"%>
<%@page import="com.pooh.s1.product.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 접두사로 c. 이제 얘는 c라는 태그가 붙는다. 태그가 정의되어있는 주소는 url(자동완성하고 위에서 3번째거) -->
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품정보</title>
	<c:import url="../template/common_css.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
	
	<div class="row">
		<h1 class="title">Product List Page</h1>
	</div>

	<!-- div블럭으로 테이블, 페이징, 검색창을 크게 감싼다 -->
	<div class="row">
		<!-- table -->
		<table class="tbl">
			<thead>
				<tr>
					<th>상품명</th>
					<th>평점</th>
				</tr>
			</thead>
			
			<tbody>
				<!-- EL($) -->
				<!-- EL에서는 안꺼내와도 되고, 형변환도 안해도 됨 -->
				<!-- if는 단일 if문, choose는 switch/case, for는 forEach -->
				<!-- 반복문 forEach 뒤에 attribute로 items에 꺼내올 list, var는 대입할 변수(dto)를 의미 -->
				<c:forEach items="${list}" var="dto"> <!-- dto는 page영역에 담김. page는 JSP영역에서만 사용가능한 Scope -->
					<tr>
						<td><a href="./detail?productNum=${dto.productNum}">${pageScope.dto.productName }</a></td>
						<td>${dto.productScore}</td>
					<tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- paging -->
		<div class="row">
			<nav aria-label="Page navigation example">
			  <ul class="pagination">
			  
			    <li class="page-item">
			      <a class="page-link" href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    
<%-- 			    <c:forEach>
			    	<li class="page-item"><a class="page-link" href="#">1</a></li>
			    </c:forEach> --%>
			    
			    <li class="page-item">
			      <a class="page-link" href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li> 
			  </ul>
			</nav>
		</div>
		
		
		
		
		<!-- 버튼 -->
		<div class="row">
			<a class="btn btn-danger" href="./productAdd">상품등록</a>
		</div>
	</div>
	<!-- 예전에는 스크립틀릿을 썼는데, 요새는 EL($)을 쓴다 -->
<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>

	<!-- 스크립틀릿과 EL에서 반복문을 사용하는 방법 -->
	<!-- 스크립틀릿 -->
<%-- 	<%
		List<ProductDTO> ar = (List<ProductDTO>)request.getAttribute("list");
		for(ProductDTO productDTO : ar){
	%>
		<h3><%=productDTO.getProductName() %></h3>
		<h3><%=productDTO.getProductScore() %></h3>
	<%} %>
	
	<hr> --%>
	
	