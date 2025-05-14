<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>AnnoMapping.jsp</title>
	</head>
	<body>
		<h2>애너테이션(@)으로 매핑하기</h2>
		<p>
		<!-- EL을 이용해 request 에 저장된 데이터 출력  -->
			<strong>${ message }</strong> <br/>
			<a href="<%= request.getContextPath() %>/12Servlet/AnnoMapping.do">바로가기</a>
		</p>
	</body>
</html>