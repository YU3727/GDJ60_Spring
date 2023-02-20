<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
  <!-- Navbar content -->
  <!-- bootstrap web page - Docs - Components - Navbar 참고 -->
  <div class="container-fluid">
  	<a href="/" class="navbar-brand"><img src="/resources/images/logo.png" alt=""></a>
  	<!-- 햄버거 버튼 만들기(그대로 코드붙여넣기) -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <!-- 메뉴들을 모으는 div 클래스 -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<!-- 아직 url이 정해지지 않은경우 # -->
			<li class="nav-item"><a class="nav-link active" href="#">공지사항</a></li>
			<li class="nav-item"><a class="nav-link active" href="/product/list">제품</a></li>
			<li class="nav-item"><a class="nav-link active" href="/bankBook/list">저축상품</a></li>
			<li class="nav-item"><a class="nav-link active" href="#">기타메뉴</a></li>
		</ul>
		
		<ul class="navbar-nav">
			<!-- 원하는 값이 없을떄는 SASS에 들어가서 compile해서 쓰거나, 직접 입력하는 식으로 사용 -->
			<c:if test="${empty member}">
				<li class="nav-item"><a class="nav-link" href="/member/memberLogin">LOGIN</a></li>
				<li class="nav-item"><a class="nav-link" href="/member/memberAgree">JOIN</a></li>
			</c:if>
			<c:if test="${not empty member}">
				<li class="nav-item"><a class="nav-link" href="/member/memberPage">MYPAGE</a></li>
				<li class="nav-item"><a class="nav-link" href="/member/memberLogout">LOGOUT</a></li>
			</c:if>
			<li class="nav-item"><a class="nav-link" href="#">KO</a></li>
			<li class="nav-item"><a class="nav-link" href="#">EN</a></li>
			<li class="nav-item"><a class="nav-link" href="#">JP</a></li>
			<li class="nav-item"><a class="nav-link" href="#">CN</a></li>
		</ul>
 	 </div>
</nav>


<!-- 모든 페이지에 공통적으로 들어가기 떄문에 절대경로로 작성해둬야한다 -->
<!-- bootstrap에 우리가 만든 내용을 옮겼으므로, 주석처리를 한다 -->
<!-- 	<header>
		<div class="header_wrap">
			<div class="header_logo">
				여기는 절대경로를 쓰는걸로
				<a href="/"><img src="/resources/images/logo.png" alt=""></a>
			</div>
			<nav class="header_nav">
				메뉴 만들때 많이 쓰는 태그가 list(ol, ul)
				<ul>
					아직 url이 정해지지 않은경우 #
					<li><a href="#">공지사항</a></li>
					<li><a href="/product/list">제품</a></li>
					<li><a href="/bankBook/list">저축상품</a></li>
					<li><a href="#">기타메뉴</a></li>
				</ul>
			</nav>
			<div class="header_sub">
				<ul>
					<li><a href="#">LOGIN</a></li>
					<li><a href="/member/memberAdd">JOIN</a></li>
					<li><a href="#">KO</a></li>
					<li><a href="#">EN</a></li>
					<li><a href="#">JP</a></li>
					<li><a href="#">CN</a></li>
				</ul>
			</div>
		</div>
	</header> -->