<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
</style>
<article class="container">
	<div class="page-header">
		<div class="col-md-6 col-md-offset-3">
			<h1 style="text-align: center">로그인</h1>
		</div>
	</div>
	<div class="col-sm-6 col-md-offset-3">
		<form class="form-horizontal" action="/member/login" method="post">
			<div class="form-group">
				<label for="userid" class="col-sm-2 control-label">ID</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="userid" name="userid"
						placeholder="아이디를 입력하세요." style="width: 80%">
				</div>
			</div>
			<div class="form-group">
				<label for="userpw" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="userpw"
						name="userpw" placeholder="비밀번호를 입력하세요." style="width: 80%">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label> <input type="checkbox"> 아이디 저장
						</label>
					</div>
				</div>
			</div>
			<div class="form-group text-center">

				<button type="button" class="btn btn-default"
					onclick="location.href='/member/join'">회원가입</button>
				&nbsp;
				<button type="submit" class="btn btn-default">로그인</button>

			</div>
		</form>
	</div>
</article>
<c:import url="/WEB-INF/views/layout/footer.jsp" />