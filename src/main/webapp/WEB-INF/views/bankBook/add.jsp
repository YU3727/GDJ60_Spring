<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>BankBook Add Page</h1>
	<form action="./add" method="post">
		<!-- BOOKNUMBER : <input type="text" name="bookNumber"> <br> -->
		BOOKNAME : <input type="text" name="bookName" placeholder="제품명을 입력하세요"> <br>
		BOOKRATE : <input type="text" name="bookRate"> <br>
		BOOKSALE : <input type="text" name="bookSale"> <br>
		BOOKDETAIL : <textarea rows="10" cols="20" name="bookDetail"></textarea> <br>
		<button type="submit" value="등록">상품등록</button>
	</form>
	
</body>
</html>