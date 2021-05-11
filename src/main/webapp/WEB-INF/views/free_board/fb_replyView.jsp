<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<title>자유게시판</title>
</head>

<body>
	<div id="root">
		<header>
			<h1>자유게시판</h1>
		</header>
		<hr />
			
	<form action="fb_reply" method="post">
		<input type="hidden" name="fb_id" value="${fb_reply.fb_id}" />
		<input type="hidden" name="fb_group" value="${fb_reply.fb_group}" />
		<input type="hidden" name="fb_step" value="${fb_reply.fb_step}" />
		<input type="hidden" name="fb_indent" value="${fb_reply.fb_indent}" />
		<table width="500" cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td>번호</td>
				<td>${fb_reply.fb_id}</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="m_id" value="${fb_reply.m_id}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="fb_title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" name="fb_content">
				</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록"> <a
					href="fb_list">목록</a></td>
			</tr>
		</table>
	</form>
</div>
</body>