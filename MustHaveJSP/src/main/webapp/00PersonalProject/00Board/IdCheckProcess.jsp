<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    String userId = request.getParameter("userId");
    boolean isAvailable = false;

    // DB 연결 설정
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "musthave", "1234");

        String sql = "SELECT * FROM users WHERE user_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userId);
        rs = pstmt.executeQuery();

        if (!rs.next()) {
            isAvailable = true; // 아이디가 존재하지 않음 (사용 가능)
        }
    } catch (Exception e) {
        out.println("DB 오류: " + e.getMessage());
    } finally {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    }

    if (isAvailable) {
%>
<script>
    alert("사용 가능한 ID입니다.");
    opener.document.getElementById("idCheck").value = "idCheck";
    window.close();
</script>
<%
    } else {
%>
<script>
    alert("이미 존재하는 ID입니다.");
    history.back();
</script>
<%
    }
%>
