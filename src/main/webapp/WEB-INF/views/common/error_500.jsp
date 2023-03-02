<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<!-- Button trigger modal -->
		<button type="button" id="launch" class="btn btn-primary d-none" data-bs-toggle="modal" data-bs-target="#exampleModal">
		  Launch modal
		</button>
		
		<!-- 위의 data-bs-target의 속성값과 아래의 Modal의 id 속성값이 같아야함.(#~)는 id의 의미 -->
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-body">
		        ${message}
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		<a href="/">Home</a>
	</div>


</div>

<c:import url="../template/common_js.jsp"></c:import>
<script type="text/javascript">
	const launch = document.getElementById("launch");
	launch.click();
</script>
</body>
</html>