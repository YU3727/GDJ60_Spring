<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/common_css.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
	
<div class="container-fluid my-5">

	<div class="row justify-content-center">
		<h1 class="col-md-7 mx-auto text-center border-bottom border-dark pb-4">Member Login Page</h1>
	</div>
	
	<div class="row justify-content-center">
	<form class="col-md-7" action="./memberLogin" method="post">
		<div class="mb-3">
		  <label for="id" class="form-label">ID</label>
		  <input type="text" name="id" class="form-control" id="id" placeholder="id 입력">
		</div>
		
		<div class="mb-3">
		  <label for="pw" class="form-label">Password</label>
		  <input type="password" name="pw" class="form-control" id="pw" placeholder="password 입력">
		</div>
	</div>
	
	<div class="mb-3 form-check">
		<input type="checkbox" name="remember" value="remember" class="form-check-input" id="remember">
		<label class="form-check-label" for="remember">ID 기억하기</label>
	</div>

		<div class="row justify-content-center mx-auto">
			<button class="btn btn-outline-success col-2" type="submit" value="등록">로그인</button>	
		</div>

	</form>
</div>


<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>