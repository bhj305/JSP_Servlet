<%@page import="model1.board.BoardDTO"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%> 
<%

String sessionId = session.getAttribute("UserId").toString();
String sessionName = session.getAttribute("UserName").toString();

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:include page="../../Common/Link_personal.jsp" />  
		
		<div class="container">
			아이디: <%= sessionId %> <br/>
			이름: <%= sessionName %>
		</div>
		
		<!-- 내 프로필 -->
		<!-- 작성한 게시글 -->
		
	</body>
</html>