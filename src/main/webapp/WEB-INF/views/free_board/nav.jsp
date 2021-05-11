<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<style type="text/css">
	li{list-style: none; display:inline; padding:6px;}
</style>

<ul>
	<li><a href="/">메인페이지</a></li>
	<li><a href="/free_board/fb_list">목록</a></li>
	<li><a href="/free_board/fb_writeView">글 작성</a></li>
	<li>
		<c:if test="${member != null }"><a href="/member/logout">로그아웃</a></c:if>
		<c:if test="${member == null }"><a href="/">로그인</a></c:if>
	</li>
	<li>
		<c:if test="${member != null }">
			<p>${member.m_id}님이 로그인 하셨습니다.</p>
		</c:if>
	</li>
</ul>