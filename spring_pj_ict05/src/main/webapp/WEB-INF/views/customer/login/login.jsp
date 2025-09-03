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
<link rel="stylesheet" href="${path}/resources/css/customer/login.css">

<!--  js -->
<script src="https://kit.fontawesome.com/8760f92571.js" crossorigin="anonymous"></script>
<script src="/jsp_pj_ict05/resources/js/common/main.js" defer></script>
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
					<h1 align="center"> 로그인 </h1>
				</div>
				
				<!-- 상단 중앙 2 시작 -->
				<div id="section2">
					<div id="s2_inner">
						<div class="join">
							<form name="loginform" action="loginAction.do" method="post"
								onsubmit="return loginCheck()">
								
								<table>
									<tr>
										<th>  아이디 </th>
										<td>
											<input type="text" class="input" name="user_id" size="20" placeholder="공백없이 20자 이내" required autofocus>
										</td>
									</tr>
									<tr>
										<th>  비밀번호 </th>
										<td>
											<input type="password" class="input" name="user_password" size="20" placeholder="공백없이 20자 이내" required>
										</td>
									</tr>
									<tr>
										<td colspan="2" style="border-bottom:none">
											<br>
											<div align="right">
												<input class="inputButton" type="submit" value="로그인">
												<input class="inputButton" type="reset" value="초기화">
												<input class="inputButton" type="button" value="회원가입" onclick="window.location='${path}/join.do'">
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