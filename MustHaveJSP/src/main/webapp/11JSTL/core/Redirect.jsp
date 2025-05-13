<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - redirect</title>
	</head>
	<body>
 	<!-- request 영역은 공유되지 않음 -->
		<c:set var="requestVar" value="MustHave" scope="request"/>
	<!-- 페이지 이동시 파라미터를 전달할 수 있음 -->
		<c:redirect url="/11JSTL/inc/OtherPage.jsp">
			<c:param name="user_param1" value="출판사"></c:param>
			<c:param name="user_param2" value="골든래빗"></c:param>
		</c:redirect>
		
		
		<%-- <%
		String p1 =URLEncoder.encode("하이미디어");
		String p2 =URLEncoder.encode("신촌 지점");
		
		response.sendRedirect(request.getContextPath() + "/11JSTL/inc/OtherPage.jsp?user_param1=" + p1
				+ "&user_param2="+p2);
		%> --%>
		
	</body>
</html>