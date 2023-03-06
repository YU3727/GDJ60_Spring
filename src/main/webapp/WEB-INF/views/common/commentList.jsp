<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 작성자, 내용, 날짜만 출력 -->

<table class="table table-striped">
	<c:forEach items="${list}" var="dto">
	  <tr>
		<!-- <td id="contents${dto.num}">${dto.contents}</td> -->
		<td id="contents${dto.num}">
			<textarea readonly>${dto.contents}</textarea>
		</td>
		<td>${dto.writer}</td>
		<td>${dto.regDate}</td>
		<!-- 댓글 수정/삭제 버튼 추가, 작성자(dto.writer)와 로그인한 사람(member.id)이 일치하면 세션에서 member라는 속성명으로 로그인정보를 넘겨줬기 때문 -->
		<td>
			<c:if test="${dto.writer eq member.id}">
				<button id="uButton" class="btn btn-warning update" data-comment-num="${dto.num}">UPDATE</button>
			</c:if>
		</td>
		<td>
			<c:if test="${dto.writer eq member.id}">
				<button class="btn btn-danger del" data-comment-num="${dto.num}">DELETE</button>
			</c:if>
		</td>
	  </tr>
	</c:forEach>
</table>

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