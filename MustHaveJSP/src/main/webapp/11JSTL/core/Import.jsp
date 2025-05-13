<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - import</title>
	</head>
	<body>
		<c:set var="requestVar" value="musthave" scope="request"/> <!-- request 영역에 있기 떄문에 가능 -->
		<c:import url="/11JSTL/inc/OtherPage.jsp" var="contents">
			<c:param name="user_param1" value="JSP"></c:param>
			<c:param name="user_param2" value="기본서"></c:param>
		</c:import>
		
		<h4>다른 문서 삽입하기</h4>
		${ contents }
		
		<div>
			<h4>이미지 삽입하기</h4>
			<!-- 상대경로를 사용해야 에러 발생 확률이 적어짐 -->
			<h5>상대 경로 지정</h5>
			<img src="../../images/Error.jpg" width="150" height="80"/>
			<h5>절대 경로 지정</h5>
			<img src="/MustHaveJSP/images/Error.jpg" width="150" height="80"/>
			<h5>절대 경로 지정(request 내장객체 사용)</h5>
			<!-- 절대경로를 사용해야 하는 경우 내장객체에서 제공하는 메서드를 사용하는 게 좋음  -->
			<img src= "<%= request.getContextPath()%>/images/Error.jpg" width="150" height="80"/>
		</div>
		
		<h4>외부 자원 삽입하기</h4>
		<iframe src="../inc/GoldPage.jsp" style="width: 100%; height: 600px;"></iframe>
		
		
	</body>
</html>