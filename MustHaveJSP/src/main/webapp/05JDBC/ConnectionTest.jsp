<%@page import="common.DBConnPool"%>
<%@page import="common.JDBConnect"%><!-- JDBCCOnnect 임포 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>JDBC</title>
	</head>
	<body>
		<h2>JDBC Test 1</h2>
		<%
		JDBConnect jdbc1 = new JDBConnect(); // 객체생성
		jdbc1.close(); // 자원해제
		%>
		
		<h2>JDBC Test 2</h2>
		<%
		String driver = application.getInitParameter("OracleDriver");
		String url = application.getInitParameter("OracleURL");
		String id = application.getInitParameter("OracleId");
		String pwd = application.getInitParameter("OraclePwd");
		
		JDBConnect jdbc2 = new JDBConnect(driver, url, id, pwd); // 객체생성
		jdbc2.close(); // 자원해제
		%>
		
		<!-- 효율적임 -->
		<h2>JDBC Test 3</h2>
		<%
		JDBConnect jdbc3 = new JDBConnect(application); // 객체생성
		jdbc3.close(); // 자원해제
		%>
		
		<h2>Connection Pool Test</h2>
		<%
		DBConnPool pool = new DBConnPool(); // 객체생성
		pool.close(); // 자원해제
		%>
	</body>
</html>