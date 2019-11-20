<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
table, th {
	text-align: center;
}

tr td:nth-child(2) {
	text-align: left;
}

tr td:not (:first-child ), tr th:not (:first-child ){
	border-left: 3px solid white;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		//글쓰기 동작
		$("#btnWrite").click(function() {
			if(${empty login}){
				
				$(location).attr("href", "/main");
			}
			else{
				$(location).attr("href","/board/write");
			}
		});

	});
</script>

<div class="container">

	<h1>게시판 목록</h1>
	<hr>
	
	<div class="text-right" style="margin-bottom: 20px;">
		<button class="btn btn-default" onclick="location.href='/main';">메인</button>
		<button class="btn btn-default"  id="btnWrite">글쓰기</button>
	</div>
	
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

	<jsp:include page="/WEB-INF/views/layout/paging.jsp" />

</div>
<!-- .container -->

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
