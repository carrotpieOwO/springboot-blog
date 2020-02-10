$('#post--update--submit').on('click', function() {
		$('#updateForm').submit();
	});

	$('#comment--save--submit').on('click', function() {
		console.log('클릭');
		var data = {
			postId: $('#postId').val(),
			userId: $('#userId').val(),
			content: $('#content').val()
		};
		
		$.ajax({
			type : 'POST',
			url : '/comment/write',
			data : JSON.stringify(data),
			contentType : 'application/json; charset=utf-8', //보내는 데이터
			dataType : 'json' //응답 데이터, 데이터 주고받을땐 무조건 스트링으로 인식해서 이렇게 해줘야 제이슨으로 인식함
		}).done(function(r) { //그래서 여기서 받을 때 잭슨이 제이슨을 자바스크립트로 바꿔줘서 자바스크립트 오브젝트화됨
			if (r.status.statusCode == 200) {
				alert('댓글이 등록 되었습니다.');
				makeCommentItem(r);
			}else{
				console.log(r);
				alert('댓글 등록 실패');
				}
		}).fail(function(r) {
			alert('댓글 등록 실패 실패');
		});
	});

	

	$('#post--delete--submit').on('click', function() {
		var id= $('#post--delete--submit').val();
		$.ajax({
			type : 'DELETE',
			url : '/post/delete/'+id,
			dataType : 'json' //응답 데이터, 데이터 주고받을땐 무조건 스트링으로 인식해서 이렇게 해줘야 제이슨으로 인식함
		}).done(function(r) { //그래서 여기서 받을 때 잭슨이 제이슨을 자바스크립트로 바꿔줘서 자바스크립트 오브젝트화됨
			if (r.statusCode == 200) {
				alert('글이 삭제 되었습니다.');
				location.href = '/';
			}else{
				alert('글 삭제 실패');
			}
		}).fail(function(r) {
			alert('글 삭제 실패');
		});
	});
		
	function makeCommentItem(r){
		var comment_item = `<li id="comment--item--${r.id}" class="list-group-item d-flex justify-content-between align-items-center">`;
		comment_item += `<div class="font-italic">${r.content}</div>`;
		comment_item += `<div class="badge badge-warning badge-pill ml-auto">작성자:${r.username}</div>`;
		comment_item += `<button onclick="commentDelete(${r.id})" class="badge badge-danger badge-pill">삭제</button>`;
		comment_item += `</li>`;
		$('#comment--items').prepend(comment_item);
				
	}
	
	function commentDelete(commentId){
		$.ajax({
			type : 'DELETE',
			url : '/comment/delete/'+commentId,
			dataType : 'json' //응답 데이터, 데이터 주고받을땐 무조건 스트링으로 인식해서 이렇게 해줘야 제이슨으로 인식함
		}).done(function(r) { //그래서 여기서 받을 때 잭슨이 제이슨을 자바스크립트로 바꿔줘서 자바스크립트 오브젝트화됨
			console.log(r);
			if (r.statusCode == 200) {
				alert('댓글이 삭제 되었습니다.');
				$('#comment--item--'+commentId).remove();
			}else{
				alert('댓글 삭제 실패');
			}
		}).fail(function(r) {
			alert('댓글 삭제 실패');
		});
	}