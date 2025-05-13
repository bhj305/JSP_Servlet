<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSTL - Fmt1</title>
	</head>
	<body>
		<h4>숫자 포맷 설정</h4>
		<c:set var="number1" value="12345" />
		콤마 O: <fmt:formatNumber value="${ number1 }"/><br/>
		콤마 X: <fmt:formatNumber value="${ number1 }" groupingUsed="false"/><br/>
		
<%-- 		원화 안나올떄  --%>
		<fmt:setLocale value="ja_jp"/> <!-- en_us, ja_jp, ko-KR-->
		<fmt:formatNumber value="${ number1 }" type="currency" var="printNum1" /><br/>
		통화기호(원화로 표시): ${ printNum1 }
		
		<fmt:formatNumber value="${ number1 }" type="currency" var="printNum1" currencySymbol="¥" /><br/>
		통화기호(원화로 표시): ${ printNum1 }
		
		<fmt:formatNumber value="${ number1 }" type="currency" var="printNum1" currencySymbol="₩" /><br/>
		통화기호(원화로 표시): ${ printNum1 }
		
		<fmt:formatNumber value="${ number1 }" type="currency" var="printNum1" currencySymbol="$" /><br/>
		통화기호(달러로 표시): ${ printNum1 }
		
		<fmt:formatNumber value="0.03" type="percent" var="printNum2"/><br/>
		퍼센트: ${ printNum2 }
		
		<h4>문자열을 숫자로 변경</h4>
		<c:set var="number2" value="6,790.01"/>
		<fmt:parseNumber value="${ number2 }" pattern="00,000.00" var="printNum3"/>
		소수점까지: ${ printNum3 } <br/>
		<fmt:parseNumber value="${ number2 }" integerOnly="true" var="printNum4"/>
		소수점까지: ${ printNum4 } <br/>
	</body>
</html>