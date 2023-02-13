<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- href 상대경로, 둘 다 가상의 url
	 - /bankBook/list : 현재 있는 경로는 web page 주소창의 url로 생각하기
	 - /resources/images/iu.jpg : 찾아가야할 주소는 sts나 VSCode상에서 파일경로로 생각하기
	 -->
	<c:import url="../template/common_css.jsp"></c:import>
	<link rel="stylesheet" href="/resources/css/table.css">
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

	<h1 class="title">BankBook List Page</h1>
	
	
	
	<table class="tbl2">
		<thead>
			<tr class="tr">
				<th>상품명</th>
				<th>이자율</th>
				<th>판매여부</th>
			</tr>
		</thead>
		<tbody>
			<!-- 값이 담겨있는 model의 영역은 requestScope의 영역과 같음 -->
			<!-- var는 이 page에서만 사용하기 때문에 pageScope이다 -->
		<c:forEach items="${list}" var="dto">
			<tr>
				<td><a href="./detail?bookNumber=${dto.bookNumber}">${pageScope.dto.bookName}</a></td>
				<td class="tbl_td">${dto.bookRate}</td>
				<td class="tbl_td">
					<%-- if else와 switch case의 혼합 --%>
					<%-- when : if같은 느낌, otherwise : else같은 느낌 --%>
					<!-- 123 -->
					<c:choose>
						<c:when test="${dto.bookSale eq 1}">판매중</c:when>
						<c:otherwise>판매종료</c:otherwise>
					</c:choose>
					
					<%-- 					
					<c:if test="${dto.bookSale eq 1}">판매중</c:if>
					<c:if test="${dto.bookSale eq 0}">판매종료</c:if>
					--%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

<a href="./add">상품 등록</a> <br>
<div class="image">
	<img alt="" src="../resources/images/iu.jpg">
</div>

</body>
</html>