/**
 * 
 */

$(document).ready(function(){
	
	//등록 버튼 클릭할 때 이벤트 부분
	$('form').submit(function(){
		
	})
	
	$('#upfile').change(function(){
		console.log($(this).val()) //C:\fakepath\1번문제-정연재.txt
		var inputfile=$(this).val().split('\\');
		$('#filevalue').text(inputfile[inputfile.length-1]);
	});
}); //ready() end