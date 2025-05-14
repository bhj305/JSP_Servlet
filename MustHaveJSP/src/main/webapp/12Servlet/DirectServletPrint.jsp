<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>DirectServletPrint.jsp</title>
	</head>
	<body>
		<h2>web.xml에서 매핑 후 Servlet에서 직접 출력하기</h2>
		<!-- 상대경로로 링크 생성, 메소드는 POST 로 진행 요청 방식을 잘못설정하면 405 에러 발생함 -->
		<form action="../12Servlet/DirectServletPrint.do" method="post">
			<input type="submit" value="바로가기"/>
		</form>
	</body>
</html>