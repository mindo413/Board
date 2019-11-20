<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />
<!-- 스마트에디터2 라이브러리 -->
<script type="text/javascript"
	src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
	// <form>이 submit되면
	// 스마트 에디터 내용을 <textarea>반영해주는 함수
	function submitContents(elClickedObj) {
		// 에디터의 내용이 textarea에 적용된다.
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

		try {
			elClickedObj.form.submit(); // <form> submit 수행
		} catch (e) {
		}
	}
</script>

<script type="text/javascript">
	$(document).ready(function() {
		//수정 취소버튼 동작
		$("#btnCancel").click(function() {
			$(location).attr("href", "/board/list");
		});

		//수정버튼 동작
		$("#btnUpdate").click(function() {
			submitContents($("#btnList"));

			$("form").submit();
		});

	});
</script>

<div class="container">

	<h1>게시판 - 수정하기</h1>

	<hr>
	<form name="update" action="/board/update" method="post">
		<input type="hidden" name="boardno" value="${board.boardno }" />

		<table class="table table-bordered">
			<tr>
				<td class="info">글번호</td>
				<td colspan="3">${board.boardno }</td>
			</tr>

			<tr>
				<td class="info">제목</td>
				<td colspan="3"><input type="text" class="form-control"
					id="title" name="title" value="${board.title }"/></td>
			</tr>

			<tr>
				<td class="info">아이디</td>
				<td>${board.id }</td>
				<td class="info">닉네임</td>
				<td>[ 추후 추가 ]</td>
			</tr>

			<tr>
				<td class="info">조회수</td>
				<td>${board.hit }</td>
				<td class="info">추천수</td>
				<td>[ 추후 추가 ]</td>
			</tr>

			<tr>
				<td class="info">작성일</td>
				<td colspan="3">${board.writtendate }</td>
			</tr>

			<tr>
				<td class="info">첨부파일</td>
				<td colspan="3">${boardfile.originname }</td>
			</tr>

			<tr>
				<td class="info" colspan="4">내용</td>
			</tr>

			<tr>
				<td colspan="4"><textarea class="form-control" rows="8"
						id="content" name="content">${board.content }</textarea></td>
			</tr>

		</table>

		<div class="text-center">
			<button type="button" id="btnUpdate" class="btn btn-info">수정</button>
			<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
		</div>
		<br>
	</form>
</div>
<!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />
<!-- 스마트 에디터 적용 코드 -->
<!-- <textarea>태그에 스마트에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "content", // 에디터가 적용되는 <textarea>의 id
		sSkinURI : "/resources/se2/SmartEditor2Skin.html", // 에디터의 스킨
		fCreator : "createSeditor2"

	})
</script>