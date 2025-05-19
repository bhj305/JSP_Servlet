<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>NaverLoginSDK Test With BootStrap</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
		<script src="naveridlogin_js_sdk_2.0.2.js"></script>
	</head>
	<body>
		<a id="gnbLogin" href="#">Login</a>
		<div id="naverIdLogin">
		
		<script>
			var naverLogin = new naver.LoginWithNaverId({
				clientId : "ZNkRkG1bj9lN9i714GDJ",
				callbackUrl : "http://localhost:8081/MustHaveJSP/NaverLogin/login.jsp",
				loginButton : { color: "green", type: 3, height: 60 }
				
			});
			
			naverLogin.init();
			
			$("#gnbLogin").attr("href", naverLogin.generateAuthorizeUrl());
			
			window.addEventListener('load', function(){
				naverLogin.getLoginStatus(function(status){
					if(status){
						setLoginStatus();
					}
				});
			});
			
			
			function setLoginStatus() {
				console.log(naverLogin.user);
				var uid = naverLogin.user.getId();
				var profileImage = naverLogin.user.getProfileImage();
				var uName = naverLogin.user.getName();
				var nickName = naverLogin.user.getNickName();
				var eMail = naverLogin.user.getEmail();

				$("#naverIdLogin").html(
						'<br><br><img src="' + profileImage + 
						'" height=50 /> <p>' + uid + "=" + eMail + "-" + uName + '님 반갑습니다.</p>');
				$("#gnbLogin").html("Logout");
				$("#gnbLogin").attr("href", "#");
				/* (7) 로그아웃 버튼을 설정하고 동작을 정의합니다. */
				$("#gnbLogin").click(function () {
					naverLogin.logout();
					window.location.replace("login.jsp");
				});
			}
			
		</script>
		
		</div>
	</body>
</html>