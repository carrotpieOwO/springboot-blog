<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>

<div class="container">

	<!-- submit 안쓰고 자바스크립트 처리할거라서 requried 이런거 필요 없다. -->
	<form>
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username" maxlength="15">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" maxlength="15">
		</div>
		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" class="form-control" placeholder="Enter email" id="email" maxlength="30">
		</div>
	</form>
	<button id="join--submit" class="btn btn-dark">join</button>

	<!-- 내거도, 부트스트랩도 중간에 - 로 쓴다. 부트스트랩껀지 내껀지 구분하기위해 --두개 쓰기  -->
	<!-- css에서 쓸 아이디 만들때는 __ 이걸로 (이 수업의 약속) -->
</div>

<script>
	$('#join--submit').on('click', function() {
		var data = {
			username : $('#username').val(),
			password : $('#password').val(),
			email : $('#email').val()
		};
		$.ajax({
			type : 'POST',
			url : '/user/join',
			data : JSON.stringify(data), //만약 get 타입으로 데이터 보내면 'username=ssar' 이런식으로 쿼리스트링처럼 해서 날려야함,
			contentType : 'application/json; charset=utf-8',
			dataType : 'json'
		}).done(function(r) {
			if (r.statusCode == 200) {
				alert('회원가입 성공');
				location.href = '/user/login';
			} else {
				if (r.msg == '아이디중복') {
					alert('아이디가 중복되었습니다.');
				} else {
					alert('회원가입 실패');

				}
			}
		}).fail(function(r) {
			alert('서버오류');
		});
	});
</script>

<%@include file="../include/footer.jsp"%>
