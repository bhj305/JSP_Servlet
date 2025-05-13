<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - Catch</title>
	</head>
	<body>
		<h4>자바 코드에서의 예외</h4>
		<%
		int num1 = 100;
		%>
		<!-- catch 태그: EL이나 JSP에서 발생한 예외를 처리할 떄 사용,
		 JSTL 문법 오류는 catch 되지 않음 -->
		<c:catch var="eMssage">
			<%
			/* 정수를 0으로 나누면 무한대이므로 오류 발생 */
				int result = num1 / 0;
			%>
		</c:catch>
		예외 내용: ${ eMssage }
		
		<h4>EL 에서의 예외</h4>
		<c:set var="num2" value="200"/>
		<c:catch var="eMssage">
			${ "일" + num2 }
		</c:catch>
		예외 내용: ${ eMssage }
	</body>
</html>