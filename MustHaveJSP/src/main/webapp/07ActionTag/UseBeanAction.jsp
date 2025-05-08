<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Action Tag UseBean</title>
	</head>
	<body>
		<h2>액션 태그로 폼값 한 번에 받기</h2>
		<!-- scope 값은 지정하지 않았으므로 디폴트값인 page 영역에 저장됨. -->
		<jsp:useBean id="person" class="common.Person"></jsp:useBean>
		<jsp:setProperty property="*" name="person"/>
		<ul>
			<li>이름: <jsp:getProperty property="name" name="person"/> </li>
			<li>나이: <jsp:getProperty property="age" name="person"/> </li>
		</ul>
	</body>
</html>