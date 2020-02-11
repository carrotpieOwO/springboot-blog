<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>
<div class="container">

	<!-- 얘네는 사진때문에 컨텐트타입 멀티파트로 바꾸고 이런거 때문에 귀찮아서 그냥 폼으로 전송 -->
	<!-- form은.. put으로 안됨.. -->
	<form:form action="/user/profile" method="PUT" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${principal.id}">
		
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" class="form-control" id="username" value="${principal.username}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" id="password" name="password" value="" required="required"/>
			<p id="pwmsg"></p>
		</div>
		<div class="form-group">
			<label for="email">Email:</label> 
			<input type="email" class="form-control" id="email" value="${principal.email}" readonly="readonly">
		</div>

		<!-- 프로필 사진 -->
		<div class="form-group">
			<label for="profile">profile:</label> 
			<input type="file" class="form-control-file border" name="profile" id="profile" />
			<p>${principal.profile}</p>
		</div>
		<button id="update--submit" class="btn btn-dark">수정</button>
	</form:form>

</div>


<script>
/* 	$('#update--submit').on('click', function() {
		
		
		var data = {
			id : ${sessionScope.principal.id},
			password : $('#password').val(),
			profile :$('#profile').val()
		};
		$.ajax({
			type : 'PUT',
			url : '/user/profile',
			data : JSON.stringify(data), //만약 get 타입으로 데이터 보내면 'username=ssar' 이런식으로 쿼리스트링처럼 해서 날려야함,
			contentType : 'application/json; charset=utf-8',
			dataType : 'json'
		}).done(function(r) {
			if (r.statusCode == 200) {
				alert('수정 성공');
				location.href = '/';
			} else {
					alert('수정 실패');

				}
			
		}).fail(function(r) {
			alert('회원가입 실패');
			$('#pwmsg').html(r.responseJSON.password);
			 
		});
	}); */
</script>

<%@include file="../include/footer.jsp"%>
