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
	<!-- jsp에서 java코드를 사용하기 위해 쓰는 '꺽쇠% %꺽쇠'는 스크립틀릿이라 부름. 출력은 '꺽쇠%= %꺽쇠'으로 -->
	<!-- JSP는 Model, ModelAndView타입을 모른다. 대신 request를 사용함 -->
	<% ProductDTO productDTO = (ProductDTO)request.getAttribute("dto"); %>
	<h3><%= productDTO.getProductName() %></h3>
	<h3><%= productDTO.getProductDetail() %></h3>
	
	<hr>
	<!-- EL 형식 -->
	<!-- ${requestScope.dto} 까지가 위의 ProductDTO productDTO = (ProductDTO)request.getAttribute("dto");에 해당 -->
	<h3>${requestScope.dto.productName}</h3>
	<h3>${requestScope.dto.productDetail}</h3>
</body>
</html>