<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="row col-md-1 offset-md-3">
		<table class="table table-hover">
			<thead>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dto" varStatus="i">
					<tr>
						<td>
							<c:if test="${boardName ne 'BankbookComment'}">
								<a href="./detail?num=${dto.num}">${dto.title}</a></td>
							</c:if>				
							<!-- 처음 글이면 컨텐츠도 보여줘라. 근데 dto에 컨텐츠를 안꺼내는듯 -->
							<c:if test="${i.first}">
								${dto.contents}
							</c:if>
						<td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>






