<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>

<div class="container">

	<div class="card">
		<div class="card-header">
			<h4 class="card-title">${post.title}</h4>
		</div>
		<div class="card-body">
			<p class="card-text">${post.content}</p>
		</div>

		<div class="card-footer">
            <c:if test="${sessionScope.principal.id eq post.userId}">      
			<button id="post--update--submit" class="btn btn-warning ">수정</button>
			<button id="post--delete--submit" class="btn btn-danger ">삭제</button>
			</c:if>
			<a href="/" class="btn btn-dark ">목록</a>
		</div>
	</div>

	<form id="updateForm" action="/post/update/${post.id}" method="GET">
		<input type="hidden" name="userId" value="${post.userId}" />
	</form>


	<br />
	<div class="card">
		<div class="form-group">
			<div class="card-header">
				<h6 class="card-title">댓글</h6>
			</div>
			<div class="card-body">
				<textarea class="form-control" rows="2" id="content"></textarea>
			</div>
			<div class="card-footer">
				<button id="comment--save--submit" class="btn btn-dark ">등록</button>
			</div>
		</div>
	</div>
	<br />
	<div class="card">
		<div class="form-group">
			<div class="card-header">
				<h4 class="card-title">댓글리스트</h4>
			</div>
			<div class="comment--items card-body">
				<div class="comment--item">
					<span class="comment--content">내용</span> <span
						id="comment--delete--submit" value="1">X</span>
				</div>
				<div class="comment--item">
					<span class="comment-content">내용</span> <span
						id="comment-delete-submit" value="1">X</span>
				</div>
				<div class="comment--item">
					<span class="comment-content">내용</span> <span
						id="comment-delete-submit" value="1">X</span>
				</div>

			</div>
		</div>
	</div>
</div>

<script>
	$('#post--update--submit').on('click', function() {
		$('#updateForm').submit();
	});

	$('#post--delete--submit').on('click', function() {
		var data = {
			id: ${post.id},
			userId: ${post.userId}
		};
		$.ajax({
			type : 'DELETE',
			url : '/post/delete',
			data : JSON.stringify(data),
			contentType : 'application/json; charset=utf-8', //보내는 데이터
			dataType : 'json' //응답 데이터, 데이터 주고받을땐 무조건 스트링으로 인식해서 이렇게 해줘야 제이슨으로 인식함
		}).done(function(result) { //그래서 여기서 받을 때 잭슨이 제이슨을 자바스크립트로 바꿔줘서 자바스크립트 오브젝트화됨
			if (result.statusCode == 200) {
				alert('글이 삭제 되었습니다.');
				location.href = '/';
			}
		}).fail(function() {
			alert('글 삭제 실패');
		});
	});
	
</script>
<%@include file="../include/footer.jsp"%>
