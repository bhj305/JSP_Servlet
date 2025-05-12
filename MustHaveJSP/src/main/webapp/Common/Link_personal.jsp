<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
		<!-- bootstrap  -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
        <nav class="navbar navbar-inverse">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                  <img src="../images/logo.png" alt="" style="height: 30px;">
                </a>
            </div>
        <div class="container">
            <div class="btn-group">
                <ul class="nav navbar-nav">
                    <li><a href="List.jsp">자유게시판</a></li>
                    <li><a href="#">자료실</a></li>
                    <li><a href="#">방명록</a></li>
                    
                    <form class="navbar-form navbar-left" action="#">
                        <div class="form-group">
                          <input type="text" class="form-control" placeholder="Search" name="search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <li ><a href="../00Board/SignUp.jsp">
                        회원가입</a></li>
                    <li><a href="#">
                        회원정보수정</a></li>
                    <li>
                    <% if (session.getAttribute("UserId") == null) { %>
			            <a href="../00Board/LoginForm.jsp">로그인</a>
			        <% } else { %>
			            <a href="../00Board/Logout.jsp">로그아웃</a>
			            <li>
			            <a href="../00Board/MyPage.jsp">마이페이지</a>
			            </li>
			        <% } %>
                     </li>
                    
                </ul>
            </div>
        </div>
    </nav>
        
            
 
                
    

