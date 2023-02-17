<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>