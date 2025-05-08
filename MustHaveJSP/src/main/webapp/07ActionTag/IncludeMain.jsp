<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/* 외부파일의 경로를 변수로 저장, 표현식 사용 가능 여부를 위함 */
String outerPath1 ="./inc/OuterPage1.jsp";
String outerPath2 ="./inc/OuterPage2.jsp";

/* page영역, request영역  */
pageContext.setAttribute("pAttr", "동명왕");
request.setAttribute("rAttr", "온조왕");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>지시어와 액션태그 동작 방식 비교</title>
	</head>
	<body>
		<h2>지시어와 액션태그 동작 방식 비교</h2>
		<h3>지시어로 페이지 포함하기</h3>
		<%@ include file= "./inc/OuterPage1.jsp" %>
		<%-- <%@ include page= "<%= outerPath2 %>" %> --%> <!-- 표현식 실행 안됨 -->
		<p>외부파일에 선언한 변수: <%= newVal1 %></p> <!-- 지시어를 통한 include는 하나의 페이지라 생각하면 됨. -->
		
		<h3>액션 태그로 페이지 포함하기</h3>
		<jsp:include page="./inc/OuterPage2.jsp"></jsp:include>
		<jsp:include page="<%= outerPath2 %>"/>
		<p>외부파일에 선언한 변수: <%-- <%= newVal2 %> --%></p>
	</body>
</html>