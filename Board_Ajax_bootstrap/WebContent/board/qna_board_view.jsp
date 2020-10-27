<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<title>Insert title here</title>
<jsp:include page="header.jsp"/>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/view.js"></script>
<style>
#count{
position: relative; font-size:13px;
top: -10px; left:10px; background:orange;
color:white; border-radius:30%;
}
</style>
<script>
	$(function(){
		$("form").submit(function(){
			if($("#board_pass").val() == ''){
				alert("Enter the password");
				$("#board_pass").focus();
				return false;
			}
		})
	})
</script>
<style>
tr:nth-child(1){
	text-align:center;
}
td:nth-child(1){
	width:20%;
}
a{
	color:white;
}
tr:nth-child(5) > td:nth-child(2) > a{
	color:black;
}
tbody tr:last-child{
	text-align:center;
}
.btn-primary{
	background-color:#4f97e5;
}
#myModal{
	display:none;
}
</style>
</head>
<body>
<input type="hidden" id="loginid" value="${id }" name="loginid">
<div class="container">
	<table class="table table-striped">
	<tr>
		<th colspan="2"> MVC Board - View Page</th>
	</tr>
	<tr>
		<td><div>Writer</div></td>
		<td><div>${boardData.BOARD_NAME}</div></td>
	</tr>
	<tr>
		<td><div>Subject</div></td>
		<td><div><c:out value="${boardData.BOARD_SUBJECT}" escapeXml="true"/></div></td>
	</tr>
	<tr>
		<td><div>Content</div></td>
		<td><textarea class="form-control" rows="5" style="width:102%" readOnly>
			<c:out value="${boardData.BOARD_CONTENT}" escapeXml="true"/></textarea>
		</td>
	</tr>
	<%-- 원본글인 경우에만 첨부파일을 추가 할 수 있게 if문 설정. --%>
	<c:if test = "${boardData.BOARD_RE_LEV==0}">
	<tr>
		<td><div>Attached File</div></td>
		<%-- 파일이 첨부되어 있는 경우 --%>
		<c:if test="${!empty boardData.BOARD_FILE}">
		<td><img src="image/down.png" width="10px">
			<a href="BoardFileDown.bo?filename=${boardData.BOARD_FILE}">
			${boardData.BOARD_FILE}
			</a>
		</td>
		</c:if>
		<%-- 파일을 첨부하지 않은 경우 --%>
		<c:if test="${empty boardData.BOARD_FILE}">
			<td></td>
		</c:if>
	</tr>
	</c:if>
	
	<tr>
		<td colspan="2" class="center">
			
				<button class = "btn btn-primary start">댓글<span id="count">${count }</span></button>
			
			<c:if test="${boardData.BOARD_NAME==id || id=='admin'}">
			<a href="BoardModifyView.bo?num=${boardData.BOARD_NUM}">
				<button class = "btn btn-info">수정</button>
			</a>
			<%-- href의 주소를 #으로 설정 --%>
			<a href="#">
			<button class="btn btn-danger" data-toggle="modal" data-target="#myModal">삭제</button>
			</a>
			</c:if>
			<a href="BoardList.bo">
				<button class = "btn btn-warning">목록</button>
			</a>
			<a href="BoardReplyView.bo?num=${boardData.BOARD_NUM}">
			<button class="btn btn-success">답변</button>
			</a>
		</td>
	</tr>
	</table>

<%-- Board View End --%>

<%-- Modal Start --%>
<div class="modal" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<%-- Modal Body --%>
			<div class="modal-body">
				<form name="deleteForm" method="post" action="BoardDeleteAction.bo">
				<%--http://localhost:8088/Board_Ajax_bootstrap/BoardDetailAction.bo?num=22
					위의 주소를 보면 num을 파라미터로 넘기고 있다.
					이 값을 가져와서 ${param.num}을 사용하거나 ${boardData.BOARD_NUM}으로 사용하면 된다. --%>
				<input type="hidden" name="num" value="${boardData.BOARD_NUM}" id="BOARD_RE_REF">
				<input type="hidden" name="pass" value="${param.pass}">
				<div class="form-group">
					<label for="pwd">Password</label>
					<input type="password" class="form-control" 
					placeholder="Enter Password" id="board_pass" name="BOARD_PASS">
				</div>
 	 			<button type="submit" class="btn btn-primary">Submit</button>
  				<button type="button" class="btn btn-danger" data-dismis="modal">Cancel</button>
				</form>
			</div>
		</div>
	</div>
</div><%--id=modal end --%>
<div id="comment">
  <button class="btn btn-info float-left" id="wordsu">총 50자까지 가능합니다.</button>
  <button id="write" class="btn btn-info float-right">등록</button>
    <textarea rows=3  class="form-control" id="content" maxLength="50" id="area"></textarea>
    
    <table class="table table_striped" id="idcontentdate">
    <thead>
    <tr><td>아이디</td><td>내용</td><td>날짜</td></tr>
    </thead>
    <tbody>
    
    </tbody>
    
    </table>

</div>
</div>
</body>
</html>