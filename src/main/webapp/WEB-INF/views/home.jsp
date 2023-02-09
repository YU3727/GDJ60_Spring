<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="./resources/css/main.css">
</head>
<body>
<h1>
	Hello world!  
</h1>
	<a href="./product/list?num=1&num=2&num=3" class="a">ProductList relativePath</a>
	<a href="/product/list" class="a">ProductList absolutePath</a> <br>
	<hr>
	<a href="/bankBook/list" class="a">BankBookList relativePath</a>
	<a href="./bankBook/list" class="a">BankBookList absolutePath</a>

<!-- src는 넣을 이미지 파일의 경로를 넣으라는 의미, alt는 설명기능 및 표시, 웹 접근성 등을 사용할때 필요하다 -->
<!-- 기본경로 /(root)는 src~webapp까지 -->
<img alt="달사진" src="/resources/images/moon.jpg">
<img alt="태양사진" src="./resources/images/sun.jpg">

<iframe width="560" height="315" src="https://www.youtube.com/embed/jUNz-uTF--E" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
