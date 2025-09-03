<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>main</title>

<!-- css -->
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/customer/join.css">

<!--  js -->
<script src="https://kit.fontawesome.com/8760f92571.js" crossorigin="anonymous"></script>

<!-- (3-4). 자바스크립트 소스 연결 -->
<!-- defer : html을 다 읽은 후에 자바 스크립트를 실행해라 -> 페이지가 모두 로딩 된 후 외부 스크립트 실행 -->
<script src="${path}/resources/js/common/main.js" defer></script>
<script src="${path}/resources/js/customer/join.js" defer></script>
<!-- daum 주소 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function addrclick(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 각 주소의 노출 규칙에 따라 주소 조합
	            // 내려오는 변수 값이 없는 경우 공백('')을 가지므로, 이를 참고하며 분기
	            var roadAddr = data.roadAddress; 	// 도로명 주소 변수
                var extraRoadAddr = ''; 			// 참고 항목 변수
	            
	            
            	// 법정동명 추가 (동/로/가)
            	if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
            		extraRoadAddr += data.bname;
                }
            	// 건물명이 있고 공동주택일 경우
            	if(data.buildingName !== '' && data.apartment === 'Y'){
            		extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
       			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                	extraRoadAddr = ' (' + extraRoadAddr + ')';
                } 
       			
             	// 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('user_zipcode').value = data.zonecode;
                document.getElementById("user_addr1").value = roadAddr + "(" + data.jibunAddress + ")";
                document.getElementById("user_addr2").focus();
                
                
             	/* // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("user_addr3").value = extraRoadAddr;
                } else {
                    document.getElementById("user_addr3").value = '';
                } */
	        }
	    }).open();
	}
	
</script>
</head>
<body>
	<div class="wrap">
		<!-- header 시작 -->
		<%@ include file="../../common/header.jsp" %>	<!--  customer/webapp/common/header.jsp -->
		<!-- header 끝 -->
		 
		<!-- 컨텐츠 시작 -->
		<div id="container">
			<div id="contents">
				<!-- 상단 중앙 1 시작-->
				<div id="section1">
					<h1 align="center"> 회원가입 </h1>
				</div>
				
				<!-- 상단 중앙 2 시작 -->
				<div id="section2">
					<div id="s2_inner">
						<div class="join">
							<form name="inputform" action="joinAction.do" method="post"
								onsubmit="return signInCheck()">
								
								<!-- 2-1. 중복확인 버튼 안 눌렀을 때 0으로 설정 누르면 1로 변경 -->
								<input type="hidden" name="hiddenUserId" value="0">
								
								<table>
									<tr>
										<th> * 아이디 </th>
										<td>
											<input type="text" class="input" name="user_id" size="20" placeholder="공백없이 20자 이내" required autofocus>
											<input type="button" name="dubChk" value="중복확인" style="margin-left:10px" onclick="confirmId()">
										</td>
									</tr>
									<tr>
										<th> * 비밀번호 </th>
										<td>
											<input type="password" class="input" name="user_password" size="20" placeholder="공백없이 20자 이내" required>
										</td>
									</tr>
									<tr>
										<th> * 비밀번호(확인) </th>
											<td>
												<input type="password" class="input" name="re_password" size="20" placeholder="비밀번호 확인" required>
											</td>
									</tr>
									<tr>
										<th> * 이름 </th>
										<td>
											<input type="text" class="input" name="user_name" size="20" placeholder="이름 작성" required>
										</td>
									</tr>
									<tr>
										<th><label for="birth"> * 생년월일 </label></th>
										<td>
											<input type="date" id ="birth" class="input" name="user_birthday" size="8" placeholder="- 없이 '19991030'형식으로 작성" required>
										</td>
									</tr>
									<tr>
										<th> * 주소 </th>
										<td>
											<input type="text" id ="user_zipcode" class="addr_input" name="user_address1" size="10" placeholder="우편번호" required>
											<input type="button" onclick="addrclick()" value="우편번호 찾기"><br>
											<input type="text" class="input" id="user_addr1" name ="user_address2" placeholder="주소"><br>
											<input type="text" class="input" id="user_addr2"name ="user_address3"  placeholder="상세주소" required>
											
											<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
											<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
											</div>
										</td>
									</tr>
									<tr>
										<th> 연락처 </th>
										<td>
											<input type="text" class="input" name="user_hp1" size="3" style="width:50px" >
											-
											<input type="text" class="input" name="user_hp2" size="4" style="width:70px" >
											-
											<input type="text" class="input" name="user_hp3" size="4" style="width:70px" >
										</td>
									</tr>
									<tr>
										<th> * 이메일 </th>
										<td>
											<input type="text" class="input" name="user_email1" size="8" style="width:100px" required>
											@
											<input type="text" class="input" name="user_email2" size="20" style="width:100px" required>
											<select class="input" name="user_email3" style="width:100px" onchange="selectEmailChk()">
												<option value="0">직접 입력</option>
												<option value="naver.com">naver.com</option>
												<option value="gmail.com">gmail.com</option>
												<option value="daum.net">daum.net</option>
												<option value="nate.com">nate.com</option>
											</select>
										</td>
									</tr>
									
									
									<tr>
										<td colspan="2" style="border-bottom:none">
											<br>
											<div align="right">
												<input class="inputButton" type="submit" value="회원가입">
												<input class="inputButton" type="reset" value="초기화">
												<input class="inputButton" type="button" value="가입취소" onclick="window.location='main.do'">
												<!-- .do => 컨트롤러로 신호를 보냄 확장자가 .do인지 확인(if체크)해서 main.jsp로 보냄 -->
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>	<!-- join -->
					</div>
				</div>
				<!-- 상단 중앙 2 끝 -->
				
			</div>		
		</div>
		<!-- 컨텐츠 끝 -->
		
		
		<!-- footer 시작 -->
		<%@ include file="../../common/footer.jsp" %>
		<!-- footer 끝 -->
		
	</div>

</body>
</html>