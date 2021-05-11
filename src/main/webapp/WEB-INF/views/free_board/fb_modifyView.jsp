<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
</head>
<script type="text/javascript">
	$(document).ready(function(){
		var formObj = $("form[name='modifyForm']");
		
		$(".cancelBtn").on("click", function(){
			event.preventDefault();
			location.href="/free_board/fb_list?fb_id={update.fb_id}"
							+"&page=${searchCri.page}"
							+"&perPageNum=${searchCri.perPageNum}"
							+"&searchType=${searchCri.searchType}"
							+"&keyword=${searchCri.keyword}";
		})
		
		$(".updateBtn").on("click", function(){
			if(fn_valiChk()){
				return false;
			}
			formObj.attr("action", "/free_board/fb_modify");
			formObj.attr("method", "post");
			formObj.submit();
		})
	})
	
	function fn_valiChk(){
		var updateForm = $("form[name='updateForm'].chk").length;
		for(var i=0; i<updateForm; i++){
			if($(".chk").eq(i).val()==""||$(".chk").eq(i).val()==null){
				alert($(".chk").eq(i).attr("title"));
				return true;
			}
		}
	}
</script>
<body>
	<div id="root">
		<header>
			<h1>자유게시판</h1>
		</header>
		<hr/>
		
		<div>
			<%@include file="nav.jsp" %>
		</div>
		
		<hr/>
		
		<section id="container">
			<form name="modifyForm" role="form" action="/free_board/fb_modify" method="post">
				<input type="hidden" name=fb_id value="${fb_update.fb_id }" readonly="readonly" />
				<input type="hidden" name=m_id value="${fb_update.m_id }" readonly="readonly"/>
				<table>
					<tbody>
						<tr>
							<td>
								<label for="fb_id">게시글 번호</label>
							</td>
							<td>${fb_update.fb_id }</td>
						</tr>
						<tr>
							<td>
								<label for="fb_date">작성일</label>
							</td>
							<td><fmt:formatDate value="${fb_update.fb_date }" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<td>
								<label for="m_id">아이디</label>
							</td>
							<td>${fb_update.m_id }</td>
						</tr>
						<tr>
							<td>
								<label for="fb_title">제목</label>
							</td>
							<td><input type="text" name="fb_title" size="50" class="chk" title="제목을 입력하세요"></td>
						</tr>
						<tr>
							<td>
								<label for="fb_content">내용</label>
							</td>
							<td><textarea name="fb_content" rows=30 cols=50 class="chk" title="내용을 입력하세요"></textarea></td>
						</tr>
					</tbody>
				</table>
				<div>
					<button type="submit" class="updateBtn">저장</button>
					<button type="submit" class="cancelBtn">취소</button>
				</div>		
			</form>
		</section>
		<hr/>
	</div>

</body>
</html>