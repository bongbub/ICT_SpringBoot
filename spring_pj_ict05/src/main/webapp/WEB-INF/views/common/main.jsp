<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
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
<link rel="stylesheet" href="${path}/resources/css/common/main.css">


<!--  js -->
<!-- <script src="https://kit.fontawesome.com/8760f92571.js" crossorigin="anonymous"></script> -->

<!-- kit방식 만료되어서 그냥 CDN방식으로 삽입 (아이콘) -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" referrerpolicy="no-referrer" />


<!-- (3-4). 자바스크립트 소스 연결 -->
<!-- defer : html을 다 읽은 후에 자바 스크립트를 실행해라 -> 페이지가 모두 로딩 된 후 외부 스크립트 실행 -->
<script src="${path}/resources/js/customer/join.js" defer></script>
<script src="${path}/resources/js/common/main.js" defer></script>
</head>
<body>

	<div class="wrap">
		<!-- header 시작 -->
		<%@ include file="header.jsp" %>

		<!-- header 끝 -->
		<!-- 컨텐츠 시작 -->
		<div align="center">
			<h3> 강아지 귀여워 </h3>
			<img src="${path}/resources/images/main/puppy.jpg" width="500px">
		</div>
		<!-- 컨텐츠 끝 -->
		
		
		<!-- footer 시작 -->
		<%@ include file="footer.jsp" %>
		<!-- footer 끝 -->
		
	</div>

</body>
</html>