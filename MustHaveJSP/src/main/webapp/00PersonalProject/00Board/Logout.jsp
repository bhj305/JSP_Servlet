<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* 1. 회원인증 정보를 session 영역에서 삭제 */
/* session.removeAttribute("UserId");
session.removeAttribute("UserName"); */

/* 2.  */
session.invalidate();

response.sendRedirect("LoginForm.jsp");
%>
