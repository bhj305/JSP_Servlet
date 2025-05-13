<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>회원가입</title>
  <script>
    function checkId() {
      var id = document.getElementById("id").value;
      if (id === "") {
        alert("아이디를 입력해주세요.");
        return;
      }
      window.open("IdCheckProcess.jsp?id=" + encodeURIComponent(id), "idCheck", "width=400,height=200");
    }

    // 아이디 변경 시 중복확인 상태 초기화
    function resetIdCheck() {
      document.getElementById("idCheckButton").name = "idCheckBtn";
      document.joinForm.idCheck.value = "unchecked";
    }
  </script>
</head>
<body>
  <form name="joinForm" method="post" action="registerProcess.jsp">
    아이디: 
    <input type="text" name="id" id="id" onchange="resetIdCheck()">
    <input type="button" id="idCheckButton" name="idCheckBtn" value="중복 확인" onclick="checkId()">
    <input type="hidden" name="idCheck" value="unchecked">
    <br><br>
    비밀번호: 
    <input type="password" name="password">
    <br><br>
    <input type="submit" value="회원가입">
  </form>
</body>
</html>
