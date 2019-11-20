<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<article class="container">
	<div class="page-header">
		<div class="col-md-6 col-md-offset-3">
			<h3>회원가입</h3>
		</div>
	</div>
	<div class="col-sm-6 col-md-offset-3">
		<form role="form" action="/member/join" method="post">
			<div class="form-group">
				<label for="userid">아이디</label> <input type="text"
					class="form-control" id="userid" name="userid"
					placeholder="아이디를 입력해 주세요" />
			</div>

			<div class="form-group">
				<label for="userpw">비밀번호</label> <input type="password"
					class="form-control" id="userpw" name="userpw"
					placeholder="비밀번호를 입력해주세요" />
			</div>

			<div class="form-group">
				<label for="usernick">닉네임</label> <input type="text"
					class="form-control" id="usernick" name="usernick"
					placeholder="닉네임을 입력해 주세요" />
			</div>

			<div class="form-group text-center">
				<button type="submit" id="join-submit" class="btn btn-primary">
					회원가입
				</button>
				<button type="button" class="btn btn-warning" onclick="location.href='/main'">
					가입취소
				</button>
			</div>
		</form>
	</div>

</article>

<c:import url="/WEB-INF/views/layout/footer.jsp" />