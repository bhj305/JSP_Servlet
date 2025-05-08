<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/* 로그인 폼에서 입력한 아이디, 패스워드를 받는다. */
String userId = request.getParameter("user_id");
String userPwd = request.getParameter("user_pw");

String oracleDriver = application.getInitParameter("OracleDriver");
String oracleUrl = application.getInitParameter("OracleURL");
String oracleId = application.getInitParameter("OracleId");
String oraclePwd = application.getInitParameter("OraclePwd");

/* 위 정보를 통해 DAO 객체를 생성하고, 이때 오라클에 연결됨. */
MemberDAO dao = new MemberDAO(oracleDriver, oracleUrl, oracleId, oraclePwd);
MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);

dao.close();

if(memberDTO.getId() != null){
	/* 로그인이 성공하면 세션영역에 회원 아이디와 이름을 저장함. */
	session.setAttribute("UserId", memberDTO.getId());
	session.setAttribute("UserName", memberDTO.getName());
	/* 그리고 로그인 페이지로 이동한다. */
	response.sendRedirect("LoginForm.jsp");
} else{
	request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
	request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
}
%>