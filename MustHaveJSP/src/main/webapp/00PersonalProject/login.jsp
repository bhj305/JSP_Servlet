<%@page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

String loginEmail = CookieManager.readCookie(request, "loginEmail");
String cookieCheck = "";
if (!loginEmail.equals("")) {
    cookieCheck = "checked";
}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title>로그인 페이지</title>
</head>
<body class="bg-light d-flex align-items-center justify-content-center">
    <div class="card shadow-lg w-100" style="max-width: 480px;">
        <div class="card-body">
            <div class="text-center">
                <h1 class="card-title h3">Sign in</h1>
                <p class="card-text text-muted">Sign in below to access your account</p>
            </div>
            <div class="mt-4">
                <form action="EmailSaveProcess.jsp" method="post">
                    <div class="mb-4">
                        <label for="email" class="form-label text-muted">이메일 주소</label>
                        <input type="email" class="form-control" name="user_email" id="email" value="<%= loginEmail %>" tabindex="1" placeholder="Email" required>
                    	<input type="checkbox" name = "save_check" value="Y" <%= cookieCheck %> />
                    	이메일 저장하기
                    </div>
                    <div class="mb-4">
                        <label for="password" class="form-label text-muted">비밀번호</label>
                        <input type="password" class="form-control" name="user_pw" tabindex="2" id="password" placeholder="Password" required>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-dark btn-lg">Sign in</button>
                    </div>
                    <p class="text-center text-muted mt-4">Don't have an account yet?
                        <a href="#!" class="text-decoration-none">Sign up</a>.
                    </p>
                </form>
            </div>
        </div>
    </div>
</body>
</html>