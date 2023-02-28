<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${boardName} Detail</title>
<c:import url="../template/common_css.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
	<div class="container-fluid">
		<div class="row">
			<h3>${boardName} Detail Page</h3>
			<h3>${dto.title}</h3>
			<h3>${dto.writer}</h3>
			<p>${dto.contents}</p>
			
			<!-- list를 가져옴, 이 리스트를 변수 var(fileDTO)에 담음 -->
			<c:forEach items="${dto.boardFileDTOs}" var="fileDTO">
				<!-- notice와 함께 쓰는 jsp. 그러므로 boardName에서 꺼내오고, 그 아래 파일명은 fileDTO에서 꺼내오면 된다 -->
				<%-- <a href="../resources/upload/${boardName}/${fileDTO.fileName}">${fileDTO.oriName}</a> --%>
				<a href="./fileDown?fileNum=${fileDTO.fileNum}">${fileDTO.oriName}</a>
			</c:forEach>
			
			<!-- notice일때는 필요없고 qna일때는 필요한 답글기능 -->
			<c:if test="${boardName ne 'notice'}">
			<a href="./reply?num=${dto.num}" class="btn btn-primary">답글</a>
			</c:if>
			
			
			<div>
				<!-- update도 delete도 모두 DTO의 num이 필요하다 -->
				<form action="./update" id="frm" method="get">
					<!-- name은 파라미터 이름, value는 파라미터의 값 -->
					<input type="hidden" name="num" value="${dto.num}">
					<button id="update" type="submit" class="btn btn-primary">UPDATE</button>
					<button id="delete" type="button" class="btn btn-info">DELETE</button>
				</form>
			</div>
			
		</div>
	</div>



<script src="../resources/js/boardForm.js"></script>
<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>