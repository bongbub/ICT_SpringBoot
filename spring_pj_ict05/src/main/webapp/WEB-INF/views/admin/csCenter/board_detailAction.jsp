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

<title>상세페이지</title>

<!-- css -->
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_board_detailAction.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_leftMenu.css">

<!--  js -->
<script src="https://kit.fontawesome.com/8760f92571.js" crossorigin="anonymous"></script>

<!-- (3-4). 자바스크립트 소스 연결 -->
<!-- defer : html을 다 읽은 후에 자바 스크립트를 실행해라 -> 페이지가 모두 로딩 된 후 외부 스크립트 실행 -->
<script src="${path}/resources/js/common/main.js" defer></script>

<script>
	$(function(){		// 상세페이지가 로딩되면
		
		// 댓글목록 - 기존 작성한 것도 뿌려줘야하기 때문에 가장 먼저 호출 (=> 자동으로 댓글목록 로딩)
		comment_list();
		
		//  [댓글쓰기 버튼] 클릭 (1)
		$('#btnCommentSave').click(function(){
			comment_add();
		});
		
		
		// <게시글목록> 버튼 클릭할 때 -> 컨트롤러의 목록으로 이동
		$('#btnList').click(function(){
			location.href="${path}/board_list.bc";
		});
		
		
		// <게시글 수정/삭제> 버튼 클릭 시 -> 게시글 수정삭제 화면으로 이동(컨트롤러에서 패스워드 체크 후)
		$('#btnEdit').click(function(){
			document.detailForm.action="${path}/password_chkAction.bc";		// 이 페이지로 이동후 
			document.detailForm.submit();	// submit 하라.
		});
		
	});
	
	//  [댓글쓰기 버튼] 클릭 (2)
	function comment_add(){
		//alert('comment_add()');
		
		// 게시글 번호, 댓글 작성자, 댓글 내용 parameter로 전송
		let param ={
				"board_num" : ${dto.b_num},		// => key:value -> 댓글번호:게시글번호
				"writer" : $('#c_writer').val(),
				"content" : $('#c_content').val()
		}
		$.ajax({
			url:'${path}/comment_insert.bc',		// 컨트롤러 이동 (3) 
			type:'POST',
			data: param,
			success:function(){			// 콜백함수(6) => 댓글쓰기가 완료되면 서버에서 콜백함수
				$('#c_writer').val("");
				$('#c_content').val("")
				comment_list();			// 댓글목록 새로고침 (7)
			},
			error: function(){
				alert('comment_add() 오류');
			}
		});
	}
	
	
	// 댓글목록
	function comment_list(){
		// alert("comment_list()");
		 $.ajax({
			url:'${path}/comment_list.bc',
			type:'POST',
			data: 'board_num=${dto.b_num}',		//게시글 번호가 같을 때
			success:function(result){		// 성공이면
				// 콜백함수
				$('#comment_list').html(result);		// 콜백함수 호출
			},
			error: function(){
				alert('comment_list() 오류');
			}
		}); 
	} 
	

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
					<h1 align="center"> 게시판 상세페이지 </h1>
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
							<form name="detailForm" method="post" >
								<table>
									<tr>
										<th>글번호</th>
										<td>${dto.b_num}</td>
										<th>조회수</th>
										<td>${dto.b_readCnt}</td>
										
									</tr>
									<tr>
										<th>작성자</th>
										<td>${dto.b_writer}</td>
										<th>비밀번호</th>
										<td>
											<input type="password" class="input" name="b_password"
												id="b_password" size="4" placeholder="비밀번호 4자리 입력" required>
												
												<c:if test="${param.message == 'error' }">
													<br> <span id="not_pwd">비밀번호 불일치</span> 
												</c:if>
										</td>
									</tr>
									<tr>
										<th> 글제목 </th>
										<td colspan="3"> ${dto.b_title } </td>
									</tr>
									<tr>
										<!-- 글 내용  -->
										<td colspan="4" id="content_box" style=" height:500px">
											<div class="content_box">
												${dto.b_content}
											</div>
										</td>
									</tr>
									<tr>
										<th>작성일</th>
										<td colspan="3">${dto.b_regDate }</td>
									</tr>
									<tr>
										<td colspan="4">
											<!-- 게시글번호 hidden 추가 : 다음 처리에서 인증에 필요하지만, 넘겨줄 값을 받는 input이 없으므로 (게시글 번호는 입력받지 않기 때문에) -->
											<input type="hidden" name="hidden_b_num" value="${dto.b_num}">
											<input type="button" class="inputButton" value="수정/삭제" id="btnEdit">
											<input type="button" class="inputButton" value="목록" id="btnList">
										</td>
									</tr>
								</table>
								<br><br><br>
								
								<!-- 댓글 목록 코드 -->
								<div id="comment_list" class="comment" align="center">
									<!-- 댓글 목록 -->
									
								</div>
								
								<!-- 댓글 입력 코드 -->
								<table class="comment">
									<tr>
										<th>작성자</th>
										<td>
											<input type="text" class="input_com" name="c_writer"
												id="c_writer" size="50" placeholder="작성자 입력">
										</td>
									</tr>
									<tr>
										<th>글내용</th>
										<td>
											<textarea rows="5" cols="93" name="c_content" id="c_content" placeholder="댓글입력">
											</textarea>
										</td>
										<td>
											<div align="center">
												<input type="button" class="inputButton" value="작성" id="btnCommentSave">
											</div>
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
