<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()"> <!-- 트루, 펄스 리턴 -->
    <sec:authentication property="principal" var="principal" /> <!-- principal변수에 principal 담음 principal->userdetail타입을 담은 객체-->
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

	<nav class="navbar navbar-expand-md bg-danger navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="/">당근로그</a>

		<!-- Toggler/collapsibe Button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
		
				<c:choose>
					<c:when test="${not empty principal}">
						<li class="nav-item"><a class="nav-link" href="/post/write">Post</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/profile/${principal.id}">Profile</a></li>
						<li class="nav-item"><a class="nav-link" href="/logout">LogOut</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/user/join">Join</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/login">Login</a></li>	
					</c:otherwise>
				</c:choose>		
			</ul>
			<img src="/media/${principal.profile}" class="rounded-circle my__img ml-auto" width="30px" height="30px" onError="javascript:this.src='/images/unknown.jpg'"/>
			<!-- 사진 못찾았을 경우 디폴트 이미지 주기 onerror -->
		</div>
	</nav>
	<br />