<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery/min.js"></script>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

	<style>
		.fb_content{
			text-aligh:center;
		}
	</style>
	<title>자유게시판</title>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		var formObj = $("form[name='writeForm']");
		$(".writeBtn").on("click", funtion()){
			if(fn_valiChk()){
				return false;
			}
			formObj.attr("action", "/free_board/fb_write");
			formObj.attr("method", "post");
			formObj.submit();
		};
	})
	function fn_valiChk(){
		var regForm = $("form[name='writeForm'].chk").length;
		for(var i=0; i<regForm; i++){
			if($(".chk").eq(i).val()==""||$(".chk").eq(i).val() ==null){
				alert($(".chk").eq(i).attr("title"));
				return true;
			}
		}
	}
</script>

<body>

	<div id="root">
		<header>
			<h1>자유 게시판</h1>
		</header>
		<hr />
		
		<div>
			<%@include file="nav.jsp" %>
		</div>
		<hr />
		
		<section id="container">
			<form name="writeForm" method="post" action="/free_board/fb_write" enctype="multipart/form-data">
				<table>
					<tbody>	
						<c:if test="${member.m_id != null }"> 
							<tr>
								<td>
									<label for="m_id">아이디</label>
									<input type="text" id="m_id" name="m_id" class="chk" value="${member.m_id }"/>
								</td>
							</tr>
							<tr>
								<td>
									<label for="fb_title">제목</label>
									<input type="text" id="fb_title" name="fb_title" class="chk" title="제목을 입력하세요"/>
								</td>
							</tr>
							<tr>
								<td>
									<label for="fb_content" class="fb_contnet">내용</label>
									<textarea rows=30 cols=50 id="fb_content" name="fb_content" class="chk" title="내용을 입력하세요"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2">첨부 파일 <input type="file" name="file"></td>
							</tr>
							<tr>
								<td colspan="2"><button class="fileAdd_btn" type="button">파일 추가</button>
							</tr>
							<tr>
								<td>
									<button class="writeBtn" type="submit">등록</button>
							</tr>	
						</c:if>
					</tbody>
				<c:if test="${member.m_id == null }">
					<p>로그인 후에 글 작성이 가능합니다.</p>
				</c:if>
				</table>
			</form>
		</section>
		<hr />
	</div>
	

</body>
</html>