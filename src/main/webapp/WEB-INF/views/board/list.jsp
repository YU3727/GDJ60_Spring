<%@page import="com.pooh.s1.board.BbsDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/common_css.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
<div class="container-fluid">
	<div class="row my-5">
		<!-- 어느 영역(notice, qna, bankbook 등...)에서 온 데이터인지는 Controller까지만 알고있다. jsp는 모름 -->
		<!-- 어느 영역으로 부터 온 데이터인지 알고싶으면, 위처럼 ModelAttribute로 table 이름을 넣어주면 된다 -->
		<h1>${boardName} List</h1>
	</div>


	<div class="row">
		<%-- <% List<BbsDTO> ar = (List<BbsDTO>)request.getAttribute("list"); %> --%>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>NUM</th>
					<th>TITLE</th>
					<th>WRITER</th>
					<th>DATE</th>
					<th>HIT</th>
				</tr>
			</thead>
			<tbody>
				<!-- 여기 담긴건 BbsDTO. title과 hit이 없지만 EL은 형변환을 안해도 원래 만들어준 데이터를 참고해서 꺼내준다 -->
				<!-- Mapper에서는 NoticeDTO로 result를 주기 때문 -->
				<!-- QNA에서 또한 이걸로 그대로 써도 된다.. 경로를 상대경로로 사용하는 이유 또한 마찬가지다. -->
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.num}</td>
						<td><a href="./detail">${dto.title}</a></td>
						<td>${dto.writer}</td>
						<td>${dto.regDate}</td>
						<td>${dto.hit}</td>
					</tr>
				</c:forEach>
			</tbody>		
		</table>
	</div>
	
	<!-- Paging -->
	<!-- 만들어 뒀던거 그대로 쓸 수 있음. -> 공용으로 만들어서 import -->
	<div class="row">
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		  
		  	<li class="page-item">
		      <a class="page-link" href="#" aria-label="Previous" data-board-page="1">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		  
		    <li class="page-item ${pager.before ? 'disabled' :''}" data-board-page="${pager.startNum-1}">
		      <a class="page-link" href="#" aria-label="Previous">
		        <span aria-hidden="true">&lsaquo;</span>
		      </a>
		    </li>
		    
		    <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" step="1" var="i">
		    	<li class="page-item"><a class="page-link" href="#" data-board-page="${i}">${i}</a></li>			    
		    </c:forEach>

		    <li class="page-item ${pager.after eq false ? 'disabled' :''}">
		      <a class="page-link" href="#" aria-label="Next" data-board-page="${pager.lastNum+1}">
		        <span aria-hidden="true">&rsaquo;</span>
		      </a>
		    </li>
		    
		    <li class="page-item">
		      <a class="page-link" href="#" aria-label="Next" data-board-page="${pager.totalPage}">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>

	<!-- search -->
	<!-- 만들어뒀던거 쓸수는 있는데 select의 option 태그를 좀 바꿔야함 -->
	<div class="row">
		<form class="row g-3" action="./list" method="get" id="searchForm">
			<input type="hidden" name="page" value="1" id="page">
			<div class="col-auto">
				<label for="kind" class="visually-hidden">Kind</label>
				<select class="form-select" name="kind" id="kind" aria-label="Default select example">
					<option value="title" ${pager.kind eq 'title'? 'selected':''}>Title</option>
					<option value="contents" ${pager.kind eq 'contents'? 'selected':''}>Contents</option>
					<option value="writer" ${pager.kind eq 'writer'? 'selected':''}>Writer</option>
				</select>
			</div>
			<div class="col-auto">
				<label for="search" class="visually-hidden">Search</label>
				<input type="text" class="form-control" name="search" value="${pager.search}" id="search" placeholder="검색어를 입력하세요">
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary mb-3">검색</button>
			</div>
		</form>
	</div>


	
</div>
<c:import url="../template/common_js.jsp"></c:import>
<script src="../resources/js/pagination.js"></script>
</body>
</html>