<%@page import="com.pooh.s1.product.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product Detail Page</h1>
	<!-- 스크립틀릿 -->
	<!-- jsp에서 java코드를 사용하기 위해 쓰는 '꺽쇠% %꺽쇠'는 스크립틀릿이라 부름. 출력은 '꺽쇠%= %꺽쇠'으로 -->
	<!-- JSP는 Model, ModelAndView타입을 모른다. 대신 request를 사용함 -->
	<% ProductDTO productDTO = (ProductDTO)request.getAttribute("dto"); %>
	<h3><%= productDTO.getProductName() %></h3>
	<h3><%= productDTO.getProductDetail() %></h3>
	
	<hr>
	<!-- EL 형식 -->
	<!-- ${requestScope.dto} 까지가 위의 ProductDTO productDTO = (ProductDTO)request.getAttribute("dto");에 해당 -->
	<h3>${requestScope.dto.productName}</h3>
	<h3>${requestScope.dto.getProductDetail()}</h3>
	<!-- Scope명은 생략가능하다. 그러나 다른 Scope영역에 같은 속성명이 존재할 수 있는데, 이때는 영역명인 스코프 이름을 써줘야함 -->
	<h3>${dto.getProductDetail()}</h3>
	<!-- 연산자를 이용할때는 하나의 중괄호 안에서 연산을 처리해야한다. 연산자 사용 연습-->
	<h3>${dto.productScore * dto.productNum}</h3>
</body>
</html>