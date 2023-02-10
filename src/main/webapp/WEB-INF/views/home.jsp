<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="./resources/css/reset.css">
	<link rel="stylesheet" href="./resources/css/main.css">
</head>
<body>
	<header>
		<div class="header_wrap">
			<div class="header_logo">
				<!-- 여기는 절대경로를 쓰는걸로 -->
				<img src="/resources/images/logo.png" alt="">
			</div>
			<nav class="header_nav">
				<!-- 메뉴 만들때 많이 쓰는 태그가 list(ol, ul) -->
				<ul>
					<!-- 아직 url이 정해지지 않은경우 # -->
					<li><a href="#">공지사항</a></li>
					<li><a href="#">제품</a></li>
					<li><a href="#">저축상품</a></li>
					<li><a href="#">기타메뉴</a></li>
				</ul>
			</nav>
			<div class="header_sub">
				<ul>
					<li><a href="#">LOGIN</a></li>
					<li><a href="#">JOIN</a></li>
					<li><a href="#">KO</a></li>
					<li><a href="#">EN</a></li>
					<li><a href="#">JP</a></li>
					<li><a href="#">CN</a></li>
				</ul>
			</div>
		</div>
	</header>

</body>
</html>
