package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Member_deleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		MemberDAO memberdao=new MemberDAO();
		int result=0;
		String id=request.getParameter("id");
		System.out.println("id="+id);
		
		result = memberdao.delete(id);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(result==0) {
			out.println("<script>");
			out.println("alert('삭제 실패입니다.);");
			out.println("history.back()");
			out.println("</script>");
			
			
		
		}else {
		out.println("<script>");
		out.println("alert('삭제 성공입니다.');");
		out.println("location.href='member_list.net'");
		out.println("</script>");
		}
		out.close();
		
		return null;
		
	}

}
