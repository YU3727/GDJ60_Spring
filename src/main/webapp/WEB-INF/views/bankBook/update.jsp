<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상품수정 Page</h1>
	
	<%--입력을 해야하니까 form을 쓰자 --%>
	<form action="./update" method="post">
		<%-- 이미 입력된 데이터를 입력창에 보여주는 속성 : value --%>
		<%-- 입력될 값에 대한 힌트를 보여주는 속성 : placeholder --%>
		<%-- textarea는 여닫는 태그가 모두 존재하므로, content영역에 EL을 써준다 --%>
		<input type="hidden" name="bookNumber" value="${dto.bookNumber}">
		BOOKNAME : <input type="text" name="bookName" value="${dto.bookName}"> <br>
		BOOKRATE : <input type="text" name="bookRate" value="${dto.bookRate}"> <br>
		BOOKSALE : <input type="text" name="bookSale" value="${dto.bookSale}"> <br>
		BOOKDETAIL : <textarea rows="10" cols="20" name="bookDetail">${dto.bookDetail}</textarea> <br>
		<button type="submit" value="수정">상품수정</button>
		
	</form>
</body>
</html>