<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%> 
<%
/* 수정 폼에서 입력한 내용을 파라미터로 받는다. */
String num = request.getParameter("num");
String title = request.getParameter("title");
String content = request.getParameter("content");

BoardDTO dto = new BoardDTO();
dto.setNum(num);
dto.setTitle(title);
dto.setContent(content);

BoardDAO dao = new BoardDAO(application);
int affected = dao.updateEdit(dto);
dao.close();

if(affected == 1){
	/* 수정이 되었음을 의미  */
	response.sendRedirect("View.jsp?num=" + dto.getNum());
} else {
	JSFunction.alertBack("수정하기 실패하였습니다.", out);
}

%>
