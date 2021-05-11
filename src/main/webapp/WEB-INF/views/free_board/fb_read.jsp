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
	
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj=$("form[name='readForm']");
			
			//수정
			$(".update_btn").on("click", function(){
				formObj.attr("action", "/free_board/fb_modifyView");
				formObj.attr("method", "get");
				formObj.submit();
			})
			
			//답글
			$(".reply_btn").on("click", function(){
				formObj.attr("action", "/free_board/fb_replyView");
				formObj.attr("method", "post");
				formObj.submit();
			})
			
			//삭제
			$(".delete_btn").on("click", function(){
				var deleteYN = confirm("삭제하시겠습니까?");
				
				if(deleteYN==true){
					formObj.attr("action", "/free_board/fb_delete");
					formObj.attr("method","post");
					formObj.submit();
				}
			})
			
			//목록
			$(".list_btn").on("click", function(){
				location.href="/free_board/fb_list?page${searchCri.page}"
				+"&perPageNum=${searchCri.perPageNum}"
				+"&searchType=${searchCri.searchType}&keyword=${searchCri.keyword}";
			})
			
			//댓글 작성
			$(".writeCommentBtn").on("click", function(){
				var formObj = $("form[name='commentForm']");
				formObj.attr("action", "/free_board/fb_writeComment");
				formObj.submit();
			})
			
			//댓글 수정 View
			$(".updateCommentBtn").on("click", function(){
				location.href="/free_board/fb_updateCommentView?fb_id=${fb_read.fb_id}"
								+"&page=${searchCri.page}"
								+"&perPageNum=${searchCri.perPageNum}"
								+"&searchType=${searchCri.searchType}"
								+"&keyword=${searchCri.keyword}"
								+"&fc_id="+$(this).attr("data-fc_id");
			});
			
			//댓글 삭제 View
			$(".deleteCommentBtn").on("click", function(){
				location.href="/free_board/fb_deleteCommentView?fb_id=${fb_read.fb_id}"
								+"&page=${searchCri.page}"
								+"&perPageNum=${searchCri.perPageNum}"
								+"&searchType=${searchCri.searchType}"
								+"&keyword=${searchCri.keyword}"
								+"&fc_id="+$(this).attr("data-fc_id");
			});
		})
		
		//첨부파일 다운
		function fn_fileDown(fb_file_no){
			var formObj = $("form[name='readForm']");
			$("#fb_file_no").attr("value", fb_file_no);
			formObj.attr("action", "/free_board/fb_fileDown");
			formObj.submit();
		}
	</script>
	<title>자유게시판</title>
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
		<hr />
		
		<section id="container">
			<form name="readForm" method="post">
				<input type="hidden" id="fb_id" name="fb_id" value="${fb_read.fb_id }" />
				<input type="hidden" id="page" name="page" value="${searchCri.page }" />
				<input type="hidden" id="perPageNum" name="perPageNum" value="${searchCri.perPageNum }" />
				<input type="hidden" id="searchType" name="searchType" value="${searchCri.searchType }" />
				<input type="hidden" id="keyword" name="keyword" value="${searchCri.keyword }" />
				<input type="hidden" id="fb_file_no" name="fb_file_no" value="">
			</form>
			
			<table width="500" cellpadding="0" cellspacing="0" border="1">
				<tbody>
					<tr class="form-group">
						<td class="col-sm-2 control-label">게시글 번호</td>
						<td class="form-control">${fb_read.fb_id }</td>
					</tr>
					<tr class="form-group">
						<td class="col-sm-2 control-label">작성일</td>
						<td><fmt:formatDate value="${fb_read.fb_date }" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr class="form-group">
						<td class="col-sm-2 control-label">아이디</td>
						<td class="form-control">${fb_read.m_id }</td>
					</tr>
					<tr class="form-group">
						<td class="col-sm-2 control-label">조회수</td>
						<td class="form-control">${fb_read.fb_hit }</td>
					</tr>
					<tr class="form-group">
						<td class="col-sm-2 control-label">제목</td>
						<td class="form-control">${fb_read.fb_title }</td>
					</tr>
					<tr class="form-group">
						<td class="col-sm-2 control-label">내용</td>
						<td class="form-control">${fb_read.fb_content }</td>
					</tr>
					
					<tr>
						<td>첨부 파일</td>
						<td>
							<c:forEach var="file" items="${fb_file }">
								<a href="#" onclick="fn_fileDown('${file.FB_FILE_NO }'); return false;">${file.FB_ORG_NAME }</a>(${file.FB_FILE_SIZE }kb)<br>
							</c:forEach>
						</td>
					</tr>
				</tbody>
				
					<tr>
						<td colspan="2">
						<!-- 삭제,수정,답글,목록보기 -->
							<button type="submit" class="update_btn btn btn-warning">수정</button>
							<button type="submit" class="reply_btn btn btn-info">답글</button>
							<button type="submit" class="delete_btn btn btn-danger">삭제</button>
							<button type="submit" class="list_btn btn btn-primary">목록</button>
						</td>
					</tr>
			</table>
			
			<!-- 댓글 -->
			<div id="comment">
				<ol class="commentList">
					<c:forEach items="${fb_commentRead }" var="fb_commentRead">
						<li>
							<p>
								작성자:${fb_commentRead.m_id }<br />
								작성 날짜:	<fmt:formatDate value="${fb_commentRead.fc_date }" pattern="yyyy-MM-dd" />
							</p>
							
							<p>${fb_commentRead.fc_content }</p>
							<div>
								<button type="button" class="updateCommentBtn btn btn-warning" data-fc_id="${fb_commentRead.fc_id }">수정</button>
								<button type="button" class="deleteCommentBtn btn btn-danger" data-fc_id="${fb_commentRead.fc_id }">삭제</button>
							</div>
						</li>
					</c:forEach>
				</ol>
			</div>
			
			<form name="commentForm" method="post" class="form-horizontal">
				<input type="hidden" id="fb_id" name="fb_id" value="${fb_read.fb_id }" />
				<input type="hidden" id="page" name="page" value="${searchCri.page }" />
				<input type="hidden" id="perPageNum" name="perPageNum" value="${searchCri.perPageNum }" />
				<input type="hidden" id="searchType" name="searchType" value="${searchCri.searchType }" />
				<input type="hidden" id="keyword" name="keyword" value="${searchCri.keyword }" />
				
				<div class="form-group">
					<label for="m_id" class="col-sm-2 control-label">댓글 작성자</label>
					<div class="col-sm-10">
						<input type="text" id="m_id" name="m_id" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="fc_content" class="col-sm-2 control-label">댓글 내용</label>
					<div class="col-sm-10">
						<input type="text" id="fc_content" name="fc_content" class="form-control">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="writeCommentBtn btn btn-success">작성</button>
					</div>
				</div>
			</form>
			
		</section>
	</div>

</body>
</html>