/**
 * 
 */
$(document).ready(function(){
	$('form').submit(function(){
		if($.trim($("input[name=BOARD_SUBJECT").val())==""){
			alert("제목을 입력하세요");
			$("input[name=BOARD_SUBJECT").focus();
			return false;
		}
		
		if($.trim($("textarea").val())==""){
			alert("내용을 입력하세요");
			$("textarea").focus();
			return false;
		}
		
		if($.trim($("input:password").val())==""){
			alert("비밀번호를 입력하세요");
			$("input:password").focus();
			return false;
		}
	})
})