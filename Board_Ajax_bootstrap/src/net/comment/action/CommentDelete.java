package net.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.action.Action;
import net.member.action.ActionForward;

public class CommentDelete implements Action{

	public CommentDelete() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        net.comment.db.CommentDAO dao = new net.comment.db.CommentDAO();
		
		int num =Integer.parseInt(request.getParameter("num"));
		
		int result = dao.commentDelete(num);
		
		response.getWriter().print(result);
		System.out.println("댓글 삭제 완료");
		
		return null;
	}

}
