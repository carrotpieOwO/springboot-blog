<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>
<div class="container">

<!-- submit 안쓰고 자바스크립트 처리할거라서 requried 이런거 필요 없다. -->
	<form>
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
	</form>
	<button id="login--submit" class="btn btn-dark">login</button>
	
	<!-- 내거도, 부트스트랩도 중간에 - 로 쓴다. 부트스트랩껀지 내껀지 구분하기위해 --두개 쓰기  -->
	<!-- css에서 쓸 아이디 만들때는 __ 이걸로 (이 수업의 약속) -->
</div>

<%@include file="../include/footer.jsp"%>
