<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 230209 2교시 입력태그 -->
<!-- 230213 6~7교시 bootstrap -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bankbook Add Page</title>
	<c:import url="../template/common_css.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container-fluid my-5">

	<div class="row justify-content-center">
		<h1 class="col-md-7 mx-auto text-center border-bottom border-dark pb-4">BankBook Add Page</h1>
	</div>
	
	<div class="row justify-content-center">
	<form class="col-md-7" action="./add" method="post" enctype="multipart/form-data">
		<div class="mb-3">
		  <label for="bookName" class="form-label">상품이름</label>
		  <input type="text" name="bookName" class="form-control" id="bookName" placeholder="제품명 입력">
		</div>
		
		<div class="mb-3">
		  <label for="bookRate" class="form-label">이자율</label>
		  <input type="text" name="bookRate" class="form-control" id="bookRate" placeholder="이자율 입력">
		</div>
	
		<div class="mb-3">
		  <label for="bookDetail" class="form-label">상세정보</label>
		  <textarea type="text" name="bookDetail" class="form-control" id="bookDetail" placeholder="상세설명 입력" rows="5"></textarea>
		</div>
		
		<div class="mb-3">
		  <label for="files" class="form-label">이미지</label>
		  <input type="file" class="form-control" id="files" name="pic">
		</div>
		
<!-- 		<div class="mb-3">
			<label class="form-label">판매여부</label>
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="bookSale" id="bookSale1" value="1" checked>
			  <label class="form-check-label" for="bookSale1">
			    판매중
			  </label>
			</div>
			
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="bookSale" id="bookSale2" value="0">
			  <label class="form-check-label" for="bookSale2">
			    판매중단
			  </label>
			</div>	
		</div> -->
		
		<!-- 이걸 쓸때는 데이터값이 null, 1로 넘어가기 떄문에 DTO의 getter/setter를 손봐줘야한다 -->
		<div class="form-check form-switch">
		   <label class="form-check-label" for="bookSale">판매여부</label>
		   <input name="bookSale" class="form-check-input" value="1" type="checkbox" role="switch" id="bookSale">
		</div>
		
	</div>
		

		<div class="row justify-content-center mx-auto">
			<button class="btn btn-outline-success col-2" type="submit" value="등록">상품등록</button>	
		</div>



	</form>
</div>

<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>