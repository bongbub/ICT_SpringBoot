<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 좌측메뉴</title>
<link rel="stylesheet" href="${path}/resources/css/admin/ad_leftMenu.css">
</head>
<body>
	<!-- 좌측메뉴 시작 -->
	<div id="left">
		<div class="left_inside">
			<!-- 좌측 메뉴바 시작 -->
			<ul class="left_menubar">
				<li><h3>관리자 메뉴</h3></li>
				<hr>
				
				<li>
					<h4> 상품관리 </h4>
					<ul class="product_menu">
						<li><a href="${path}/ad_product_list.pd">상품목록</a></li>
						<li><a href="${path}/ad_product_add.pd">상품등록</a></li>
						<li><a href="#">카테고리</a></li>
					</ul>
				</li>
				<li>
					<h4> 주문관리 </h4>
					<ul class="order_menu">
						<li><a href="#">주문목록</a></li>
						<li><a href="#">주문등록</a></li>
						<li><a href="#">환불요청</a></li>
						<li><a href="#">환불완료</a></li>
						<li><a href="#">환불취소</a></li>
					</ul>
				</li>
				<hr>
				<li>
					<h4> 회원관리 </h4>
					<ul class="order_menu">
						<li><a href="#">회원목록</a></li>
						<li><a href="#">게시판</a></li>
						<li><a href="#">결산</a></li>
						<li><a href="#">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<!-- 좌측 메뉴바 종료 -->
		</div>
	</div>
</body>
</html>