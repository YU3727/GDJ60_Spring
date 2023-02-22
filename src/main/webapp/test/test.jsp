<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Remove Test</h1>
	<div id="result">
		<c:forEach begin="0" end="4" var="i" varStatus="j">
			<div id="p${j.index}" class="items">
				<h3>${i}</h3>
				<button class="dels" data-div-id="p${j.index}">DELETE</button>
			</div>
		</c:forEach>

	</div>
	<button id="deleteAll">DELETEALL</button>
	
	<script>
		// 이벤트 거는거부터. 각각의 버튼이 각각의 div를 지울 수 있게끔
		// 동일한 일을 하는 것들은 클래스로....
		const dels = document.getElementsByClassName("dels");

		const deleteAll = document.getElementById("deleteAll");
		const result = document.getElementById("result");

		deleteAll.addEventListener("click", function(){
			let items = document.getElementsByClassName("items");
			//모두 지우고싶어서 이렇게 하면 java와는 다르게 배열의 길이가 줄어듦으로 깔끔하게 안지워진다
			// for(let i=0; i<items.length; i++){
			// 	items[i].remove(); 
			// }
			//이럴때는 다른 for문을 써야한다 또는... 조건을 다르게
			//JavaScript의 배열은 배열이라기보다 list에 가깝다는게 이런의미
			for(let i=0; i != items.length;){
				items[0].remove();
			}

		});


		for(let i=0; i<dels.length; i++){
			dels[i].addEventListener('click', function(){
				// 지우는법 1
				// document.getElementById("p"+i).remove();

				// 지우는법 2 - 1을 못쓸때
				console.log(this.getAttribute("data-div-id"));
				let id = this.getAttribute("data-div-id");
				document.getElementById(id).remove();
			});
		}

	</script>
</body>
</html>