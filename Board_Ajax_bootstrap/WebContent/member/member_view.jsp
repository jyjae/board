<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />

<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>

#list,table{
text-align:center;
}

</style>
</head>
<body>
<div class="container">
<table class="table table-bordered">
<tr>
<th>아이디</th> <td>${member.id }</td>
</tr>
<tr>
<th>비밀번호</th> <td>${member.password}</td>
</tr>
<tr>
<th>이름</th> <td>${member.name }</td>
</tr>
<tr>
<th>나이</th> <td>${member.age}</td>
</tr>
<tr>
<th>성별</th> <td>${member.gender }</td>
</tr>
<tr>
<th>이메일</th> <td>${member.email }</td>
</tr>
<tr>
<td colspan="2" id="list"><a href="member_list.net" >리스트로 돌아가기</a>
</tr>
</table>
</div>
</body>
</html>