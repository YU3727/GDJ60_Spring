<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 230213 7~8교시 bootstrap -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join us</title>
	<c:import url="../template/common_css.jsp"></c:import>
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>

<div class="container-fluid my-5">

	<div class="row justify-content-center">
		<h1 class="col-md-7 mx-auto text-center border-bottom border-dark pb-4">Member Join Page</h1>
	</div>
	
	<div class="row justify-content-center">
	<form class="col-md-7" action="./memberAdd" method="post">
		<div class="mb-3">
		  <label for="id" class="form-label">ID</label>
		  <input type="text" name="id" class="form-control" id="id" placeholder="id 입력">
		</div>
		
		<div class="mb-3">
		  <label for="pw" class="form-label">Password</label>
		  <input type="text" name="pw" class="form-control" id="pw" placeholder="password 입력">
		</div>

		<div class="mb-3">
		  <label for="name" class="form-label">이름</label>
		  <input type="text" name="memberName" class="form-control" id="name" placeholder="회원 이름 입력">
		</div>

		<div class="mb-3">
		  <label for="phone" class="form-label">전화번호</label>
		  <input type="text" name="memberPhone" class="form-control" id="phone" placeholder="전화번호 입력">
		</div>
		
		<div class="mb-3">
		  <label for="email" class="form-label">e-mail</label>
		  <input type="text" name="email" class="form-control" id="email" placeholder="e-mail 입력">
		</div>

	</div>

		<div class="row justify-content-center mx-auto">
			<button class="btn btn-outline-success col-2" type="submit" value="등록">회원가입</button>	
		</div>


	</form>
</div>

<c:import url="../template/common_js.jsp"></c:import>

</body>
</html>