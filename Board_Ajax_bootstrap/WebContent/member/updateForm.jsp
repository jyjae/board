<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
<script src="http://code.jquery.com/jquery-latest.js">
</script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link href="css/join.css" rel="stylesheet" type="text/css">
<script>

$(function(){
	var checkid=false;
	var checkemail=false;
	
	$("input[value='${m.gender}']").prop('checked',true);
	
	$('form').submit(function(){
		if(!$.isNumeric($("input[name='age']").val())){
			alert("나이는 숫자를 입력하세요");
			$("input[name=age]").val('');
			$("input[name=age]").focus();
			return false;
		}
		
		if(!checkemail){
			alert("email 형식을 확인하세요");
			$("input:eq(4)").val('').focus();
			return false;
		}
		
		if(!checkid){
			alert("사용가능한 id로 입력하세요");
			$("input:eq(0)").val('').focus();
			return false;
		}
	}); //submit
	
		
		
		
		
	})
	  $('#age').keyup(function(){
 		 
	         pattern=/^[0-9]+$/;
	          age=$('#age').val();
	         if(!pattern.test(age)){

	            $('#message1').css("color","red").html('나이는 숫자를 입력하세요');
	          
	            return;
	         }
	         
		 	
      });
	  
	  $('#email').keyup(function(){
		  $('email_message').empty();
	 		 
	         pattern=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
             
	        
	          email=$('#email').val();
	         if(!pattern.test(email)){

	            $('#message2').css("color","red").html('이메일 형식에 맞게 입력하세요');
	          
	         }else{
	        	 $('#message2').css("color","blue").html('이메일 형식에 맞습니다.');
	         }
	         
	         if($('#email').val()=""){
	        	 $('#message2').css("color","red").html('');
	         }
	         
		 	
   });
	
	 $('#id').keyup(function(){
		 
	         pattern=/^\w{5,12}$/;
	          id=$('#id').val();
	         if(!pattern.test(id)){

	            $('#message').css("color","red").html('영문자 숫자 _로 5~12자 가능합니다.');
	            checkid=false;
	            return;
	         }
	         
 
	 		$.ajax({url:"idcheck.net", //요청 전송 url
			    data:{"id":id},	
			    success: function(rdata){		    
			    	
			    			 if(rdata==-1){
			    				 $("#message").text("사용가능한 아이디입니다.").css('color','green');
			    			 }else{
			    				 $("#message").text("사용 중인 아이디입니다.").css('color','blue');
			    			 }
			    	
			    },
			    error: function(request,status, error){
			    	$("body").append("<div id='error'>code :"
			    			  +request.status+"<br>"
			    			  +"받은 데이터 :"+request.responseText+"<br>"
			    			  +"error status : "+status+"<br>"
			    			  +"error 메시지 : "+error+"</div>");
			    	
			    },
			    complete: function(){//요청의 실패, 성공과 상관 없이 완료 될 경우 호출
			    	
			    }
			    });
	         
	 		
	   });

</script>
<jsp:include page="../board/header.jsp"/>
</head>
<body>
<form name="joinform" action="joinProcess.net" method="post">
<h1>회원수정 페이지</h1>
<hr>
<b>아이디</b>
<input type="text" name="id" id="id" value="${id}" required maxLength="12">
<span id="message"></span>

<b>비밀번호</b>
<input type="password" name="pass" value="${m.password }" required>

<b>이름</b>
<input type="text" name="name" value="${m.name}" maxlength=15 required>
<b>나이</b>
<input type="text" name="age" id="age" value="${m.age}" maxlength=2 required>
<span id="message1"></span>
<b>성별</b>
<div>
<input type="radio" name="gender" value="남" checked><span>남자</span>
<input type="radio" name="gender" value="여" ><span>여자</span>
</div>
<b>이메일 주소</b>
<input type="text" name="email" id="email" placeholder="Enter email" value="${m.email}" required><span id="email_message"></span>
<span id="message2"></span>
<div class="clearfix">
<button type="submit" class="submitbtn">수정</button>
<button type="reset" class="cancelbtn">다시작성</button>
</div>
</form>
</body>
</html>