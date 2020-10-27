/**
 * 
 */
function setPaging(href, digit){
	output+="<li class=page-item>";
	gray="";
	if(href==""){
		gray=" gray";
	}
	anchor="<a class='page-link"+gray+"'"+href+">"+digit+"</a></li>";
	output+=anchor;
}

function go(page){
	var limit = $('#viewcount').val();
	var data="limit="+limit+"&state=ajax&page="+page;
	ajax(data);
}

function ajax(sdata){
//1줄보기 선택시 리턴된 데이터
	console.log(sdata)
	output="";
	$.ajax({
		type:"POST",
		data:sdata,
		url:"BoardList.bo",
		dataType:"json",
		cache: false,
		success:function(data){
			$('#viewcount').val(data.limit);
			$('table').find('font').text('글 개수 : '+data.listcount);
			
			
			if(data.listcount>0){
				$('tbody').remove();
				var num = data.listcount - (data.page-1) * data.limit;
				console.log(num);
				output="<tbody>";
				//총 갯수가 0보다 클 경우
				
				$(data.boardlist).each(
						function(index,item){
							output+='<tr><td>'+(num--)+'</td>'
							blank="&nbsp;";
							if(item.BOARD_RE_LEV!=0){
								blank_count=item.BOARD_RE_LEV*2;
								for(var i=0; i<blank_count;i++){
									blank+='&nbsp;&nbsp;';
								}
							}
							img="";
							if(item.BOARD_RE_LEV>0){
								img="<img src='image/line.gif>";
							}
							var subject=item.BOARD_SUBJECT.replace(/</g,'&lt;')
						
				output+="<td><div>"+blank+img;
				output+='<a href="BoardDetailAction.bo?num='+item.BOARD_NUM+'&page='+data.page+'">'
				output+=subject+'</a></div></td>'
				output+="<td><div>"+item.BOARD_NAME+'</div></td>'
				output+="<td><div>"+item.BOARD_DATE+"</div></td>"
				output+="<td><div>"+item.BOARD_READCOUNT+"</div></td>"
				output+="</tr>"
						})
				output+="</tbody>"
				$('table').append(output)//table 완성
						
				$('.pagination').empty(); //페이징 처리 영역 내용 제거
				output="";
				
				digit='이전&nbsp;'
				href="";
				if(data.page>1){
					href="href=javascript:go("+(data.page-1)+')';
				}
				setPaging(href,digit);
				
				for(var i=data.startpage; i<=data.endpage; i++){
					digit=i;
					href="";
					if(i!=data.page){
						href='href=javascript:go('+i+')';
					}
					setPaging( href,digit);
				}
				digit='&nbsp;다음';
				href="";
				if(data.page<data.maxpage){
					href='href=javascript:go('+(data.page+1)+')';
				}
				setPaging( href,digit);
				
				$('.pagination').append(output)
			}//if(data.listcount) end
					
		},//success end
		
		error:function(){
			console.log('에러');
		}
	})//ajax end
}//function ajax end


$(function(){
	$('#viewcount').change(function(){
		go(1); //보여줄 페이지를 1페이지로 설정합니다.
	});
	
	$("button").click(function(){
		location.href="BoardWrite.bo";
	})
})