<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<style>
@charset "EUC-KR";

body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
	border-radius: 2px;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer; /* 손가락 커서 모양 */
	width: 80%;
	opacity: 0.9;
}

button:hover {
	opacity: 1;
}

button:focus {
	outline: none;
}

/* 취소 버튼*/
.join {
	padding: 14px 20px;
	background-color: #f44336;
}

.join, .submitbtn {
	float: left;
	width: 50%;
}

form {
	background-color: #fefefe;
	margin: 10% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
	width: 50%; /* Could be more or less, depending on screen size */
	padding: 16px;
}

hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Clear floats */
.clearfix::after {
	content: "";
	clear: both;
	display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
	.join, .signupbtn {
		width: 100%;
	}
}
</style>
<script>
$(document).ready(function(){
	$(".join").click(function(){
		location.href="join.net";
	});
	
	var id_val='${id}';
	
	if(id_val){
		$('#id').val(id_val);
		$("#remember").prop('checked',true);
	}
	
})
</script>
</head>




<body>
<div class="container">
	<form name="loginform" action="loginProcess.net" method="post" class="border border-light p-5">
		<h1>로그인</h1>
		<hr>
		<b>아이디</b> <input type="text" name="id" id="id" placeholder="Enter id"
			required> <b>Password</b> <input type="password" name="pass"
			id="pass" placeholder="Enter password" required>
		<div class="form-group custom-control custom-chexckbox">
			<input type="checkbox" class="custom-control-input" id="remember"
				name="remember" value="store"> <label
				class="custom-control-label" for="remember">아이디 기억하기</label>
		</div>
		<div class="clearfix">
			<button type="submit" class="submitbtn">로그인</button>
			<button type="button" class="join">회원가입</button>
		</div>
	</form>
	</div>
</body>
</html>