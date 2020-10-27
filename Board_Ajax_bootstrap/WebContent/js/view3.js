$(function(){
	$("#comment table").hide();
	// 댓글창 밑 테이블을 감춤
		function getList(){ // 테이블
		$.ajax({
			type : "post",
			url : "CommentList.bo",
			data : {"BOARD_RE_REF" : $("#BOARD_RE_REF").val()},
			dataType : "json",
			success : function(rdata){
				$("#comment tbody").empty()	// tbody를 초기화
				
				if(rdata.length > 0){
					$("#comment table").show(); // hide 되었던 부분을 보이게 만듬
					$("#comment thead").show(); // 글이 있을 대 보이게 함
					$("#message").text('');		// 기존 메세지를 삭제
				
					$(rdata).each(function(){
						output = '';
						img = '';
						if($("#loginid").val() == this.id){ // 로그인한 ID와 작성자가 같은경우에만 수정, 삭제 가능하도록
							img = "<img src='image/pencil2.png' width='15px' class='update'>"
								+ "<img src='image/remove.png' width='15px' class='remove'>"
								+ "<input type='hidden' value='" + this.num + "'>";
						}
						output += "<tr><td>" + this.id + "</td>";
						output += "<td></td>"; // Content에 스크립트를 입력할 수 있어 처리하기 위해 아무것도 넣지 않음.
						output += "<td>" + this.reg_date + img + "</td></tr>";
						
						$("#comment tbody").append(output); // content를 제외한 나머지들은 html화 한 뒤 tbody에 넣음
					
						// append한 마지막 tr의 2번째 자식 td를 찾아 text() method로 content를 넣음.
						$("#comment tbody tr:last").find("td:nth-child(2)").text(this.content);
					}); //each end
				}else{
					$("#message").text("등록된 댓글이 없습니다.");
					$("#comment thead").hide();
				}
				$("#count").text(rdata.length);
			}//success end
		});//ajax end
	}//function end
		
		
		
		
	$(".start").click(function(){
		getList();
	});

	$("#content").keyup(function(){
		var content=$(this).val();
		
		$("#counting").html("("+content.length+" / 최대 50자)");
	})//key up end
	
	$("#write").click(function(){
		buttonText = $("#write").text();
		content = $("#content").val();
		$(".float-left").text("총 50자까지 가능합니다.");
		
		if(buttonText=="Submit"){
			url = "CommentAdd.bo";
			data = {"content" : content,
					"id" : $("#loginid").val(),
					"BOARD_RE_REF" : $("#BOARD_RE_REF").val()};
			
		}else{
			url = "CommentUpdate.bo";
			data = {"num" : num,
					"content" : content,};
			$("#write").text("Submit");
		}
		
		$.ajax({
			type : "post",
			url : url,
			data : data,
			success : function(result){
				$("#content").val('');
				if(result == 1){
					getList();
				}
			}
		})//ajax end
	})//$("#write") end
	
	/*
	 	연필 그림을 클릭한 경우
	 	1. 선택한 내용이 #content에 나타나도록 한다.
	 	2. #write에는 수정완료라는 라벨이 뜨도록한다.
	 	3. num 변수는 수정할 댓글번호를 저장합니다.
	 	4. 펜슬을 선택한 행을 'lightgray'로 배경색을 지정
	 */
	$("#comment").on('click', '.update', function(){
		$("comment tr").css('background', 'white');
		// 다른 배경색은 하얗게 만든다.
		
		before = $(this).parent().prev().text(); // 선택한 내용을 가져온다.
		// $(this) = pencil.png, parant() = td, prev() = content
		
		$("#content").focus().val(before);	// textarea에 수정 전 내용을 보여준다.
		
		num = $(this).next().next().val(); // 댓글의 고유 번호를 가져온다.
		// $(this) = pencil.png, next() = remove그림, next() = hidden value값
		
		$("#write").text('수정완료');
		$(this).parent().parent().css('background', 'lightgray');
	})
	
	$("#comment").on('click', '.remove', function(){
		var num = $(this).next().val();
		$.ajax({
			type : "post",
			url : "CommentDelete.bo",
			data : {"num" : num},
			success : function(result){
				if(result == 1){
					getList();
				}
			}
		})//ajax end
	})//$("#comment") end
});