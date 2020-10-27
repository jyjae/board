$(document).ready(function(){
	var checkid=false;
	var checkemail=true;
	
	$('form').submit(function(){
		if(!$.isNumeric($('input[name="age"]').val())){
			alert("숫자로 입력하세요");
			$('input[name="age"]').val("");
			$('input[name="age"]').focus();
			return false;
		}

		
		if(!checkemail){
			alert("email 형식을 확인하세요");
			$('inpue:eq(6)').val('').focus();
			return false;
		}
	});
	
	$('input:eq(0)').on('keyup',
			function(){
				$('#message').empty();
				var pattern=/^\w{5,12}$/;	//[A-Za-z0-9_]의 의미가 \w
				var id=$('input:eq(0)').val();
				
				if(!pattern.test(id)){
					$('#message').css('color', 'red').html('영문자 숫자 _로 5~12자 가능')
					checkid=false;
					return;
				}

				$.ajax({
					url:"idcheck.net",
					//url:"<%=request.getContextPath()%>/idcheck.net",
					//url:"${pageContext.request.contextPath}/idcheck.net",
					data: {"id": id},
					success: function(resp){
						if(resp==-1){
							$('#message').css('color', 'green').html('사용가능한 id입니다.');
							checkid=true;
						}else{
							$('#message').css('color', 'blue').html('사용중인 id입니다.')
							checkid=false;
						}
					}
				})
		
	});
	

	$('input:eq(6)').on('keyup',
			function(){	
				$('#email_message').empty();
				var pattern=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;	//email정규식
				var email=$('input:eq(6)').val();
				console.log(email);
				
				if(!pattern.test(email)){
					$('#email_message').css('color', 'red').html('이메일 형식에 맞지 않습니다.');
					checkeamil=false;
				}else{
					$('#email_message').css('color', 'green').html('이메일 형식에 맞습니다.');
					checkemail=true;
			}	
	});
});