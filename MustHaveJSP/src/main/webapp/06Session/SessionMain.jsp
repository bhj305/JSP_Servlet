<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/* 시간 서식 지정 */
SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // HH:mm:ss 표시형식

long creationTime = session.getCreationTime(); // 최초 요청(세션 생성) 시간
String creationTimeStr = dateFormat.format(new Date(creationTime));

long lastTime = session.getLastAccessedTime(); // 최종 요청 시간
String lastTimeStr = dateFormat.format(new Date(lastTime));

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Session</title>
	</head>
	
	<body>
		<h2>Session 설정 확인</h2>
		<!-- 별도 설정이 없으면 30분으로 설정됨. -->
		<ul>
			<li>세션 유지 시간: <%= session.getMaxInactiveInterval() %> </li> <!-- 20분 설정해서 1200 출력됨 -->
			<li>세션 아이디: <%= session.getId() %></li>
			<li>최초 요청 시간: <%= creationTimeStr %> </li>
			<li>최종 요청 시간: <%= lastTimeStr %> </li>
		</ul>
		
	</body>
</html>