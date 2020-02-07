<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>

<div class="container">

	<!-- submit 안쓰고 자바스크립트 처리할거라서 requried 이런거 필요 없다. -->
	<form>
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username" maxlength="15">
		</div>
		<p id="idmsg" style="color: red; font-size: 12px"></p>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" maxlength="15">
		</div>
		<p id="pwmsg" style="color: red; font-size: 12px"></p>

		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" class="form-control" placeholder="Enter email" id="email" maxlength="30">
		</div>
		<p id="emailmsg" style="color: red; font-size: 12px"></p>

	</form>
	<button id="join--submit" class="btn btn-dark">join</button>

	<!-- 내거도, 부트스트랩도 중간에 - 로 쓴다. 부트스트랩껀지 내껀지 구분하기위해 --두개 쓰기  -->
	<!-- css에서 쓸 아이디 만들때는 __ 이걸로 (이 수업의 약속) -->
</div>


<script src="/js/join.js"></script>

<%@include file="../include/footer.jsp"%>
