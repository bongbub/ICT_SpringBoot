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

<title>상품 상세페이지</title>

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
					<h1 align="center"> 상품 상세수정 페이지 </h1>
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
						
							<form name="ad_product_add" action="ad_product_updateAction.pd" method="post" enctype="multipart/form-data">
								<table>
									<!-- hidden : 직접 input 박스에서 입력받지 못한 값들을 전달할 때 사용
												여기선 pageNum, pdNo, pdImg 를 넘길 것임
									 -->
									 <input type="hidden" name="hiddenPageNum" value="${pageNum}">	<!-- addAttri로 넘겨준 pageNum -->
									 <input type="hidden" name="hiddenPdNo" value="${dto.pdNo}">
									 <input type="hidden" name="hiddenPdImg" value="${dto.pdImg}">	<!-- 기존 상품 이미지 -->
									 
									<tr>
										<th> * 상품번호 </th>
										<td>
											${dto.pdNo}
										</td>
									</tr>
									<tr>
										<th> * 브랜드 </th>
										<td >
											<input type="text" class="input" name="pdBrand" id="pdBrand"			
												size="50" value="${dto.pdBrand}" placeholder="50자 이내로 작성" required>
										</td>
									</tr>
									<tr>
										<th> * 상품명 </th>
										<td >
											<input type="text" class="input" name="pdName" id="pdName"			
												size="50" value="${dto.pdName}" placeholder="50자 이내로 작성" required>
										</td>
									</tr>
									<tr>
										<th> * 상품 이미지 </th>
										<td>
											<img src="${dto.pdImg}" style="width:200px"><br>
											<input type="file" class="input" name="pdImg" id="pdImg"			
												accept="image/*"> <!-- required 삭제 -->
										</td>
									</tr>
									<tr>
										<th> * 상품카테고리 </th>
										<td >
											<select class="input" name="pdCategory" required>
												<option value=""> 상품카테고리</option>
												<option
													<c:if test="${dto.pdCategory == '주방가전'}">
														selected
													</c:if> 
													value="주방가전">주방가전</option>
												<option 
													<c:if test="${dto.pdCategory == '생활가전'}">
														selected
													</c:if> 
													value="생활가전">생활가전</option>
												<option 
													<c:if test="${dto.pdCategory == '모바일/전자기기'}">
														selected
													</c:if>
													value="모바일/전자기기">모바일/전자기기</option>
											</select>
										</td>
									</tr>
									<tr>
										<th> * 상품설명 </th>
										<td >
											<textarea  rows="5" cols="77" name="pdContent" id="pdContent"			
												 placeholder="상품 설명 작성"> ${dto.pdContent}</textarea>
										</td>
									</tr>
									<tr>
										<th> * 상품가격 </th>
										<td >
											<input type="number" class="input" name="pdPrice" id="pdPrice"			
												size="10" value="${dto.pdPrice}" 
												placeholder="가격 작성" required>
										</td>
									</tr>
									<tr>
										<th> * 상품수량 </th>
										<td >
											<input type="number" class="input" name="pdQuantity" id="pdQuantity"			
												size="10" value="${dto.pdQuantity}"
												placeholder="수량 작성" required>
										</td>
									</tr>
									<tr>
										<th> * 상품 상태코드 </th>
										<td >
											<select class="input" name="pdStatus" required>
												<option value=""> 상품상태코드</option>
												<option 
													<c:if test="${dto.pdStatus == '판매중'}">
														selected
													</c:if>
												value="판매중">판매중</option>
												<option
													<c:if test="${dto.pdStatus == '입고대기'}">
														selected
													</c:if>
												value="입고대기">입고대기</option>
												<option 
													<c:if test="${dto.pdStatus == '품절'}">
														selected
													</c:if>
												value="품절">품절</option>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<input type="submit" class="inputButton" value="상품수정">
											<input type="button" class="inputButton" value="초기화">
											<input type="button" class="inputButton" value="상품목록" onclick="window.location='${path}/ad_product_list.pd'">
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
