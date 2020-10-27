package net.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.Action;
import net.member.action.ActionForward;

public class CommentUpdate implements Action{

	public CommentUpdate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		net.comment.db.CommentDAO dao = new net.comment.db.CommentDAO();
		net.comment.db.Comment cmt = new net.comment.db.Comment();
		
		cmt.setNum(Integer.parseInt(request.getParameter("num")));
		cmt.setContent(request.getParameter("content"));
		
		int result = dao.commentUpdate(cmt);
		
		response.getWriter().print(result);
		System.out.println("댓글 수정 완료");
		
		return null;
	}
	
	
}
