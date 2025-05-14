<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FileUpload</title>
		<script>
		/* 유효성 인증 */
			function validateForm(form) {
				if(form.title.value == ""){
					alert("제목을 입력해주세요.");
					form.title.focus();
					return false;
				}
				
				if(form.ofile.value == "") {
					alert("첨부파일은 필수 입력입니다.");
					return false;
				}
			}
		</script>
	</head>
	<body>
		<h3>파일 업로드</h3>
		<!-- 업로드 실패 시 에러메시지 표현을 위함, errorMessage에 저장됨 -->
		<span style="color: red;">${ errorMessage }</span>
		
		<!-- 파일업로드는 반드시 post로 저정해야 함. -->
		<form name="fileForm" method="post" enctype="multipart/form-data" 
			action="UploadProcess.do" onsubmit="return validateForm(this)">
			제목: <input type="text" name="title"/> <br/>
			카테고리(선택사항):
				<input type="checkbox" name="cate" value="사진" checked />사진
				<input type="checkbox" name="cate" value="과제" />과제
				<input type="checkbox" name="cate" value="워드" />워드
				<input type="checkbox" name="cate" value="음원" />음원 <br/>
				
			첨부파일: <input type="file" name="ofile"/><br/>
			<!-- 폼값 전송 버튼 -->
			<input type="submit" value="전송하기"/>
		</form>
	</body>
</html>