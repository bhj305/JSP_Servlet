<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - Fmt3</title>
	</head>
	<body>
		<c:set var="today" value="<%= new java.util.Date() %>"></c:set>
		한글로 설정: <fmt:setLocale value="ko-KR"/>
		<fmt:formatNumber value="10000" type="currency"/> /
		<fmt:formatDate value="${ today }"/> <br/>
		
		일어로 설정: <fmt:setLocale value="ja-JP"/>
		<fmt:formatNumber value="10000" type="currency"/> /
		<fmt:formatDate value="${ today }"/> <br/>
		
		영어로 설정: <fmt:setLocale value="en-US"/>
		<fmt:formatNumber value="10000" type="currency"/> /
		<fmt:formatDate value="${ today }"/> <br/>
	</body>
</html>