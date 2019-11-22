<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
table, th {
	text-align: center;
}
/* 두번째자식(제목)은 왼쪽정렬 */
tr td:nth-child(2) {
	text-align: left;
}
/* 첫번째자식이 아닌 tr td에 세로 선 입력 */
tr td:not (:first-child ), tr th:not (:first-child ){
	border-left: 3px solid white;
}


</style>

<script type="text/javascript">
	$(document).ready(function() {
		//글쓰기 동작
		$("#btnWrite").click(function() {
			location.href = "/board/write";
		});

		// 		//체크삭제 동작
		// 		$("#btnDelete").click(function() {
		// 			location.href = "/board/listDelete";
		// 		});

		//메인버튼 동작
		$("#btnMain").click(function() {
			location.href = "/main";
		});

	});

	$(document).ready(function() {
		//최상단 체크박스 클릭
		$("#checkAll").click(function() {
			//클릭되었으면
			if ($("#checkAll").prop("checked")) {
				//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
				$("input[name=checks]").prop("checked", true);
				//클릭이 안되있으면
			} else {
				//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
				$("input[name=checks]").prop("checked", false);
			}
		});
	});
</script>

<div class="container">

	<h1>게시판 목록</h1>
	<hr>

	<div class="text-right" style="margin-bottom: 10px;">
		<form action="/board/list" method="get" style="margin-bottom: -34px;">
			<input type="text" id="search" name="search"
				style="margin: auto 0px -34px 280px; width: 400px;" value="${search }"/>
			<button class="btn btn-default" id="btnSearch"
				style="margin: auto 250px auto auto;">검색</button>
		</form>
		<button class="btn btn-default" id="btnMain">메인</button>
		<button class="btn btn-default" id="btnWrite">글쓰기</button>
	</div>

	<form action="/board/listDelete" method="post">
		<table class="table table-hover table table-striped table-condensed">
			<tr class="info">
				<th style="width: 2%"><input type="checkbox" id="checkAll" /></th>
				<th style="width: 10%">게시글 번호</th>
				<th style="width: 35%">제목</th>
				<th style="width: 20%">아이디</th>
				<!-- <th style="width: 30%">내용</th> -->
				<th style="width: 10%">조회수</th>
				<th style="width: 10%">최초작성일</th>
			</tr>
			<c:forEach items="${list }" var="i">
				<tr>
					<td><input type="checkbox" id="checks" name="checks"
						value="${i.boardno }" /></td>
					<td style="text-align: center">${i.boardno }</td>
					<td><a href="/board/view?boardno=${i.boardno }">${i.title }</a></td>
					<td>${i.id }</td>
					<%-- <td>${i.content }</td> --%>
					<td>${i.hit }</td>
					<td>${i.writtendate }</td>
				</tr>
			</c:forEach>

		</table>
		<button class="btn btn-default btn-xs" id="btnDelete">체크삭제</button>
	</form>

	<jsp:include page="/WEB-INF/views/layout/paging.jsp" />

</div>
<!-- .container -->

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
