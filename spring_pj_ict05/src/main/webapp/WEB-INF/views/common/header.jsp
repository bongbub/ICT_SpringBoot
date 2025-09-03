<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %> 	<!-- jstl로 contextPath를 변수(path)로 만들어 둠. 그것을 사용하기 위해 import  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>

</head>
<body>
	<!-- header 시작 -->
	<!-- 요청 : url, button, link -->
	<nav class="navbar">
		<ul class="navbar_title">
			<li><i class="fa-solid fa-dragon"></i></li>
		</ul>
		<ul class="navbar_menu">
			<li><a href="${path}/main.do">홈</a></li>
			<li><a href="#">제품소개</a></li>
			<li><a href="#">Q&A</a></li>
		</ul>
		
		<ul class="navbar_icons">
			<li><a href="#"><i class="fa-brands fa-twitter"></i></a></li>
			<li><a href="#"><i class="fa-brands fa-facebook"></i></a></li>
			<li><a href="${path}/login.do">LOGIN</a></li>
			<li><a href="${path}/join.do">JOIN</a></li>
			<li><a href="#"><i class="fa-solid fa-basket-shopping"></i></a></li>
			<li><a href="#"><i class="fa-solid fa-user"></i></a></li>
			<li><a href="#"><i class="fa-solid fa-magnifying-glass"></i></a></li>	<!-- 검색 -->
		</ul>
		
		
		<!-- 반응형 웹 만들기 -->
		<a href="#" class="navbar_toggleBtn">
			<!-- 반응형웹 - (1).햄버거 아이콘 : https://fontawesome.com/ => 돋보기 => 기본이 free > bars 선택 -->
			<i class="fa-solid fa-bars"></i>  <!-- 목록 아이콘 삽입 -->
		
		</a>
	</nav>
	<!-- header 끝 -->
	
</body>
</html>