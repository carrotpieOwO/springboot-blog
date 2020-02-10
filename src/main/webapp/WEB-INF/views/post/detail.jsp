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
				<button id="post--delete--submit" class="btn btn-danger"
					value="${post.id}">삭제</button>
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
				<input type="hidden" id="postId" value="${post.id}" /> <input
					type="hidden" id="userId" value="${sessionScope.principal.id}" />
				<textarea class="form-control" rows="2" id="content"></textarea>
			</div>
			<div class="card-footer">
				<button id="comment--save--submit" class="btn btn-dark">등록</button>
			</div>
		</div>
	</div>
	<br />
	<div class="card">
		<div class="form-group">
			<div class="card-header">
				<h4 class="card-title">댓글리스트</h4>
			</div>
			<ul id="comment--items" class="list-group">
				<c:forEach var="comment" items="${comments}">

					<li id="comment--item--1"
						class="list-group-item d-flex justify-content-between align-items-center">

						<div class="font-italic">${comment.content}</div>
						<div class="badge badge-warning badge-pill ml-auto">작성자:${comment.username}</div>
						<button onclick="commentDelete(1)"
							class="badge badge-danger badge-pill">삭제</button>
						<br>
					</li>
				</c:forEach>

			</ul>
			<br>
		</div>
	</div>
</div>

<script src="/js/detail.js"></script>
<%@include file="../include/footer.jsp"%>
