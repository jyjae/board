package net.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import net.member.action.Action;
import net.member.action.ActionForward;

public class CommentList implements Action{
	
	public CommentList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        net.comment.db.CommentDAO dao = new net.comment.db.CommentDAO();
		
		int BOARD_RE_REF = Integer.parseInt(request.getParameter("BOARD_RE_REF"));
		System.out.println(BOARD_RE_REF);
		
		JsonArray json = dao.getCommentList(BOARD_RE_REF);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
		
		return null;
	}
	

}
