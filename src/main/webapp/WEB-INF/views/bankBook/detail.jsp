<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/common_css.jsp"></c:import>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
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


	<!-- Update Form Modal -->
	<!-- Modal -->
	<div class="modal fade" id="contentsModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="form-floating">
					<textarea class="form-control" placeholder="Leave a comment here" id="contents"></textarea>
					<label for="contents">Comments</label>
				</div>
			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="closeModal">Cancel</button>
			<button type="button" class="btn btn-primary" data-comment-num="" id="contentsConfirm">확인</button>
			</div>
		</div>
		</div>
	</div>
	
	
	
	<!-- 테스트 후 삭제할 영역 -->
	<div class="my-5">
		<button type="button" id="b1">button1</button>
		<input type="text" id="t1">
		<div class="my-5">
			<input type="checkbox" class="ch" name="ch" id="" value="v1">
			<input type="checkbox" class="ch" name="ch" id="" value="v2">
			<input type="checkbox" class="ch" name="ch" id="" value="v3">
			<input type="checkbox" class="ch" name="ch" id="" value="v4">

		</div>

	</div>
	
	<!-- ---------------- -->


<c:import url="../template/common_js.jsp"></c:import>
<script src="/resources/js/bankBookReply.js"></script>
</body>
</html>