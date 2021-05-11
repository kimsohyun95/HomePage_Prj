<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>
<head>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> 

	<meta charset="UTF-8">
	<title>회원탈퇴</title>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		//취소
		$(".cancel").on("click", function(){
			location.href="/"
		})
		
		$("#submit").on("click", function(){
			if($("#m_pw").val()==""){
				alert("비밀번호를 입력해주세요");
				$("#m_pw").focus();
				return false;
			}
		});
	})
</script>

<body>
	<section id="container">
		<form action="/member/memberDelete" method="post">
		<input type="hidden" id="m_id" name="m_id" value="${member.m_id }" />
		<input type="hidden" id="m_name" name="m_name" value="${member.m_name }" />
			<div class="form-group has-feedback">
				<label class="control-label" for="m_id">아이디</label>
				<input class="form-control" type="text" id="m_id" name="m_id" value="${member.m_id}" readonly="readonly"/>
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="m_pw">비밀번호</label>
				<input class="form-control" type="password" id="m_pw" name="m_pw" />
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="m_name">성명</label>
				<input class="form-control" type="text" id="m_name" name="m_name" value="${member.m_name}" readonly="readonly"/>
			</div>			
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="submit" id="submit">회원탈퇴</button>
				<button class="cancel btn btn-danger" type="button">취소</button>
			</div>
		</form>
		<div>
			<c:if test="${msg==false }">
				비밀번호가 틀렸습니다.
			</c:if>
		</div>
	</section>
</body>
</html>