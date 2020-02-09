<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/nav.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>No</th>
				<th>title</th>
				<!-- <th>username</th> -->
				<th>date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${posts}">
				<!-- boards를 돌면서 board 변수에 추가  -->
				<tr>
					<td>${post.id}</td>
					<td><a href="/post/${post.id}">${post.title}</a></td>
					<!-- 하이퍼링크 클릭은 모두 get요청 -->
					<td><fmt:formatDate value="${post.createDate}"
							pattern="yyyy.MM.dd HH:mm" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<%@include file="../include/footer.jsp"%>
