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

<title>boardList</title>

<!-- css -->
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_boardList.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_leftMenu.css">

<!--  js -->
<script src="https://kit.fontawesome.com/8760f92571.js" crossorigin="anonymous"></script>

<!-- (3-4). 자바스크립트 소스 연결 -->
<!-- defer : html을 다 읽은 후에 자바 스크립트를 실행해라 -> 페이지가 모두 로딩 된 후 외부 스크립트 실행 -->
<script src="${path}/resources/js/common/main.js" defer></script>
<script>
	$(function(){
			$("#btnInsert").click(function(){
			window.location="${path}/board_insert.bc";
			
			});
			$("#sortType").change(function(){
				var sortType = $(this).val();
					window.location="${path}/board_list.bc?pageNum=${paging.currentPage}&searchType=${searchType}&keyword=${keyword}&sortType="+sortType;
		});
	});

</script>

</head>
<body>

	<div class="wrap">
		<!-- header 시작 -->
		<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<!-- header 끝 -->
		
		<!-- 컨텐츠 시작 -->
		<div id="container">
			<div id="contents">
				<!-- 상단 중앙 1 시작 -->
				<div>
					<h1 align="center"><a href="${path}/board_list.bc" style="color:black">게시판 목록</a> </h1>
				</div>
				<!-- 상단 중앙 1 끝 -->
				
				<!-- 상단 중앙 2 시작 -->
				<div id="section2">
					<!-- 좌측 메뉴 시작 -->
					<%@ include file="/WEB-INF/views/admin/common/leftMenu.jsp" %>
					<!-- 좌측 메뉴 끝 -->
					
					<!-- 우측 화면 시작 -->
					<div id="right">
						<div class="table_div">
							<form method="get" action="${pageContext.request.contextPath}/board_list.bc">
								<select name="searchType">
									<option value="T" ${searchType=="T"?"selected":""}>제목</option>
									<option value="W" ${searchType=="W"?"selected":""}>작성자</option>
									<option value="C" ${searchType=="C"?"selected":""}>내용</option>
								</select>
								<input type="text" name="keyword" placeholder="검색어를 입력하세요" value="${fn:escapeXml(keyword)}">	
								<button type="submit">검색</button>
								<select name="sortType" id="sortType">
									<option value="rn" ${sortType=="rn"?"selected":""}>최신순</option>
									<option value="viewCnt" ${sortType=="viewCnt"?"selected":""}>조회수순</option>
									<option value="commCnt" ${sortType=="commCnt"?"selected":""}>댓글순</option>
								</select>
							</form>
							<form name="boardList" >
								<table>
									<tr>
										<td colspan="5">
											<div align="right">
												<input type="button" class="inputButton" value="글쓰기" id="btnInsert">
											</div>
										</td>
									</tr>
									<tr> 
										<th>글번호</th>
										<th>작성자</th>
										<th>글제목</th>
										<th>작성일</th>
										<th>조회수</th>
									</tr>
									
									<!-- 게시글이 있으면 -->
									<c:forEach var="dto" items="${list}">
										<tr>
											<td>${dto.b_num}</td>
											<td>${dto.b_writer}</td>
											<td>
												<c:if test="${dto.b_comment_count != 0}">
													<a href="${path}/board_detailAction.bc?b_num=${dto.b_num}">${dto.b_title} [${dto.b_comment_count}]</a>
												</c:if>
												<c:if test="${dto.b_comment_count == 0}">
													<a href="${path}/board_detailAction.bc?b_num=${dto.b_num}">${dto.b_title}</a>
												</c:if>
											</td>
											<td>${dto.b_regDate}</td>
											<td>${dto.b_readCnt}</td>
										</tr>
									</c:forEach>
									
									<!-- 페이지 번호와 버튼 -->
									<tr>
										<td colspan="5" align="center">
											<ul class="pagination">
												<!-- 페이징 처리 -->
												<!-- 이전 버튼 활성화 -->
												<c:if test="${paging.startPage > 10}">
													<li>
														<a href="${path}/board_list.bc?pageNum=${paging.prev}&searchType=${searchType}&keyword=${keyword}&sortType=${sortType}" class="prevPage"> [ 이전 ]</a>
													</li>
												</c:if>
												
												<!-- 페이지 번호 처리 -->
												<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">	<!-- paging=>serviceimpl에서 넘겨준 참조변수, 의 멤버변수 호출 -->
													<li>
														<!-- 해당 페이지를 클릭할 때마다 번호를 pageNum에 넣어 ServiceImpl전송 및 처리 -->
														<a href="${path}/board_list.bc?pageNum=${num}&searchType=${searchType}&keyword=${keyword}&sortType=${sortType}" class="<c:if test='${num == paging.currentPage}'> active </c:if>">${num}</a>
													</li>
												</c:forEach>
												
												
												<!-- 다음 버튼 활성화 -->
												<c:if test="${paging.endPage < paging.pageCount}">
													<li>
														<a href="${path}/board_list.bc?pageNum=${paging.next}&searchType=${searchType}&keyword=${keyword}&sortType=${sortType}" class="nextPage"> [ 다음 ]</a>
													</li>
												</c:if>
												
												
											</ul>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<!-- 우측 화면 끝 -->
				</div>
				<!-- 상단 중앙 2 끝 -->
			</div>
		</div>
		<!-- 컨텐츠 끝 -->
		
		
		<!-- footer 시작 -->
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>
		<!-- footer 끝 -->
		
	</div>

</body>
