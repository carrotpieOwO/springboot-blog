<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>
<div class="container">

	<form>
		<div class="form-group">
			<label for="title">제목:</label> 
			<input type="text" class="form-control" id="title" value="${post.title}">
		</div>
		<div class="form-group">
			<label for="content">내용:</label> 
			<textarea class="form-control" rows="5" id="content">${post.content}</textarea>
		</div>
	</form>
	<button id="update--submit" class="btn btn-dark">수정</button>
	
	
</div>
<script>
$('#update--submit').on('click', function() {
	var data = {
		id : ${post.id},
		title : $('#title').val(),
		content : $('#content').val()
	};
	$.ajax({
		type : 'PUT',
		url : '/post/update',
		data : JSON.stringify(data), //만약 get 타입으로 데이터 보내면 'username=ssar' 이런식으로 쿼리스트링처럼 해서 날려야함,
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'
	}).done(function(r) {
		if (r.statusCode == 200) {
			alert('수정 성공');
			location.href = '/post/detail/${post.id}';
		} else {
				alert('수정 실패');

		}
	}).fail(function(r) {
		alert('회원가입 실패');
		
	});
});
</script>
<%@include file="../include/footer.jsp"%>
