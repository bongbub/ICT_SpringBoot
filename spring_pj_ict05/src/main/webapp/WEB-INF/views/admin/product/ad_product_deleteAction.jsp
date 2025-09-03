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

<title>글작성페이지</title>

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
	$(function(){	// 상세페이지가 로딩되면
		
		// <게시글목록> 버튼 클릭할 때 -> 컨트롤러의 목록으로 이동
		$('#btnList').click(function(){
			location.href="${path}/board_list.bc";
		});
		
		// 작성시 필수요소 작성 체크
		$('#btnSave').click(function(){

/* 			if($('#b_password').val() != ""){
				if($('#b_title').val() != ""){
					if($('#b_content').val() != ""){
						document.insertForm.action="${path}/board_insertAction.bc";
						document.insertForm.submit();
					}
					else{
						alert("내용을 입력하세요.");
						$('#b_content').focus();
						return false;
					}
				}else{
					alert("제목을 입력하세요.");
					$('#b_title').focus();
					return false;
				}
			}else{
				alert("비밀번호을 입력하세요.");
				$('#b_password').focus();
				return false;
			} */
			
			const password = $('#b_password').val();
			if(password == ""){
				alert("게시글 비밀번호를 설정해주세요.");
				$('#b_password').focus();
				return false;
			}
			const title = $('#b_title').val();
			if(title == ""){
				alert("제목을 입력해주세요.");
				$('#b_title').focus();
				return false;
			}
			const content = $('#b_content').val();
			if(content == ""){
				alert("내용을 입력하세요.");
				$('#b_content').focus();
				return false;
			}
			else{
				document.insertForm.action="${path}/board_insertAction.bc";
				document.insertForm.submit();
			}
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
					<h1 align="center"> 상품 삭제 처리 </h1>
				</div>
				<!-- 상단 중앙 1 끝 -->
				
				<!-- 상단 중앙 2 시작 -->
				<div id="section2">
					<!-- 좌측 메뉴 시작 -->
					<%@ include file="/WEB-INF/views/admin/common/leftMenu.jsp" %>
					<!-- 좌측 메뉴 끝 -->
					
					<!-- 우측 화면 시작 -->
					<c:if test="${deleteCnt == 1 }">
						<script type="text/javascript">
							setTimeout(function(){
								window.location ="${path}/ad_product_list.pd";
							}, 1000);
						</script>
					</c:if>
					<c:if test="${deleteCnt != 1 }">
						<script type="text/javascript">
							setTimeout(function(){
								window.location ="${path}/ad_product_list.pd";
							}, 1000);
						</script>
					</c:if>
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
