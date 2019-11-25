<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		//목록버튼 동작
		$("#btnList").click(function() {
			$(location).attr("href", "/board/list");
		});

		//수정버튼 동작
		$("#btnUpdate").click(function() {
			$(location).attr("href", "/board/update?boardno=${board.boardno }")
		});

		//삭제버튼 동작
		$("#btnDelete").click(function() {
			$(location).attr("href", "/board/delete?boardno=${board.boardno}")
		});

	});
</script>

<script type="text/javascript">
$(document).ready(function() {
	if(${isRecommend}) {
		$("#btnRecommend")
			.addClass("btn-warning")
			.html('추천 취소');
		
	} else {
		$("#btnRecommend")
			.html('추천');
	}
	
	$("#btnRecommend").click(function() {
		
		$.ajax({
			type: "get"
			, url: "/board/recommend"
			, data: { "boardno": '${viewBoard.boardno }' }
			, dataType: "json"
			, success: function( data ) {
// 				console.log("성공");
// 				console.log(data);

				if( data.result ) { //추천 성공
					$("#btnRecommend")
					.removeClass("btn-primary")
					.addClass("btn-warning")
					.html('추천 취소');
				
				} else { //추천 취소 성공
					$("#btnRecommend")
					.removeClass("btn-warning")
					.addClass("btn-warning")
					.html('추천');
				
				}
				
				//추천수 적용
				$("#recommend").html(data.cnt);
				
			}
			, error: function() {
				console.log("실패");
				
			}
		});
		
	});
});
</script>

<div class="container">

	<h1>게시판 - 상세보기</h1>

	<hr>

	<table class="table table-bordered">
		<tr>
			<td class="info">글번호</td>
			<td colspan="3">${board.boardno }</td>
		</tr>

		<tr>
			<td class="info">제목</td>
			<td colspan="3">${board.title }</td>
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
			<td id="recommend">${board.recommend }</td>
		</tr>

		<tr>
			<td class="info">작성일</td>
			<td colspan="3">${board.writtendate }</td>
		</tr>

		<tr>
			<td class="info">첨부파일</td>
			<td colspan="3"><a href="/upload/${boardfile.storedname }">${boardfile.originname }</a></td>
		</tr>

		<tr>
			<td class="info" colspan="4">내용</td>
		</tr>

		<tr>
			<td colspan="4">${board.content }</td>
		</tr>

	</table>

	<div class="text-center">
		<c:if test="${login }">
			<button id="btnRecommend" class="btn btn-warning">추천</button>
		</c:if>
		<button id="btnList" class="btn btn-primary">목록</button>
		<c:if test="${board.id eq userid}">
			<button id="btnUpdate" class="btn btn-info">수정</button>
			<button id="btnDelete" class="btn btn-danger">삭제</button>
		</c:if>
	</div>

</div>
<!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />