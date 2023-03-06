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
	<h1>BankBook Detail Page</h1>
	<c:choose>
		<c:when test="${not empty DTO}">
			<h3>Num : ${requestScope.DTO.bookNumber}</h3>
			<h3>Title : ${requestScope.DTO.bookName}</h3>
			<h3>Rate : ${DTO.bookRate}</h3>
			<h3>Sale :
				<c:choose>
					<c:when test="${DTO.bookSale eq 1}">판매중</c:when>
					<c:otherwise>판매종료</c:otherwise>
				</c:choose>
			</h3>
			<h3>Detail : ${DTO.bookDetail}</h3>
			<div>
				<c:if test="${not empty DTO.bankBookImgDTO}">
					<img alt="" src="../resources/upload/bankBook/${DTO.bankBookImgDTO.fileName}">
				</c:if>
			</div>
			<a href="./delete?bookNumber=${DTO.bookNumber}">상품삭제</a>
		</c:when>
		<c:when test="${empty DTO}">
			<h3>존재하지 않는 상품입니다.</h3>
		</c:when>
	</c:choose>
	<a href="./update?bookNumber=${DTO.bookNumber}">상품수정</a>
	<a href="./list">목록으로</a>

	<div class="my-5" id="replyList">

	</div>

	<!-- 댓글 추가 버튼 -->
	<div class="my-5">
		<div class="mb-3">
			<textarea id="replyContents" class="form-control" rows="3"></textarea>
		</div>

		<div class="mb-3">
			<button id="replyAdd" type="button" class="btn btn-primary" data-idx-bookNumber="${DTO.bookNumber}">댓글작성</button>
		</div>
	</div>

<c:import url="../template/common_js.jsp"></c:import>
<script src="/resources/js/bankBookReply.js"></script>
</body>
</html>