<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>MVC 게시판</title>
<jsp:include page="header.jsp" />
<script src="js/modify.js"></script>
<style>
.container{
width:60%
}

h1{
font-size: 1.5rem; text-align: center;
}

#upfile{display:none}
</style>
<script src="js/modify.js"></script>
</head>
<body>

<!-- 게시판 수정 -->
<div class="container">
<form action="BoardModifyAction.bo" method="post" enctype="multipart/form-data" name="modifyform">
<input type="hidden" name="BOARD_NUM" value="${boarddata.BOARD_NUM }">
<h1>MVC게시판 -수정</h1>
<div class="form-grop">
<label for="board name">글쓴이</label>
<input name="BOARD_NAME" id="board_name" value="${boarddata.BOARD_NAME }" readOnly type="text" maxlength="30"  class="form-control">
</div>

<div class="form-group">
<label for="board_subject">제목</label>
<input name="BOARD_SUBJECT" id="board_subject" type="text" maxlength="100" class="form-control" value="${boarddata.BOARD_SUBJECT }">
</div>
<div class="form-group">
<label for="board_content">내용</label>
<textarea name="BOARD_CONTENT" id="board_content"  cols="67" rows="10" class="form-control">${boarddata.BOARD_CONTENT }</textarea>
</div>
<c:if test="${boarddata.BOARD_RE_LEV==0 }">
<div class="form-grup read">
<label for="board_file">파일 첨부</label>
<label for="upfile">
<img src="image/attach.png" alt="파일첨부" width="10px">
</label>
<input type="file" id="upfile" name="BOARD_FILE">
<span id="filevalue">${boarddata.BOARD_FILE }</span>
<img src="image/remove.png" width="10px" class="remove" alt="파일삭제" id="removeimg">
</div>
</c:if>
<div class="form-group">
<label for="board_pass">비밀번호</label>
<input name="BOARD_PASS" id="board_pass" type="password" maxlength="30" class="form-control" placeholder="Enter board_pass">
</div>
<div class="form-group">
<button type="submit" class="btn btn-primary">등록</button>
<button type="reset" class="btn btn-danger">취소</button>
</div>
</form>
</div>

</body>
</html>