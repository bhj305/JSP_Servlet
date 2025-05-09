<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JDBC</title>
	</head>
	<body>
		<h2>회원 목록 조회 테스트(executeQuery() 사용 )</h2>
		<%
		JDBConnect jdbc = new JDBConnect();
		
		String sql = "SELECT id, pass, name, regidate FROM member"; // 인파라미터(?)가 없는 정적쿼리문작성
		Statement stmt = jdbc.con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			String id = rs.getString(1); // 인덱스 사용가능
			String pw = rs.getString(2);
			String name = rs.getString("name"); // 컬럼명 사용가능
			java.sql.Date regidate = rs.getDate("regidate");
			/* 테이블로 만들어보기 */
			out.println(String.format("%s %s %s %s", id, pw, name,regidate) + "<br/>");
		}
		jdbc.close();
		%>
	</body>
</html>