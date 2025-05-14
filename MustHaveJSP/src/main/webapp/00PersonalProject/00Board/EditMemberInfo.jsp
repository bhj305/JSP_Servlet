<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String sessionId = session.getAttribute("UserId").toString();
String sessionName = session.getAttribute("UserName").toString();

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보수정</title>
	</head>
	<body>
		<jsp:include page="../../Common/Link_personal.jsp" />  
		<div class="container">
			이름: <input type="text" value=" <%= sessionName %> "><br/>
			아이디: <%= sessionId %>
		</div>
	</body>
</html>