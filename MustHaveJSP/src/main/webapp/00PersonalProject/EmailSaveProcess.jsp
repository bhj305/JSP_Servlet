<%@page import="utils.JSFunction"%>
<%@page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String user_email = request.getParameter("user_email");
String user_pw = request.getParameter("user_pw");

String save_check = request.getParameter("save_check");

if("must@have.com".equals(user_email) && "1234".equals(user_pw)){ // 사용자 인증
	if(save_check != null && save_check.equals("Y")){ // [아이디 저장] 체크확인
		CookieManager.makeCookie(response, "loginEmail", user_email, 86400);
	} else{
		CookieManager.deleteCookie(response, "loginEmail"); // 체크하지 않은 경우 쿠키 삭제
	}
	JSFunction.alertLocation("로그인에 성공했습니다.", "index.jsp", out);
}else{
	JSFunction.alertBack("로그인에 실패했습니다.", out);
}
%>
