<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="header.jsp" />
<style>
table caption {
	caption-side: top;
	text-align: center;
}

h1 {
	text-align: center
}

li .current {
	background: #faf7f7;
	color: gray;
}

body>div>table>tbody>tr>td:last-child>a {
	color: red
}

form {
	margin: 0 auto;
	width: 80%;
	text-align: center
}

select {
	color: #495057;
	background: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}

.container {
	width: 60%;
}

td:nth-child(1) {
	width: 33%
}

.input-group {
	margin-bottom: 3em
}
</style>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(function(){
	var selectedValue='${search_field}'
	if(selectedValue!='-1')
		$("#viewcount").val(selectedValue);
	
	$('#viewcount').on('change',function(){
		if($('#viewcount').val()=="1"){
			$('#search_woard').attr("placeholder","이름을 입력하세요");
			$(this).val("1").prop("selected",true);
			
		}else if($('#viewcount').val()=="2"){
			$('#search_woard').attr("placeholder","나이를 입력하세요")
		}else if($('#viewcount').val()=="3"){
			$('#search_woard').attr("placeholder","성별을 입력하세요")
		}
	});
	
	
	$('button').click(function(){
		if($('#viewcount').val()=="1"){
			
			$('#viewcount > option:nth-child(2)').prop("selected",true);
			
		}else if($('#viewcount').val()=="2"){
			$('#viewcount > option:nth-child(3)').prop("selected",true);
		}else if($('#viewcount').val()=="3"){
			$('#viewcount > option:nth-child(4)').prop("selected",true);
		}
		
		word=$(".input-group input").val();
		if(selectedValue==2){
			pattern=/^[0-9]{2}$/;
			if(!pattern.test(word)){
				alert('나이는 형식에 맞게 입력하세요(두자리 숫자)');
			}
		}
		
		if($('#search_woard').val()==""){
			alert('검색어를 입력하세요');
			return false;
		}else if($('#viewcount').val()=="3" && $('#searh_woard').val()!="남" || $('#searh_woard').val()!="여"){
			alert('남 또는 여을 입력하세요');
			return false;
		}
		
	});
	
	//회원 목록의 삭제를 클릭한 경우
	$('#cancel').click(function(event){
		var result=confirm('정말 삭제하시겠습니까?');
		console.log(result);// 취소를 클릭한 경우 - false
		if(!result)
			event.preventDefault(); //이동하지 않습니다.
		
	})
})
</script>
<title>MVC 게시판</title>
</head>
<body>
	<div class="container">
		<%-- 게시글이 있는 경우 --%>
		<c:if test='${listcount>0}'>
			<form action="member_list.net">
				<div class="input-group">
					<select id="viewcount" name="search_field">
						<option value="0" selected>아이디</option>
						<option value="1">이름</option>
						<option value="2">나이</option>
						<option value="3">성별</option>
					</select> <input name="search_word" type="text" class="form-control"
						placeholder="아이디를 입력하세요" value='${search_word}' id='search_woard'>
					<button class="btn btn-primary" type="submit">검색</button>
				</div>
			</form>
			<table class="table table-striped">
				<caption style="caption-side: top; font-weight: bold">회원
					목록</caption>
				<thead>
					<tr>
						<th colspan="2">MVC 게시판 - 회원 정보 list</th>
						<th colspan="1"><font size=3>회원 수 : ${listcount}</font></th>
					</tr>
					<tr>
						<th><div>아이디</div></th>
						<th><div>이름</div></th>
						<th><div>삭제</div></th>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value='${listcount-(page-1)*limit}' />
					<c:forEach var="m" items='${memberlist}'>
						<tr>
							<td>
								<%-- 아이디 --%>
								<div>
									<a href="member_info.net?id=${m.id}"> ${m.id} </a>
								</div>
							</td>
							<td><div>${m.name}</div></td>
							<td><div style="color: red">
									<a href="member_delete.net?id=${m.id}" id="cancel">삭제</a>
								</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="center-block">
				<ul class="pagination justify-content-center">
					<%-- gray는 이동x --%>
					<c:if test='${page <= 1}'>
						<li class="page-item"><a class="page-link gray">이전&nbsp;</a>
						</li>
					</c:if>
					<c:if test='${page>1 }'>
						<li class="page-item"><a
							href="member_list.net?page=${page-1}&search_field=${search_field}&search_word=${search_word}"
							class="page-link">이전&nbsp;</a></li>
					</c:if>

					<c:forEach var="a" begin='${startpage}' end='${endpage}'>
						<c:if test='${a==page}'>
							<li class="page-item"><a class="page-link gray">${a}</a></li>
						</c:if>
						<c:if test='${a!=page}'>
							<li class="page-item"><a
								href="member_list.net?page=${a}&search_field=${search_field}&search_word=${search_word}"
								class="page-link">${a}</a></li>
						</c:if>
					</c:forEach>

					<c:if test='${page >= maxpage}'>
						<li class="page-item"><a class="page-link gray">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test='${page<maxpage}'>
						<li class="page-item"><a
							href="member_list.net?page=${page+1}&search_field=${search_field}&search_word=${search_word}"
							class="page-link">&nbsp;다음</a></li>
					</c:if>

				</ul>
			</div>
		</c:if>
		<%-- 회원이 없으면 --%>
		<c:if test='${listcount==0 && empty search_word}'>
			<font size=5>등록된 회원이 없습니다.</font>
		</c:if>
		<%--검색결과 없으면 --%>
		<c:if test='${listcount==0 && empty search_word}'>
			<font size=5>검색 결과가 없습니다.</font>
		</c:if>
	</div>
</body>
</html>