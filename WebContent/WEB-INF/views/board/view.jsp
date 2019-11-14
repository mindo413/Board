<%@page import="web.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Board board = (Board) request.getAttribute("board");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
	border: 1px solid #ccc;
	text-align: center;
}

td {
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding: 5px 10px;
}

th {
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding: 5px 10px;
}

td:hover {
	background: #ccf;
}

#title {
	background: #FF7F50;
}

tr:nth-child(2n) {
	background-color: ivory;
}

tr:nth-child(2n+1) {
	background-color: #FAEBD7;
}
</style>
</head>
<body>
	<h1>게시판 목록</h1>
	<hr>

	<table border="1">
		<tr id="title">
			<th>게시글 번호</th>
			<th>제목</th>
			<th>아이디</th>
			<th>내용</th>
			<th>조회수</th>
			<th>최초작성일</th>
		</tr>
		<tr>
<%-- 			<c:set targer="${ board}" var="b"></c:set> --%>
			<td><%=board.getBoardno() %></td>
			<td><%=board.getTitle() %></td>
			<td><%=board.getId() %></td>
			<td><%=board.getContent() %></td>
			<td><%=board.getHit() %></td>
			<td><%=board.getWrittendate() %></td>
		</tr>

	</table>
	<button onclick="location.href='/board/list';">목록</button>
	<button>수정</button>
	<button>삭제</button>
</body>
</html>