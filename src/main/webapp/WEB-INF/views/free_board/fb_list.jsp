<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<title>자유게시판</title>
	<style type="text/css">
		li{list-style:none; float:left; padding: 6px;}
		.current{
			color:red;
		}
		.otherwise{
			color:black;
		}
		a:link{text-decoration:none;}
		a:visited{text-decoration:none;}
	
	</style>
</head>

<body>
	<div class="container">
		<header>
			<h1>자유게시판</h1>
		</header>
		<hr />
		
		<div>
			<%@include file="nav.jsp" %>
		</div>
		
		<section id="conteainer">
			<form role="form" method="get">
				<table class="table table-hover">
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>제목</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>
					
					<c:forEach items="${list }" var="fbList">
						<tr>
							<td><c:out value="${fbList.fb_id }" /></td>
							<td><c:out value="${fbList.m_id }" /></td>
							<td>
								<c:forEach begin="1" end="${fbList.fb_indent }">ㄴ(re)</c:forEach>
								<a href="/free_board/fb_read?fb_id=${fbList.fb_id }&
																page=${searchCri.page}&
																perPageNum=${searchCri.perPageNum}&
																searchType=${searchCri.searchType}&
																keyword=${searchCri.keyword}">
								<c:out value="${fbList.fb_title }" /></a></td>
							<td><fmt:formatDate value="${fbList.fb_date}" pattern="yyyy-MM-dd" /></td>
							<td><c:out value="${fbList.fb_hit }" /></td>
						</tr>
					</c:forEach>
				</table>
				
				
				<div class="search ">
					<div class="col-xs-2 col-sm-2">
						<select name="searchType" class="form-control">
							<option value="n"<c:out value="${searchCri.searchType == null ? 'selected' : '' }" />>------</option>
							<option value="t"<c:out value="${searchCri.searchType eq 't'?'selected':'' }" />>제목</option>
							<option value="c"<c:out value="${searchCri.searchType eq 'c' ? 'selected' : '' }" />>내용</option>
							<option value="m"<c:out value="${searchCri.searchType eq'm' ? 'selected': '' }" />>아이디</option>
							<option value="tc"<c:out value="${searchCri.searchType eq'tm' ? 'selected':'' }"/>>제목+내용</option>
						</select>
					</div>
					
					<div class="col-xs-10 clo-sm-10">
						<input type="text" name="keyword" id="keywordInput" value="${searchCri.keyword }" class="form-control"/>
						<span class="input-group-btn">
							<button id="searchBtn" type="button" class="btn btn-default"> 검색 </button>
						</span>
					</div>
					
					<script>
						$(function(){
							$('#searchBtn').click(function(){
								self.location = "fb_list" + '${pageMaker.makeQuery(1)}'+"&searchType="+$("select option:selected").val() 
								+"&keyword=" + encodeURIComponent($('#keywordInput').val()); 
							});
						});
					</script>
				</div>
							
				<div class="col-md-offset-3">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li><a href ="fb_list${pageMaker.makeSearch(pageMaker.startPage -1) }"><</a></li>
						</c:if>
						
						<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx" step="1">
							<c:choose>
								<c:when test="${idx eq pageMaker.currentPageNum }">
									<li><a class="current" href="fb_list${pageMaker.makeSearch(idx) }">${idx }</a></li>
								</c:when>
								<c:otherwise>
									<li><a class="otherwise" href="fb_list${pageMaker.makeSearch(idx) }">${idx }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${pageMaker.next }">
							<li><a href ="fb_list${pageMaker.makeSearch(pageMaker.endPage + 1) }">></a></li>
						</c:if>
					</ul>
				</div>
			</form>
		</section>
	</div>

</body>
</html>