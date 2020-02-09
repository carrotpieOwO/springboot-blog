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
					$('#idmsg').html('아이디가 중복되었습니다.');
				} else {
					alert('회원가입 실패');

				}
			}
		}).fail(function(r) {
			alert('회원가입 실패');
			console.log(r);
			$('#idmsg').html(r.responseJSON.username);
			$('#pwmsg').html(r.responseJSON.password);
			$('#mailmsg').html(r.responseJSON.email);
			
		});
	});