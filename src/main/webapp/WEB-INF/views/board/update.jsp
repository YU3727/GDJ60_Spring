<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 230209 2교시 입력태그 -->
<!-- 230213 6~7교시 bootstrap -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${boardName} Update Page</title>
	<c:import url="../template/common_css.jsp"></c:import>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container-fluid my-5">

	<div class="row justify-content-center">
		<h1 class="col-md-7 mx-auto text-center border-bottom border-dark pb-4">${boardName} Update Page</h1>
	</div>
	
	<div class="row justify-content-center">
	<form class="col-md-7" action="./update" method="post" enctype="multipart/form-data"> <!-- 미리 확인해야한다 + -context.xml의 mutipartresolver도 -->
		<input type="hidden" name="num" value="${dto.num}">
		<div class="mb-3">
		  <label for="writer" class="form-label">작성자</label>
		  <input type="text" name="writer" readonly value="${dto.writer}" class="form-control" id="writer">
		</div>
		
		<div class="mb-3">
		  <label for="title" class="form-label">글제목</label>
		  <input type="text" name="title" class="form-control" value="${dto.title}" id="title" placeholder="insert title here">
		</div>
	
		<div class="mb-3">
		  <label for="contents" class="form-label">상세내용</label>
		  <textarea name="contents" class="form-control" id="contents" placeholder="insert contents here" rows="5">${dto.contents}</textarea>
		</div>
		
		<div id="fileList" class="my-5">
			<button type="button" id="fileAdd">File Add</button>
			<c:forEach items="${dto.boardFileDTOs}" var="fileDTO">		
				<div class="input-group my-3">
					<div class="input-group-text">
						<input class="form-check-input mt-0 deleteCheck" type="checkbox" value="${fileDTO.fileNum}" name="fileNum" aria-label="Checkbox for following text input">
					</div>
				<input type="text" class="form-control" disabled value="${fileDTO.oriName}" aria-label="Text input with checkbox">			
				</div>
			</c:forEach>
		</div>
		

		<!-- <div id="fileList" class="my-5">
            <button type="button" class="btn btn-primary" id="fileAdd">ADD</button>
			<c:forEach items="${dto.boardFileDTOs}" var="fileDTO">
				<div class="input-group mb-3">
					<div class="input-group-text">
						<input class="form-check-input mt-0" type="checkbox" value="${fileDTO.fileNum}" name="fileNums" aria-label="Checkbox for following text input">
					</div>
				<input type="text" class="form-control" disabled value="${fileDTO.oriName}" aria-label="Text input with checkbox">
				</div>
			</c:forEach>
       </div> -->



		<div class="row justify-content-center mx-auto">
			<button class="btn btn-outline-success col-2" type="submit">글쓰기</button>	
		</div>

	</form>
</div>

<script src="../resources/js/fileManager.js"></script>
<script>
	setMax(5);
	setParam('addFiles');
	setCount('${dto.boardFileDTOs.size()}');
	$('#contents').summernote(); 
</script>
<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>