<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>BankBook List Page</h1>
	
	<table border="1">
		<thead>
			<tr>
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
				<td>${dto.bookRate}</td>
				<td>
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
	
	<a href="./add">상품 등록</a>
</body>
</html>