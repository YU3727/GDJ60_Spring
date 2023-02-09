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
		<fieldset>
			<legend>상품이름</legend>
			<input type="text" name="bookName" value="${dto.bookName}"> <br>
		</fieldset>
		
		<fieldset>
			<legend>이자율</legend>
			<input type="text" name="bookRate" value="${dto.bookRate}"> <br>
		</fieldset>
		
<%-- 	<input type="text" name="bookSale" value="${dto.bookSale}"> <br> --%>

		<fieldset>
			<legend>판매여부</legend>
			<label id="bs1">판매</label>
			<input id="bs1" type="radio" value="1" ${dto.bookSale eq '1' ? 'checked':''} name="bookSale">
			
			<label id="bs2">판매중단</label>
			<input id="bs2" type="radio" value="0" ${dto.bookSale eq '1' ? 'checked':''} name="bookSale">
		</fieldset>


		<fieldset>
			<legend>판매여부</legend>
			<select name="bookSale">
				<!-- selected 대신에 삼항연산자로 조건식, 원하는결과를 넣어줌 -->
				<option value="1" ${dto.bookSale eq '1' ? 'selected':''}>판매</option>
				<option value="0" ${dto.bookSale eq '0' ? 'selected':''}>판매중단</option>
			</select>
		</fieldset>
		
		<fieldset>
			<legend>상세설명</legend>
			<textarea rows="10" cols="20" name="bookDetail">${dto.bookDetail}</textarea> <br>
		</fieldset>
		
		<fieldset>
			<button type="submit" value="수정">상품수정</button>
		</fieldset>
		
	</form>
</body>
</html>