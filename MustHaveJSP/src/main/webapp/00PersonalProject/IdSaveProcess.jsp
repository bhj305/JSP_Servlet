<%@ page import="utils.JSFunction" %>
<%@ page import="utils.CookieManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String user_id = request.getParameter("user_id");
String user_pw = request.getParameter("user_pw");
String save_check = request.getParameter("save_check");

if ("must".equals(user_id) && "1234".equals(user_pw)) {
    // 아이디 저장 체크되었는지 확인
    if ("Y".equals(save_check)) {
        CookieManager.makeCookie(response, "loginId", user_id, 86400); // 1일 저장
    } else {
        CookieManager.deleteCookie(response, "loginId");
    }
    JSFunction.alertLocation("로그인에 성공했습니다.", "IdSaveMain.jsp", out);
} else {
    JSFunction.alertBack("로그인에 실패했습니다.", out);
}
%>
