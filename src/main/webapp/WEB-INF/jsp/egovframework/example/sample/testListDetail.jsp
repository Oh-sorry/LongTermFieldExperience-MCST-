<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- include libraries(jQuery, bootstrap) -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<script src="/resources/js/summernote-ko-KR.js"></script>
<title>글 상세 조회</title>
<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : 'content',
			minHeight : 300,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR',
			toolbar : null
		});
		$('#summernote').summernote('disable');
	});
</script>
</head>
<body
	style="text-align: auto; margin: 0 auto; display: inline; padding-top: 100px;">
	<nav class="navbar" style="background-color: #d6e6f5;">
		<div class="container-fluid">
			<a class="navbar-brand">글 상세 조회</a>
		</div>
	</nav>
	<br>
	<article>
		<div class="container" role="main">
			<form name="form" id="form" role="form" method="post"
				action="updateTest.do" encType="multipart/form-data">
				<div class="form-group">
					<label for="title">제목</label>
					<input type="text" class="form-control" name="title" id="title" value='<c:out value="${title}"></c:out>' readonly>
				</div>
				<div class="form-group">
					<label for="writer">작성자</label>
					<input type="text" class="form-control" name="writer" id="writer" value='<c:out value="${writer}"></c:out>' readonly>
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea id="summernote" rows="5" name="content" id="content">${content}</textarea>
				</div>
				<div class="form-group">
					<label for="idx">아이디</label>
					<input type="text" class="form-control" name="idx" id="idx" value='<c:out value="${idx}"></c:out>' readonly>
				</div>
				<div class="form-group">
					<label for="orgFileName">첨부 파일</label>
					<c:forEach items="${orgFileName}" var="result" varStatus="status">
						<c:if test="${result.saveFileName ne null}">
							<a href="fileDownload.do?saveFileName=${result.saveFileName}&code=${result.boardIdx}&orgFileName=${result.orgFileName}">
								<input type="text" name="orgFileName" id="orgFileName" value='<c:out value="${result.orgFileName}"></c:out>' class="form-control" readonly="readonly" />
							</a>
						</c:if>
					</c:forEach>
				</div>
				<div class="form-group">
					<label for="idx">등록일</label> 
					<input type="text" class="form-control" name="regDate" id="regDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${regDate}"/>' readonly>
				</div>
				<div class="form-group">
					<label for="code"></label>
					<input type="hidden" class="form-control" name="code" id="code" value='<c:out value="${code}"></c:out>'>
				</div>
			</form>
			<div style="float: right">
				<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>

			</div>
		</div>
	</article>
</body>
<script type="text/javascript">
	$("#btnUpdate").click(function previous() {
		$(location).attr('href', 'testListUpdate.do?code=${code}');
	});
	$("#btnList").click(function previous() {
		$(location).attr('href', 'testList.do');
	});
</script>
</html>