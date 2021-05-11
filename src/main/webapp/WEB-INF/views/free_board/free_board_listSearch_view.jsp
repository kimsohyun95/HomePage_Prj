<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreeBoard</title>
<style type="text/css">
	ul{
	   list-style:none;
	   }
	li{list-style:none;float:left;padding:6px;}
	.current{
		color:red;
	}
	.otherwise{
		color:black;
	}

</style>

</head>
<body>
	<h2>자유게시판</h2>
	
	<form action="free_board_listSearch" method="get">
 		<input type="hidden" name="page" value="${fbSearchDto.currentPageNum }" />
		<input type="hidden" name="pageDataCount" value="${fbSearchDto.pageDataCount }" />
		<select id="searchCol" name="searchCol">
			<option value="fb_title">제목</option>
			<option value="m_id">아이디</option>
		</select> 
		<input type="text" name="searchVal" > 
		<input type="submit" value="검색">
	</form>
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>아이디</td>
			<td>제목</td>
			<td>등록날짜</td>
			<td>조회수</td>
		</tr>
		<c:forEach items="${dtos }" var="fbDto">
		<tr>
			<td>${fbDto.fb_id }</td>
			<td>${fbDto.m_id }</td>
			<td>
				<c:forEach begin="1" end="${fbDto.fb_indent }">-</c:forEach>
				<a href="free_board_content_view?fb_id=${fbDto.fb_id }&page=${fbSearchDto.currentPageNum}
				&pageDataCount=${fbSearchDto.pageDataCount}&searchCol=${fbSearchDto.searchCol}&searchVal=${fbSearchDto.searchVal}">${fbDto.fb_title }</a>

			</td>
			<td>${fbDto.fb_date }</td>
			<td>${fbDto.fb_hit }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="free_board_write_view">글쓰기</a></td>
		</tr>
	</table>
	<div>
		<ul>
			<li> <a href="free_board_listSearch?page=${fbSearchDto.firstPageNum }&pageDataCount=${fbSearchDto.pageDataCount}
					&searchCol=${fbSearchDto.searchCol}&searchVal=${fbSearchDto.searchVal}"><<</a></li>
			<li> <a href="free_board_listSearch?page=${fbSearchDto.prevPageNum }&pageDataCount=${fbSearchDto.pageDataCount}
					&searchCol=${fbSearchDto.searchCol}&searchVal=${fbSearchDto.searchVal}"><</a></li>
			
			<c:forEach var="i" begin="${fbSearchDto.startPageNum }" end="${fbSearchDto.endPageNum }" step="1">
				<c:choose>
					<c:when test="${i eq fbSearchDto.currentPageNum }">
						<li> <a class="current" href="free_board_listSearch?page=${i }&pageDataCount=${fbSearchDto.pageDataCount }
								&searchCol=${fbSearchDto.searchCol}&searchVal=${fbSearchDto.searchVal}" >${i }</a></li>
					</c:when>
					<c:otherwise>
						<li> <a class="otherwise"href="free_board_listSearch?page=${i }&pageDataCount=${fbSearchDto.pageDataCount }
								&searchCol=${fbSearchDto.searchCol}&searchVal=${fbSearchDto.searchVal}">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<li> <a href="free_board_listSearch?page=${fbSearchDto.nextPageNum }&pageDataCount=${fbSearchDto.pageDataCount}
					&searchCol=${fbSearchDto.searchCol}&searchVal=${fbSearchDto.searchVal}">></a></li>
			<li> <a href="free_board_listSearch?page=${fbSearchDto.lastPageNum }&pageDataCount=${fbSearchDto.pageDataCount}
					&searchCol=${fbSearchDto.searchCol}&searchVal=${fbSearchDto.searchVal}">>></a></li>
		</ul>
	</div>
	
</body>
</html>