<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL 사용선언(core 라이브러리, functions 라이브러리) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!-- 컨텍스트 패스(=플젝명 jsp_pj_ict05 ==> 세 번째 패키지) 를 path 라는 변수에 설정해서 대체 사용 -->
<c:set var="path" value="${pageContext.request.contextPath}" />			<!-- value="jsp_pj_ict05" -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <!-- 따로 닫아야한다 -->