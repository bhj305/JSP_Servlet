<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* 4가지 영역에 동일한 속성명으로 각기 다른 값을 저장함 */
pageContext.setAttribute("scopeValue", "page 영역");
request.setAttribute("scopeValue", "request 영역");
session.setAttribute("scopeValue", "session 영역");
application.setAttribute("scopeValue", "application 영역");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>표현 언어(EL) - 내장 객체</title>
	</head>
	<body>
		<h2>ImplicitObjMain 페이지</h2>
		<h3>각 영역의 속성을 JSP의 내장 객체를 통해 읽기</h3>
		<ul>
			<li>페이지 영역: <%= pageContext.getAttribute("scopeValue") %></li>
			<li>리퀘스트 영역:  <%= request.getAttribute("scopeValue") %> </li>
			<li>세션 영역:  <%= session.getAttribute("scopeValue") %> </li>
			<li>애플리케이션 영역:  <%= application.getAttribute("scopeValue") %> </li>
		</ul>
		
		<h3>각 영역의 저장된 속성 읽기(EL 사용)</h3>
		<ul>
			<li>페이지 영역: ${ pageScope.scopeValue }</li>
			<li>리퀘스트 영역:  ${ requestScope.scopeValue } </li>
			<li>세션 영역:  ${ sessionScope.scopeValue } </li>
			<li>애플리케이션 영역:  ${ applicationScope.scopeValue } </li>
		</ul>
		
		
		<h3>가장 좁은 영역을 우선으로 출력함</h3>
		<ul>
		<!-- 가장 좁은 영역을 우선으로 출력함 -->
			<li>scopeValue 영역: ${ scopeValue }</li>
		</ul>
		
		<jsp:forward page="ImplicitForwardResult.jsp"/>
	</body>
</html>