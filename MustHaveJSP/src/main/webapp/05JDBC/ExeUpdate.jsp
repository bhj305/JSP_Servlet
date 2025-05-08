<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JDBC</title>
	</head>
	<!-- executeQuery: Select 문과 같은 쿼리문을 실행할 때 사용 -->
	<!-- executeUpdate: Insert, Update, Delete 문과 같은 쿼리문을 실행할 때 사용 -->
	<body>
		<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
		<%
		JDBConnect jdbc = new JDBConnect(); // 객체 생성
		
		/* 입력할 회원데이터 준비(하드코딩) */
		String id = "test1"; /* request.getParameter("id") => 실제입력 */
		String pass = "1111";
		String name = "테스트1회원";
		
		String sql = "INSERT INTO member VALUES(?, ?, ?, sysdate)"; // 인파라미터 3개가 있는 동적쿼리문을 준비
		
		PreparedStatement psmt = jdbc.con.prepareStatement(sql); // 동적쿼리문을 실행하기 위한 PreparedStatement 준비
		
		/* 인덱스는 1부터 시작함 */
		psmt.setString(1, id);
		psmt.setString(2, pass);
		psmt.setString(3, name);
		
		int inResult = psmt.executeUpdate(); //executeUpdate를 이용해 실행
		out.println(inResult + "행이 입력되었습니다.");
		
		jdbc.close(); // 자원해제
		
		%>
	</body>
	
</html>