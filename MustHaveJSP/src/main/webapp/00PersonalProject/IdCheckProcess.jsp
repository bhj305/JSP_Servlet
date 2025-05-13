<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
String id = request.getParameter("id");
boolean isDuplicate = false;

// 데이터베이스 연결 및 확인
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
    Class.forName("oracle.jdbc.OracleDriver");
    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "musthave", "1234");

    String sql = "SELECT id FROM member WHERE id = ?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, id);
    rs = pstmt.executeQuery();

    if (rs.next()) {
        isDuplicate = true;
    }
} catch (Exception e) {
    e.printStackTrace();
} finally {
    if (rs != null) try { rs.close(); } catch (SQLException e) {}
    if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
    if (conn != null) try { conn.close(); } catch (SQLException e) {}
}
%>

<html>
<head><title>ID 중복 확인</title></head>
<body>
<%
if (isDuplicate) {
%>
  <script>
    alert("이미 사용 중인 아이디입니다.");
    window.close();
  </script>
<%
} else {
%>
  <script>
    if (confirm("사용 가능한 아이디입니다. 사용하시겠습니까?")) {
      opener.document.getElementById("id").value = "<%= id %>";
      opener.document.getElementById("idCheckButton").name = "idChecked"; // ← name 속성 변경
      opener.document.joinForm.idCheck.value = "checked";
      window.close();
    } else {
      window.close();
    }
  </script>
<%
}
%>
</body>
</html>
