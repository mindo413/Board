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
		//작성버튼 동작
		$("#btnWrite").click(function() {

			// 스마트에디터의 내용을 <textarea>에 적용
			submitContents($("#btnWrite"));

			// form submit
			$("form").submit();
		});
		
		//취소버튼 동작
		$("#btnCancel").click(function() {
			history.go(-1);
		});
	});
</script>

<style type="text/css">
#content {
	width: 95%;
}

#smart_editor2 {
	margin: 0 auto;
}

.form-control {
	width: 98%;
}
</style>

<article class="container">
	<div class="page-header">
		<div>
			<h3>글 작성</h3>
		</div>
	</div>

	<div style="clear: both;"></div>

	<!-- 	<div class="col-sm-6 col-md-offset-3"> -->

	<div>
		<form role="form" action="/board/write" method="post"
			enctype="multipart/form-data" class="form-horizontal">
			<input type="hidden" name="boardno" value="${board.boardno }" />

			<div class="form-group">
				<label for="title">제목</label> <input type="text"
					class="form-control col-sm-5" id="title" name="title"
					placeholder="제목을 입력해 주세요" />
			</div>

			<!-- 			<div class="form-group"></div> -->

			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" rows="8" id="content" name="content"
					placeholder="내용"></textarea>
			</div>

			<div>
				<label>파일 <input type="file" name="upfile" /></label>
			</div>
			<br>

		</form>
			<div class="form-group text-center">
				<button type="button" id="btnWrite" class="btn btn-primary">작성</button>
				<button type="button" id="btnCancel" class="btn btn-warning">작성
					취소</button>
			</div>
	</div>

</article>

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