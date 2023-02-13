<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저축상품</title>
	<!-- href 상대경로, 둘 다 가상의 url
	 - /bankBook/list : 현재 있는 경로는 web page 주소창의 url로 생각하기
	 - /resources/images/iu.jpg : 찾아가야할 주소는 sts나 VSCode상에서 파일경로로 생각하기
	 -->
	<c:import url="../template/common_css.jsp"></c:import>
	<!-- <link rel="stylesheet" href="/resources/css/table.css"> -->
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container-fluid my-5">

	<div class="row mb-4">
		<h1 class="col-md-7 mx-auto text-center border-bottom border-dark pb-4">BankBook List Page</h1>
	</div>
	
	<div class="row col-md-7 mx-auto">
		<!-- boot-strap에 이미 만들어져있는 클래스명을 쓴다 -->
		<table class="table table-hover">
			<thead>
				<tr class="tr">
					<th>상품명</th>
					<th>이자율</th>
					<th>판매여부</th>
				</tr>
			</thead>
			<tbody class="table-group-divider mx-auto">
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
	</div>
	
	<div class="row col-md-7 mx-auto">
		<a href="./add" class="btn btn-primary mx-auto col-2">상품 등록</a> <br>
	</div>
	
</div>

<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>