<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 230209 2교시 입력태그 -->
<!-- 230213 6~7교시 bootstrap -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${boardName} Add Page</title>
	<c:import url="../template/common_css.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container-fluid my-5">

	<div class="row justify-content-center">
		<h1 class="col-md-7 mx-auto text-center border-bottom border-dark pb-4">${boardName} Add Page</h1>
	</div>
	
	<div class="row justify-content-center">
	<form class="col-md-7" action="./add" method="post" enctype="multipart/form-data"> <!-- 안바꾸고 쓰면 안되나? -->
		<div class="mb-3">
		  <label for="writer" class="form-label">작성자</label>
		  <input type="text" name="writer" class="form-control" id="writer">
		</div>
		
		<div class="mb-3">
		  <label for="title" class="form-label">글제목</label>
		  <input type="text" name="title" class="form-control" id="title" placeholder="insert title here">
		</div>
	
		<div class="mb-3">
		  <label for="contents" class="form-label">상세내용</label>
		  <textarea name="contents" class="form-control" id="contents" placeholder="insert contents here" rows="5"></textarea>
		</div>
		
		<div id="fileList">
<!-- 			<div class="mb-3">
				<label for="files" class="form-label">이미지</label>
				<input type="file" class="form-control" id="files" name="files">
				<button type="button" id="del01~">X</button>
			</div> -->
			<button type="button" id="fileAdd">File Add</button>
		</div>
		

		<div class="row justify-content-center mx-auto">
			<button class="btn btn-outline-success col-2" type="submit">글쓰기</button>	
		</div>

	</form>
</div>

<script src="../resources/js/fileManager.js"></script>
<script>
	setMax(5);
	// setParam('f');
</script>
<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>