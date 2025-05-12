<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>표현 언어(EL) - 내장 객체</title>
	</head>
	<body>
		<h2>ImplictForwardResult 페이지 </h2>
		<h3>각 영역의 저장된 속성 읽기(EL 사용)</h3>
		<!-- 페이지 영역은 소멸됨 -->
		<ul>
			<li>페이지 영역: ${ pageScope.scopeValue }</li>
			<li>리퀘스트 영역:  ${ requestScope.scopeValue } </li>
			<li>세션 영역:  ${ sessionScope.scopeValue } </li>
			<li>애플리케이션 영역:  ${ applicationScope.scopeValue } </li>
		</ul>
		
		표현식으로 출력하면 null 출력됨: <%= pageContext.getAttribute("scopeValue") %>
		<!-- 따라서 scopeValue 의 가장 좁은 영역은 리퀘스트가 됨 . -->
		<h3>페이지 영역은 소멸되므로 가장 좁은 영역인 리퀘스트를 우선으로 출력함</h3>
		<ul>
		<!-- 가장 좁은 영역을 우선으로 출력함 -->
			<li>scopeValue 영역: ${ scopeValue }</li>
		</ul>
	</body>
</html>