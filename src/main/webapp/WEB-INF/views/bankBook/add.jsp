<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 230209 2교시 입력태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>BankBook Add Page</h1>
	<form action="./add" method="post">
		<fieldset>
			<legend>상품이름</legend>
			<input type="text" name="bookName" placeholder = "이름을 입력하세요">
		</fieldset>
		
		<fieldset>
			<legend>이자율</legend>
			<input type="text" name="bookRate">
		</fieldset>
		
		<fieldset>
		<!-- radio 버튼은 직접 입력받는게 아니여서 value를 따로 설정해주지 않으면 on이 넘어간다. value를 설정해주면 value값이 넘어감 -->
			<legend>판매여부</legend>
			<label for="bs1">판매</label> <!-- for속성 : 누구를 향하느냐? 얘랑 연결될 id를 적어주면 됨 - 이름을 클릭해도 체크가되게끔 -->
			<input id="bs1" type="radio" checked name="bookSale" value="1">
			
			<label for="bs2">판매중단</label>
			<input id="bs2" type="radio" name="bookSale" value="0">
		</fieldset>
		
		<fieldset>
		<!-- select 또한 선택하는 것이기 때문에 value를 미리 세팅해둬야한다 -->
		<!-- name은 select tag에, value는 option tag마다 넣어준다 -->
			<legend>판매여부</legend>
			<select name="bookSale">
				<option value="1">판매</option>
				<option value="0" selected>판매중단</option>
			</select>
		</fieldset>
		
		<fieldset>
			<legend>상세설명</legend>
			<textarea rows="10" cols="20" name="bookDetail"></textarea>
		</fieldset>
		
		<fieldset>
		<button type="submit" value="등록">상품등록</button>
		</fieldset>

	</form>
	
</body>
</html>