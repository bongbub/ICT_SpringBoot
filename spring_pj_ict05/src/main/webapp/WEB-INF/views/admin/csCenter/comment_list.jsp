<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/views/common/setting.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>댓글목록</title>

<!-- css -->
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_comment_list.css">

<%-- <!--  js -->
<script src="https://kit.fontawesome.com/8760f92571.js" crossorigin="anonymous"></script>

<!-- (3-4). 자바스크립트 소스 연결 -->
<!-- defer : html을 다 읽은 후에 자바 스크립트를 실행해라 -> 페이지가 모두 로딩 된 후 외부 스크립트 실행 -->
<script src="${path}/resources/js/common/main.js" defer></script>
 --%>

</head>
<body>
	<!-- 우측 화면 시작 -->
	<div id="right">
		<div class="table_div">
		
			<form name="boardList" class="boardList">
				<c:if test="${not empty list }">
					<table border="1">
						<tr>
							<th>글번호</th>
							<th>작성자</th>
							<th>글내용</th>
							<th>작성일</th>
						</tr>
						
						<!-- 게시글이 있으면 -->
						<c:forEach var="dto" items="${list}">
							<tr>
								<td>${dto.c_board_num}</td>
								<td>${dto.c_writer}</td>
								<td>${dto.c_content}</td>
								<td>${dto.c_regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</form>
		</div>
	</div>
	<!-- 우측 화면 끝 -->
</body>
