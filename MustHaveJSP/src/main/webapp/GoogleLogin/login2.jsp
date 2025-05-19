<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Google Login #1</title>
		<script src="https://accounts.google.com/gsi/client" async></script>
		<script src="https://cdn.jsdelivr.net/npm/jwt-decode@latest/build/jwt-decode.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	    <script>
	      window.onload = function () {
	        google.accounts.id.initialize({
	          client_id: "562530512945-8bh682i2ungv7lrn2nqa3h01gkfaj3cc.apps.googleusercontent.com",
	          callback: handleCredentialResponse
	        });
	        
	        google.accounts.id.renderButton(
	          document.getElementById("buttonDiv"),
	          { theme: "outline", size: "signin", width: 250 }  // customization attributes
	        );
	        	google.accounts.id.prompt();
	      	}
	        
	      	function handleCredentialResponse(response) {
				var profile = jwt_decode(response.credential);
				console.log("ID : " + profile.sub);
				console.log("Name : " + profile.name);
				console.log("Image : " + profile.picture);
				console.log("Email : " + profile.email);
			
				$('#login').css('display', 'none');
				$('#logout').css('display', 'block');
				$('#upic').attr('src', profile.picture);
				$('#uname').html('['+ profile.name+']');
				$('#email').html('['+ profile.email+']');
			
	      	}
	      
	      	function signOut(){
				google.accounts.id.disableAutoSelect();
				$('#login').css('display', 'block');
				$('#logout').css('display', 'none');
				$('#upic').attr('src', '');
				$('#uname').html('');
	      	}
	      	
	    </script>
	</head>
	<body>
		<div id="login">
		    <div id="buttonDiv"></div>
		</div>
		
		<div id="logout" style="dispaly: none;">
			<input type="button" onclick="signOut();" value="로그아웃"/><br/>
			<img id="upic" src=""/> <br/>
			<span id="uname"></span> <br/>
			<span id="email"></span>
		</div>
	</body>
	
</html>