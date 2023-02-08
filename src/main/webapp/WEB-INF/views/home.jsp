<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<a href="./product/list?num=1&num=2&num=3">ProductList relativePath</a>
<a href="/product/list">ProductList absolutePath</a> <br>
<hr>
<a href="/bankBook/list">BankBookList relativePath</a>
<a href="./bankBook/list">BankBookList absolutePath</a>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
