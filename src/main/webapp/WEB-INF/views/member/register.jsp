<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>회원가입</title>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		//취소
		$(".cancel").on("click", function(){
			location.href="/"
		})
		
		$("#submit").on("click", function(){
			if($("#m_id").val()==""){
				alert("아이디를 입력해주세요");
				$("#m_id").focus();
				return false;
			}
			if($("#m_pw").val()==""){
				alert("비밀번호를 입력해주세요");
				$("#m_pw").focus();
				return false;
			}
			if($("#m_name").val()==""){
				alert("이름을 입력해주세요");
				$("#m_name").focus();
				return false;
			}
			if($("#m_email").val()==""){
				alert("이메일을 입력해주세요");
				$("#m_email").focus();
				return false;
			}
		});
		
	})
</script>

<body>
	<section id="container">
		<form action="/member/register" method="post">
			<div class="form-group has-feedback">
				<label class="control-label" for="m_id">아이디</label>
				<input class="form-control" type="text" id="m_id" name="m_id" />
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="m_pw">비밀번호</label>
				<input class="form-control" type="password" id="m_pw" name="m_pw" />
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="m_name">이름</label>
				<input class="form-control" type="text" id="m_name" name="m_name" />
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="m_email">이메일</label>
				<input class="form-control" type="text" id="m_email" name="m_email" />
				
			</div>
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="submit" id="submit">회원가입</button>
				<button class="cancel btn btn-danger" type="button">취소</button>
			</div>
		</form>
	</section>


</body>
</html>