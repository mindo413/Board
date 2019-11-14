<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
table, th {
	text-align: center;
}

tr td:nth-child(2){
	text-align: left;
}

tr td:not(:first-child), tr th:not(:first-child){
	border-left: 3px solid white;
}
</style>

<div class="container">

	<h1>게시판 목록</h1>
	<hr>

	<table class="table table-hover table table-striped table-condensed">
		<tr class="info">
			<th style="width: 10%">게시글 번호</th>
			<th style="width: 30%">제목</th>
			<th style="width: 20%">아이디</th>
			<!-- <th style="width: 30%">내용</th> -->
			<th style="width: 10%">조회수</th>
			<th style="width: 10%">최초작성일</th>
		</tr>
		<c:forEach items="${list }" var="i">
			<tr>
				<td>${i.boardno }</td>
				<td><a href="/board/view?boardno=${i.boardno }">${i.title }</a></td>
				<td>${i.id }</td>
				<%-- <td>${i.content }</td> --%>
				<td>${i.hit }</td>
				<td>${i.writtendate }</td>
			</tr>
		</c:forEach>

	</table>

</div>
<!-- .container -->

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
