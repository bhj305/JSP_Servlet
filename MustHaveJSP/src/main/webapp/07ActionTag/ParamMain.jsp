<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* request.setCharacterEncoding("UTF-8"); */ // web.xml에 있으므로 생략 가능 
String pValue ="방랑시인";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Action Tag - Param</title>
	</head>
	<body>
	<!-- request 영역에 저장 -->
		<jsp:useBean id="person" class="common.Person" scope="request"/>
		<!-- set으로 값 지정 -->
		<jsp:setProperty property="name" name="person" value="김삿갓"/>
		<jsp:setProperty property="age" name="person" value="56"/>
		<jsp:forward page="ParamForward.jsp?param1=김병연">
			<jsp:param value="경기도 양주" name="param2"/>
			<jsp:param value="<%= pValue %>" name="param3"/>
		</jsp:forward>
	</body>
</html>