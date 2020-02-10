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
			console.log(r);
			if (r.status.statusCode == 200) {
				alert('댓글이 등록 되었습니다.');
				makeCommentItem(r);
			}else{
				console.log(r);
				alert('댓글 등록 실패');
				}
		}).fail(function(r) {
			console.log(r);
			alert('댓글 등록 실패 실패');
		});
	});

	

	$('#post--delete--submit').on('click', function() {
		var data = {
				postId: $('#postId').val(),
				userId: $('#userId').val()
		};
		$.ajax({
			type : 'DELETE',
			url : '/post/delete',
			data : JSON.stringify(data),
			contentType : 'application/json; charset=utf-8', //보내는 데이터
			dataType : 'json' //응답 데이터, 데이터 주고받을땐 무조건 스트링으로 인식해서 이렇게 해줘야 제이슨으로 인식함
		}).done(function(r) { //그래서 여기서 받을 때 잭슨이 제이슨을 자바스크립트로 바꿔줘서 자바스크립트 오브젝트화됨
			if (result.statusCode == 200) {
				alert('글이 삭제 되었습니다.');
				location.href = '/';
			}
		}).fail(function(r) {
			alert('글 삭제 실패');
		});
	});
		
	function makeCommentItem(r){
		var comment_item = ``;
		comment_item += `<div id="comment--item--${r.id}">`;
		comment_item += `<span class="comment--username">작성자: ${r.username}</span>`;
		comment_item += `<span class="comment--content">${r.content}</span>`;
		comment_item += `	<button onclick="commentDelete(${r.id})">삭제</button>`;
		comment_item +=`</div>`;
		$('#comment--items').prepend(comment_item);
				
	}
	
	function commentDelete(commentId){
		$.ajax({
			type : 'DELETE',
			url : '/comment/delete'+commentId,
			dataType : 'json' //응답 데이터, 데이터 주고받을땐 무조건 스트링으로 인식해서 이렇게 해줘야 제이슨으로 인식함
		}).done(function(r) { //그래서 여기서 받을 때 잭슨이 제이슨을 자바스크립트로 바꿔줘서 자바스크립트 오브젝트화됨
			if (result.statusCode == 200) {
				alert('댓글이 삭제 되었습니다.');
				$('#comment--item--'+commentId).remove();
			}
		}).fail(function(r) {
			alert('댓글 삭제 실패');
		});
	}