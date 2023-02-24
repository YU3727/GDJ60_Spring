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
			
			<!-- notice일때는 필요없고 qna일때는 필요한 답글기능 -->
			<c:if test="${boardName ne 'notice'}">
			<a href="./reply?num=${dto.num}" class="btn btn-primary">답글</a>
			</c:if>
			
		</div>
	</div>





<c:import url="../template/common_js.jsp"></c:import>
</body>
</html>