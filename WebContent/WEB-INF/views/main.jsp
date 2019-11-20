<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
.center-block {
	display: block;
	margin-left: auto;
	margin-right: auto;
}
</style>

<h1>Main</h1>
<hr>

<div class="center-block">
	<c:choose>
		<c:when test="${not empty login}">
			<h3>${usernick }님,환영합니다^^</h3>
			<button class="btn btn-default"
				onclick="location.href='/member/logout' ">로그아웃</button>
			<button class="btn btn-default" onclick="location.href='/board/list'">게시판
				목록</button>

		</c:when>
		<c:otherwise>
			<button class="btn btn-default" type="submit" onclick="location.href='/member/join'">회원가입</button>
			<button onclick="location.href='/member/login'"
				class="btn btn-default" type="submit">로그인</button>
		</c:otherwise>
	</c:choose>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />