<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/* DB 연결 및 CRUD 작업을 위한 DAO 객체 생성 */
BoardDAO dao = new BoardDAO(application);

Map<String, Object> param = new HashMap<String, Object>();
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");

/* 사용자의 입력한 검색어가 있다면 */
if(searchWord != null)
{
	/* Map 컬렉션에 컬럼명과 검색어를 추가한다. */
	param.put("searchField", searchField);
	param.put("searchWord", searchWord);
}
/* Map 콜렉션을 인수로 게시물의 갯수를 카운트함(페이징을 위함) */
int totalCount = dao.selectCount(param);
/* 목록에 출력할 게시물을 추출하여 반환받는다. */
List<BoardDTO> boardLists = dao.selectList(param);
dao.close(); // 자원해제

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원제 게시판</title>
	</head>
	<body>
    <jsp:include page="../Common/Link.jsp" />  

    <h2>목록 보기(List)</h2>
    <form method="get">  <!-- 액션 값을 정하지 않았으므로 현재페이지로 전송됨. -->
    <table border="1" width="90%">
    <tr>
        <td align="center">
            <select name="searchField"> 
                <option value="title">제목</option> 
                <option value="content">내용</option>
            </select>
            <input type="text" name="searchWord" />
            <input type="submit" value="검색하기" />
        </td>
    </tr>   
    </table>
    </form>
    <!-- 게시물 목록 테이블(표) -->
    <table border="1" width="90%">
        <tr>
            <th width="10%">번호</th>
            <th width="50%">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="15%">작성일</th>
        </tr>
<%
if (boardLists.isEmpty()) {
%>
        <tr>
            <td colspan="5" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
<%
}
else {
	/* 출력할 데이터가 있는 경우, 확장 for 문으로 List 컬렉션에 저장된 데이터의 갯수만큼 반복 출력 */
    int virtualNum = 0; 
    for (BoardDTO dto : boardLists)
    {
    	// 출력할 게시물의 갯수에 따라 출력 번호는 달라지므로 totalCount를 사용하여 가상번호(virtualNum)를 생성
        virtualNum = totalCount--;   
%>
        <tr align="center">
            <td><%= virtualNum %></td>  
            <td align="left"> 
             <!-- 제목을 누르면  View.jsp로 넘어가는데 이때, num을 url로 보낸다-->
                <a href="View.jsp?num=<%= dto.getNum() %>"><%= dto.getTitle() %></a> 
            </td>
            <td align="center"><%= dto.getId() %></td>           
            <td align="center"><%= dto.getVisitcount() %></td>   
            <td align="center"><%= dto.getPostdate() %></td>    
        </tr>
<%
    }
}
%>
    </table>
   
    <table border="1" width="90%">
        <tr align="right">
            <td><button type="button" onclick="location.href='Write.jsp';">글쓰기
                </button></td>
        </tr>
    </table>
</body>

</html>