<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>
<div class="container">

	<form>
		<div class="form-group">
			<label for="title">제목:</label> 
			<input type="text" class="form-control" id="title" value="1">
		</div>
		<div class="form-group">
			<label for="content">내용:</label> 
			<textarea class="form-control" rows="5" id="content">1</textarea>
		</div>
	</form>
	<button id="update--submit" class="btn btn-dark">수정</button>
	
	
</div>

<%@include file="../include/footer.jsp"%>
