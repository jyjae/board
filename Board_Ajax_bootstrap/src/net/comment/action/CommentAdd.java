package net.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.Action;
import net.member.action.ActionForward;

public class CommentAdd implements Action{
	
public CommentAdd() {
	// TODO Auto-generated constructor stub
}

@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	net.comment.db.CommentDAO dao = new net.comment.db.CommentDAO();
	net.comment.db.Comment cmt = new net.comment.db.Comment();
	
	String id = request.getParameter("id");
	String content = request.getParameter("content");
	int board_re_ref = Integer.parseInt(request.getParameter("BOARD_RE_REF"));
	
	cmt.setId(id);
	cmt.setContent(content);
	cmt.setBoard_re_ref(board_re_ref);
	
	int result = dao.commentsInsert(cmt);
	
	response.getWriter().print(result);
	System.out.println("댓글 추가 성공");
	
	return null;
}

}
