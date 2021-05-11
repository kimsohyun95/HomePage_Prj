<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<title>회원정보수정</title>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		//취소
		$(".cancel").on("click", function(){
			location.href="/"
		})
		
		$("#submit").on("click", function(){
			if($("#m_pw").val()=="" || $("#m_pw").val()==null){
				alert("비밀번호를 입력해주세요");
				$("#m_pw").focus();
				return false;
			}
			if($("#m_name").val()=="" || $("#m_name").val()==null){
				alert("이름을 입력해주세요");
				$("#m_name").focus();
				return false;
			}
			if($("#m_email").val()=="" || $("#m_email").val()==null){
				alert("이메일을 입력해주세요");
				$("#m_email").focus();
				return false;
			}
		});
	})
</script>

<body>
	<section id="container">
		<form action="/member/memberUpdate" method="post">
		<input type="hidden" id="m_id" name="m_id" value="${member.m_id }" />
			<table>
				<tr class="form-group has-feedback">
					<td>
						<label class="control-label" for="m_id">아이디</label>
					</td>
					<td class="form-control" id="m_id">
						${member.m_id }
					</td>
				</tr>
				<tr class="form-group has-feedback">
					<td>
						<label class="control-label" for="m_pw">비밀번호</label>
					</td>
					<td>
						<input class="form-control" type="password" id="m_pw" name="m_pw" />
					</td>
				</tr>
				<tr class="form-group has-feedback">
					<td>
						<label class="control-label" for="m_name">이름</label>
					</td>
					<td>
						<input class="form-control" type="text" id="m_name" name="m_name" value="${member.m_name }" />
					</td>
				</tr>
				<tr class="form-group has-feedback">
					<td>
						<label class="control-label" for="m_email">이메일</label>
					</td>
					<td>
						<input class="form-control" type="text" id="m_email" name="m_email" value="${member.m_email }" />
					</td>
				</tr>			
				<tr class="form-group has-feedback">
					<td colspan="2">
						<button class="btn btn-success" type="submit" id="submit">회원정보수정</button>
						<button class="cancel btn btn-danger" type="button">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>