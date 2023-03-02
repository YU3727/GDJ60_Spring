<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<c:import url="../template/common_css.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

	<!-- 전체는 container나 container-fluid로 감싸라 -->
	<div class="container-fluid my-5">
	
		<div class="row justify-content-center">
			<h1 class="col-md-7 mx-auto text-center border-bottom border-dark pb-4">My Page</h1>
		</div>
		
		<!-- 데이터는 로그인할떄 세션에 정보가 남아있다. 세션에 접근하기 위해 Attribute의 이름인 member로 접근한다 -->
		<div class="row justify-content-center">
		<form action="./memberLogin" class="col-md-7" method="post">
			
			<!-- id만 받아와서 쓰는거 하기 위해 member에서 불러오는게 아니라 dto에서 불러와야함 -->
			<div class="mb-3">
			  <label for="memberName" class="form-label">회원이름</label>
			  <input type="text" name="memberName" class="form-control" id="memberName" Value="${dto.memberName}" readonly>
			</div>
			
			<div class="mb-3">
			  <label for="memberPhone" class="form-label">전화번호</label>
			  <input type="text" name="memberPhone" class="form-control" id="memberPhone" value="${dto.memberPhone}" readonly>
			</div>
			
			<div class="mb-3">
			  <label for="email" class="form-label">이메일</label>
			  <input type="text" name="email" class="form-control" id="email" value="${dto.email}" readonly>
			</div>
			
			<div class="mb-3">
			  <label for="roleName" class="form-label">권한등급</label>
			  <input type="text" name="roleName" class="form-control" id="roleName" value="${dto.roleDTO.roleName}" readonly>
			</div>
			
			<div class="mb-3">
				<a href="./memberUpdate" btn btn-danger>정보수정</a>
			</div>
		</div>
		</form>
	
	</div>
<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>