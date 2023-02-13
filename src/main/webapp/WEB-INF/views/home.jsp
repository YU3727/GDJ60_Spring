<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
	<c:import url="./template/common_css.jsp"></c:import>
</head>
<body>
	<!-- header를 직접 작성하지 말고, 공통적인 부분이기 때문에 따로 header.jsp로 만들고 이걸 가져오는 방법을 쓰자 -->
	<!-- *주의 : 아래 경로는 wep url이 아니라 서버 내부의 주소를 찾아가야하므로 jsp의 위치를 봐야한다. 상대경로로 작성하자 -->
	<c:import url="./template/header.jsp"></c:import>

</body>
</html>
