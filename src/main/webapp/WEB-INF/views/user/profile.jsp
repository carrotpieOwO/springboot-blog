<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>
<div class="container">

	<!-- 얘네는 사진때문에 컨텐트타입 멀티파트로 바꾸고 이런거 때문에 귀찮아서 그냥 폼으로 전송 -->
	<!-- form은.. put으로 안됨.. -->
	
	<form action="/user/profile" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" class="form-control" name="username" value="${sessionScope.principal.username}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" name="password">
		</div>
		<div class="form-group">
			<label for="email">Email:</label> 
			<input type="email" class="form-control" name="email" value="${sessionScope.principal.email}" readonly="readonly">
		</div>

		<!-- 프로필 사진 -->
		<div class="form-group">
			<label for="profile">Email:</label> 
			<input type="file" class="form-control-file border" name="profile" value="${sessionScope.principal.profile}" />
		</div>
		<button class="btn btn-dark">수정</button>
	</form>

</div>

<%@include file="../include/footer.jsp"%>
