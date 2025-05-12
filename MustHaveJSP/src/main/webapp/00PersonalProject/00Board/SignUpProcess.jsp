<%@page import="membership.MemberDAO"%>
<%@page import="utils.JSFunction"%>
<%@page import="model1.board.BoardDAO"%>
<%@page import="membership.MemberDTO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* 사용자가 작성한 폼값 받기 */
String id = request.getParameter("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");



/* 폼값을 DTO에 저장 */
MemberDTO dto = new MemberDTO();
dto.setId(id);
dto.setPass(pass);
dto.setName(name);


/* DB 연결을 위해 DAO 객체 생성 */
MemberDAO dao = new MemberDAO(application);
/* MemberDAO dao = new MemberDAO(application); */
int iResult = dao.insertSignUp(dto);

dao.close();

if(iResult == 1){
	/* 입력에 성공한 경우 리스트로 이동한다.(임시 메인 화면) */
	JSFunction.alertLocation("회원가입 성공", "List.jsp", out);
} else {
	/* 실패했다면 회원가입 창으로 다시 돌아감 */
	JSFunction.alertBack("회원가입에 실패했습니다.", out);
}
%>