<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
</head>

<a href="free_board/fb_list">자유게시판</a><br/>

<script type="text/javascript">
	$(document).ready(function(){
		//로그아웃
		$("#logoutBtn").on("click", function(){
			location.href="/member/logout";
		})
		//회원가입
		$("#registerBtn").on("click", function(){
			location.href="member/register";
		})
		//회원정보수정
		$("#memberUpdateBtn").on("click", function(){
			location.href="member/memberUpdateView";
		})
		//회원탈퇴
		$("#deleteBtn").on("click", function(){
			location.href="/member/memberDeleteView";
		})
	})
</script>

<body>
	<form name='homeForm' method="post" action="/member/login">
		<c:if test="${member==null }">
			<div>
				<label for="m_id">아이디</label>
				<input type="text" id="m_id" name="m_id">
			</div>
			<div>
				<label for="m_pw">비밀번호</label>
				<input type="password" id="m_pw" name="m_pw">
			</div>
			<div>
				<button type="submit">로그인</button>
				<button id="registerBtn" type="button">회원가입</button>

			</div>
		</c:if>
		<c:if test="${member!=null }">
			<div>
				<p>${member.m_id }님이 로그인 하셨습니다. </p>
				<button id="memberUpdateBtn" type="button">회원정보수정</button>
				<button id="deleteBtn" type="button">회원탈퇴</button>
				<button id="logoutBtn" type="button">로그아웃</button>
			</div>
		</c:if>
		<c:if test="${msg==false }">
			<p style="color:red;">로그인에 실패하셨습니다.<br> 아이디와 비밀번호를 확인해주세요.</p>
		</c:if>
	</form>

</body>
</html>