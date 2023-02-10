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
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/table.css">
</head>
<body>
	<h1 class="title">Product List Page</h1>
	<!-- list ar에 데이터가 담겨져 왔는지 확인 <h3>${requestScope.list }</h3>-->
	
	<!-- 스크립틀릿과 EL에서 반복문을 사용하는 방법 -->
	<!-- 스크립틀릿 -->
	<%
		List<ProductDTO> ar = (List<ProductDTO>)request.getAttribute("list");
		for(ProductDTO productDTO : ar){
	%>
		<h3><%=productDTO.getProductName() %></h3>
		<h3><%=productDTO.getProductScore() %></h3>
	<%} %>
	
	<hr>
	
	<!-- html을 통해 내용을 table에 넣어보자 -->
	<table class="tbl">
		<thead>
			<tr>
				<th>상품명</th><th>평점</th>
			</tr>
		</thead>
		
		<tbody>
		
	<!-- EL($) -->
	<!-- if는 단일 if문, choose는 switch/case, for는 forEach -->
	<!-- EL에서는 안꺼내와도 되고, 형변환도 안해도 됨 -->
	<!-- 반복문 forEach 뒤에 attribute로 items에 꺼내올 list, var는 대입할 변수(dto)를 의미 -->
	<c:forEach items="${list}" var="dto"> <!-- dto는 page영역에 담김. page는 JSP영역에서만 사용가능한 Scope -->
		<tr>
			<td><a href="./detail?productNum=${dto.productNum }">${pageScope.dto.productName }</a></td>
			<td>${dto.productScore }</td>
		<tr>
	</c:forEach>
		
		</tbody>
	</table>
	
		<a class="btn btn-danger" href="./productAdd">상품등록</a>
	
	
	<!-- 예전에는 스크립틀릿을 썼는데, 요새는 EL($)을 쓴다 -->
</body>
</html>