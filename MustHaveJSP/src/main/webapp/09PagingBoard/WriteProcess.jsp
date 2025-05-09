<%@page import="utils.JSFunction"%>
<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%> <!-- 작성 완료를 누를때도 세션이 유효한지 확인하기 위해 설정해주기  -->
<% 
String title = request.getParameter("title");
String content = request.getParameter("content");

BoardDTO dto = new BoardDTO();
dto.setTitle(title);
dto.setContent(content);
dto.setId(session.getAttribute("UserId").toString());

BoardDAO dao = new BoardDAO(application);
int iResult = dao.insertWrite(dto);

/* 더미데이터 추가를 위해 글쓰기를 누르고 제목과 내용 입력하면 1회용으로 사용하도록 */
/* int iResult = 0;
for(int i = 1; i<= 100; i++){
	dto.setTitle(title + "-" + i);
	iResult = dao.insertWrite(dto);
}
 */
dao.close(); /* 자원해제 */


if(iResult == 1){
	response.sendRedirect("List.jsp");
} else{
	JSFunction.alertBack("글쓰기에 실패하였습니다.", out);
}

%>