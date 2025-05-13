<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>JSTL - xml</title>
	</head>
	<body>
		<!-- 공공 api 연동해서 사용하면 됨. -->
		<c:set var="booklist">
			<c:import url="/11JSTL/inc/BookList.xml" charEncoding="UTF-8"/>
		</c:set>
		<!-- 파싱 준비 -->
		<x:parse xml="${ booklist }" var="bList"/>
		
		<!-- 2개의 book이 있으므로 인덱스를 통해 접근 -->
		<h4>파싱 1</h4> <!-- 파싱을 하기위해서는 xalan.jar 파일 필요!!!! -->
		제목: <x:out select="$bList/booklist/book[1]/name"/><br/>
		저자: <x:out select="$bList/booklist/book[1]/author"/><br/>
		가격: <x:out select="$bList/booklist/book[1]/price"/><br/>
		
		<h4>파싱 2</h4>
		<table border="1">
			<tr>
				<th>제목</th><th>저자</th><th>가격</th>
			</tr>
			<x:forEach select="$bList/booklist/book" var="item">
				<tr>
					<td>
						<x:out select="$item/name"/>
					</td>
					<td>
						<x:out select="$item/author"/>
					</td>
					<td>
						<x:choose>
							<x:when select="$item/price >= 20000">
								2만원 이상<br/>
							</x:when>
							<x:otherwise>
								2만원 미만<br/>
							</x:otherwise>
						</x:choose>
					</td>
				</tr>
			</x:forEach>
		</table>
		
		<h4>파싱 3</h4>
		<table border="1">
			<x:forEach select="$bList/booklist/book" var="item">
				<tr>
					<td>
						<x:out select="$item/name"/>
					</td>
					<td>
						<x:out select="$item/author"/>
					</td>
					<td>
						<x:out select="$item/price"/>
					</td>
					<td>
						<x:if select="$item/name='총, 균, 쇠'">구매함</x:if>
					</td>
				</tr>
			</x:forEach>
		</table>
	</body>
</html>