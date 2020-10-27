$(function(){
	var check=0;
	$("form").submit(function(){
		
	if($.trim($('#board_subject').val())==""){
		alert("제목을 입력하세요");
		$("board_subject").focus();
		return false;
	}
	
	if($.trim($('textarea').val())==""){
		alert("내용을 입력하세요");
		$("textarea").focus();
		return false;
	}
	
	if($.trim($('#board_pass').val())==""){
		alert("비밀번호를 입력하세요");
		$("#board_pass").focus();
		return false;
	}
	//파일첨부를 변경하지 ㅇ낳으면 $('#filevalue').text()의 파일명을 
	//파라미터 'check'라는 이름으로 form에 추가하여 전송합니다.
	
	if(check==0){
		value=$('#filevalue').text();
		html="<input type='text' value='"+value+"' name='check'>";
		$(this).append(html);
	}
	
	})
	
	$("#upfile").change(function(){
		check++;
		var inputfile=$(this).val().split('\\');
		$('#filevalue').text(inputfile[inputfile.length-1]);
		show();
	})
	
	$(".remove").click(function(){
		$("#filevalue").text('');
		$(this).css('display','none');
	})
	
    
	
	function show(){
		//파일이름이 있는 경우 remove 이미지를 보이게 하고 없는 경우는 보이지 않게 합니다.
		if($('#filevalue').text()==''){
			$(".remove").css('display','none');
		}else{
			$(".remove").css({'display':'inline-block',
			                   'position':'relative', 'top':'-5px'});
		}
	}
	
	show();
})