<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<c:import url="../template/common_css.jsp"></c:import>
	<link rel="stylesheet" href="/resources/css/table.css">
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>

<div class="container-fluid my-5">

	<div class="row mb-4">
		<h1 class="col-md-7 mx-auto text-center border-bottom border-dark pb-4">Member List Page</h1>
	</div>
	
	<div class="row col-md-7 mx-auto">
		<table class="table table-hover">
			<thead>
				<tr class="tr">
					<th>아이디</th>
					<th>비밀번호</th>
					<th>회원이름</th>
					<th>전화번호</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody class="table-group-divider mx-auto">
				<!-- 값이 담겨있는 model의 영역은 requestScope의 영역과 같음 -->
				<!-- var는 이 page에서만 사용하기 때문에 pageScope이다 -->
			<c:forEach items="${list}" var="dto">
				<tr>
					<td><a href="./detail?id=${dto.id}">${pageScope.dto.id}</a></td>
					<td class="tbl_td">${dto.pw}</td>
					<td class="tbl_td">${dto.memberName}</td>
					<td class="tbl_td">${dto.memberPhone}</td>
					<td class="tbl_td">${dto.email}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="row col-md-7 mx-auto">
		<a href="./memberAdd" class="btn btn-primary mx-auto col-2">회원 가입</a> <br>
	</div>
	
</div>

<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>